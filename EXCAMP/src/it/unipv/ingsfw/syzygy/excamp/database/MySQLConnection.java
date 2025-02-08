<<<<<<<< HEAD:EXCAMP/src/MYSQLConnection/MySQLConnection.java
package MYSQLConnection;
========
package it.unipv.ingsfw.syzygy.excamp.database;

>>>>>>>> f3142b248aeacc0f62bce6ddc5c118f96d35d1a1:EXCAMP/src/it/unipv/ingsfw/syzygy/excamp/database/MySQLConnection.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static void main(String[] args) {
        // MySQL database URL
        String url = "jdbc:mysql://127.0.0.1:3306/testdb";  // Replace with your DB name and MySQL server address
        String user = "root";  // Replace with your MySQL username
        String password = "Saida@003";  // Replace with your MySQL password

        try {
            // Establishing the connection
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}