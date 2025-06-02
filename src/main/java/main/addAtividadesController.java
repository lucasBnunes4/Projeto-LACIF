package main;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import main.Nav;
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

public class addAtividadesController extends Nav {


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
    private Button inicioBtn;

    @FXML
    private MenuItem addMateriais;

    @FXML
    private MenuItem consMateriais;

    // Métodos em botoes para abrir paginas
    @FXML
    private void goToUsuario(ActionEvent event) {
        loadScreen("/main/usuario.fxml", event);
    }

    @FXML
    private void returnToStart(ActionEvent event) {
        loadScreen("/main/hello-view.fxml", event);
    }


    // Metodos para abrir paginas pelos menus
    @FXML
    private void openAddMateriais(ActionEvent event) {
        Nav.loadScreenMenu("/main/addMateriais.fxml", event);
    }

    @FXML
    private void openRetMateriais(ActionEvent event) {
        Nav.loadScreenMenu("/main/retMateriais.fxml", event);
    }

    @FXML
    private void openAddAtividades(ActionEvent event) {
        Nav.loadScreenMenu("/main/addAtividades.fxml", event);
    }

    @FXML
    private void openConsAtividades(ActionEvent event) {
        Nav.loadScreenMenu("/main/consAtividades.fxml", event);
    }

    @FXML
    private void openAddAmostras(ActionEvent event){
        Nav.loadScreenMenu("/main/addAmostras.fxml", event);
    }

    @FXML
    private void openConsAmostras(ActionEvent event){
        Nav.loadScreenMenu("/main/consAmostras.fxml", event);
    }

    @FXML
    private void returnToMenuM(ActionEvent event){
        Nav.loadScreenMenu("/main/main.fxml", event);
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

