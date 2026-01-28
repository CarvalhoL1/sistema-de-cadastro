package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;


public class TelaNome extends JFrame {
    private JTextField nomeCampo;
    public TelaNome(Usuario u){
        setTitle("Editar Noeme");
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
        add(new JLabel("Digite o novo nome "));
        nomeCampo = new JTextField();
        add(nomeCampo);
        JButton btnNome = new JButton("Adicionar");
        add(btnNome);
        btnNome.addActionListener(e -> mudarNome(u));
        setVisible(true);
    }
    private void mudarNome(Usuario u){
        String nome = nomeCampo.getText().trim();
        try{
            String resultado = metodos.EditarNome(u.getEmail(), nome);
            JOptionPane.showMessageDialog(this, resultado);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
        }
    }
}
