package it.unipv.ingsw.syzygy.excamp.modelController.organizzazione;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JTextArea;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.AlloggioDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Albergo;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAlloggi;

public class AlloggiController {
	
   private JTextArea view;
   private OrganizzazioneAlloggi organizzazioneAlloggi;
   private AlloggioDAO alloggioDAO;
   
   public AlloggiController(OrganizzazioneAlloggi organizzazioneAlloggi, AlloggioDAO alloggioDAO) {
       this.organizzazioneAlloggi = organizzazioneAlloggi;
       this.alloggioDAO = alloggioDAO;
   }
   
   public void aggiungiAlloggioPartecipante(String CFPA, String nomePA, String cognomePA, String hotel, String location, String camp, int room) {
       Albergo alloggio = new Albergo(CFPA, nomePA, cognomePA, hotel, location, camp, room);
       organizzazioneAlloggi.aggiungiAlloggioPartecipante(alloggio);
       try {
           alloggioDAO.salvaAlloggioPartecipante(alloggio);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   public void aggiungiAlloggioStaff(String CFST, String nomeST, String cognomeST, String hotel, String location, String camp, int room) {
       Albergo alloggio = new Albergo(CFST, nomeST, cognomeST, hotel, location, camp, room);
       organizzazioneAlloggi.aggiungiAlloggioStaff(alloggio);
       try {
           alloggioDAO.salvaAlloggioStaff(alloggio);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   public void visualizzaAlloggiPartecipanti() {
       try {
           List<Albergo> alloggi = alloggioDAO.caricaAlloggiPartecipanti();
			StringBuilder sb = new StringBuilder();
			for (Albergo alloggio : alloggi) {
			sb.append("Nome: ").append(alloggio.getNome())
			   .append(" Cognome: ").append(alloggio.getCognome())
			   .append(" Hotel: ").append(alloggio.getHotel())
			   .append(" Stanza: ").append(alloggio.getRoom())
			   .append("\n");
			} view.setText(sb.toString());
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   public void visualizzaAlloggiStaff() {
       try {
           List<Albergo> alloggi = alloggioDAO.caricaAlloggiStaff();
		StringBuilder sb = new StringBuilder();
		for (Albergo alloggio : alloggi) {
		sb.append("Nome: ").append(alloggio.getNome())
		   .append(" Cognome: ").append(alloggio.getCognome())
		   .append(" Hotel: ").append(alloggio.getHotel())
		   .append(" Stanza: ").append(alloggio.getRoom())
		   .append("\n");
		} view.setText(sb.toString());
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   public void visualizzaAlloggioUtente(String CFPA) {
       try {
           List<Albergo> alloggi = alloggioDAO.caricaAlloggiPartecipanti();
           for (Albergo alloggio : alloggi) {
               if (alloggio.getCf().equals(CFPA)) {
                   String result = "Alloggio per il figlio: " +
                           "Nome: " + alloggio.getNome() +
                           " Cognome: " + alloggio.getCognome() +
                           " Hotel: " + alloggio.getHotel() +
                           " Stanza: " + alloggio.getRoom();
                   // Aggiungere aggiornamento nella vista
                   view.setText(result);
                   return;
               }
           }
           view.setText("Alloggio non trovato per il CF: " + CFPA);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
}

