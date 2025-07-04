package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelController.organizzazione.MenuController;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;

public abstract class Organizzazione {
	
   private Location location;
   
   public Organizzazione(Location location) {
       this.location = location;
   }
   
   public Location getLocation() {
       return location;
   }
   
   public void setLocation(Location location) {
       this.location = location;
   }
   
   // Metodi astratti che devono essere implementati nelle sottoclassi
   
   // Metodi generici sui programmi
   public abstract void caricaProgramma(); 
   public abstract void salvaProgramma();  
   public abstract void visualizzaProgramma(); 
   
   // Metodi generici sugli autisti
   public abstract boolean aggiungiAutista(Autista autista); 
   public abstract void rimuoviAutista(Autista autista);  
   
   // Metodi relativi agli alloggi
   public abstract void aggiungiAlloggioPartecipante(Albergo alloggio);
   public abstract void aggiungiAlloggioStaff(Albergo alloggio);
   public abstract void visualizzaAlloggiPartecipanti();
   public abstract void visualizzaAlloggiStaff();
   public abstract void visualizzaAlloggioUtente(String CFPA);
   
   // Metodi relativi ai trasporti
   public abstract void aggiungiTrasporto();
   public abstract List<Trasporto> getAllTrasporti();
   public abstract Trasporto getTrasportoByPersonaId(int personaId);
   
   // Metodi relativi al menu
   public void visualizzaMenu() {
   } 
   public void scegliMenu() {
   }	
   public void scegliMenu(MenuController controller, int personaId, String CFPA, String username, String CF) {
   }
   
}
