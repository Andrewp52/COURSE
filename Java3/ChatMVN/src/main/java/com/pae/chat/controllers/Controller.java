package com.pae.chat.controllers;

import com.pae.chat.About;
import com.pae.chat.Blacklist;
import com.pae.chat.Personal;
import com.pae.chat.Register;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import static com.pae.server.Commands.*;

public class Controller implements Initializable {
    @FXML private ListView<String> contacts;
    @FXML private Label nickLabel;
    @FXML private TextArea chatArea;
    @FXML private TextField messageField;
    @FXML private HBox loginPanel;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button registerButton;
    @FXML private HBox messageEnterBox;
    @FXML private MenuItem clearHistMenu;
    @FXML private MenuItem blacklistMenu;
    @FXML private MenuItem logoutMenu;

    private final String ADDRESS = "localhost";
    private final int PORT = 8888;
    private Socket socket;
    private DataOutput out;
    private DataInput in;
    private boolean authorized;
    private ObservableList<String> blacklist = new ObservableListWrapper<>(new LinkedList<>());
    private File historyFile;
    private final int historyLinesQuant = 100;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAuthorized(false);
    }

    public void signIn(){
        if(!isLoginValid(loginField.getText())){
            chatArea.appendText("Invalid login Format\r\n");
            return;
        }
        if (passwordField.getText().length() == 0){
            chatArea.appendText("Password can't be empty\r\n");
            return;
        }
        if(socket == null || socket.isClosed()){
            connect();
        }
        if(socket != null && !socket.isClosed()){
            try {
                out.writeUTF(String.format("%s%s %s", AUTH_REQUEST, loginField.getText(), passwordField.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            chatArea.appendText("Connection error");
        }
    }

    public void signOut() {
        if (socket != null && !socket.isClosed()) {
            try {
                out.writeUTF(CONN_STOP.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit(){
        signOut();
        ((Stage) nickLabel.getScene().getWindow()).close();
    }

    public void clearHistory(){
        if(this.socket != null && !this.socket.isClosed()){
            try {
                this.out.writeUTF(HIST_DROP.toString());
                chatArea.clear();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void openBlacklist(){
        new Blacklist(this.out, this.blacklist).show();
    }

    public void openAbout(){
        new About().show();
    }

    public void openPersonal(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            new Personal(this.contacts.getSelectionModel().getSelectedItem(), out).show();
        }
    }

    public void openRegister(){
        new Register(this.ADDRESS, this.PORT).show();
    }

    public void sendMessage(){
        if(this.messageField.getText().equals("") || this.socket == null || this.socket.isClosed()){
            return;
        }
        try {
            this.out.writeUTF(this.messageField.getText() + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
        this.messageField.clear();
        this.messageField.requestFocus();
    }

    private boolean isLoginValid(String login){
        return login.matches("^[a-zA-Z0-9]+$");
    }

    private void connect() {
        try {
            this.socket = new Socket(this.ADDRESS, this.PORT);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

            new Thread(() -> {
                String message;
                try {
                    while (true){
                        message = this.in.readUTF();
                        if (message.equals(CONN_STOP.toString())) {
                            this.chatArea.clear();
                            return;
                        }
                        if(message.equals(AUTH_OK.toString())){
                            setAuthorized(true);
                            this.loginField.clear();
                            this.passwordField.clear();
                            loadLocalHistory(this.historyLinesQuant);
                            break;
                        }
                        if (AUTH_FAIL.toString().equals(message)) {
                            this.chatArea.appendText("Login failed\r\n");
                            this.passwordField.clear();
                        }
                        if (AUTH_DUP.toString().equals(message)) {
                            this.chatArea.appendText("Login is already in use\r\n");
                            this.loginField.clear();
                            this.passwordField.clear();
                        }
                        if(message.startsWith(HIST.toString())){
                            String msg = message;
                            Platform.runLater(() -> {
                                loadHistory(msg.substring(HIST.getLength()));
                            });
                        }
                        if(message.startsWith(AUTH_NICK.toString())){
                            String msg = message;
                            Platform.runLater(() -> {
                                setNick(msg.substring(AUTH_NICK.getLength()));
                            });
                        }
                    }
                    while (true) {
                        message = this.in.readUTF();
                        if (message.equals(CONN_STOP.toString())) {
                            this.chatArea.clear();
                            break;
                        }
                        if (message.startsWith(CONT_UPD.toString())){
                            String msg = message;
                            Platform.runLater(() -> {
                                updateContacts(msg.substring(CONT_UPD.getLength()));
                            });
                        } else if (message.startsWith(BL_LIST_REQ.toString())) {
                            String msg = message;
                            Platform.runLater(() -> {
                                updateBlacklist(msg.substring(BL_LIST_REQ.getLength()));
                            });
                        } else {
                            this.chatArea.appendText(message);
                            storeMessage(message);
                        }
                    }
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    Platform.runLater(() -> {
                        disconnect();
                        setAuthorized(false);
                    });
                }
            }).start();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateBlacklist(String message) {
        if(message.length() == 0){
            blacklist.clear();
            return;
        }
        String[] tokens = message.split(" ");
        blacklist.clear();
        blacklist.addAll(Arrays.asList(tokens));
    }

    private void updateContacts(String message) {
        String[] tokens = message.split(" ");
        this.contacts.getItems().clear();
        for (String s : tokens) {
            if(!s.equals(this.nickLabel.getText())){
                this.contacts.getItems().add(s);
            }
        }
    }
    // Loading user history from database (Temporarily turned off on the server side)
    private void loadHistory(String message) {
        String[] tokens = message.split("\r\n");
        for (String s : tokens) {
            chatArea.appendText(String.format("%s\n", s));
        }
    }
    // Loading user history from local file (Working now)
    public void loadLocalHistory(int quant) {
        if(!checkHistoryFile()){
            return;
        }
        try (BufferedReader reader1 = new BufferedReader(new FileReader(historyFile))){
            String[] buffer = new String[quant];                                        // Cyclic buffer
            int index = 0;
            String line;
            while ((line = reader1.readLine()) != null) {
                buffer[index] = line;
                if(++index > quant - 1){
                    index = 0;
                }
            }
            for (int i = index; i < buffer.length; i++) {                               // Reading buffer tail first
                if(buffer[i] == null){
                    break;
                }
                chatArea.appendText(buffer[i] + "\r\n");
            }
            for (int i = 0; i < index; i++) {                                           // Reading buffer head
                if(buffer[i] == null){
                    break;
                }
                chatArea.appendText(buffer[i] + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Saving message locally
    private void storeMessage(String message){
        if(!checkHistoryFile()){
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true))){
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNick(String nick){
        this.nickLabel.setText(nick);
        this.historyFile = historyFile = new File(String.format("src/main/java/com/pae/chat/%s.chat", nick));     // Filename for history
    }

    private void disconnect(){
        try {
            if(this.socket != null && !this.socket.isClosed()){
                this.socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        setAuthorized(false);
    }

    private void setAuthorized(boolean authorized){
        this.authorized = authorized;
        this.messageEnterBox.setDisable(!authorized);
        this.contacts.setDisable(!authorized);
        this.loginPanel.setVisible(!authorized);
        this.clearHistMenu.setDisable(!authorized);
        this.blacklistMenu.setDisable(!authorized);
        this.logoutMenu.setDisable(!authorized);
        this.registerButton.setVisible(!authorized);
        if (!authorized){
            this.nickLabel.setText("Logged off");
            this.chatArea.clear();
        }
    }

    private boolean checkHistoryFile(){
        if(historyFile.exists()){
            return true;
        }
        try {
            return historyFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
