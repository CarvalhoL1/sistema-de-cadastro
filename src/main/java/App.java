
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.Migrations;
import db.ConnectionFactory;
import security.SenhaSegura;

public class App {  
    public static void add_usuario(String nome, String email, String senha) throws SQLException{
        String insertSQL = "INSERT INTO usuarios (nome, email, senha_hash) VALUES (?, ?, ?)";
        String hash = SenhaSegura.hashPassword(senha);
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, email);
                pstmt.setString(3, hash);

                pstmt.executeUpdate();
                System.out.println("Usuario " + nome +  " adicionado");
             }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean login(String email, String senha) throws SQLException{
        String selectSQL = "SELECT senha_hash FROM usuarios WHERE email = ?";

    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {

        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String hashSalvo = rs.getString("senha_hash");
            return SenhaSegura.checkPassword(senha, hashSalvo);
        } else {
            return false;
        }
    }
    }
    public static void main(String[] args){
    Scanner entrada = new Scanner(System.in);
    boolean ok;
    boolean continuar = true;
        try{
            Migrations.migrate();
        System.out.println("Sucesso!");
        
        while (continuar) {
        System.out.println("O que deseja fazer? \n [0] Sair\n [1] Cadastrar\n [2] Login");
        int escolha = entrada.nextInt();
        switch (escolha) {
            case 0:
                continuar = false;
                break;
            case 1:
                System.out.println("Digite o nome");
                String nome_cadastro = entrada.next();
                System.out.println("Digite o email");
                String email_cadastro = entrada.next();
                System.out.println("Digite a senha");
                String senha_cadastro = entrada.next();

                add_usuario(nome_cadastro, email_cadastro, senha_cadastro);
                ok = login(email_cadastro, senha_cadastro);
                if (ok) {
                    System.out.println("Cadastro bem-sucedido!");
                } else {
                    System.out.println("Cadastro falhou!");
                }
                        break;
            case 2:
                System.out.println("Digite seu email");
                String email_login = entrada.next();
                System.out.println("Digite sua senha");
                String senha_login = entrada.next();
                ok = login(email_login, senha_login);

                if (ok) {
                    System.out.println("Login bem-sucedido!");
                } else {
                    System.out.println("Login falhou!");
                }
                break;
            default:
                break;
        }
    }
            
    }catch (SQLException e) {
        System.err.println("Falha ao preparar o banco: " + e.getMessage());
        return;
    }

    }
}