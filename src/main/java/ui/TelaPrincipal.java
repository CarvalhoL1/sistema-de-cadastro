package ui;

import javax.swing.*;
import service.metodos;
import service.metodos.Usuario;

import java.awt.*;
import java.sql.SQLException;

public class TelaPrincipal extends JFrame{
    public TelaPrincipal(Usuario u){
        setTitle("Principal");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Olá, " + u.getNome() + " o que deseja fazer?"));
        add(new JLabel("Sua frase é: " + u.getFrase()));
        JButton btnVoltar = new JButton("Logout");
        add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            new TelaLogin();
            dispose(); 
        });
        JButton btnBuscar = new JButton("Buscar Usuario");
        add(btnBuscar);
        btnBuscar.addActionListener(e -> {
        new TelaBusca(u);
        dispose(); 
        });
        JButton btnEditarNome = new JButton("Editar nome");
        add(btnEditarNome);
        btnEditarNome.addActionListener(e -> {
        new TelaNome(u);
        dispose(); 
        });
        JButton btnEditarSenha = new JButton("Editar senha");
        add(btnEditarSenha);
        btnEditarSenha.addActionListener(e -> {
        new TelaSenha(u);
        dispose(); 
        });
        JButton btnApagar = new JButton("Apagar conta");
        add(btnApagar);
        btnApagar.addActionListener(e -> {
             int resposta = JOptionPane.showConfirmDialog(
                        this,
                        "Tem certeza que deseja excluir sua conta??",
                        "Confirmar Ação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (resposta == JOptionPane.YES_OPTION) {
                    try{
                        metodos.deletar_conta(u.getEmail());
                        JOptionPane.showMessageDialog(this, "Conta excluida, voltando a tela inicial.");
                        new TelaLogin();
                        dispose();
                    }
                    catch(SQLException ex){
                        JOptionPane.showMessageDialog(this, "Erro no banco: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ação cancelada");
                }
        });
        setVisible(true);
        
    }
}
