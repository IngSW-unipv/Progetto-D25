package it.unipv.ingsw.syzygy.excamp.modelDomain.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.syzygy.excamp.database.dao.StaffProfileDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.TrasportoDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.AccountLockedException;
import it.unipv.ingsw.syzygy.excamp.exceptions.AccountNotFoundException;
import it.unipv.ingsw.syzygy.excamp.exceptions.StayEndedException;
import it.unipv.ingsw.syzygy.excamp.exceptions.WrongPasswordException;
import it.unipv.ingsw.syzygy.excamp.modelController.FeedbackFormController;
import it.unipv.ingsw.syzygy.excamp.modelDomain.LoginModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.StaffProfileModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Autista;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ListaContattiAutisti;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Menu;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Organizzazione;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAlloggi;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAutisti;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneProgrammi;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaAlternativo;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaGiornaliero;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Trasporto;

public class Utente extends Persona{
	
	private String namePA;
	private String surnamePA;
	private int agePA;
	private String CFPA;
	private String accomodationPA;
	private Departure pointOfDeparture;
	private Transportation transportation;
	private String allergiesPA;
	private String medicalIssuesPA;
	private String sensitiveInfoPA;
	private String parentsInfoPA;
	private Camp camp;
	private Menu menuPersonale;
	
	public Utente(int id, String username, String password, String namePA, String surnamePA,
			int agePA, String CFPA, String accomodationPA, Departure pointOfDeparture,
			Transportation transportation, String allergiesPA, String medicalIssuesPA,
			String sensitiveInfoPA, String parentsInfoPA, Camp camp) {
		super(id, username, password);
		this.namePA=namePA;
		this.surnamePA= surnamePA;
		this.agePA=agePA;
		this.CFPA=CFPA;
		this.accomodationPA=accomodationPA;
		this.pointOfDeparture = pointOfDeparture;;
		this.transportation=transportation;
		this.allergiesPA=allergiesPA;
		this.medicalIssuesPA=medicalIssuesPA;
		this.sensitiveInfoPA=sensitiveInfoPA;
		this.parentsInfoPA=parentsInfoPA;
		this.camp= camp;
	}
	
	@Override
    public boolean login(LoginModel loginModel) throws AccountLockedException {
        try {
            loginModel.loginUtente(username, password);
            setLoggedIn(true);
            System.out.println("Login riuscito per utente " + username);
            return true;
        } catch (AccountNotFoundException | WrongPasswordException | StayEndedException e) {
            System.out.println("Errore di login: " + e.getMessage());
            return false;
        }
    }
	
	
	public void visualizzaAlloggioUtente(String CFPA) {
	    OrganizzazioneAlloggi alloggi = new OrganizzazioneAlloggi(null);
	    alloggi.visualizzaAlloggioUtente(CFPA);
	}
	
	public void visualizzaProgrammiGiornalieri() {
	    OrganizzazioneProgrammi organizzazioneProgrammi = new OrganizzazioneProgrammi(null, null, null, null);
	    organizzazioneProgrammi.caricaProgramma();
	    organizzazioneProgrammi.visualizzaProgramma();
	}

	public void visualizzaProgrammiAlternativi() {
	    OrganizzazioneProgrammi organizzazioneProgrammi = new OrganizzazioneProgrammi(null, null, null, null);
	    organizzazioneProgrammi.caricaProgramma();
	    organizzazioneProgrammi.visualizzaProgramma();
	}

	
	public void visualizzaListaAutisti() {
		    OrganizzazioneAutisti organizzazioneAutisti = new OrganizzazioneAutisti(null) ;

		    ListaContattiAutisti lista = organizzazioneAutisti.getListaContattiAutisti();
		    for (Autista autista : lista.getAutisti()) {
		        System.out.println("Nome: " + autista.getNameAU() + 
		                           ", Cognome: " + autista.getSurnameAU() + 
		                           ", Telefono: " + autista.getPhoneNumberAU() + 
		                           ", Location: " + autista.getLocation());
		    }
		}

	
	 public List<Trasporto> getAllTrasporti(Connection connection) {
	        try {
	            TrasportoDAO trasportoDAO = new TrasportoDAO(connection);
	            return trasportoDAO.getAllTrasporti();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<>();
	        }
	    }
	 
	 public void visualizzaMenuPersonale() {
		    if (menuPersonale != null) {
		        System.out.println("Il tuo menu personale:");
		        menuPersonale.visualizzaMenu(); // Chiama il metodo della classe Menu
		    } else {
		        System.out.println("Nessun menu assegnato.");
		    }
		}

	 public void visualizzaProfiloStaff(Connection connection) {
		    try {
		        StaffProfileDAO profileDAO = new StaffProfileDAO(connection);
		        StaffProfileModel staffProfile = profileDAO.getStaffProfileById(getid());

		        if (staffProfile != null) {
		            staffProfile.setUsername(getUsername()); // Assicuriamoci che l'username sia impostato
		            staffProfile.visualizzaProfilo();
		        } else {
		            System.out.println("Profilo non trovato per lo staff con username: " + getUsername());
		        }
		    } catch (Exception e) {
		        System.out.println("Errore durante la visualizzazione del profilo: " + e.getMessage());
		    }
		}
	 
	 public void compilaFeedback() {
			try {
				new FeedbackFormController(getid());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public String getNamePA() {
		return namePA;
	}
	public String getSurnamePA() {
		return surnamePA;
	}
	public int getAgePA() {
		return agePA;
	}
	public String getCFPA() {
		return CFPA;
	}
	
	public String getAccomodationPA() {
		return accomodationPA;
	}
	public Departure getPointOfDeparture() {
		return pointOfDeparture;
	}
	public Transportation getTransportation() {
		return transportation;
	}
	public String getAllergiesPA() {
		return allergiesPA;
	}
	public String getMedicalIssuesPA() {
		return medicalIssuesPA;
	}
	public String getSensitiveInfoPA() {
		return sensitiveInfoPA;
	}
	public String getParentsInfoPA() {
		return parentsInfoPA;
	}
	public Camp getCamp() {
		return camp;
	}

	public int getPersonaId() {
		
		return 0;
	}
}


