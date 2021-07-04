package com.pae.chat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static com.pae.server.Commands.REG_OK;
import static com.pae.server.Commands.REG_REQ;

public class RegisterController {
    @FXML private TextField loginField;
    @FXML private TextField passwordField;
    @FXML private TextField nickField;
    @FXML private Label resultLabel;
    @FXML private Button registerButton;
    private String addr;
    private int port;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public void register(){
        if(!checkFieldsValid()){
            return;
        }
        if(registerButton.getText().equals("Exit")){
            exit();
        } else {
            try {
                this.socket = new Socket(addr, port);
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());
                String req = String.format(
                        "%s%s %s %s", REG_REQ, loginField.getText(), passwordField.getText(), nickField.getText()
                );
                if(this.socket != null && !socket.isClosed()){
                    this.out.writeUTF(req);
                    if (this.in.readUTF().equals(REG_OK.toString())) {
                        this.resultLabel.setText("Registration success");
                        this.registerButton.setText("Exit");
                    } else {
                        this.resultLabel.setText("Registration failed. Try again");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    this.in.close();
                    this.out.close();
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void exit(){
        ((Stage) loginField.getScene().getWindow()).close();
    }

    public void setup(String addr, int port){
        this.addr = addr;
        this.port = port;
    }

    private boolean checkFieldsValid(){
        boolean res = true;
        String validRegex = "^[a-zA-Z0-9]+$";
        if(!loginField.getText().matches(validRegex)) {
            resultLabel.setText("Invalid login. ");
            res = false;
        }
        if (!passwordField.getText().matches(validRegex)){
            res = false;
            resultLabel.setText(resultLabel.getText() + "Invalid password. ");
        }
        if (!nickField.getText().matches(validRegex)){
            res = false;
            resultLabel.setText(resultLabel.getText() + "Invalid nick. ");
        }
        return res;
    }
}
