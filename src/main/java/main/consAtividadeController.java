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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import main.daos.consAtividadeDAO;
import main.models.modelAtividade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class consAtividadeController extends Nav {

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

    //Para busca
    @FXML private TextField campoBusca;
    @FXML private TableView<modelAtividade> tabela;
    @FXML private TableColumn<modelAtividade, String> colNome;
    @FXML private TableColumn<modelAtividade, LocalDate> colInicio;
    @FXML private TableColumn<modelAtividade, LocalDate> colTermino;
    @FXML private TableColumn<modelAtividade, String> colParticipantes;
    @FXML private TableColumn<modelAtividade, String> colStatus;

    private main.daos.consAtividadeDAO dao = new consAtividadeDAO();

    @FXML
    public void initialize() {
        // Configurar as colunas da tabela
        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeAtividade"));
        colInicio.setCellValueFactory(new PropertyValueFactory<>("inicioAtividade"));
        colTermino.setCellValueFactory(new PropertyValueFactory<>("fimAtividade"));
        colParticipantes.setCellValueFactory(new PropertyValueFactory<>("participantes"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusAtividade"));
    }

    @FXML
    private void buscarAtividade() {
        String nomeAtividade = campoBusca.getText().trim();
        if (!nomeAtividade.isEmpty()) {
            ObservableList<modelAtividade> dados = FXCollections.observableArrayList(dao.buscarPorNome(nomeAtividade));
            System.out.println("Itens retornados: " + dados.size());
            tabela.setItems(dados);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, digite um nome para buscar.");
            alert.showAndWait();
        }
    }

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

