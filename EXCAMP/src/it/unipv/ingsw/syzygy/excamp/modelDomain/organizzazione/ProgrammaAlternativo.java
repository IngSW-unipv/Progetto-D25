package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import java.util.List;

public class ProgrammaAlternativo {
	
   private String data;
   private List<Attività> attivita;
   private Location location; // Aggiungi location
   private Camp camp; // Aggiungi camp
   
   public ProgrammaAlternativo(String data, List<Attività> attivita, Location location, Camp camp) {
       this.data = data;
       this.attivita = attivita;
       this.location = location;
       this.camp = camp;
   }
   
   public String getData() { return data; }
   public List<Attività> getAttivita() { return attivita; }
   public Location getLocation() { return location; }
   public Camp getCamp() { return camp; }
  
   public void aggiungiAttivita(Attività attivita) {
       this.attivita.add(attivita);
   }
   
   @Override
   public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("Programma alternativo ").append(data).append(" a ").append(location).append(" per il camp: ").append(camp).append("\n");
       for (Attività att : attivita) {
           sb.append(att.toString()).append("\n");
       }
       return sb.toString();
   }
   
}

