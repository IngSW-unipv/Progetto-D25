package it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Camp;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Attività;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaAlternativo;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaGiornaliero;

public class ProgrammaDAO {

   private Connection connection;
   
   public ProgrammaDAO(Connection connection) {
       this.connection = connection;
   }
   
   public void salvaProgrammaGiornaliero(ProgrammaGiornaliero programma) throws SQLException {
       String query = "INSERT INTO programma_giornaliero (data, nome, descrizione, orario, luogo) VALUES (?, ?, ?, ?, ?)";
       try (PreparedStatement ps = connection.prepareStatement(query)) {
           for (Attività att : programma.getAttivita()) {
               ps.setString(1, programma.getData());
               ps.setString(2, att.getNome());
               ps.setString(3, att.getDescrizione());
               ps.setString(4, att.getOrario());
               ps.setString(5, att.getLuogo());
               ps.executeUpdate();
           }
       }
   }
   
   public List<ProgrammaGiornaliero> caricaProgrammiGiornalieri() throws SQLException {
       List<ProgrammaGiornaliero> programmi = new ArrayList<>();
       String query = "SELECT * FROM programma_giornaliero";
       try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
           while (rs.next()) {
               List<Attività> attivita = new ArrayList<>();
               
               String locationString = rs.getString("luogo"); // Supponiamo che "luogo" sia il nome della colonna
               String campString = rs.getString("camp");     // Supponiamo che "camp" sia il nome della colonna

               // Converti i valori di stringa in enum
               Location location = Location.valueOf(locationString.toUpperCase()); // Assicurati che i valori siano maiuscoli
               Camp camp = Camp.valueOf(campString.toUpperCase());
               
               Attività attivita1 = new Attività(rs.getString("nome"), rs.getString("descrizione"), rs.getString("orario"), rs.getString("luogo"));
               attivita.add(attivita1);
               ProgrammaGiornaliero programma = new ProgrammaGiornaliero(rs.getString("data"), attivita, location, camp);
               programmi.add(programma);
           }
       }
       return programmi;
   }
   
   public void salvaProgrammaAlternativo(ProgrammaAlternativo programma) throws SQLException {
       String query = "INSERT INTO programma_alternativo (data, nome, descrizione, orario, luogo) VALUES (?, ?, ?, ?, ?)";
       try (PreparedStatement ps = connection.prepareStatement(query)) {
           for (Attività att : programma.getAttivita()) {
               ps.setString(1, programma.getData());
               ps.setString(2, att.getNome());
               ps.setString(3, att.getDescrizione());
               ps.setString(4, att.getOrario());
               ps.setString(5, att.getLuogo());
               ps.executeUpdate();
           }
       }
   }
   
   public List<ProgrammaAlternativo> caricaProgrammiAlternativi() throws SQLException {
       List<ProgrammaAlternativo> programmi = new ArrayList<>();
       String query = "SELECT * FROM programma_alternativo";
       try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
           while (rs.next()) {
               List<Attività> attivita = new ArrayList<>();
               
               String locationString = rs.getString("luogo"); 
               String campString = rs.getString("camp");    

             
               Location location = Location.valueOf(locationString.toUpperCase()); 
               Camp camp = Camp.valueOf(campString.toUpperCase());
               
               Attività attivita1 = new Attività(rs.getString("nome"), rs.getString("descrizione"), rs.getString("orario"), rs.getString("luogo"));
               attivita.add(attivita1);
               ProgrammaAlternativo programma = new ProgrammaAlternativo(rs.getString("data"), attivita, location, camp);
               programmi.add(programma);
           }
       }
       return programmi;
   }
   
}
