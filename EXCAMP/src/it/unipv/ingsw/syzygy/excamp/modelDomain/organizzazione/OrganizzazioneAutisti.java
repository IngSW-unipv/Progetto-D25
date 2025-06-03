package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;

public abstract class OrganizzazioneAutisti extends Organizzazione {
	
   private ListaContattiAutisti listaContattiAutisti;
   private static final int MIN_AUTISTI = 5; // Quantità minima di autisti
   
   public OrganizzazioneAutisti(Location location) {
       super(location);  // Chiamata al costruttore della classe genitore
       this.listaContattiAutisti = new ListaContattiAutisti();
   }
   
   public ListaContattiAutisti getListaContattiAutisti() {
       return listaContattiAutisti;
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
   public void visualizzaMenu() {
   }
   
   // Aggiungi autista con controllo del numero minimo di autisti
   @Override
   public boolean aggiungiAutista(Autista autista) {
       if (autista == null) {
           System.out.println("Errore: L'autista passato è null.");
           return false;
       }

       if (listaContattiAutisti.getAutisti().size() >= MIN_AUTISTI) {
           listaContattiAutisti.aggiungiAutista(autista);
           return true;
       } else {
           System.out.println("Errore: Devi avere almeno " + MIN_AUTISTI + " autisti per continuare.");
           return false;
       }
   }
   
   // Rimuovi autista  
   public void rimuoviAutista(Autista autista) {
	    if (autista != null) {
	        listaContattiAutisti.rimuoviAutista(autista);
	    } else {
	        System.out.println("Errore: L'autista passato è null.");
	    }
	}
	
   @Override
   public List<Trasporto> getAllTrasporti() {
		return null;
   }
	
   @Override
   public Trasporto getTrasportoByPersonaId(int personaId){
		return null;
   }
	
}

