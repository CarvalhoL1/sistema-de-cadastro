package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private JTextField emailCampo;
    private JPasswordField senhaCampo;
    private JButton btnEntrar;
    private JButton btnCadastro;
    public TelaLogin() {
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 4, 2, 4));

        add(new JLabel("Email:"));
        emailCampo = new JTextField();
        add(emailCampo);

        add(new JLabel("Senha:"));
        senhaCampo = new JPasswordField();
        add(senhaCampo);

        btnEntrar = new JButton("Entrar");
        add(new JLabel());
        add(btnEntrar);

        add(new JLabel("Não tem uma conta?"));
        btnCadastro = new JButton("Criar conta");
        add(btnCadastro);
        btnEntrar.addActionListener(e -> fazerLogin());
        btnCadastro.addActionListener(e -> {
            new TelaCadastro();
            dispose();
        });
        setVisible(true);
    }

     private void fazerLogin(){
        String email = emailCampo.getText().trim();
        String senha = new String(senhaCampo.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha email e senha.");
            return;
        }

        try {
            Usuario u = metodos.login(email, senha);

            if (u != null) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + u.getNome() + "!");
                new TelaPrincipal(u);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
        }
    }
}