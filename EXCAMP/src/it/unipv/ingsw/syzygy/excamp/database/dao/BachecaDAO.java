package it.unipv.ingsw.syzygy.excamp.database.dao;

import it.unipv.ingsw.syzygy.excamp.database.MySQLConnection;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BachecaDAO {
	private Connection connection;
	
	public BachecaDAO() throws SQLException {
		connection = MySQLConnection.getConnection();
	}
	
	public void insertPhoto(InputStream imageData, String filePath, String dateTaken, String albumPath) throws SQLException {
		String query = "INSERT INTO bacheca (image, file_path, dateTaken, album) " +
				"VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setBlob(1, imageData);
			pstmt.setString(2, filePath);
			pstmt.setString(3, dateTaken);
			pstmt.setString(4, albumPath);
			
			pstmt.executeUpdate();
		}
	}
	
	public ResultSet getAllPhotos() throws SQLException {
		String query = "SELECT * FROM bacheca";
		
		try (Statement stmt = connection.createStatement()) {
	        return stmt.executeQuery(query);
	    }
	}
	
	public List<String> getAllWeeks() throws SQLException {
		String query ="SELECT DISTINCT album FROM bacheca ORDER BY album";
		List<String> weeks = new ArrayList<>();
		
		try (Statement stmt = connection.createStatement(); 
				ResultSet rs =stmt.executeQuery(query)) {
			while (rs.next()) {
				String album = rs.getString("album");
				weeks.add(album);
			}
		}
		return weeks;
	}
	
	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}
