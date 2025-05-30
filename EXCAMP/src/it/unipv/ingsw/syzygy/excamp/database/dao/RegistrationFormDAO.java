package it.unipv.ingsw.syzygy.excamp.database.dao;

import it.unipv.ingsw.syzygy.excamp.database.MySQLConnection;
import it.unipv.ingsw.syzygy.excamp.modelDomain.RegistrationFormModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationFormDAO {
	private Connection connection;
	
	public RegistrationFormDAO() throws SQLException {
		this.connection = MySQLConnection.getConnection();
	}
	
	public void insertIntoDatabase(RegistrationFormModel iscrizione) throws Exception {
		String query = "INSERT INTO iscrizione (username2, password2, nameGE2, surnameGE2, parentsInfoPA2, namePA2, surnamePA2, " +
			"agePA2, cFPA2, allergiesPA2, medicalIssuesPA2, sensitiveInfoPA2, genderPA2, paese2, indirizzo2, civico2, cAP2, " +
			"provincia2, dateOfBirth2, placeOfBirth2, camp2, transportation2, pointOfDeparture2, participationWeek2, notes2, accettatoRegolamento2," +
			"accettatoConditions2, accettatoPrivacy2)" +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, iscrizione.getUsername());
			pstmt.setString(2, new String (iscrizione.getPassword()));
			pstmt.setString(3, iscrizione.getNameGE());
			pstmt.setString(4, iscrizione.getSurnameGE());
			pstmt.setString(5, iscrizione.getParentsInfoPA());
			pstmt.setString(6, iscrizione.getNamePA());
			pstmt.setString(7, iscrizione.getSurnamePA());
			pstmt.setInt(8, iscrizione.getAgePA());
			pstmt.setString(9, iscrizione.getCFPA());
			pstmt.setString(10, iscrizione.getAllergiesPA());
			pstmt.setString(11, iscrizione.getMedicalIssuesPA());
			pstmt.setString(12, iscrizione.getSensitiveInfoPA());
			pstmt.setString(13, iscrizione.getGenderPA());
			pstmt.setString(14, iscrizione.getPaese());
			pstmt.setString(15, iscrizione.getIndirizzo());
			pstmt.setString(16, iscrizione.getCivico());
			pstmt.setString(17, iscrizione.getCAP());
			pstmt.setString(18, iscrizione.getProvincia());
			pstmt.setDate(19, new java.sql.Date(iscrizione.getDateOfBirthPA().getTime()));
			pstmt.setString(20, iscrizione.getPlaceOfBirthPA());
			pstmt.setString(21, iscrizione.getCamp().toString());
			pstmt.setString(22, iscrizione.getTransportation().toString());
			pstmt.setString(23, iscrizione.getPointOfDeparture().toString());
			pstmt.setDate(24, new java.sql.Date(iscrizione.getParticipationWeek().getTime()));
			pstmt.setString(25, iscrizione.getNotes());
			pstmt.setBoolean(26, iscrizione.isAcceptedRegulation());
			pstmt.setBoolean(27, iscrizione.isAcceptedConditions());
			pstmt.setBoolean(28, iscrizione.isAcceptedPrivacy());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Dati inseriti con successo!");
			} else {
				System.out.println("Inserimento dati fallito.");
			}
		} catch (Exception ex) {
			throw new SQLException("Errore nell'inserimento dei dati: " + ex.getMessage());
		}
	}
	
   public void closeConnection() {
       try {
           if (connection != null && !connection.isClosed()) {
               connection.close();
               System.out.println("Connesione chiusa con successo.");
           }
       } catch (SQLException e) {
           System.out.println("Errore nella chiusura della connession: " + e.getMessage());
       }
   }
}
