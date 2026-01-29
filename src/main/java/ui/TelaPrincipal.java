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
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        painelPrincipal.add(new JLabel("Olá, " + u.getNome() + " o que deseja fazer?"));
        if(u.getFrase() != "não encontrado" && u.getFrase() != null){
        painelPrincipal.add(new JLabel("Sua frase é: " + u.getFrase()));
    }
        JButton btnVoltar = new JButton("Logout");
        
        
        JButton btnBuscar = new JButton("Buscar Usuario");
        
        btnBuscar.addActionListener(e -> {
        new TelaBusca(u);
        dispose(); 
        });
        JButton btnEditarNome = new JButton("Editar nome");
        
        btnEditarNome.addActionListener(e -> {
        new TelaNome(u);
        dispose(); 
        });
        JButton btnEditarSenha = new JButton("Editar senha");
        
        btnEditarSenha.addActionListener(e -> {
        new TelaSenha(u);
        dispose(); 
        });
        JButton btnEditarFrase = new JButton("Editar Frase");
        
        btnEditarFrase.addActionListener(e -> {
        new TelaFrase(u);
        dispose(); 
        });
        JButton btnApagar = new JButton("Apagar conta");

        painelPrincipal.add(btnVoltar);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(btnBuscar);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(btnEditarNome);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(btnEditarSenha);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(btnEditarFrase);
        painelPrincipal.add(Box.createVerticalStrut(8));
        painelPrincipal.add(btnApagar);

        setContentPane(painelPrincipal);

        btnVoltar.addActionListener(e -> {
            new TelaLogin();
            dispose(); 
        });
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
