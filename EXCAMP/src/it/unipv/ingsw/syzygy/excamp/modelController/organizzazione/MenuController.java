package it.unipv.ingsw.syzygy.excamp.modelController.organizzazione;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import it.unipv.ingsw.syzygy.excamp.database.dao.UtenteDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.PastiDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.StaffDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.MissingMenuChoiceException;
import it.unipv.ingsw.syzygy.excamp.exceptions.SafeMenu;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Menu;
import it.unipv.ingsw.syzygy.excamp.modelDomain.user.Staff;
import it.unipv.ingsw.syzygy.excamp.modelDomain.user.Utente;
public class MenuController {
   private UtenteDAO utenteDAO;
   public PastiDAO pastiDAO;
   private StaffDAO staffDAO;
   private List<String> pranzoOpzioniStaff;  
   private List<String> cenaOpzioniStaff;    
   
   
   public MenuController(UtenteDAO utenteDAO, PastiDAO pastiDAO, StaffDAO staffDAO) {
       this.utenteDAO = utenteDAO;
       this.pastiDAO = pastiDAO;
       this.staffDAO = staffDAO;
   }
   // Metodo per creare un menù iniziale uguale per tutti gli utenti e lo staff
   public Menu creaMenuIniziale(int personaId, String CFPA, String username, String CF) throws SafeMenu, MissingMenuChoiceException {
	    // Recupera tutti i pasti dal database
	    List<String> pasti = pastiDAO.getAllPasti();

	    // Se non ci sono pasti disponibili, ritorna un menu predefinito
	    if (pasti.isEmpty()) {
	        Location location = null;
	        return new Menu("Pasto predefinito pranzo", "Pasto predefinito cena", location);
	    }

	    String pranzo = pasti.get(0);
	    String cena = pasti.size() > 1 ? pasti.get(1) : pasti.get(0);

	    // Verifica se è un utente
	    if (isUtente(personaId, CFPA)) {
	        List<String> allergie = ottieniAllergie(personaId, CFPA);

	        if (hasAllergyInMenu(pranzo, allergie)) {
	            pranzo = "MENU ALTERNATIVO";
	        }

	        if (hasAllergyInMenu(cena, allergie)) {
	            cena = "MENU ALTERNATIVO";
	        }

	        if ("MENU ALTERNATIVO".equalsIgnoreCase(pranzo) || "MENU ALTERNATIVO".equalsIgnoreCase(cena)) {
	            throw new SafeMenu();
	        }try {
	            salvaScelteUtente(username, pranzo, cena);
	            System.out.println("Scelte menu utente salvate nel database.");
	        } catch (SQLException e) {
	            System.out.println("Errore nel salvataggio delle scelte menu utente: " + e.getMessage());
	        }
	    }
	    // Verifica se è uno staff (usando username e CF)
	    else if (isStaff(username, CF)) {
	        List<String> pranzoOpzioniStaff = getStaffMenu(pasti);
	        List<String> cenaOpzioniStaff = getStaffMenu(pasti);

	        pranzo = String.join(" / ", pranzoOpzioniStaff);
	        cena = String.join(" / ", cenaOpzioniStaff);
	    }

	    Location location = null;
	    return new Menu(pranzo, cena, location);
	}

   private List<String> getStaffMenu(List<String> pasti) {
       List<String> staffMenu = new ArrayList<>();
       for (int i = 0; i < 3 && i < pasti.size(); i++) {
           staffMenu.add(pasti.get(i));  // Aggiunta il pasto corrente alle opzioni
       }
       return staffMenu;
   }
   
   public List<String> getPranzoOpzioniStaff() {
       return pranzoOpzioniStaff;
   }
   
   public List<String> getCenaOpzioniStaff() {
       return cenaOpzioniStaff;
   }
   
   
   public void salvaScelteUtente(String username, String pastoPranzo, String pastoCena) throws SQLException {
	    utenteDAO.salvaScelteUtente(username, pastoPranzo, pastoCena);
	}
   
   // Salva la scelta finale dello staff per il pranzo e la cena
   public void salvaScelteStaff(String usernameST, String CFST, String pastoPranzo, String pastoCena) throws MissingMenuChoiceException {
       // Verifica se pranzo e cena sono selezionati
       if (pastoPranzo == null || pastoPranzo.isEmpty()) {
           throw new MissingMenuChoiceException(); 
       }
       if (pastoCena == null || pastoCena.isEmpty()) {
           throw new MissingMenuChoiceException(); 
       }
       // Salva le scelte dello staff
       this.pranzoOpzioniStaff = new ArrayList<>();
       this.pranzoOpzioniStaff.add(pastoPranzo); 
       this.cenaOpzioniStaff = new ArrayList<>();
       this.cenaOpzioniStaff.add(pastoCena);     
       // Aggiorna il database con le scelte dello staff usando il metodo della classe StaffDAO
       boolean success = staffDAO.aggiornaPastiStaff(usernameST, CFST, pastoPranzo, pastoCena);  
       if (success) {
           System.out.println("Scelte pasti aggiornate con successo!");
       } else {
           System.out.println("Errore nell'aggiornamento delle scelte pasti.");
       }
   }
   // Verifica se un utente è presente nel sistema
   private boolean isUtente(int personaId, String CFPA) {
	    Utente utente = utenteDAO.getUtente(personaId, CFPA);
	    return utente != null;
	}
   // Verifica se uno staff è presente nel sistema
   private boolean isStaff(String username, String CF) {
       Staff staff = staffDAO.getStaff(username, CF);
       return staff != null;
   }

   public List<String> ottieniAllergie(int personaId, String CFPA) {
	    Utente utente = utenteDAO.getUtente(personaId, CFPA);
	    List<String> allergie = new ArrayList<>();
	    if (utente != null && utente.getAllergiesPA() != null) {
	        String[] allergieArray = utente.getAllergiesPA().split(",");
	        for (String allergene : allergieArray) {
	            allergie.add(allergene.trim());
	        }
	    }
	    return allergie;
	}
 
   private boolean hasAllergyInMenu(String menuItem, List<String> allergie) {
       for (String allergia : allergie) {
           if (menuItem.toLowerCase().contains(allergia.toLowerCase())) {
               return true;  // Se uno degli allergeni è nel pasto, ritorna true
           }
       }
       return false;  
   }
}

