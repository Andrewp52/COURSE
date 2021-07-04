package com.pae.chat;

import com.pae.chat.controllers.PersonalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataOutput;
import java.io.IOException;

public class Personal extends Stage {
    DataOutput out;
    public Personal(String user, DataOutput out){
        this.out = out;
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/personal.fxml"));
            root = loader.load();
            setTitle("Personal message for " + user);
            Scene scene = new Scene(root, 350, 200);
            setResizable(false);
            setScene(scene);
            PersonalController controller = loader.getController();
            setOnShowing(event -> {
                controller.setup(this.out, user);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
