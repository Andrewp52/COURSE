package com.pae.chat.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.DataOutput;
import java.io.IOException;

import static com.pae.server.Commands.BL_LIST_ADD;
import static com.pae.server.Commands.BL_LIST_REM;

public class BlacklistController {
    @FXML private ListView<String> blacklist;
    @FXML private TextField nameField;
    private DataOutput out;

    public void blockUser(){
        if(blacklist.getItems().contains(nameField.getText())){                 // Avoiding duplicates
            nameField.clear();
            return;
        }
        try {
            out.writeUTF(BL_LIST_ADD.toString() + nameField.getText());
            nameField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unblockSelected() {
        try {
            out.writeUTF(BL_LIST_REM.toString() + blacklist.getSelectionModel().getSelectedItem());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setup(DataOutput out, ObservableList<String> blist){
        this.out = out;
        this.blacklist.setItems(blist);
    }
}
