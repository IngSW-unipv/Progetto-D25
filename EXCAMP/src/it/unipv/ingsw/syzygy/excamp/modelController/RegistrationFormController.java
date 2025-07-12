package it.unipv.ingsw.syzygy.excamp.modelController;

import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import it.unipv.ingsw.syzygy.excamp.database.dao.RegistrationFormDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.RegistrationFormModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import it.unipv.ingsw.syzygy.excamp.modelView.RegistrationFormView;

public class RegistrationFormController {
   private RegistrationFormView view;
   private RegistrationFormModel model;
   private RegistrationFormDAO dao;
   public RegistrationFormController(RegistrationFormView view, RegistrationFormModel model) {
       this.view = view;
       this.model = model;
       try {
           this.dao = new RegistrationFormDAO();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   // Validazione dei campi per la fase 1
   public void validateFase1(String nameGE, String surnameGE, String username, String confirmUsername,
                             String password, String confirmPassword, String parentsInfoPA) {
       if (nameGE.isEmpty() || surnameGE.isEmpty() || username.isEmpty() || confirmUsername.isEmpty() ||
           password.isEmpty() || confirmPassword.isEmpty() || parentsInfoPA.isEmpty()) {
           JOptionPane.showMessageDialog(view.getFrame(), "Campi obbligatori vuoti");
           return;
       }
       if (!isValidEmail(username)) {
           JOptionPane.showMessageDialog(view.getFrame(), "Email non valida. Per favore inserire una email valida");
           return;
       }
       if (!emailConfirmed(username, confirmUsername)) {
           JOptionPane.showMessageDialog(view.getFrame(), "Email non coincide");
           return;
       }
       if (!isValidPassword(password)) {
           JOptionPane.showMessageDialog(view.getFrame(), "La password deve avere almeno 6 caratteri");
           return;
       }
       if (!passwordConfirmed(password, confirmPassword)) {
           JOptionPane.showMessageDialog(view.getFrame(), "Password non coincide");
           return;
       }
       model.setNameGE(nameGE);
       model.setSurnameGE(surnameGE);
       model.setUsername(username);
       model.setPassword(password);
       model.setParentsInfoPA(parentsInfoPA);
       view.showPhase(2);
   }
   // Validazione dei campi per la fase 2
   public void validateFase2(String namePA, String surnamePA, Date dateOfBirth, String placeOfBirth, int agePA,
                             String CFPA, String allergiesPA, String medicalIssues, String sensitiveInfoPA,
                             String genderPA, String paese, String indirizzo, String civico, String CAP, String Provincia) {
       if (namePA.isEmpty() || surnamePA.isEmpty() || dateOfBirth == null || genderPA.isEmpty()) {
           JOptionPane.showMessageDialog(view.getFrame(), "Campi obbligatori vuoti");
           return;
       }
       if (!isValidCF(CFPA)) {
           JOptionPane.showMessageDialog(view.getFrame(), "Codice fiscale non valido.");
           return;
       }
       model.setNamePA(namePA);
       model.setSurnamePA(surnamePA);
       model.setDateOfBirthPA(dateOfBirth);
       model.setPlaceOfBirthPA(placeOfBirth);
       model.setAgePA(agePA);
       model.setCFPA(CFPA);
       model.setAllergiesPA(allergiesPA);
       model.setMedicalIssuesPA(medicalIssues);
       model.setSensitiveInfoPA(sensitiveInfoPA);
       model.setGenderPA(genderPA);
       model.setPaese(paese);
       model.setIndirizzo(indirizzo);
       model.setCivico(civico);
       model.setCAP(CAP);
       model.setProvincia(Provincia);
       view.showPhase(3);
   }
   // Invio dei dati finali
   public void submitForm(Camp camp, Transportation transportation, Departure pointOfDeparture, Date participationWeek,
                          String notes, boolean regolamentoCheckBox, boolean conditionsCheckBox, boolean privacyCheckBox) throws Exception {
       if (camp == null || transportation == null || participationWeek == null) {
           JOptionPane.showMessageDialog(view.getFrame(), "Tutti i campi obbligatori devono essere selezionati!");
           return;
       }
       model.setCamp(camp);
       model.setTransportation(transportation);
       model.setPointOfDeparture(pointOfDeparture);
       model.setParticipationWeek(participationWeek);
       model.setNotes(notes);
       model.setAcceptedRegulation(regolamentoCheckBox);
       model.setAcceptedConditions(conditionsCheckBox);
       model.setAcceptedPrivacy(privacyCheckBox);
       // Prosegui con l'inserimento nel database
       try {
           dao.insertIntoDatabase(model);
           JOptionPane.showMessageDialog(view.getFrame(), "Modulo completato con successo!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(view.getFrame(), "Errore nel salvataggio del modulo.");
           throw e;
       }
       // Mostra il prezzo prima di procedere al pagamento
       String prezzo = model.getPrezzo();  // Recupera il prezzo dal modello
       int risposta = JOptionPane.showConfirmDialog(view.getFrame(),
           "Il prezzo per l'iscrizione è: " + prezzo + "\nVuoi procedere con il pagamento?",
           "Conferma Prezzo", JOptionPane.YES_NO_OPTION);
       if (risposta == JOptionPane.YES_OPTION) {
           // Se l'utente conferma, procedi con il pagamento
           proceedToPayment();
       } else {
           // Se l'utente rifiuta, non fare nulla
           JOptionPane.showMessageDialog(view.getFrame(), "Pagamento annullato.");
       }
   }
   // Verifica se l'email è valida
   private boolean isValidEmail(String username) {
       return username != null && username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
   }
   // Verifica se le email coincidono
   private boolean emailConfirmed(String username, String confirmUsername) {
       return username.equals(confirmUsername);
   }
   // Verifica se la password è valida
   private boolean isValidPassword(String password) {
       return password != null && password.length() >= 6;
   }
   // Verifica se le password coincidono
   private boolean passwordConfirmed(String password, String confirmPassword) {
       return password.equals(confirmPassword);
   }
   // Verifica se il codice fiscale è valido
   private boolean isValidCF(String cf) {
       return cf != null && cf.length() == 16;
   }
   // Passa alla fase di pagamento
   public void proceedToPayment() {
	    if (view.getMainWindow() != null) {
	        System.out.println("MainWindow: " + view.getMainWindow());
	        view.getMainWindow().showPaymentSelection();
	    }
	}

   
   public void setView(RegistrationFormView view) {
	    this.view = view;
	}
}
