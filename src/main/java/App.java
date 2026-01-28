
import db.Migrations;

import ui.TelaLogin;

public class App {
    public static void main(String[] args) {
        try {
            Migrations.migrate();
            new TelaLogin();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}