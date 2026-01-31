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

import java.sql.SQLException;

public class TelaBusca{
    @FXML
    private TextField buscaCampo;

    private void alert(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    @FXML
    private void voltarLogin(){

    }
    @FXML
    private void buscarUsuario(){
        String busca = buscaCampo.getText().trim();
        try{
            Usuario u = metodos.buscar_usuario(busca);
            if(u == null){
                alert("Usuario não encontrado!");
            }
            else if (u.getFrase() == null) {
                alert("Usuario encontrado! esse usuario não possui frase");
            }
            else{
                alert("Usuario encontrado! sua frase é " + u.getFrase() + " e seu nome é " + u.getNome());
            }




        }
        catch (SQLException ex) {
            alert("Erro no banco: " + ex.getMessage());
        }
    }
}
