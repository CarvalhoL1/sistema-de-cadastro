package ui;

import java.sql.SQLException;
import java.util.Scanner;
import db.Migrations;
import service.metodos;   
import service.metodos.Usuario;

public class menu {
    public void iniciar(){
    Scanner entrada = new Scanner(System.in);
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

                metodos.add_usuario(nome_cadastro, email_cadastro, senha_cadastro);
        
                if (metodos.login(email_cadastro, senha_cadastro) != null) {
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
                Usuario u = metodos.login(email_login, senha_login);
                if (u != null) {
                    System.out.println("Login bem-sucedido! Bem vindo, " + u.getNome());
                    System.out.println("O que deseja fazer?\n [0] Apagar conta\n [1] Adicionar/editar frase\n [2] Editar nome\n [3] Editar senha");
                    int oquefazer = entrada.nextInt();
                    entrada.nextLine();
                        switch (oquefazer) {
                            case 0:
                                System.out.println("tem certeza? s/n");
                                char certeza = entrada.next().charAt(0);
                                if (certeza == 's') {
                                metodos.deletar_conta(email_login);
                                continuar = false;
                                }
                                else if(certeza == 'n'){
                                    System.out.println("Certo! operação cancelada");
                                }
                                break;
                            case 1: 
                                System.out.println("Digite a sua frase");
                                String frase = entrada.nextLine();
                                metodos.mudar_frase(email_login, frase);
                                
                                break;
                            case 2:
                                System.out.println("Digite o novo nome");
                                String nome = entrada.nextLine();
                                metodos.EditarNome(email_login, nome);
                                break;
                            case 3:
                                System.out.println("Digite a nova senha");
                                String senha = entrada.nextLine();
                                metodos.EditarSenha(email_login, senha);
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
                String frase = metodos.buscar_usuario(email_buscar);
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
