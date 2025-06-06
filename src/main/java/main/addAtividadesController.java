package main;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import main.Nav;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.time.LocalDate;

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


    //inseria as atividades
    @FXML private TextField nomeAtividadeField;
    @FXML private DatePicker inicioPicker;
    @FXML private TextField participantesField;
    @FXML private TextField statusField;
    @FXML private DatePicker terminoPicker;

    private consAtividadeDAO dao = new consAtividadeDAO();


    @FXML
    private void handleRegistrar(ActionEvent event) {
        try {
            modelAtividade atividade = new modelAtividade(
                    nomeAtividadeField.getText(),
                    inicioPicker.getValue(),
                    terminoPicker.getValue(),
                    participantesField.getText(),
                    statusField.getText()
            );

            if (dao.registrarAtividade(atividade)) {
                showAlert("Sucesso", "Atividade registrada com sucesso!");
                limparCampos();
            } else {
                showAlert("Erro", "Falha ao registrar atividade.");
            }
        } catch (Exception e) {
            showAlert("Erro", "Preencha todos os campos corretamente.");
        }
    }

    private void showAlert(String sucesso, String s) {

    }

    private void limparCampos() {
        nomeAtividadeField.clear();
        inicioPicker.setValue(null);
        terminoPicker.setValue(null);
        participantesField.clear();
        statusField.clear();
    }
}

