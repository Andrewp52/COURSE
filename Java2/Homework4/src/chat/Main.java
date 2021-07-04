package chat;

import chat.controllers.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/chatWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("MyChat");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(240);
        primaryStage.setMinWidth(400);
        primaryStage.sizeToScene();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Controller controller = loader.getController();
                controller.signOut();
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
