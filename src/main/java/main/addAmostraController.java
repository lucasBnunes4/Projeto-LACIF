package main;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import main.daos.*;
import main.models.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import java.time.LocalDate;


public class addAmostraController extends Nav {

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

    //variáveis para cadastrar amostras
    @FXML private ComboBox<String> cmbTipoAmostra;
    @FXML private TextField txtPesoNatural;
    @FXML private TextField txtPesoSeco;
    @FXML private TextField txtParcela;
    @FXML private TextField txtTratamento;
    @FXML private TextField txtPesoTotal;
    @FXML private DatePicker dtCorte;
    @FXML private DatePicker dtPesagem;
    @FXML private Button cadastrarAmostra;

    private amostrasDao dao = new amostrasDao();


    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        cmbTipoAmostra.getItems().addAll("Amendoin Forrageiro", "Capim Tamani");
    }

    @FXML
    private void cadastrarAmostra() {
        try {

            String tipoAmostra = cmbTipoAmostra.getValue();
            double pesoNatural = Double.parseDouble(txtPesoNatural.getText().replace(",", "."));
            double pesoSeco = Double.parseDouble(txtPesoSeco.getText().replace(",", "."));
            int parcela = Integer.parseInt(txtParcela.getText().trim());
            String tratamento = txtTratamento.getText();
            LocalDate dataCorte = dtCorte.getValue();
            LocalDate dataPesagem = dtPesagem.getValue();
            double pesoTotal = Double.parseDouble(txtPesoTotal.getText().replace(",", "."));

            int idAmostra = 0;
            modelAmostras amostras = new modelAmostras(idAmostra, tipoAmostra, pesoNatural, pesoSeco, dataCorte, dataPesagem, parcela, tratamento, pesoTotal);


            boolean sucesso = dao.registrarAmostra(amostras);
            Alert alerta = new Alert(sucesso ? AlertType.INFORMATION : AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText(sucesso ? "Amostra cadastrado com sucesso!" : "Erro ao cadastrar amostra");
            alerta.showAndWait();
        } catch (Exception e) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText("Erro");
            alerta.setHeaderText("Preencha todos os campos corretamente.");
            alerta.showAndWait();
            e.printStackTrace();
        }
    }
}

