package it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione;

import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Trasporto;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Departure;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Transportation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrasportoDAO {
	
    private Connection connection;

    public TrasportoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Trasporto> getAllTrasporti() throws SQLException {
        List<Trasporto> trasporti = new ArrayList<>();
        String query = "SELECT * FROM TRASPORTI";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Trasporto trasporto = new Trasporto(
                    rs.getString("idTR"),
                    Transportation.valueOf(rs.getString("modalitaTR")),
                    rs.getDate("dateTR"),
                    rs.getTime("timeTR"),
                    Departure.valueOf(rs.getString("placeOfDeparture")),
                    Location.valueOf(rs.getString("destination")),
                    rs.getTime("ETA"),
                    rs.getInt("persona_id")
                );
                trasporti.add(trasporto);
            }
        }
        return trasporti;
    }

    public void addTrasporto(Trasporto trasporto) throws SQLException {
        String query = "INSERT INTO TRASPORTI (idTR, dateTR, timeTR, placeOfDeparture, modalitaTR, ETA, persona_id, destination) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, trasporto.getIdTR());
            ps.setDate(2, trasporto.getDateTR());
            ps.setTime(3, trasporto.getTimeTR());
            ps.setString(4, trasporto.getPlaceOfDeparture().name());
            ps.setString(5, trasporto.getModalitaTR().name());
            ps.setTime(6, trasporto.getEta());
            ps.setInt(7, trasporto.getPersonaId());
            ps.setString(8, trasporto.getDestination().name());
            ps.executeUpdate();
        }
    }

    public Trasporto getTrasportoByPersonaId(int personaId) throws SQLException {
    String query = "SELECT * FROM TRASPORTI WHERE persona_id = ?";
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, personaId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return new Trasporto(
	                    rs.getString("idTR"),
	                    Transportation.valueOf(rs.getString("modalitaTR")),
	                    rs.getDate("dateTR"),
	                    rs.getTime("timeTR"),
	                    Departure.valueOf(rs.getString("placeOfDeparture")),
	                    Location.valueOf(rs.getString("destination")),
	                    rs.getTime("ETA"),
	                    rs.getInt("persona_id") 
	                );
	            }
	         }
	     }
	     return null;
     }

}

