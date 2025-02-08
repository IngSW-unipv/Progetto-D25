import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static void main(String[] args) {
        // MySQL database URL
        String url = "jdbc:mysql://127.0.0.1:3306/testdb";  // Replace with your DB name and MySQL server address
        String user = "root";  // Replace with your MySQL username
        String password = "09202002";  // Replace with your MySQL password

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