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
        
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        

        setContentPane(painelPrincipal);
        JButton btnVoltar = new JButton("Voltar");
       
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal();
            dispose();
        });
        JLabel lblsenha = new JLabel("Digite a nova senha ");
        senhaCampo = new JTextField();
        senhaCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaCampo.setMaximumSize(new Dimension(400, 40)); 
        lblsenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnSenha = new JButton("Adicionar");
        btnSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSenha.addActionListener(e -> mudarSenha(u));
        topo.add(btnVoltar);
        painelPrincipal.add(topo);
        painelPrincipal.add(Box.createVerticalStrut(30));

        painelPrincipal.add(lblsenha);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(senhaCampo);

        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(btnSenha);
        setContentPane(painelPrincipal);
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
