package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;


public class TelaSenha extends JFrame {
    private JTextField senhaCampo;
    public TelaSenha(Usuario u){
        setTitle("Editar Senha");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 4, 2, 4));
        JButton btnVoltar = new JButton("Voltar");
        add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal(u);
            dispose();
        });
        add(new JLabel("Digite a nova senha "));
        senhaCampo = new JTextField();
        add(senhaCampo);
        JButton btnSenha = new JButton("Adicionar");
        add(btnSenha);
        btnSenha.addActionListener(e -> mudarSenha(u));
        setVisible(true);
    }
    private void mudarSenha(Usuario u){
        String senha = senhaCampo.getText().trim();
        try{
            String resultado = metodos.EditarSenha(u.getEmail(), senha);
            JOptionPane.showMessageDialog(this, resultado);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
        }
    }
}
