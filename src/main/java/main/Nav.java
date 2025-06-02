package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class Nav {

    void loadScreen(String fxmlpath, ActionEvent event){
    try {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (Exception e) {
        System.err.println("Erro ao carregar a tela: " + fxmlpath);
        e.printStackTrace();
    }
}

    public static void loadScreenMenu(String fxmlPath, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Nav.class.getResource(fxmlPath));
            Stage stage;

            // Verifica se veio de MenuItem ou Node comum
            if (event.getSource() instanceof Node) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else if (event.getSource() instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) event.getSource();
                ContextMenu context = menuItem.getParentPopup();
                stage = (Stage) context.getOwnerWindow();
            } else {
                stage = new Stage(); // Fallback
            }

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Falha ao carregar: " + fxmlPath);
            e.printStackTrace();

            // Mostra alerta de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao carregar a tela");
            alert.setContentText("Arquivo não encontrado: " + fxmlPath);
            alert.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
