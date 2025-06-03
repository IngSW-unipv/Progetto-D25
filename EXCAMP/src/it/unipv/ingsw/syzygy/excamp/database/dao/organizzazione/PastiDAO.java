package it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PastiDAO {
   private Connection connection;

   public PastiDAO(Connection connection) {
       this.connection = connection;
   }
   // Metodo per recuperare tutti i pasti dalla tabella PASTI
   public List<String> getAllPasti() {
       List<String> pasti = new ArrayList<>();
       String query = "SELECT pasti FROM PASTI";
       try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
           while (rs.next()) {
               pasti.add(rs.getString("pasti"));
           }
       } catch (SQLException e) {
           e.printStackTrace();  
       }
       return pasti;
   }
}

