package db;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Migrations {
    public static void migrate() throws SQLException {

    try (Connection connection = ConnectionFactory.getConnection();
    Statement statment = connection.createStatement()) {
    String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome TEXT NOT NULL," +
            "email TEXT UNIQUE NOT NULL," +
            "senha_hash TEXT NOT NULL," +
            "frase TEXT);";
    statment.executeUpdate(sql);}

}
}

