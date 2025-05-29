package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class mainController {

    // Variáveis para cada botão (com @FXML)
    @FXML
    private Button usuarioBtn;

    @FXML
    private Button materiaisBtn;

    @FXML
    private Button atividadesBtn;

    @FXML
    private Button amostrasBtn;

    @FXML
    private Button sairBtn;

    // Métodos de ação para cada botão:
    @FXML
    private void handleUsuario() {
        showAlert("Menu Usuário", "Você clicou em USUÁRIO");
    }

    @FXML
    private void handleMateriais() {
        showAlert("Menu Materiais", "Você clicou em MATERIAIS");
    }

    @FXML
    private void handleAtividades() {
        showAlert("Menu Atividades", "Você clicou em ATIVIDADES");
    }

    @FXML
    private void handleAmostras() {
        showAlert("Menu Amostras", "Você clicou em AMOSTRAS");
    }

    @FXML
    private void handleSair() {
        System.exit(0);
    }

    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
