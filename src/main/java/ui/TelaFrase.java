package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;


public class TelaFrase extends JFrame {
    private JTextField fraseCampo;
    public TelaFrase(Usuario u){
        setTitle("Editar Frase");
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
        add(new JLabel("Digite a nova frase "));
        fraseCampo = new JTextField();
        add(fraseCampo);
        JButton btnfrase = new JButton("Adicionar");
        add(btnfrase);
        btnfrase.addActionListener(e -> mudarFrase(u));
        setVisible(true);
    }
    private void mudarFrase(Usuario u){
        String frase = fraseCampo.getText().trim();
        try{
            String resultado = metodos.mudar_frase(u.getEmail(), frase);
            JOptionPane.showMessageDialog(this, resultado);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
        }
    }
}
