package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;

public class TelaPrincipal extends JFrame{
    public TelaPrincipal(Usuario u){
        setTitle("Principal");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 4, 2, 4));
        JButton btnVoltar = new JButton("Logout");
        add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            new TelaLogin();
            dispose(); 
        });
        setVisible(true);
        
    }
}
