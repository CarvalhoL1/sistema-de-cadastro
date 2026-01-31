package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.metodos.Usuario;
import service.metodos.Usuario;
import java.awt.*;
import java.sql.SQLException;

public class TelaPrincipal {
    @FXML
    private Label mensagem;

    public void setUsuario(Usuario u) {
        mensagem.setText("Bem-vindo, " + u.getNome() + "!");
    }

}
