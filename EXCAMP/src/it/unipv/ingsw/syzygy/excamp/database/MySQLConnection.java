package it.unipv.ingsw.syzygy.excamp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    // Rendi la variabile 'url' statica
    private static String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Europe/Rome";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        try {
            // Carica il driver JDBC di MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Ora puoi usare 'url' direttamente
            return DriverManager.getConnection(url, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
}

