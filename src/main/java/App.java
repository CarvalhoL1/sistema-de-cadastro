
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.Migrations;

public class App {  
    public static void main(String[] args){
        try{
            Migrations.migrate();
        System.out.println("Sucesso!");
    }catch (SQLException e) {
        System.err.println("Falha ao preparar o banco: " + e.getMessage());
        return;
    }
    }
}