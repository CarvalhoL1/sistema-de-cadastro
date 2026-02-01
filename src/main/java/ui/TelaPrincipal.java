package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.metodos.Usuario;
import service.metodos.Usuario;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class TelaPrincipal {
    @FXML
    private Label mensagem;
    @FXML
    private void abrirLogin(ActionEvent event){
        try {
            javafx.fxml.FXMLLoader loader =
                    new javafx.fxml.FXMLLoader(getClass().getResource("/ui/tela-login.fxml"));

            javafx.scene.Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setUsuario(Usuario u) {
        mensagem.setText("Bem-vindo, " + u.getNome() + ", o que deseja fazer?");
    }
    @FXML private void buscar(ActionEvent event) throws IOException { trocarTela("/ui/tela-busca.fxml", event); }
    @FXML private void senha(ActionEvent event) throws IOException { trocarTela("/ui/tela-senha.fxml", event); }
    @FXML private void nome(ActionEvent event) throws IOException { trocarTela("/ui/tela-nome.fxml", event); }
    @FXML private void frase(ActionEvent event) throws IOException { trocarTela("/ui/tela-frase.fxml", event); }

    private void trocarTela(String fxml, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
