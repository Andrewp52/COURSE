package com.pae.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import static com.pae.server.Commands.*;

public class ClientHandler implements Runnable{
    private final Server server;
    private final Socket socket;
    private final InetAddress ipAddress;
    private DataOutputStream out;
    private DataInputStream in;

    private String nickName;
    private List<String> blacklist;
    private final Logger log = LogManager.getLogger(ClientHandler.class);
    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.out = new DataOutputStream(this.socket.getOutputStream());
        this.in = new DataInputStream(this.socket.getInputStream());
        this.ipAddress = socket.getInetAddress();
    }

    @Override
    public void run() {
        log.debug(String.format("Handler for %s started", this.ipAddress));
        String message;
        try {
            this.socket.setSoTimeout(120_000);
            while (true){
                message = in.readUTF();
                if(CONN_STOP.toString().equals(message)){
                    sendServiceMessage(message);
                    break;
                }
                if(message.startsWith(REG_REQ.toString())){
                    registerUser(message.substring(REG_REQ.getLength()));
                    break;
                }
                if(authUser(message)){
                    break;
                } else {
                    log.debug(String.format("Auth from %s failed", this.ipAddress));
                }
            }
            if (this.nickName == null){
                sendServiceMessage(CONN_STOP.toString());
                return;
            }
            socket.setSoTimeout(0);
            log.debug(String.format("Auth from %s success", this.ipAddress));
            while (true) {
                message = this.in.readUTF();
                if (CONN_STOP.toString().equals(message)) {
                    sendServiceMessage(message);
                    log.debug(String.format("Stop command received from %s", this.ipAddress));
                    break;
                }
                if (message.startsWith(PRIV_MESSAGE.toString())) {
                    String[] tokens = message.split(" ", 2);
                    this.server.sendPrivate(this, tokens[0].substring(PRIV_MESSAGE.getLength()), tokens[1]);
                } else if (message.startsWith(BL_LIST_ADD.toString())) {
                    blockUser(message.substring(BL_LIST_ADD.getLength()));
                } else if (message.equals(HIST_DROP.toString())) {
                    dropHistory();
                } else if (message.equals(BL_LIST_REQ.toString())) {
                    sendBlackList();
                } else if (message.startsWith(BL_LIST_REM.toString())){
                    unblockUser(message.substring(BL_LIST_REM.getLength()));
                } else {
                    this.server.sendToAll(this, message);
                }
            }
        } catch (IOException e) {
            log.error("Client handler error: ", e);
        } finally {
            try {
                if(isConnectionAlive()){
                    this.in.close();
                    this.out.close();
                    this.socket.close();
                }
                if (this.nickName != null){
                    this.server.unsubscribe(this);
                }
                log.debug(String.format("Handler for %s stopped.", this.ipAddress));
            } catch (IOException e) {
                log.error("Client handler error: ", e);
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        if(isConnectionAlive()) {
            this.out.writeUTF(message);
            AuthService.storeUserHistory(this.nickName, message);
        }
    }

    public void sendServiceMessage(String message) throws IOException { // For service messages. No history logging
        if(isConnectionAlive()){
            this.out.writeUTF(message);
        }
    }

    public String getNickName() {
        return this.nickName;
    }

    public boolean isUserBlocked(String nick){
        return blacklist.contains(nick);
    }

    private void registerUser(String message) throws IOException {
        log.debug(String.format("Registration started for %s", this.ipAddress));
        String[] tokens = message.split(" ");
        if(AuthService.registerUser(tokens[0], tokens[1], tokens[2]) > 0){
            sendServiceMessage(REG_OK.toString());
            log.debug(String.format("Registration %s aka [%s] - success", tokens[0], tokens[2]));
        } else {
            sendServiceMessage(REG_FAIL.toString());
            log.debug(String.format("Registration %s aka [%s] - failed", tokens[0], tokens[2]));
        }
    }

    private boolean authUser(String message) throws IOException{
        if(!message.startsWith(AUTH_REQUEST.toString())) {
            return false;
        }
        String[] tokens = message.split(" ");
        String nick = AuthService.getNickByLoginPassword(tokens[1], tokens[2]);
        if(nick == null){
            sendServiceMessage(AUTH_FAIL.toString());
            return false;
        } else if (server.isNickDuplicate(nick)){
            sendServiceMessage(AUTH_DUP.toString());
            log.debug(String.format("Nick - [%s] is duplicate", nick));
            return false;
        }
        this.nickName = nick.intern();                              // Intern to avoid data duplication with clientsMap
        this.blacklist = AuthService.getBlacklistByOwner(this.nickName);
        //sendHistory(AuthService.getHistoryByOwner(this.nickName));        // HISTORY TURNED OFF
        sendServiceMessage(AUTH_NICK.toString() + this.nickName);
        sendServiceMessage(AUTH_OK.toString());
        this.server.subscribe(this);
        sendBlackList();
        return true;
    }

    private void blockUser(String nick) throws IOException {
        log.trace(String.format("[%s] is blocking [%s]...", this.nickName, nick));
        if(nick.equals(this.nickName)){
            return;
        }
        AuthService.blockUser(this.nickName, nick);
        this.blacklist = AuthService.getBlacklistByOwner(this.nickName);
        sendBlackList();
    }

    private void unblockUser(String nick) throws IOException {
        log.trace(String.format("[%s] is unblocking [%s]...", this.nickName, nick));
        AuthService.unblockUser(this.nickName, nick);
        this.blacklist = AuthService.getBlacklistByOwner(this.nickName);
        sendBlackList();
    }

    private void sendBlackList() throws IOException {
        log.debug(String.format("Sending blacklist for [%s]", this.nickName));
        StringJoiner sj = new StringJoiner(" ", BL_LIST_REQ.toString(), "");
        for (String s : blacklist) {
            sj.add(s);
        }
        sendServiceMessage(sj.toString());
    }

    private void sendHistory(List<String> history) throws IOException{
        log.debug(String.format("Sending history for [%s]", this.nickName));
        if(history.size() == 0){
            return;
        }
        StringJoiner sj = new StringJoiner("", HIST.toString(), "");
        for (String s : history) {
            sj.add(s);
        }
        sendServiceMessage(sj.toString());
    }

    private void dropHistory() throws IOException {
        log.trace(String.format("[%s] is dropping it's history...", this.nickName));
        AuthService.dropHistoryByOwner(this.nickName);
        sendServiceMessage("History clear\r\n");
    }

    private boolean isConnectionAlive(){
        return this.socket != null && !this.socket.isClosed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return Objects.equals(this.ipAddress, that.ipAddress) && Objects.equals(this.nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ipAddress, this.nickName);
    }
}
