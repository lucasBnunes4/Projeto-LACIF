package main;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import main.Nav;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import main.models.*;
import main.daos.*;
import javafx.scene.control.Alert;

public class retMateriaisController implements Initializable {


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
        Nav.loadScreen("/main/usuario.fxml", event);
    }

    @FXML
    private void returnToStart(ActionEvent event) {
        Nav.loadScreen("/main/hello-view.fxml", event);
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

    //entrada de dados
    @FXML
    private TextField txtMatriculaRetirada;
    @FXML
    private TextField txtNomeMaterialRetirada;
    @FXML
    private DatePicker dpDataRetirada;
    @FXML
    private Button btnRetirar;
    @FXML
    private TextField txtNomeMaterialDevolucao;
    @FXML
    private DatePicker dpDataDevolucao;
    @FXML
    private Button btnDevolver;

    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private materialDao materialDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        materialDao = new materialDao();
    }

    @FXML
    private void handleRetirarMaterial() {
        String matricula = txtMatriculaRetirada.getText().trim();
        String nomeMaterial = txtNomeMaterialRetirada.getText().trim();
        LocalDate dataRetirada = dpDataRetirada.getValue();

        if (matricula.isEmpty() || nomeMaterial.isEmpty() || dataRetirada == null) {
            showAlert("Erro", "Preencha todos os campos para retirada!");
            return;
        }

        try {
            // Primeiro consulta o material para obter o ID
            modelMaterial material = materialDao.consultarMaterial(nomeMaterial);

            if (material == null) {
                showAlert("Erro", "Material não encontrado!");
                return;
            }

            materialDao.retirarMaterial(material.getId(), matricula, dataRetirada);
            showAlert("Sucesso", "Material retirado com sucesso!");
            limparCamposRetirada();
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao retirar material: " + e.getMessage());
        }
    }

    @FXML
    private void handleDevolverMaterial() {
        String nomeMaterial = txtNomeMaterialDevolucao.getText().trim();

        if (nomeMaterial.isEmpty()) {
            showAlert("Erro", "Digite o nome do material para devolução!");
            return;
        }

        try {
            // Primeiro consulta o material para obter o ID
            modelMaterial material = materialDao.consultarMaterial(nomeMaterial);

            if (material == null) {
                showAlert("Erro", "Material não encontrado!");
                return;
            }

            materialDao.devolverMaterial(material.getId());
            showAlert("Sucesso", "Material devolvido com sucesso!");
            limparCamposDevolucao();
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao devolver material: " + e.getMessage());
        }
    }

    private void limparCamposRetirada() {
        txtMatriculaRetirada.clear();
        txtNomeMaterialRetirada.clear();
        dpDataRetirada.setValue(null);
    }

    private void limparCamposDevolucao() {
        txtNomeMaterialDevolucao.clear();
        dpDataDevolucao.setValue(null);
    }
}

