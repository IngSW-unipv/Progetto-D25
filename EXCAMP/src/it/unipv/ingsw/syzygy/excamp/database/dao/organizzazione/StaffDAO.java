package it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione;
import java.sql.*;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Camp;
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
   public Staff getStaff(String username, String CFST) {
       Staff staff = null;
       String query =
           "SELECT p.id AS persona_id, p.username, p.pwd, " +
           "s.nameST, s.surnameST, s.ageST, s.CFST, " +
           "s.accommodationST, s.residenceST, s.placeOfDeparture, " +
           "s.destination, s.idTR, s.transportationST, " +
           "s.allergiesST, s.medicalIssuesST, s.phonenumberST, " +
           "s.camp " +
           "FROM STAFF s " +
           "JOIN PERSONA p ON s.persona_id = p.id " +
           "WHERE p.username = ? AND s.CFST = ?";
       try (PreparedStatement pstmt = connection.prepareStatement(query)) {
           pstmt.setString(1, username);
           pstmt.setString(2, CFST);
           try (ResultSet rs = pstmt.executeQuery()) {
               if (rs.next()) {
                   int id = rs.getInt("persona_id");
                   String password = rs.getString("pwd");
                   String nameST = rs.getString("nameST");
                   String surnameST = rs.getString("surnameST");
                   int ageST = rs.getInt("ageST");
                   String accommodationST = rs.getString("accommodationST");
                   String residenceST = rs.getString("residenceST");
                   String pointOfDepartureStr = rs.getString("placeOfDeparture");
                   String destination = rs.getString("destination");
                   String idTR = rs.getString("idTR");
                   String transportationStr = rs.getString("transportationST");
                   String allergiesST = rs.getString("allergiesST");
                   String medicalIssuesST = rs.getString("medicalIssuesST");
                   String phoneNumberST = rs.getString("phonenumberST");
                   String campStr = rs.getString("camp");
              
                   Departure pointOfDeparture = pointOfDepartureStr != null ? Departure.valueOf(pointOfDepartureStr) : null;
                   Transportation transportation = transportationStr != null ? Transportation.valueOf(transportationStr) : null;
                   Camp camp = campStr != null ? Camp.valueOf(campStr) : null;
                  
                   staff = new Staff(
                       id,
                       username,
                       password,
                       nameST,
                       surnameST,
                       ageST,
                       CFST,
                       accommodationST,
                       residenceST,
                       pointOfDeparture,
                       destination,
                       idTR,
                       transportation,
                       allergiesST,
                       medicalIssuesST,
                       phoneNumberST,
                       camp
                   );
               }
           }
       }catch (SQLException e) {
           e.printStackTrace();
       }
       return staff;
   }
   public boolean addStaff(Staff staff) {
       String insertPersona = "INSERT INTO PERSONA (username, pwd) VALUES (?, ?)";
       String insertStaff =
           "INSERT INTO STAFF (persona_id, CFST, nameST, surnameST, ageST, accommodationST, residenceST, " +
           "placeOfDeparture, destination, idTR, transportationST, allergiesST, " +
           "medicalIssuesST, phonenumberST, camp) " +
           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       try {
           connection.setAutoCommit(false);
           try (PreparedStatement pstmtPersona = connection.prepareStatement(insertPersona, Statement.RETURN_GENERATED_KEYS)) {
               pstmtPersona.setString(1, staff.getUsername());
               pstmtPersona.setString(2, staff.getPassword());
               pstmtPersona.executeUpdate();
               try (ResultSet generatedKeys = pstmtPersona.getGeneratedKeys()) {
                   if (generatedKeys.next()) {
                       int personaId = generatedKeys.getInt(1);
                       try (PreparedStatement pstmtStaff = connection.prepareStatement(insertStaff)) {
                           pstmtStaff.setInt(1, personaId);
                           pstmtStaff.setString(2, staff.getCFST());
                           pstmtStaff.setString(3, staff.getNameST());
                           pstmtStaff.setString(4, staff.getSurnameST());
                           pstmtStaff.setInt(5, staff.getageST());
                           pstmtStaff.setString(6, staff.getAccommodationST());
                           pstmtStaff.setString(7, staff.getResidenceST());
                           pstmtStaff.setString(8, staff.getPointOfDeparture() != null ? staff.getPointOfDeparture().name() : null);
                           pstmtStaff.setString(9, staff.getDestination());
                           pstmtStaff.setString(10, staff.getIdTR());
                           pstmtStaff.setString(11, staff.getTransportation() != null ? staff.getTransportation().name() : null);
                           pstmtStaff.setString(12, staff.getAllergiesST());
                           pstmtStaff.setString(13, staff.getMedicalIssuesST());
                           pstmtStaff.setString(14, staff.getPhoneNumberST());
                           pstmtStaff.setString(15, staff.getCamp() != null ? staff.getCamp().name() : null);
                           int rows = pstmtStaff.executeUpdate();
                           connection.commit();
                           return rows > 0;
                       }
                   } else {
                       connection.rollback();
                       return false;
                   }
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
           try {
               connection.rollback();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           return false;
       } finally {
           try {
               connection.setAutoCommit(true);
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
   }
   public boolean updateStaff(Staff staff) {
       String updateStaff =
           "UPDATE STAFF SET nameST = ?, surnameST = ?, ageST = ?, accommodationST = ?, residenceST = ?, " +
           "placeOfDeparture = ?, destination = ?, idTR = ?, transportationST = ?, " +
           "allergiesST = ?, medicalIssuesST = ?, phonenumberST = ?, camp = ? " +
           "WHERE persona_id = ? AND CFST = ?";
       String updatePersona = "UPDATE PERSONA SET pwd = ? WHERE id = ?";
       try {
           connection.setAutoCommit(false);
           try (PreparedStatement pstmtPersona = connection.prepareStatement(updatePersona)) {
               pstmtPersona.setString(1, staff.getPassword());
               pstmtPersona.setInt(2, staff.getid());
               pstmtPersona.executeUpdate();
           }
           try (PreparedStatement pstmtStaff = connection.prepareStatement(updateStaff)) {
               pstmtStaff.setString(1, staff.getNameST());
               pstmtStaff.setString(2, staff.getSurnameST());
               pstmtStaff.setInt(3, staff.getageST());
               pstmtStaff.setString(4, staff.getAccommodationST());
               pstmtStaff.setString(5, staff.getResidenceST());
               pstmtStaff.setString(6, staff.getPointOfDeparture() != null ? staff.getPointOfDeparture().name() : null);
               pstmtStaff.setString(7, staff.getDestination());
               pstmtStaff.setString(8, staff.getIdTR());
               pstmtStaff.setString(9, staff.getTransportation() != null ? staff.getTransportation().name() : null);
               pstmtStaff.setString(10, staff.getAllergiesST());
               pstmtStaff.setString(11, staff.getMedicalIssuesST());
               pstmtStaff.setString(12, staff.getPhoneNumberST());
               pstmtStaff.setString(13, staff.getCamp() != null ? staff.getCamp().name() : null);
               pstmtStaff.setInt(14, staff.getid());
               pstmtStaff.setString(15, staff.getCFST());
               int rows = pstmtStaff.executeUpdate();
               connection.commit();
               return rows > 0;
           }
       } catch (SQLException e) {
           e.printStackTrace();
           try {
               connection.rollback();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           return false;
       } finally {
           try {
               connection.setAutoCommit(true);
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
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
   
   String query = "UPDATE STAFF SET pastoPranzo = ?, pastoCena = ? " +
                  "WHERE CFST = ? AND persona_id = (SELECT id FROM PERSONA WHERE username = ?)";
   try (PreparedStatement pstmt = connection.prepareStatement(query)) {
       pstmt.setString(1, pastoPranzo);
       pstmt.setString(2, pastoCena);    
       pstmt.setString(3, CFST);         
       pstmt.setString(4, usernameST);  
       int rowsAffected = pstmt.executeUpdate();
       return rowsAffected > 0;  // Ritorna true se l'aggiornamento ha avuto successo
   } catch (SQLException e) {
       e.printStackTrace();  
       return false;
   }
   }
   
   public String[] getPastiStaff(String usernameST, String CFST) {
	    String query = "SELECT pastoPranzo, pastoCena " +
	                   "FROM STAFF " +
	                   "WHERE CFST = ? AND persona_id = (SELECT id FROM PERSONA WHERE username = ?)";

	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setString(1, CFST);
	        pstmt.setString(2, usernameST);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                String pranzo = rs.getString("pastoPranzo");
	                String cena = rs.getString("pastoCena");
	                return new String[] { pranzo, cena };
	            } else {
	                return null; // Nessun risultato trovato
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
