package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import static server.Commands.*;

public class Server {
    private final int PORT = 8888;
    private ServerSocket srv = null;
    private static final Map<String, ClientHandler> clientsMap = Collections.synchronizedMap(new HashMap<>());

    public void start() {
        AuthService.connect();
        System.out.println("Server start");
        try {
            this.srv = new ServerSocket(PORT);
            while (true){
                Socket socket = this.srv.accept();
                new ClientHandler(this, socket).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(ClientHandler client){
        System.out.println(client.getNickName() + " logged in.");
        clientsMap.put(client.getNickName().intern(), client);          // Intern to avoid data duplication with handler
        sendContactsUpdate();
    }
    public void unsubscribe(ClientHandler client){
        System.out.println(client.getNickName() + " logged out.");
        clientsMap.remove(client.getNickName(), client);
        sendContactsUpdate();
    }
    public boolean isNickDuplicate(String nick){
        return clientsMap.containsKey(nick);
    }
    public void sendToAll(ClientHandler sender, String message) throws IOException {
        for (ClientHandler client : clientsMap.values()) {
            client.sendMessage(String.format("[%s]: %s",sender.getNickName(), message));
        }
    }
    public void sendPrivate(ClientHandler sender, String to, String message) throws IOException {
        ClientHandler rcpt = clientsMap.getOrDefault(to, null);
        if(rcpt == null){
            sender.sendServiceMessage(String.format("Private sending failed : '%s' not found.\r\n", to));
            return;
        }
        if (rcpt.isUserBlocked(sender.getNickName())){
            sender.sendServiceMessage(String.format("Private sending to %s failed : Blocked.\r\n", to));
            return;
        }
        if (!rcpt.equals(sender)){
            rcpt.sendMessage(String.format("[Private from %s] %s",sender.getNickName(), message));
            sender.sendMessage(String.format("[Private to %s] %s",to, message));
        }
    }
    private void sendContactsUpdate(){
        StringJoiner sj = new StringJoiner(" ", CONT_UPD.toString(), "");
        clientsMap.values().forEach((cl) -> sj.add(cl.getNickName()));
        clientsMap.values().forEach((cl) -> {
            try {
                cl.sendServiceMessage(sj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
