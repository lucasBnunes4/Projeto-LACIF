package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class HelloController {

    // Variáveis para cada botão (com @FXML)
    @FXML
    private Button menuBt;

    @FXML
    private Button newUserBt;

    // Métodos de ação para cada botão:
    @FXML
    private void goToMenu(ActionEvent event) {
        loadScreen("/main/main.fxml", event);
    }

    @FXML
    private void goToNewUser(ActionEvent event) {
        loadScreen("/main/newUser.fxml", event);
    }

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