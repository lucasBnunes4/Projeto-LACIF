package main;

import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Nav;
import java.time.LocalDate;
import main.daos.*;
import main.models.*;

public class consAmostrasController extends Nav {

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
    private void openAddAmostras(ActionEvent event) {
        Nav.loadScreenMenu("/main/addAmostras.fxml", event);
    }

    @FXML
    private void openConsAmostras(ActionEvent event) {
        Nav.loadScreenMenu("/main/consAmostras.fxml", event);
    }

    @FXML
    private void returnToMenuM(ActionEvent event) {
        Nav.loadScreenMenu("/main/main.fxml", event);
    }

    //variaveis para busca
    @FXML
    private ComboBox<String> cbxBuscarAmostra;
    @FXML
    private Button buscarAmostra;
    @FXML
    private TableView<modelAmostras> tabelaAmostra;
    @FXML
    private TableColumn<modelAmostras, String> colTipoAmostra;
    @FXML
    private TableColumn<modelAmostras, Integer> colParcela;
    @FXML
    private TableColumn<modelAmostras, Double> colPesoNatural;
    @FXML
    private TableColumn<modelAmostras, Double> colPesoSeco;
    @FXML
    private TableColumn<modelAmostras, LocalDate> colDataCorte;
    @FXML
    private TableColumn<modelAmostras, LocalDate> colDataPesagem;
    @FXML
    private TableColumn<modelAmostras, String> colTratamento;
    @FXML
    private TableColumn<modelAmostras, Double> colPesoTotal;

    private main.daos.amostrasDao dao = new amostrasDao();


    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    //métodos para busca das amostras
    @FXML
    public void initialize() {
        cbxBuscarAmostra.getItems().addAll("Capim Tamani", "Amendoin Forrageiro");
        colTipoAmostra.setCellValueFactory(new PropertyValueFactory<>("tipoAmostra"));
        colParcela.setCellValueFactory(new PropertyValueFactory<>("parcela"));
        colPesoNatural.setCellValueFactory(new PropertyValueFactory<>("pesoNatural"));
        colPesoSeco.setCellValueFactory(new PropertyValueFactory<>("pesoSeco"));
        colDataCorte.setCellValueFactory(new PropertyValueFactory<>("dataCorte"));
        colDataPesagem.setCellValueFactory(new PropertyValueFactory<>("dataPesagem"));
        colTratamento.setCellValueFactory(new PropertyValueFactory<>("tratamento"));
        colPesoTotal.setCellValueFactory(new PropertyValueFactory<>("pesoTotal"));
    }

    @FXML
    private void buscarAmostra() {
        String tipoAmostra = cbxBuscarAmostra.getSelectionModel().getSelectedItem();
        if (tipoAmostra != null && !tipoAmostra.isEmpty()) {
            ObservableList<modelAmostras> dados = FXCollections.observableArrayList(dao.buscarAmostra(tipoAmostra));
            System.out.println("Itens retornados: " + dados.size());
            tabelaAmostra.setItems(dados);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um valor para buscar.");
            alert.showAndWait();
        }
    }

}
