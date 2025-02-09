package it.unipv.ingsw.syzygy.excamp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static void main(String[] args) {
	       // MySQL database URL
	       String url = "jdbc:mysql://localhost:3306/testdb";  // Replace with your DB 
	       String user = "root";  // Replace with your MySQL username
	       String password = "password";  // Replace with your MySQL password
	       try {
	    	   // Carica il driver
	           Class.forName("com.mysql.cj.jdbc.Driver");

	           // Establishing the connection
	           Connection connection = DriverManager.getConnection(url, user, password);
	           if (connection != null) {
	               System.out.println("Connected to the database successfully!");
	           }
	       } catch (SQLException e) {
	           System.out.println("Connection failed!");
	           e.printStackTrace();
	       } catch (ClassNotFoundException e) {
	    	   e.printStackTrace();
		}
	   }
}
