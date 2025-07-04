package it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione;

import java.sql.*;

import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Departure;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Transportation;
import it.unipv.ingsw.syzygy.excamp.modelDomain.user.Staff;

public class StaffDAO {

    private Connection connection;

    // Costruttore che riceve la connessione al database
    public StaffDAO(Connection connection) {
        this.connection = connection;
    }

    // Metodo per recuperare un oggetto Staff dal database
    public Staff getStaff(String usernameST, String CFST) {
        Staff staff = null;

        String query = "SELECT persona_id, usernameST, passwordST, nameST, surnameST, ageST, CFST, accomodationST, pointOfDeparture, transportationST, allergiesST, medicalIssuesST, phonenumberST " +
                       "FROM STAFF WHERE usernameST = ? AND CFST = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, usernameST);
            pstmt.setString(2, CFST);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Recupera i dati dal ResultSet
                    int id = rs.getInt("persona_id");
                    String passwordST = rs.getString("passwordST");
                    String nameST = rs.getString("nameST");
                    String surnameST = rs.getString("surnameST");
                    int ageST = rs.getInt("ageST");
                    String accomodationST = rs.getString("accomodationST");
                    String pointOfDepartureStr = rs.getString("pointOfDeparture");
                    String transportationStr = rs.getString("transportationST");
                    String allergiesST = rs.getString("allergiesST");
                    String medicalIssuesST = rs.getString("medicalIssuesST");
                    String phonenumberST = rs.getString("phonenumberST");

                    // Enum safe conversion
                    Departure pointOfDeparture = pointOfDepartureStr != null ? Departure.valueOf(pointOfDepartureStr) : null;
                    Transportation transportation = transportationStr != null ? Transportation.valueOf(transportationStr) : null;

                    // Crea l'oggetto Staff
                    staff = new Staff(
                        id,
                        usernameST,
                        passwordST,
                        nameST,
                        surnameST,
                        ageST,
                        CFST,
                        accomodationST,
                        pointOfDeparture,
                        transportation,
                        allergiesST,
                        medicalIssuesST,
                        phonenumberST
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestione errore
        }

        return staff;
    }

    // Metodo per aggiungere un nuovo staff al database (se necessario)
    public boolean addStaff(Staff staff) {
        String query = "INSERT INTO STAFF (usernameST, passwordST, nameST, surnameST, ageST, CFST, accomodationST, pointOfDeparture, transportationST, allergiesST, medicalIssuesST, phonenumberST) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, staff.getUsername());
            pstmt.setString(2, staff.getPassword());
            pstmt.setString(3, staff.getNameST());
            pstmt.setString(4, staff.getSurnameST());
            pstmt.setInt(5, staff.getageST());
            pstmt.setString(6, staff.getCFST());
            pstmt.setString(7, staff.getAccomodationST());
            pstmt.setString(8, staff.getPointOfDeparture() != null ? staff.getPointOfDeparture().name() : null);
            pstmt.setString(9, staff.getTransportation() != null ? staff.getTransportation().name() : null);
            pstmt.setString(10, staff.getAllergiesST());
            pstmt.setString(11, staff.getMedicalIssuesST());
            pstmt.setString(12, staff.getPhoneNumberST());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo per aggiornare i dati di uno staff esistente (se necessario)
    public boolean updateStaff(Staff staff) {
        String query = "UPDATE STAFF SET passwordST = ?, nameST = ?, surnameST = ?, ageST = ?, accomodationST = ?, pointOfDeparture = ?, transportationST = ?, allergiesST = ?, medicalIssuesST = ?, phonenumberST = ? " +
                       "WHERE usernameST = ? AND CFST = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, staff.getPassword());
            pstmt.setString(2, staff.getNameST());
            pstmt.setString(3, staff.getSurnameST());
            pstmt.setInt(4, staff.getageST());
            pstmt.setString(5, staff.getAccomodationST());
            pstmt.setString(6, staff.getPointOfDeparture() != null ? staff.getPointOfDeparture().name() : null);
            pstmt.setString(7, staff.getTransportation() != null ? staff.getTransportation().name() : null);
            pstmt.setString(8, staff.getAllergiesST());
            pstmt.setString(9, staff.getMedicalIssuesST());
            pstmt.setString(10, staff.getPhoneNumberST());
            pstmt.setString(11, staff.getUsername());
            pstmt.setString(12, staff.getCFST());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo per rimuovere uno staff dal database (se necessario)
    public boolean deleteStaff(String usernameST, String CFST) {
        String query = "DELETE FROM STAFF WHERE usernameST = ? AND CFST = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, usernameST);
            pstmt.setString(2, CFST);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Ritorna true se la rimozione ha avuto successo
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ritorna false in caso di errore
        }
    }

    // Metodo per aggiornare i pasti selezionati per lo staff
    public boolean aggiornaPastiStaff(String usernameST, String CFST, String pastoPranzo, String pastoCena) {
        String query = "UPDATE STAFF SET pastoPranzo = ?, pastoCena = ? WHERE usernameST = ? AND CFST = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, pastoPranzo);  // Imposta il pasto per il pranzo
            pstmt.setString(2, pastoCena);    // Imposta il pasto per la cena
            pstmt.setString(3, usernameST);   // Imposta l'username
            pstmt.setString(4, CFST);         // Imposta il codice fiscale dello staff

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Ritorna true se l'aggiornamento ha avuto successo
        } catch (SQLException e) {
            e.printStackTrace();  // Gestisci l'errore in modo appropriato
            return false;  // Ritorna false in caso di errore
        }
    }
}
