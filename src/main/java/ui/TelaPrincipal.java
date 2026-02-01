package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.metodos;
import service.metodos.Usuario;
import service.metodos.Usuario;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class TelaPrincipal {
    @FXML
    private Label mensagem;
    private void alert(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
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

    @FXML
    private void apagarConta(ActionEvent event){
        Usuario u = Sessao.usuarioLogado;
        Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
        aviso.setContentText("Tem certeza? isso apagara sua conta para sempre");
        Optional<ButtonType> confirmar = aviso.showAndWait();
        if (confirmar.get() == ButtonType.OK){
            try {
                metodos.deletar_conta(u.getEmail());
                alert("Usuario deletado com sucesso! voltando a tela de login");
                abrirLogin(event);
            }catch (SQLException ex){
                alert("Falha ao deletar usuario " + ex.getMessage());
            }
        }
    }
}
