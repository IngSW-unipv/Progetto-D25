package it.unipv.ingsw.syzygy.excamp.modelController.organizzazione;

import java.sql.SQLException;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.AutistaDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Autista;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAutisti;

public class AutistaController {
	
   private OrganizzazioneAutisti organizzazioneAutisti;
   private AutistaDAO autistaDAO;
   
   public AutistaController(OrganizzazioneAutisti organizzazioneAutisti, AutistaDAO autistaDAO) {
       this.organizzazioneAutisti = organizzazioneAutisti;
       this.autistaDAO = autistaDAO;
   }
   
   // Aggiungi autista con validazione
   public boolean aggiungiAutista(String nameAU, String surnameAU, String phoneNumberAU, String location) {
       if (!isPhoneNumberValid(phoneNumberAU)) {
           System.out.println("Errore: Numero di telefono non valido.");
           return false;
       }
       if (!isLocationValid(location)) {
           System.out.println("Errore: Location non valida.");
           return false;
       }
       Autista autista = new Autista(nameAU, surnameAU, Integer.parseInt(phoneNumberAU), Location.valueOf(location));
       return organizzazioneAutisti.aggiungiAutista(autista);
   }
   
   // Validazione del numero di telefono (deve essere numerico e avere 10 cifre)
   private boolean isPhoneNumberValid(String phoneNumber) {
       return phoneNumber.matches("\\d{10}");
   }
   
   // Validazione della location (deve essere una delle località valide)
   private boolean isLocationValid(String location) {
       try {
           Location.valueOf(location); 
           return true;
       } catch (IllegalArgumentException e) {
           return false;
       }
   }
   
   // Visualizza la lista degli autisti dal database
   public void visualizzaListaAutisti() {
       try {
           List<Autista> autisti = autistaDAO.caricaAutisti();
           for (Autista autista : autisti) {
               System.out.println("Nome: " + autista.getNameAU() + " Cognome: " + autista.getSurnameAU() + " Tel: " + autista.getPhoneNumberAU() + " Location: " + autista.getLocation());
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
}


