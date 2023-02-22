package postgreslibrary.application;

import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Paths.get("src/main/java/postgreslibrary/gui/views/MainView.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Public Library Database - Main View");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}