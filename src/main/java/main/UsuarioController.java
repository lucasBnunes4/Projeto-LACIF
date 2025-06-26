package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import main.Nav;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.time.LocalDate;
import main.models.*;
import main.daos.*;

public class UsuarioController extends Nav {


    // Variáveis para cada botão (buttons de troca de página)
    @FXML private Button usuarioBtn;
    @FXML private Button materiaisBtn;
    @FXML private Button atividadesBtn;
    @FXML private Button amostrasBtn;
    @FXML private Button inicioBtn;
    @FXML private MenuItem addMateriais;
    @FXML private MenuItem consMateriais;

    //Variáveis para usar busca
    @FXML private TextField campoBuscaUsuario;
    @FXML private TableView<modelUser> tabelaUsuario;
    @FXML private TableColumn<modelUser, String> colNomeUsuario;
    @FXML private TableColumn<modelUser, String> colMatricula;
    @FXML private TableColumn<modelUser, String> colCargo;
    @FXML private TableColumn<modelUser, LocalDate> colDataNascimento;
    @FXML private TableColumn<modelUser, Double> colBolsa;
    @FXML private TableColumn<modelUser, String> colEscala;
    @FXML private TableColumn<modelUser, String> colHorarios;

    private main.daos.userDao dao = new userDao();

    // Métodos em botoes para abrir paginas
    @FXML
    private void goToUsuario(ActionEvent event) {
        loadScreen("/main/usuario.fxml", event);
    }

    @FXML
    private void returnToStart(ActionEvent event) {
        loadScreen("/main/hello-view.fxml", event);
    }

    @FXML
    private void returnToMenu(ActionEvent event) {
        loadScreen("/main/main.fxml", event);
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

    // métodos para a busca de informações do usuario
    @FXML
    public void initialize() {
        colNomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colBolsa.setCellValueFactory(new PropertyValueFactory<>("valorBolsa"));
        colEscala.setCellValueFactory(new PropertyValueFactory<>("escala"));
        colHorarios.setCellValueFactory(new PropertyValueFactory<>("horario"));
    }

    @FXML
    private void buscarUsuario() {
        String matricula = campoBuscaUsuario.getText().trim();
        if (!matricula.isEmpty()) {
            ObservableList<modelUser> dados = FXCollections.observableArrayList(dao.buscarPorNome(matricula));
            System.out.println("Itens retornados: " + dados.size());
            tabelaUsuario.setItems(dados);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, digite um nome para buscar.");
            alert.showAndWait();
        }
    }

}

