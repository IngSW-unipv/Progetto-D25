package it.unipv.ingsw.syzygy.excamp.modelDomain.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.syzygy.excamp.database.dao.StaffProfileDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.TrasportoDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.AccountLockedException;
import it.unipv.ingsw.syzygy.excamp.exceptions.AccountNotFoundException;
import it.unipv.ingsw.syzygy.excamp.exceptions.FileExceededException;
import it.unipv.ingsw.syzygy.excamp.exceptions.InvalidImageFormatException;
import it.unipv.ingsw.syzygy.excamp.exceptions.WrongPasswordException;
import it.unipv.ingsw.syzygy.excamp.modelController.organizzazione.MenuController;
import it.unipv.ingsw.syzygy.excamp.modelDomain.BachecaModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.LoginModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.StaffProfileModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Autista;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ListaContattiAutisti;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Menu;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAlloggi;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAutisti;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneProgrammi;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaAlternativo;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaGiornaliero;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Trasporto;

public class Staff extends Persona {
	private String nameST;
	private String surnameST;
	private int ageST;
	private String CFST;
	private String accomodationST;
	private Departure pointOfDeparture;
	private Transportation transportation;
	private String allergiesST;
	private String medicalIssuesST;
	private String phonenumberST;
	private Menu menuPersonale;
	
	public Staff(int id, String username, String password, String nameST,
			String surnameST, int ageST,String CFST, String accomodationST,
			Departure pointOfDeparture, Transportation transportation,
			String allergiesST, String medicalIssuesST, String phonenumberST) {
		super(id, username, password);
		// TODO Auto-generated constructor stub
		this.nameST = nameST;
		this.surnameST = surnameST;
		this.ageST = ageST;
		this.CFST = CFST;
		this.accomodationST = accomodationST;
		this.pointOfDeparture = pointOfDeparture;
		this.transportation = transportation;
		this.allergiesST = allergiesST;
		this.medicalIssuesST = medicalIssuesST;
		this.phonenumberST = phonenumberST;
	
	}
		
	 @Override
	 public boolean login(LoginModel loginModel) throws AccountLockedException {
	        try {
	            loginModel.loginStaff(getUsername(), getPassword());
	            setLoggedIn(true);
	            System.out.println("Login riuscito per staff " + getUsername());
	            return true;
	        } catch (AccountNotFoundException | WrongPasswordException e) {
	            System.out.println("Errore di login: " + e.getMessage());
	            return false;
	        }
	    }
		 
	 public Menu scegliMenu(MenuController controller) {
		    try {
		        Menu menu = controller.creaMenuIniziale(getid(), null, getUsername(), getCFST());
		        System.out.println("Menu selezionato correttamente:");
		        menu.visualizzaMenu();
		        return menu;
		    } catch (Exception e) {
		        System.out.println("Errore durante la selezione del menu: " + e.getMessage());
		        return null;
		    }
		}
	
	 public void visualizzaMenu() {

		    if (menuPersonale != null) {
		        System.out.println("Il tuo menu selezionato:");
		        menuPersonale.visualizzaMenu();
		    } else {
		        System.out.println("Nessun menu selezionato. Scegli un menu prima di visualizzarlo.");
		    }
		}
 
	 public void visualizzaAlloggiStaff() {
	        OrganizzazioneAlloggi organizzazioneAlloggi = new OrganizzazioneAlloggi(null);
			organizzazioneAlloggi.visualizzaAlloggiStaff(); // Usa un'istanza
	    } 
	 
	 public void visualizzaAlloggiPartecipanti() {
	        OrganizzazioneAlloggi organizzazioneAlloggi = new OrganizzazioneAlloggi(null);
			organizzazioneAlloggi.visualizzaAlloggiPartecipanti(); // Usa un'istanza
	    }
	
	 public void visualizzaProgrammi() {
		    // Inizializzi un'organizzazione programmi con una location fittizia o null se non serve
		    OrganizzazioneProgrammi organizzazioneProgrammi = new OrganizzazioneProgrammi(null, null);

		    // Carichi i programmi dal database
		    organizzazioneProgrammi.caricaProgramma();

		    // Li visualizzi
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
	 	 
	 public void updateProfilePicture(String newImagePath, Connection connection) {
	        try {
	            StaffProfileDAO profileDAO = new StaffProfileDAO(connection);
	            // Usa ID per cercare il profilo
	            StaffProfileModel staffProfile = profileDAO.getStaffProfileById(getid());
	            if (staffProfile != null) {
	                staffProfile.setProfilePicturePath(newImagePath);
	                boolean success = profileDAO.updateProfilePicture(staffProfile);
	                if (success) {
	                    System.out.println("Immagine del profilo aggiornata con successo.");
	                } else {
	                    System.out.println("Errore durante l'aggiornamento dell'immagine del profilo.");
	                }
	            } else {
	                System.out.println("Errore: profilo dello staff non trovato.");
	            }
	        } catch (Exception e) {
	            System.out.println("Errore: " + e.getMessage());
	        }
	    }
	 
	 public void uploadExperience(String newExperience, Connection connection) {
	        try {
	            StaffProfileDAO profileDAO = new StaffProfileDAO(connection);
	            StaffProfileModel staffProfile = profileDAO.getStaffProfileById(getid());
	            if (staffProfile != null) {
	                staffProfile.setExperience(newExperience);
	                boolean success = profileDAO.updateExperience(staffProfile);
	                if (success) {
	                    System.out.println("Esperienza aggiornata con successo.");
	                } else {
	                    System.out.println("Errore durante l'aggiornamento dell'esperienza.");
	                }
	            } else {
	                System.out.println("Errore: profilo dello staff non trovato.");
	            }
	        } catch (Exception e) {
	            System.out.println("Errore: " + e.getMessage());
	        }
	    }
 
	 public void photoUploadApp(File photo) {
		    try {
		        BachecaModel bachecaModel = new BachecaModel();
		        bachecaModel.insertPhoto(photo); 
		        System.out.println("Foto caricata con successo nella bacheca.");
		        bachecaModel.close();
		    } catch (InvalidImageFormatException e) {
		        System.out.println("Errore: formato immagine non valido. Sono accettati solo JPG, JPEG, PNG.");
		    } catch (FileExceededException e) {
		        System.out.println("Errore: il file supera la dimensione massima di 10MB.");
		    } catch (FileNotFoundException e) {
		        System.out.println("Errore: file non trovato.");
		    } catch (IOException e) {
		        System.out.println("Errore durante la copia del file: " + e.getMessage());
		    } catch (SQLException e) {
		        System.out.println("Errore del database durante il caricamento della foto: " + e.getMessage());
		    }
		}
	 
	 public void visualizzaProfiloStaff(Connection connection) {
	        try {
	            StaffProfileDAO profileDAO = new StaffProfileDAO(connection);
	            StaffProfileModel staffProfile = profileDAO.getStaffProfileById(getid());
	            if (staffProfile != null) {
	                staffProfile.setUsername(getUsername());
	                staffProfile.visualizzaProfilo();
	            } else {
	                System.out.println("Profilo non trovato per lo staff con id: " + getid());
	            }
	        } catch (Exception e) {
	            System.out.println("Errore durante la visualizzazione del profilo: " + e.getMessage());
	        }
	    }
	 
	public String getPastoPranzo() {
	    return getPastoPranzo();
	    }

	public String getNameST() {
		return nameST;
	}
	public String getSurnameST() {
		return surnameST;
	}
	public int getageST() {
		return ageST;
	}
	public String getCFST() {
		return CFST;
	}
	public String getAccomodationST() {
		return accomodationST;
	}
	public Departure getPointOfDeparture() {
		return pointOfDeparture;
	}
	public Transportation getTransportation() {
		return transportation;
	}
	public String getAllergiesST() {
		return allergiesST;
	}
	public String getMedicalIssuesST() {
		return medicalIssuesST;
	}
	public String getPhoneNumberST() {
		return phonenumberST;
	}
}
