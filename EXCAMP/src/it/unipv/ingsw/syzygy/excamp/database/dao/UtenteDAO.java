package it.unipv.ingsw.syzygy.excamp.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Camp;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Departure;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Transportation;
import it.unipv.ingsw.syzygy.excamp.modelDomain.user.Utente;

public class UtenteDAO {

    private Connection connection;

    public UtenteDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveUtente(Utente utente) {
        try {
            String query = "INSERT INTO UTENTE (persona_id, namePA, surnamePA, agePA, CFPA, accomodationPA, placeOfDeparture, destination, idTR, transporationPA, allergiesPA, medicalIssuesPA, sensitiveInfoPA, parentsInfoPA, camp) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, utente.getPersonaId());
            statement.setString(2, utente.getNamePA());
            statement.setString(3, utente.getSurnamePA());
            statement.setInt(4, utente.getAgePA());
            statement.setString(5, utente.getCFPA());
            statement.setString(6, utente.getAccomodationPA());
            statement.setString(7, utente.getPointOfDeparture().toString());
            statement.setString(8, utente.getTransportation().toString());
            statement.setString(9, utente.getAllergiesPA());
            statement.setString(10, utente.getMedicalIssuesPA());
            statement.setString(11, utente.getSensitiveInfoPA());
            statement.setString(12, utente.getParentsInfoPA());
            statement.setString(13, utente.getCamp().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utente getUtente(int personaId, String CFPA) {
        try {
            String query = "SELECT * FROM UTENTE WHERE persona_id = ? AND CFPA = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, personaId);
            statement.setString(2, CFPA);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Utente(
                		resultSet.getInt("persona_id"), 
                		resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("namePA"),
                        resultSet.getString("surnamePA"),
                        resultSet.getInt("agePA"),
                        resultSet.getString("CFPA"),
                        resultSet.getString("accomodationPA"),
                        Departure.valueOf(resultSet.getString("placeOfDeparture")),
                        Transportation.valueOf(resultSet.getString("transporationPA")),
                        resultSet.getString("allergiesPA"),
                        resultSet.getString("medicalIssuesPA"),
                        resultSet.getString("sensitiveInfoPA"),
                        resultSet.getString("parentsInfoPA"),
                        Camp.valueOf(resultSet.getString("camp"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void salvaScelteUtente(String username, String pastoPranzo, String pastoCena) throws SQLException {
        String query = "UPDATE UTENTE SET pastoPranzo = ?, pastoCena = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pastoPranzo);
            stmt.setString(2, pastoCena);
            stmt.setString(3, username);
            stmt.executeUpdate();
        }
    }
}
