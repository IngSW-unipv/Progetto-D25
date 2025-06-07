package it.unipv.ingsw.syzygy.excamp.database.dao;

import it.unipv.ingsw.syzygy.excamp.database.MySQLConnection;
import java.sql.*;

public class BachecaDAO {
	private Connection connection;
	
	public BachecaDAO() throws SQLException {
		connection = MySQLConnection.getConnection();
	}
	
	public void insertPhoto(String filePath, String dateTaken, String albumPath) throws SQLException {
		String query = "INSERT INTO bacheca (file_path, dateTaken, album) " +
				"VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, filePath);
			pstmt.setString(2, dateTaken);
			pstmt.setString(3, albumPath);
			
			pstmt.executeUpdate();
		}
	}
	
	public ResultSet getAllPhotos() throws SQLException {
		String query = "SELECT * FROM bacheca";
		
		try (Statement stmt = connection.createStatement()) {
	        return stmt.executeQuery(query);
	    }
	}
	
	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}
