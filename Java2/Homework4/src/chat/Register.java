package chat;

import chat.controllers.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Register extends Stage {

    public Register(String address, int port)
    {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/register.fxml"));
            root = loader.load();
            setTitle("Sign up new user");
            Scene scene = new Scene(root, 350, 250);
            setResizable(false);
            setScene(scene);
            RegisterController controller = loader.getController();
            setOnShowing(event -> {
                controller.setup(address, port);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
