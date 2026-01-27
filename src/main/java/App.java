
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static void deletar_conta(String email) throws SQLException{
        String deletSQL = "DELETE FROM usuarios WHERE email = ?";
        try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(deletSQL)) {

        pstmt.setString(1, email);
        int linhasAfetadas = pstmt.executeUpdate();
        if (linhasAfetadas > 0) {
            System.out.println("Usuario deletado");
        }
        else{
            System.out.println("Falha ao deletar usuario");
        }
    }
}
    public static void mudar_frase(String email, String frase) throws SQLException{
        String insertSQL = "UPDATE usuarios SET frase = ? WHERE email = ?";
        try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

        pstmt.setString(1, frase);
        pstmt.setString(2, email);
        int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Falha ao adicionar a frase");
            }
            else{
                System.out.println("Frase adicionada!");
            }
    }
    }
    public static String buscar_usuario(String email) throws SQLException{
        String selectSQL = "SELECT frase FROM usuarios WHERE email = ?";
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String frase = rs.getString("frase");
            return frase;
        } else {
            return "não encontrado";
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
        System.out.println("O que deseja fazer? \n [0] Sair\n [1] Cadastrar\n [2] Login\n [3] Buscar usuario");
        int escolha = entrada.nextInt();
        switch (escolha) {
            case 0:
                continuar = false;
                break;
            case 1:
                entrada.nextLine();
                System.out.println("Digite o nome");
                String nome_cadastro = entrada.nextLine();
                System.out.println("Digite o email");
                String email_cadastro = entrada.nextLine();
                System.out.println("Digite a senha");
                String senha_cadastro = entrada.nextLine();

                add_usuario(nome_cadastro, email_cadastro, senha_cadastro);
                ok = login(email_cadastro, senha_cadastro);
                if (ok) {
                    System.out.println("Cadastro bem-sucedido!");
                } else {
                    System.out.println("Cadastro falhou!");
                }
                        break;
                
            case 2:
                entrada.nextLine();
                System.out.println("Digite seu email");
                String email_login = entrada.nextLine();
                System.out.println("Digite sua senha");
                String senha_login = entrada.nextLine();
                ok = login(email_login, senha_login);
                if (ok) {
                    System.out.println("Login bem-sucedido!");
                    System.out.println("O que deseja fazer?\n [0] Apagar conta\n [1] Adicionar/editar frase");
                    int oquefazer = entrada.nextInt();
                    entrada.nextLine();
                        switch (oquefazer) {
                            case 0:
                                System.out.println("tem certeza? s/n");
                                char certeza = entrada.next().charAt(0);
                                if (certeza == 's') {
                                deletar_conta(email_login);
                                continuar = false;
                                }
                                else if(certeza == 'n'){
                                    System.out.println("Certo! operação cancelada");
                                }
                                break;
                            case 1: 
                                System.out.println("Digite a sua frase");
                                String frase = entrada.nextLine();
                                mudar_frase(email_login, frase);
                                
                                break;
                            default:
                                break;
                        }
                } else {
                    System.out.println("Login falhou!");
                }
                break;
            case 3:
                System.out.println("Digite o email desse usuario");
                String email_buscar = entrada.next();
                String frase = buscar_usuario(email_buscar);
                if(frase != null && !frase.equals("não encontrado")){
                    System.out.println("Usuario encontrado! sua frase é " + frase);
                }
                else if(frase == null){
                    System.out.println("Usuario encontrado! parece que ele não tem frase");
                }
                else if(frase.equals("não encontrado")){
                    System.out.println("Não encontrado");
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