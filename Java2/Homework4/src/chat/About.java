package chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class About extends Stage {
    public About(){
        try {
            Parent root = new FXMLLoader(getClass().getResource("views/about.fxml")).load();
            setTitle("About");
            Scene scene = new Scene(root, 200,200);
            setScene(scene);
            setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
