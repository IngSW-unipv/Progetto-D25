package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;

public class OrganizzazioneAlloggi extends Organizzazione {
	
   private Alloggi alloggi;
   
   public OrganizzazioneAlloggi(Location location) {
       super(location);
       this.alloggi = new Alloggi();
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
   public void visualizzaAutisti() {
   }
   
   @Override
   public void aggiungiTrasporto() {
   }
   
   @Override
   public void visualizzaMenu() {
   }
   
   @Override
   public void aggiungiAlloggioPartecipante(Albergo alloggio) {
       alloggi.aggiungiAlloggioPartecipante(alloggio);
   }
   
   @Override
   public void aggiungiAlloggioStaff(Albergo alloggio) {
       alloggi.aggiungiAlloggioStaff(alloggio);
   }
   
   @Override
   public void visualizzaAlloggiPartecipanti() {
	    alloggi.visualizzaAlloggiPartecipanti();
   }
   
   @Override
   public void visualizzaAlloggiStaff() {
       alloggi.visualizzaAlloggiStaff();
   }
   
   @Override
   public void visualizzaAlloggioUtente(String CFPA) {
       alloggi.visualizzaAlloggioUtente(CFPA);
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
