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

        JLabel lblfrase = new JLabel("Digite a nova frase:");
        lblfrase.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblfrase.setAlignmentY(Component.CENTER_ALIGNMENT);
        fraseCampo = new JTextField();
        fraseCampo.setMaximumSize(new Dimension(400, 40)); 
        fraseCampo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        fraseCampo.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton btnFrase = new JButton("Adicionar");
        btnFrase.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFrase.addActionListener(e -> mudarFrase(u));

        painelPrincipal.add(topo);
        painelPrincipal.add(Box.createVerticalStrut(30));

        painelPrincipal.add(lblfrase);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(fraseCampo);

        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(btnFrase);

        setContentPane(painelPrincipal);
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
