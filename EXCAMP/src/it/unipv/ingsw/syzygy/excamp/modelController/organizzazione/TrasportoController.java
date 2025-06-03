package it.unipv.ingsw.syzygy.excamp.modelController.organizzazione;

import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.TrasportoDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Trasporto;
import it.unipv.ingsw.syzygy.excamp.modelView.organizzazione.TrasportoView;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.util.List;

public class TrasportoController {
	
    private TrasportoDAO trasportoDAO;
    private TrasportoView view;

    public TrasportoController(TrasportoDAO trasportoDAO, TrasportoView view) {
        this.trasportoDAO = trasportoDAO;
        this.view = view;
        this.view.showButtonListener(e -> mostraTrasporti());
    }
    
    // Metodo per aggiungere un trasporto
    public boolean aggiungiTrasporto(String idTR, Transportation modalitaTR, Date dateTR, Time timeTR,
            Departure placeOfDeparture, Location destination, Time eta, int personaId) {

		if (idTR == null || idTR.isEmpty()) {
		throw new IllegalArgumentException("ID Trasporto non può essere vuoto.");
		}
		if (modalitaTR == null) {
		throw new IllegalArgumentException("Modalità di trasporto non selezionata.");
		}
		if (dateTR == null) {
		throw new IllegalArgumentException("Data del trasporto non può essere vuota.");
		}
		if (timeTR == null) {
		throw new IllegalArgumentException("Orario del trasporto non può essere vuoto.");
		}
		if (placeOfDeparture == null) {
		throw new IllegalArgumentException("Luogo di partenza non selezionato.");
		}
		if (destination == null) {
		throw new IllegalArgumentException("Destinazione non selezionata.");
		}
		if (eta == null) {
		throw new IllegalArgumentException("ETA non può essere vuota.");
		}

		// Crea il nuovo oggetto Trasporto includendo personaId
		Trasporto trasporto = new Trasporto(idTR, modalitaTR, dateTR, timeTR, placeOfDeparture, destination, eta, personaId);
		try {
		trasportoDAO.addTrasporto(trasporto);  // DAO aggiornato per includere persona_id
		return true;
		} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Errore durante l'aggiunta del trasporto.", e);
		}
		
    }

   // Ottieni tutti i trasporti
   public List<Trasporto> getTrasporti() {
       try {
           return trasportoDAO.getAllTrasporti();
       } catch (SQLException e) {
           e.printStackTrace();
           view.updateTextArea("Errore nel recupero dei trasporti: " + e.getMessage());
           return null;
       }
   }
   
   private void mostraTrasporti() {
       List<Trasporto> trasporti = getTrasporti();
       if (trasporti == null || trasporti.isEmpty()) {
           view.updateTextArea("Nessun trasporto trovato.");
           return;
       }
       StringBuilder sb = new StringBuilder();
       for (Trasporto t : trasporti) {
           sb.append(formatTrasporto(t)).append("\n");
       }
       view.updateTextArea(sb.toString());
   }
   
   private String formatTrasporto(Trasporto t) {
       return String.format("ID: %s | Modalità: %s | Partenza: %s | Destinazione: %s | Data: %s | Orario: %s | ETA: %s",
               t.getIdTR(),
               t.getModalitaTR(),
               t.getPlaceOfDeparture(),
               t.getDestination(),
               t.getDateTR(),
               t.getTimeTR(),
               t.getEta());
   }

   public Trasporto getTrasportoByPersonaId(int personaId) {
	    try {
	        return trasportoDAO.getTrasportoByPersonaId(personaId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}

