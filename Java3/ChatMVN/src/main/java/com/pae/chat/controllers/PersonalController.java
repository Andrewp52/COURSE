package com.pae.chat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataOutput;
import java.io.IOException;

import static com.pae.server.Commands.PRIV_MESSAGE;

public class PersonalController {
    @FXML private TextArea messageArea;
    private DataOutput out;
    private String user;

    public void sendMessage(){
        try {
            this.out.writeUTF(String.format("%s%s %s\r\n", PRIV_MESSAGE.toString(), user, messageArea.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        exit();
    }

    public void setup(DataOutput out, String user){
        this.user = user;
        this.out = out;
    }

    private void exit(){
        ((Stage) this.messageArea.getScene().getWindow()).close();
    }
}
