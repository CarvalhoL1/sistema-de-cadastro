package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;


public class TelaNome extends JFrame {
    private JTextField nomeCampo;
    public TelaNome(Usuario u){
        setTitle("Editar Nome");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JButton btnVoltar = new JButton("Voltar");
        topo.add(btnVoltar);

        btnVoltar.addActionListener(e -> {
            new TelaPrincipal(u);
            dispose();
        });
        JLabel lblnome = new JLabel("Digite o novo nome ");
        lblnome.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomeCampo = new JTextField();
        nomeCampo.setMaximumSize(new Dimension(400, 40));
        nomeCampo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nomeCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnNome = new JButton("Adicionar");
        btnNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNome.addActionListener(e -> mudarNome(u));
        nomeCampo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(topo);
        painelPrincipal.add(Box.createVerticalStrut(30));

        painelPrincipal.add(lblnome);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(nomeCampo);

        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(btnNome);

        setContentPane(painelPrincipal);

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
