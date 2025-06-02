package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/hello-view.fxml"));
            Scene scene = new Scene(root, 1000, 600);
            stage.setTitle("Sistema LACIF");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar FXML: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}