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
import main.models.modelUser;
import java.time.LocalDate;
import main.daos.userDao;
import java.io.IOException;

public class newUserController extends Nav {

    //stack pane nao é usado
    @FXML
    private StackPane mainContent;

    // Variáveis para cada botão (com @FXML)
    @FXML private Button usuarioBtn;
    @FXML private Button materiaisBtn;
    @FXML private Button atividadesBtn;
    @FXML private Button amostrasBtn;
    @FXML private Button inicioBtn;

    //Variáveis para os menus itens
    @FXML private MenuItem addMateriais;
    @FXML private MenuItem consMateriais;

    //variáveis para cadastros
    @FXML private TextField txtNome;
    @FXML private ComboBox<String> cmbCargo;
    @FXML private DatePicker dtNascimento;
    @FXML private TextField txtBolsa;
    @FXML private TextField txtMatricula;

    @FXML private CheckBox ckSegunda;
    @FXML private CheckBox ckTerca;
    @FXML private CheckBox ckQuarta;
    @FXML private CheckBox ckQuinta;
    @FXML private CheckBox ckSexta;

    @FXML private TextField txtHoSeg;
    @FXML private TextField txtHoTer;
    @FXML private TextField txtHoQua;
    @FXML private TextField txtHoQui;
    @FXML private TextField txtHoSex;

    private userDao dao = new userDao();
    private int idUsuario;


    // Métodos em botoes para abrir paginas
    @FXML
    private void goToUsuario(ActionEvent event) {

        loadScreen("/main/usuario.fxml", event);
    }

    @FXML
    private void returnToMenu(ActionEvent event){
        loadScreen("/main/main.fxml", event);
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

    //metodos para inserir informações para o cadastro
    @FXML
    private void initialize() {
        cmbCargo.getItems().addAll("Técnico", "Professor", "Aluno-graduação", "Aluno-mestrado", "Aluno-doutorado");
    }

    @FXML
    private void cadastrar() {
        try {
            String nome = txtNome.getText();
            String cargo = cmbCargo.getValue();
            LocalDate dataNascimento = dtNascimento.getValue();
            double bolsa = Double.parseDouble(txtBolsa.getText().replace(",", "."));

            StringBuilder escala = new StringBuilder();
            StringBuilder horarios = new StringBuilder();

            String matricula = txtMatricula.getText();

            if (ckSegunda.isSelected()) {
                escala.append("Segunda-feira,");
                horarios.append("Segunda:").append(txtHoSeg.getText()).append(";");

            }
            if (ckTerca.isSelected()) {
                escala.append("Terça-feira,");
                horarios.append("Terça:").append(txtHoTer.getText()).append(";");

            }
            if (ckQuarta.isSelected()) {
                escala.append("Quarta-feira,");
                horarios.append("Quarta:").append(txtHoQua.getText()).append(";");

            }
            if (ckQuinta.isSelected()) {
                escala.append("Quinta-feira,");
                horarios.append("Quinta:").append(txtHoQui.getText()).append(";");

            }
            if (ckSexta.isSelected()) {
                escala.append("Sexta-feira,");
                horarios.append("Sexta:").append(txtHoSex.getText()).append(";");

            }
            
            modelUser user = new modelUser(idUsuario, nome, cargo, dataNascimento, bolsa, escala.toString(), horarios.toString(), matricula);

            boolean sucesso = dao.registrarUsuario(user);
            Alert alerta = new Alert(sucesso ? AlertType.INFORMATION : AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText(sucesso ? "Usuário cadastrado com sucesso!" : "Erro ao cadastrar usuário");
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
