package Sistema.Ordenes.Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Solutions";
    private static final String USER = "postgres";
    private static final String PASSWORD = "040910062120";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
    }
}
