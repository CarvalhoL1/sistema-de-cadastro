package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.metodos.Usuario;
import service.metodos.Usuario;
import java.awt.*;
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
        mensagem.setText("Bem-vindo, " + u.getNome() + "!");
    }

}
