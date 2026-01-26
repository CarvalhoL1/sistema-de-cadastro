package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    public static Connection conectar(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:base.db");
            }
        catch(SQLException e) {
            System.err.println(e.getMessage());
            }
        finally {
        try {
            if(connection != null){
            connection.close();
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        }
        }
}
