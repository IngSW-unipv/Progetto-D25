package it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione;

import java.sql.*;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Autista;

public class AutistaDAO {
	
   private Connection connection;
   
   public AutistaDAO(Connection connection) {
       this.connection = connection;
   }
   
   public void salvaAutista(Autista autista) throws SQLException {
       String query = "INSERT INTO AUTISTI (nameAU, surnameAU, phoneNumberAU, location) VALUES (?, ?, ?, ?)";
       try (PreparedStatement statement = connection.prepareStatement(query)) {
       statement.setString(1, autista.getNameAU());
       statement.setString(2, autista.getSurnameAU());
       statement.setInt(3, autista.getPhoneNumberAU());  
       statement.setString(4, autista.getLocation().name());  
       statement.executeUpdate();
        }
   }
   
   public List<Autista> caricaAutisti() throws SQLException {
       List<Autista> autisti = new ArrayList<>();
       String query = "SELECT * FROM AUTISTI";
       try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
               String nameAU = resultSet.getString("nameAU");
               String surnameAU = resultSet.getString("surnameAU");
               String phoneNumberAUString = resultSet.getString("phoneNumberAU");
               int phoneNumberAU = Integer.parseInt(phoneNumberAUString);
               String locationString = resultSet.getString("location");
               Location location = Location.valueOf(locationString); 
               autisti.add(new Autista(nameAU, surnameAU, phoneNumberAU, location));
           }
       }
       return autisti;
   }
   
}



