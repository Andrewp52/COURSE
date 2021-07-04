package chat;

import chat.controllers.BlacklistController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Blacklist extends Stage {
    DataOutput out;
    public Blacklist(DataOutput out, ObservableList<String> blist)
    {
        this.out = out;
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/blacklist.fxml"));
            root = loader.load();
            setTitle("Blacklist management");
            Scene scene = new Scene(root, 350, 250);
            setResizable(false);
            setScene(scene);
            BlacklistController controller = loader.getController();
            setOnShowing(event -> {
                controller.setup(this.out, blist);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
