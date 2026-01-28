package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;

public class TelaCadastro extends JFrame{
    private JTextField emailCampo;
    private JPasswordField senhaCampo;
    private JButton btnCriar;
    private JTextField nomeCampo;
    public TelaCadastro(){
        setTitle("Cadastro");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JButton btnVoltar = new JButton("Voltar para login");
        add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            new TelaLogin();
            dispose(); 
        });

        add(new JLabel("Nome:"));
        nomeCampo = new JTextField();
        nomeCampo.setMaximumSize(new Dimension(400, 40));
        nomeCampo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(nomeCampo);

        add(new JLabel("Email:"));
        emailCampo = new JTextField();
        emailCampo.setMaximumSize(new Dimension(400, 40));
        emailCampo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(emailCampo);

        add(new JLabel("Senha:"));
        senhaCampo = new JPasswordField();
        senhaCampo.setMaximumSize(new Dimension(400, 40));
        senhaCampo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(senhaCampo);

        btnCriar = new JButton("Criar conta");
        add(btnCriar);
        btnCriar.addActionListener(e -> Cadastro());

        setVisible(true);
    }
    private void Cadastro(){
        String nome = nomeCampo.getText().trim();
        String email = emailCampo.getText().trim();
        String senha = new String(senhaCampo.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha email e senha.");
            return;
        }
        try{
            metodos.add_usuario(nome, email, senha);
            JOptionPane.showMessageDialog(this, "Conta criada com sucesso! voltando a tela de login");
            new TelaLogin();
            dispose(); 
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
        }
    }
}
