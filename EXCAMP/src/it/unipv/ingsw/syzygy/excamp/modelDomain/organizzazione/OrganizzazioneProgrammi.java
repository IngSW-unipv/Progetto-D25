package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.sql.SQLException;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.ProgrammaDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Camp;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;

public class OrganizzazioneProgrammi extends Organizzazione {
	
	private Map<Location, Map<Camp, ProgrammaGiornaliero>> programmiGiornalieriPerLocationCamp = new HashMap<>();
	private Map<Location, Map<Camp, ProgrammaAlternativo>> programmiAlternativiPerLocationCamp = new HashMap<>();
	
	private Map<String, ProgrammaGiornaliero> programmaGiornalieroPerData = new HashMap<>();
	private Map<String, ProgrammaAlternativo> programmaAlternativoPerData = new HashMap<>();
	
    private ProgrammaDAO programmaDAO;
	   
	public OrganizzazioneProgrammi(Location location, ProgrammaDAO programmaDAO) {
		   super(location);
	       this.programmaDAO = programmaDAO;
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
   
   @Override
   public void visualizzaMenu() {
   }
   
   @Override
   public void caricaProgramma() {
       try {
           // Carica i programmi giornalieri dal database
       List<ProgrammaGiornaliero> programmiGiornalieriList = programmaDAO.caricaProgrammiGiornalieri();
       for (ProgrammaGiornaliero programma : programmiGiornalieriList) {
           // Associa il programma alla Location e al Camp specifico
           Location location = programma.getLocation(); 
           Camp camp = programma.getCamp(); 
           // Crea la struttura se non esiste
           programmiGiornalieriPerLocationCamp.putIfAbsent(location, new HashMap<>());
           programmiGiornalieriPerLocationCamp.get(location).put(camp, programma);
       }
       // Carica i programmi alternativi dal database
       List<ProgrammaAlternativo> programmiAlternativiList = programmaDAO.caricaProgrammiAlternativi();
       for (ProgrammaAlternativo programma : programmiAlternativiList) {
           Location location = programma.getLocation();
           Camp camp = programma.getCamp();
           // Crea la struttura se non esiste
           programmiAlternativiPerLocationCamp.putIfAbsent(location, new HashMap<>());
           programmiAlternativiPerLocationCamp.get(location).put(camp, programma);
       }
       System.out.println("Programmi caricati correttamente.");
   } catch (SQLException e) {
       e.printStackTrace();
       System.out.println("Errore durante il caricamento dei programmi dal database.");
       }
   }
   
   @Override
   public void salvaProgramma() {
       try {
       // Salva i programmi giornalieri nel database
	   for (Map<Camp, ProgrammaGiornaliero> locationCampMap : programmiGiornalieriPerLocationCamp.values()) {
		    for (ProgrammaGiornaliero programma : locationCampMap.values()) {
		        programmaDAO.salvaProgrammaGiornaliero(programma);
		    }
		}
       // Salva i programmi alternativi nel database
       for (Map<Camp, ProgrammaAlternativo> locationCampMap : programmiAlternativiPerLocationCamp.values()) {
           for (ProgrammaAlternativo programma : locationCampMap.values()) {
               programmaDAO.salvaProgrammaAlternativo(programma);
           }
       }
       System.out.println("Programmi salvati correttamente nel database.");
   } catch (SQLException e) {
       e.printStackTrace();
       System.out.println("Errore durante il salvataggio dei programmi nel database.");
       }
   }
   
   @Override
   public void visualizzaProgramma() {
       // Visualizza i programmi in base alla Location e al Camp selezionati
       for (Map<Camp, ProgrammaGiornaliero> locationCampMap : programmiGiornalieriPerLocationCamp.values()) {
           for (ProgrammaGiornaliero programma : locationCampMap.values()) {
               System.out.println(programma);
           }
       }
       for (Map<Camp, ProgrammaAlternativo> locationCampMap : programmiAlternativiPerLocationCamp.values()) {
           for (ProgrammaAlternativo programma : locationCampMap.values()) {
               System.out.println(programma);
           }
       }
   }
   
   public void aggiungiProgrammaGiornaliero(String data, ProgrammaGiornaliero programma) {
	    programmaGiornalieroPerData.put(data, programma);
   }

   public ProgrammaGiornaliero getProgrammaGiornaliero(String data) {
	    return programmaGiornalieroPerData.get(data);
   }

   public void aggiungiProgrammaAlternativo(String data, ProgrammaAlternativo programma) {
	    programmaAlternativoPerData.put(data, programma);
   }

   public ProgrammaAlternativo getProgrammaAlternativo(String data) {
	    return programmaAlternativoPerData.get(data);
   }
   
}

