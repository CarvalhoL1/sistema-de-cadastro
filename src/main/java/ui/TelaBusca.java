package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;

public class TelaBusca extends JFrame{
    private JTextField buscaCampo;
    public TelaBusca(Usuario u){
        setTitle("Buscar usuario");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(new JLabel("Olá, " + u.getNome() + " quem deseja buscar?"));
        painelPrincipal.add(new JLabel("Email da pessoa:"));
        buscaCampo = new JTextField();
        buscaCampo.setMaximumSize(new Dimension(400, 40));
        JButton btnBuscar = new JButton("Buscar");
        
        btnBuscar.addActionListener(e -> Buscar());
        JButton btnVoltar = new JButton("Voltar");
        
        

        painelPrincipal.add(buscaCampo);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(btnBuscar);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(btnVoltar);

        setContentPane(painelPrincipal);

        btnVoltar.addActionListener(e -> {
            new TelaPrincipal(u);
            dispose();
        });
        setVisible(true);
    }
    private void Buscar(){
        String busca = buscaCampo.getText().trim();
        try{
            Usuario u = metodos.buscar_usuario(busca);
            if(u == null){
                JOptionPane.showMessageDialog(this, "Usuario não encontrado!");
            }
            else if (u.getFrase() == null) {
                JOptionPane.showMessageDialog(this, "Usuario encontrado! esse usuario não possui frase");
            }
            else{
                JOptionPane.showMessageDialog(this, "Usuario encontrado! sua frase é " + u.getFrase() + " e seu nome é " + u.getNome());
            }




        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
        }
    }
}
