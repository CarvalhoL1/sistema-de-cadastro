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
        setLayout(new GridLayout(4, 4, 2, 4));
        add(new JLabel("Olá, " + u.getNome() + " quem deseja buscar?"));
        add(new JLabel("Email da pessoa:"));
        buscaCampo = new JTextField();
        add(buscaCampo);
        JButton btnBuscar = new JButton("Buscar");
        add(btnBuscar);
        btnBuscar.addActionListener(e -> Buscar());
        JButton btnVoltar = new JButton("Voltar");
        add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal(u);
            dispose();
        });
        setVisible(true);
    }
    private void Buscar(){
        String busca = buscaCampo.getText().trim();
        try{
            String frase = metodos.buscar_usuario(busca);
            if (frase != "não encontrado"){
                if (frase == null) {
                    JOptionPane.showMessageDialog(this, "Usuario encontrado! esse usuario não possui frase");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Usuario encontrado! sua frase é " + frase);
                }             
            }
            else{
                JOptionPane.showMessageDialog(this, "Usuario não encontrado!");
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
        }
    }
}
