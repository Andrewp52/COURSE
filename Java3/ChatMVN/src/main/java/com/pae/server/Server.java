package com.pae.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.pae.server.Commands.*;

public class Server {
    private final ExecutorService exeService = Executors.newFixedThreadPool(100);
    private final int PORT = 8888;
    private ServerSocket srv = null;
    private static final Map<String, ClientHandler> clientsMap = Collections.synchronizedMap(new HashMap<>());
    private final Logger log = LogManager.getLogger(Server.class);
    public void start() {
        AuthService.connect();
        log.info("Server start");
        try {
            this.srv = new ServerSocket(PORT);
            log.info("Waiting for connections");
            while (true){
                Socket socket = this.srv.accept();
                exeService.execute(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            log.error("Server error: ", e);
        } finally {
            AuthService.disconnect();
            exeService.shutdown();
            log.info("Server stopped");
        }
    }

    public void subscribe(ClientHandler client){
        log.debug(client.getNickName() + " logged in.");
        clientsMap.put(client.getNickName().intern(), client);          // Intern to avoid data duplication with handler
        sendContactsUpdate();
    }
    public void unsubscribe(ClientHandler client){
        log.debug(client.getNickName() + " logged out.");
        clientsMap.remove(client.getNickName(), client);
        sendContactsUpdate();
    }
    public boolean isNickDuplicate(String nick){
        return clientsMap.containsKey(nick);
    }
    public void sendToAll(ClientHandler sender, String message) throws IOException {
        log.trace(String.format("Broadcast from [%s]", sender.getNickName()));
        for (ClientHandler client : clientsMap.values()) {
            client.sendMessage(String.format("[%s]: %s",sender.getNickName(), message));
        }
    }
    public void sendPrivate(ClientHandler sender, String to, String message) throws IOException {
        ClientHandler rcpt = clientsMap.getOrDefault(to, null);
        log.trace(String.format("Private from [%s] to [%s]", sender.getNickName(), to));
        if(rcpt == null){
            sender.sendServiceMessage(String.format("Private sending failed : '%s' not found.\r\n", to));
            log.trace(String.format("Private sending failed : '%s' not found.\r\n", to));
            return;
        }
        if (rcpt.isUserBlocked(sender.getNickName())){
            sender.sendServiceMessage(String.format("Private sending to %s failed : Blocked.\r\n", to));
            log.trace(String.format("Private sending failed to %s: Blocked.", to));
            return;
        }
        if (!rcpt.equals(sender)){
            rcpt.sendMessage(String.format("[Private from %s] %s",sender.getNickName(), message));
            sender.sendMessage(String.format("[Private to %s] %s",to, message));
        }
    }
    private void sendContactsUpdate(){
        log.debug("Sending contacts update");
        StringJoiner sj = new StringJoiner(" ", CONT_UPD.toString(), "");
        clientsMap.values().forEach((cl) -> sj.add(cl.getNickName()));
        clientsMap.values().forEach((cl) -> {
            try {
                cl.sendServiceMessage(sj.toString());
            } catch (IOException e) {
                log.error("Sending contacts error: ", e);
            }
        });
    }
}
