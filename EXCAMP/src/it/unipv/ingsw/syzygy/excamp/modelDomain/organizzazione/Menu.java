package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.util.List;

import it.unipv.ingsw.syzygy.excamp.exceptions.MissingMenuChoiceException;
import it.unipv.ingsw.syzygy.excamp.exceptions.SafeMenu;
import it.unipv.ingsw.syzygy.excamp.modelController.organizzazione.MenuController;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;

  public class Menu extends Organizzazione {
   String colazione = "BUFFET";
   String pranzo;
   String cena;
   
   public Menu(String pranzo, String cena, Location location) {
	   super (location);
       this.pranzo = pranzo;
       this.cena = cena;
   }
   
   @Override
   public void visualizzaMenu() {
       System.out.println("Colazione: " + colazione);
       System.out.println("Pranzo: " + pranzo);
       System.out.println("Cena: " + cena);
   }
  
   @Override
   public void scegliMenu(MenuController controller, int personaId, String CFPA, String username, String CF) {
	    try {
	        Menu nuovoMenu = controller.creaMenuIniziale(personaId, CFPA, username, CF);
	        this.pranzo = nuovoMenu.pranzo;
	        this.cena = nuovoMenu.cena;
	        System.out.println("Menu scelto con successo!");
	    } catch (SafeMenu e) {
	        System.out.println("Menu alternativo necessario per motivi di allergie.");
	    } catch (MissingMenuChoiceException e) {
	        System.out.println("Errore: scelta del menu non valida.");
	    }
	}

   
   @Override
   public void caricaProgramma() {
   }
   @Override
   public void salvaProgramma() {
   }
   @Override
   public void visualizzaProgramma() {
   }
   @Override
   public boolean aggiungiAutista(Autista autista) {
       return false;
   }
   @Override
   public void rimuoviAutista(Autista autista) {
   }
   @Override
   public void aggiungiAlloggioPartecipante(Albergo alloggio) {
   }
   @Override
   public void aggiungiAlloggioStaff(Albergo alloggio) {
   }
   @Override
   public void visualizzaAlloggiPartecipanti() {
   }
   @Override
   public void visualizzaAlloggiStaff() {
   }
   @Override
   public void visualizzaAlloggioUtente(String CFPA) {
   }
   @Override
   public void aggiungiTrasporto() {
   }
   
   @Override
   public List<Trasporto> getAllTrasporti() {
		return null;
	}
	@Override
	public Trasporto getTrasportoByPersonaId(int personaId) {
		return null;
	}
}
