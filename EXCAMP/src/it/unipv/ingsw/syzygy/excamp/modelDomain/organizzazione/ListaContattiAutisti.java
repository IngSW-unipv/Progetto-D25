package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.util.ArrayList;
import java.util.List;

public class ListaContattiAutisti {
	
   private List<Autista> autisti;
   
   public ListaContattiAutisti() {
       this.autisti = new ArrayList<>();
   }
   
   public void aggiungiAutista(Autista autista) {
       autisti.add(autista);
   }
   
   public void rimuoviAutista(Autista autista) {
       autisti.remove(autista);
   }
   
   public List<Autista> getAutisti() {
       return autisti;
   }
   
}
