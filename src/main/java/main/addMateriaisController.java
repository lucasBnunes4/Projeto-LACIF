package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import main.daos.*;
import main.models.*;
import java.util.List;
import java.net.URL;
import java.sql.*;
import java.util.*;
import main.Nav;


public class addMateriaisController implements Initializable {


    // Variáveis para cada botão do menu
    @FXML private Button usuarioBtn;
    @FXML private Button materiaisBtn;
    @FXML private Button atividadesBtn;
    @FXML private Button amostrasBtn;
    @FXML private Button inicioBtn;
    @FXML private MenuItem addMateriais;
    @FXML private MenuItem consMateriais;

    // variáveis dos campus de add
    @FXML private TextField txtNomeMaterialAdd;
    @FXML private ComboBox<String> cbTipoMaterialAdd;
    @FXML private Button btnAddMaterial;
    @FXML private TextField txtNomeMaterialConsult;
    @FXML private Button btnConsultMaterial;

    private materialDao materialDao;

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

    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        materialDao = new materialDao();
        // Inicializa o ComboBox com tipos de material (exemplo de ENUM do seu SQL)
        cbTipoMaterialAdd.getItems().addAll("Campo", "Laboratório");
    }


    @FXML
    private void handleAdicionarMaterial(ActionEvent event) {
        String nomeMaterial = txtNomeMaterialAdd.getText().trim();
        String tipoMaterial = cbTipoMaterialAdd.getValue();

        if (nomeMaterial.isEmpty() || tipoMaterial == null) {
            showAlert("Erro de Entrada", "Por favor, preencha todos os campos para adicionar o material.");
            return;
        }

        modelMaterial material = new modelMaterial(nomeMaterial, tipoMaterial);

        try {
            materialDao.adicionarMaterial(material);
            showAlert("Sucesso", "Material adicionado com sucesso!");
            limparCamposAdicionar();
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao adicionar material: " + e.getMessage());
        }
    }

    @FXML
    private void handleConsultarMaterial() {
        String nomeMaterial = txtNomeMaterialConsult.getText().trim();

        if (nomeMaterial.isEmpty()) {
            showAlert("Erro", "Digite o nome do material para consultar!");
            return;
        }

        try {
            modelMaterial material = materialDao.consultarMaterial(nomeMaterial);

            if (material != null) {
                String status = material.isDisponivel() ? "Disponível" : "Em uso";
                String mensagem = "Material: " + material.getNomeMaterial() +
                        "\nTipo: " + material.getTipoMaterial() +
                        "\nStatus: " + status;

                if (!material.isDisponivel()) {
                    modelUser usuario = materialDao.consultarUsuarioComMaterial(material.getId());
                    if (usuario != null) {
                        mensagem += "\nUsuário: " + usuario.getNome() +
                                "\nMatrícula: " + usuario.getMatricula();
                    }
                }

                showAlert("Consulta", mensagem);
            } else {
                showAlert("Consulta", "Material não encontrado!");
            }
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao consultar material: " + e.getMessage());
        }
    }

    private void limparCamposAdicionar() {
        txtNomeMaterialAdd.clear();
        cbTipoMaterialAdd.getSelectionModel().clearSelection();
    }

}

