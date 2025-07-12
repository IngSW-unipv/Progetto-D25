package it.unipv.ingsw.syzygy.excamp.database.dao;

import it.unipv.ingsw.syzygy.excamp.database.MySQLConnection;
import it.unipv.ingsw.syzygy.excamp.modelDomain.FeedbackFormModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackFormDAO {
	private Connection connection;
	
	public FeedbackFormDAO() throws SQLException {
		this.connection = MySQLConnection.getConnection();
	}
	
	public void saveFeedback(FeedbackFormModel feedback) {
		String query = "INSERT INTO feedback (id, experience, mostlikedact, leastlikedact, menu, accomodation, " +
					"transportation, comments) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, feedback.getUserId());
			pstmt.setInt(2, feedback.getStelle());
			pstmt.setString(3, feedback.getAttivitaLiked());
			pstmt.setString(4, feedback.getAttivitaDisliked());
			pstmt.setInt(5, feedback.getVotoMenu());
			pstmt.setInt(6, feedback.getVotoAlloggio());
			pstmt.setInt(7, feedback.getVotoTrasporti());
			pstmt.setString(8, feedback.getCommenti());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	   public void closeConnection() {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
