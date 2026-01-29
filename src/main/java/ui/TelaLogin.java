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
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        JLabel lblEmail = new JLabel("Email:");
        emailCampo = new JTextField();
        emailCampo.setMaximumSize(new Dimension(400, 40));
        emailCampo.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JLabel lblSenha = new JLabel("Senha:");
        senhaCampo = new JPasswordField();
        senhaCampo.setMaximumSize(new Dimension(400, 40));
        senhaCampo.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnEntrar = new JButton("Entrar");
        add(new JLabel());
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblConta = new JLabel("Não tem uma conta?");
        lblConta.setAlignmentX(Component.CENTER_ALIGNMENT);


        btnCadastro = new JButton("Criar conta");
        btnCadastro.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(lblEmail);
        painelPrincipal.add(Box.createVerticalStrut(5));
        painelPrincipal.add(emailCampo);

        painelPrincipal.add(Box.createVerticalStrut(15));

        painelPrincipal.add(lblSenha);
        painelPrincipal.add(Box.createVerticalStrut(5));
        painelPrincipal.add(senhaCampo);

        painelPrincipal.add(Box.createVerticalStrut(20));

        painelPrincipal.add(btnEntrar);

        painelPrincipal.add(Box.createVerticalStrut(15));

        painelPrincipal.add(lblConta);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(btnCadastro);
        
        setContentPane(painelPrincipal);
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