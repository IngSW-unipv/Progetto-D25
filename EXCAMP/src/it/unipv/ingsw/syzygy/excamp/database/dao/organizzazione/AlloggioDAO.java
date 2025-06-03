package it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Albergo;

public class AlloggioDAO {
	
   private Connection connection;
   
   public AlloggioDAO(Connection connection) {
       this.connection = connection;
   }
   
   public void salvaAlloggioPartecipante(Albergo alloggio) throws SQLException {
       String query = "INSERT INTO ALLOGGIOPA (CFPA, namePA, surnamePA, location, camp, hotel, room) VALUES (?, ?, ?, ?, ?, ?, ?)";
       try (PreparedStatement statement = connection.prepareStatement(query)) {
           statement.setString(1, alloggio.getCf());
           statement.setString(2, alloggio.getNome());
           statement.setString(3, alloggio.getCognome());
           statement.setString(4, alloggio.getHotel());
           statement.setString(5, alloggio.getLocation());
           statement.setString(6, alloggio.getCamp());
           statement.setInt(7, alloggio.getRoom());
           statement.executeUpdate();
       }
   }
   
   public void salvaAlloggioStaff(Albergo alloggio) throws SQLException {
       String query = "INSERT INTO ALLOGGIOST (CFST, nameST, surnameST, location, camp, hotel, room) VALUES (?, ?, ?, ?, ?, ?, ?)";
       try (PreparedStatement statement = connection.prepareStatement(query)) {
           statement.setString(1, alloggio.getCf());
           statement.setString(2, alloggio.getNome());
           statement.setString(3, alloggio.getCognome());
           statement.setString(4, alloggio.getHotel());
           statement.setString(5, alloggio.getLocation());
           statement.setString(6, alloggio.getCamp());
           statement.setInt(7, alloggio.getRoom());
           statement.executeUpdate();
       }
   }
   
   public List<Albergo> caricaAlloggiPartecipanti() throws SQLException {
       List<Albergo> alloggi = new ArrayList<>();
       String query = "SELECT * FROM ALLOGGIOPA";
       try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
           while (resultSet.next()) {
               String cf = resultSet.getString("CFPA");
               String nome = resultSet.getString("namePA");
               String cognome = resultSet.getString("surnamePA");
               String hotel = resultSet.getString("hotel");
               String location = resultSet.getString("location");
               String camp = resultSet.getString("camp");
               int room = resultSet.getInt("room");
               alloggi.add(new Albergo(cf, nome, cognome, hotel, location, camp, room));
           }
       }
       return alloggi;
   }
   
   public List<Albergo> caricaAlloggiStaff() throws SQLException {
       List<Albergo> alloggi = new ArrayList<>();
       String query = "SELECT * FROM ALLOGGIOST";
       try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
               String cf = resultSet.getString("CFST");
               String nome = resultSet.getString("nameST");
               String cognome = resultSet.getString("surnameST");
               String hotel = resultSet.getString("hotel");
               String location = resultSet.getString("location");
               String camp = resultSet.getString("camp");
               int room = resultSet.getInt("room");
               alloggi.add(new Albergo(cf, nome, cognome, hotel, location, camp, room));
           }
       }
       return alloggi;
   }
   
}

