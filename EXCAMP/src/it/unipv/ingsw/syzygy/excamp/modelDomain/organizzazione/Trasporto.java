package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.TrasportoDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;

public class Trasporto extends Organizzazione {
   
	private String idTR;
    private Transportation modalitaTR;
    private Date dateTR;
    private Time timeTR;
    private Departure placeOfDeparture;
    private Location destination;
    private Time eta;
    private int personaId; 
    private TrasportoDAO trasportoDAO;

    public Trasporto(String idTR, Transportation modalitaTR, Date dateTR, Time timeTR,
            Departure placeOfDeparture, Location destination, Time eta, int personaId) {
		super(destination);
		this.idTR = idTR;
		this.modalitaTR = modalitaTR;
		this.dateTR = dateTR;
		this.timeTR = timeTR;
		this.placeOfDeparture = placeOfDeparture;
		this.destination = destination;
		this.eta = eta;
		this.personaId = personaId;
	}
  
    @Override
    public void caricaProgramma() {}

    @Override
    public void salvaProgramma() {}

    @Override
    public void visualizzaProgramma() {}

    @Override
    public boolean aggiungiAutista(Autista autista) {
        return false;
    }

    @Override
    public void rimuoviAutista(Autista autista) {}

    @Override
    public void aggiungiAlloggioPartecipante(Albergo alloggio) {}

    @Override
    public void aggiungiAlloggioStaff(Albergo alloggio) {}

    @Override
    public void visualizzaAlloggiPartecipanti() {}

    @Override
    public void visualizzaAlloggiStaff() {}

    @Override
    public void visualizzaAlloggioUtente(String CFPA) {}

    @Override
    public void visualizzaMenu() {}

    @Override
    public void aggiungiTrasporto() {
        Trasporto trasporto = new Trasporto(idTR, modalitaTR, dateTR, timeTR, placeOfDeparture, destination, eta, personaId); 
        try {
            trasportoDAO.addTrasporto(trasporto); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trasporto> getAllTrasporti() {
        try {
            return trasportoDAO.getAllTrasporti();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Trasporto getTrasportoByPersonaId(int personaId) {
        try {
        	return trasportoDAO.getTrasportoByPersonaId(personaId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIdTR() { return idTR; }
    public void setIdTR(String idTR) { this.idTR = idTR; }

    public Transportation getModalitaTR() { return modalitaTR; }
    public void setModalitaTR(Transportation modalitaTR) { this.modalitaTR = modalitaTR; }

    public Date getDateTR() { return dateTR; }
    public void setDateTR(Date dateTR) { this.dateTR = dateTR; }

    public Time getTimeTR() { return timeTR; }
    public void setTimeTR(Time timeTR) { this.timeTR = timeTR; }

    public Departure getPlaceOfDeparture() { return placeOfDeparture; }
    public void setPlaceOfDeparture(Departure placeOfDeparture) { this.placeOfDeparture = placeOfDeparture; }

    public Location getDestination() { return destination; }
    public void setDestination(Location destination) { this.destination = destination; }

    public Time getEta() { return eta; }
    public void setEta(Time eta) { this.eta = eta; }

    public int getPersonaId() { return personaId; }
    public void setPersonaId(int personaId) { this.personaId = personaId; }
}


