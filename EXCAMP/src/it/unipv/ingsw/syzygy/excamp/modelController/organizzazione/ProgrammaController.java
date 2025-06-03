package it.unipv.ingsw.syzygy.excamp.modelController.organizzazione;

import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Camp;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Attività;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaAlternativo;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaGiornaliero;
import it.unipv.ingsw.syzygy.excamp.modelDomain.user.Amministratore;

public class ProgrammaController {
	
   private Amministratore amministratore;
   
   public ProgrammaController(Amministratore amministratore) {
       this.amministratore = amministratore;
   }
   
   public void creaProgramma(String data, List<Attività> attivita, Location location, Camp camp) {
       ProgrammaGiornaliero programma = new ProgrammaGiornaliero(data, attivita, location, camp);
       amministratore.pubblicaProgramma(programma);
   }
   
   public void creaProgrammaAlternativo(String data, List<Attività> attivita, Location location, Camp camp) {
       if (attivita == null || attivita.isEmpty()) {
           System.out.println("Attività non valide per il programma alternativo.");
           return;
       }
       ProgrammaAlternativo programma = new ProgrammaAlternativo(data, attivita, location, camp);
       amministratore.pubblicaProgrammaAlternativo(programma);
   }
   
   public void visualizzaProgramma(String data) {
       amministratore.visualizzaProgramma(data);
   }
   
   public void visualizzaProgrammaAlternativo(String data) {
       amministratore.visualizzaProgrammaAlternativo(data);
   }
   
}

