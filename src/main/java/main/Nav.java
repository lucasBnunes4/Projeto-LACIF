package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class Nav {

    private void loadScreen(String fxmlpath, ActionEvent event){
    try {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (Exception e) {
        System.err.println("Erro ao carregar a tela: " + fxmlpath);
        e.printStackTrace();
    }
}
}
