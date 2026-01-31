package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.metodos;
import service.metodos.Usuario;

import java.io.IOException;
import java.sql.SQLException;


public class TelaSenha{
    @FXML
    PasswordField senhaCampo;

    private void alert(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void voltarPainel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/tela-principal.fxml"));
        Parent root = loader.load();
        TelaPrincipal controller = loader.getController();
        controller.setUsuario(Sessao.usuarioLogado);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void mudarSenha(ActionEvent event){
        String senha = senhaCampo.getText().trim();
        Usuario u = Sessao.usuarioLogado;
        if (senha.isEmpty()) {
            alert("Digite a nova senha!");
            return;
        }

        try{
            String resultado = metodos.EditarSenha(u.getEmail(), senha);
            alert(resultado);
        }
        catch(SQLException ex){
            alert("Erro no banco: " + ex.getMessage());
        }
    }
}
