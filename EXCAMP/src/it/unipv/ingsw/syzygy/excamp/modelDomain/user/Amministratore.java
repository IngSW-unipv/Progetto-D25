package it.unipv.ingsw.syzygy.excamp.modelDomain.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.TrasportoDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.AccountLockedException;
import it.unipv.ingsw.syzygy.excamp.exceptions.AccountNotFoundException;
import it.unipv.ingsw.syzygy.excamp.exceptions.WrongPasswordException;
import it.unipv.ingsw.syzygy.excamp.modelDomain.LoginModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Albergo;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Autista;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ListaContattiAutisti;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Menu;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAlloggi;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneAutisti;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.OrganizzazioneProgrammi;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaAlternativo;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaGiornaliero;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Trasporto;

public class Amministratore extends Persona {
	private Location location;
	private OrganizzazioneProgrammi organizzazioneProgrammi;
	private OrganizzazioneAutisti organizzazioneAutisti;
	
	 public Amministratore(int id, String username, String password,Location location,
			 OrganizzazioneProgrammi organizzazioneProgrammi, OrganizzazioneAutisti organizzazioneAutisti) {
	        super(id, username, password);
	        this.setLocation(location);
	        this.organizzazioneProgrammi = organizzazioneProgrammi;
	        this.organizzazioneAutisti = new OrganizzazioneAutisti(location) {
				@Override
				public List<Trasporto> getAllTrasporti() {
					// TODO Auto-generated method stub
					return null;
				}
	        };
	 }
	
	 @Override
	    public boolean login(LoginModel loginModel) throws AccountLockedException {
	        try {
	            loginModel.loginAmministratore(username, password);
	            setLoggedIn(true);
	            System.out.println("Login riuscito per amministratore " + username);
	            return true;
	        } catch (AccountNotFoundException | WrongPasswordException e) {
	            System.out.println("Errore di login: " + e.getMessage());
	            return false;
	        }
	   }

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

    
	   // Gestione degli alloggi (Aggiungi partecipante e staff)
    public void aggiungiAlloggioPartecipante(Albergo alloggio) {
        OrganizzazioneAlloggi organizzazioneAlloggi = new OrganizzazioneAlloggi(null);
		organizzazioneAlloggi.aggiungiAlloggioPartecipante(alloggio);
    }

    public void aggiungiAlloggioStaff(Albergo alloggio) {
        OrganizzazioneAlloggi organizzazioneAlloggi = new OrganizzazioneAlloggi(null);
		organizzazioneAlloggi.aggiungiAlloggioStaff(alloggio);
    }

    // Recupera gli alloggi dei partecipanti e dello staff
    public void visualizzaAlloggiPartecipanti() {
        OrganizzazioneAlloggi organizzazioneAlloggi = new OrganizzazioneAlloggi(null);
		organizzazioneAlloggi.visualizzaAlloggiPartecipanti(); // Usa un'istanza
    }

    public void visualizzaAlloggiStaff() {
        OrganizzazioneAlloggi organizzazioneAlloggi = new OrganizzazioneAlloggi(null);
		organizzazioneAlloggi.visualizzaAlloggiStaff(); // Usa un'istanza
    }
 
    
	public void caricaProgramma() {
        organizzazioneProgrammi.caricaProgramma();
    }

    public void salvaProgramma() {
        organizzazioneProgrammi.salvaProgramma();
    }
	
	public void pubblicaProgramma(ProgrammaGiornaliero programma) {
	        organizzazioneProgrammi.aggiungiProgrammaGiornaliero(programma.getData(), programma);
	    }

    public void pubblicaProgrammaAlternativo(ProgrammaAlternativo programma) {
        organizzazioneProgrammi.aggiungiProgrammaAlternativo(programma.getData(), programma);
    }

    public ProgrammaGiornaliero visualizzaProgramma(String data) {
        return organizzazioneProgrammi.getProgrammaGiornaliero(data);
    }

    public ProgrammaAlternativo visualizzaProgrammaAlternativo(String data) {
        return organizzazioneProgrammi.getProgrammaAlternativo(data);
    }

    
    // Gestione autisti: aggiungi un autista alla lista
    public boolean aggiungiAutista(Autista autista) {
        return organizzazioneAutisti.aggiungiAutista(autista);
    }

    public void rimuoviAutista(Autista autista) {
        organizzazioneAutisti.rimuoviAutista(autista);
    }

    // Visualizza la lista degli autisti (per l'amministratore)
    public void visualizzaListaAutisti() {
        ListaContattiAutisti lista = organizzazioneAutisti.getListaContattiAutisti();
        for (Autista autista : lista.getAutisti()) {
            System.out.println("Nome: " + autista.getNameAU() + " Cognome: " + autista.getSurnameAU() + " Tel: " + autista.getPhoneNumberAU() + " Location: " + autista.getLocation());
        }
    }

    public ListaContattiAutisti getListaContattiAutisti() {
        return organizzazioneAutisti.getListaContattiAutisti();
    }

    
    public void aggiungiTrasporto(String idTR, Transportation modalita, Date dateTR, Time timeTR,
            Departure placeOfDeparture, Location destination, Time eta, int personaId) {
    	Trasporto trasporto = new Trasporto(idTR, modalita, dateTR, timeTR, placeOfDeparture, destination, eta, personaId);
        try {
            TrasportoDAO trasportoDAO = new TrasportoDAO(null);
			trasportoDAO.addTrasporto(trasporto);
        } catch (SQLException e) {
            e.printStackTrace();
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


    public Menu creaMenu(String pranzo, String cena) {
        // Controllo delle allergie
        if (hasAllergy(pranzo)) pranzo = "MENU ALTERNATIVO";
        if (hasAllergy(cena)) cena = "MENU ALTERNATIVO";
        return new Menu(pranzo, cena, location);
    }

    // Controlla se l'utente ha allergia per un determinato cibo
    public boolean hasAllergy(String food) {
        if (food == null || food.trim().isEmpty()) {
            return false; 
        }
                      
        return false;
    }
  
    
}
