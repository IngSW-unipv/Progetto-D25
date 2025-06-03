package it.unipv.ingsw.syzygy.excamp.modelController;

import it.unipv.ingsw.syzygy.excamp.modelDomain.LoginModel;
import it.unipv.ingsw.syzygy.excamp.modelView.LoginView;
import it.unipv.ingsw.syzygy.excamp.exceptions.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
  private LoginModel model;
  private LoginView view;
  public LoginController(LoginModel model, LoginView view) {
      this.model = model;
      this.view = view;
    
  
      view.getLoginButton().addActionListener(new LoginButtonHandler());
  }
  private class LoginButtonHandler implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
          String username = view.getUsername().getText();
          String password = new String(view.getPassword().getPassword());
          // Controlla se i campi sono vuoti
          if (view.checkEmptyFields()) {
              view.setErrorLabel("Campi obbligatori vuoti");
              return;
          }
          try {
              // Aumenta il numero dei tentativi
              model.incrementLoginAttempts();
              // Determina il tipo di account
              if (view.getUtenteRadioButton().isSelected()) {
                  model.loginUtente(username, password);
              } else if (view.getStaffRadioButton().isSelected()) {
                  model.loginStaff(username, password);
              } else if (view.getAmministratoreRadioButton().isSelected()) {
                  model.loginAmministratore(username, password);
              }
            
              // Reset dei tentativi dopo un login riuscito
              model.resetLoginAttempts();
              // Se il login è riuscito, procedi con l'accesso
          } catch (AccountNotFoundException | WrongPasswordException | StayEndedException | AccountLockedException e) {
          
              view.setErrorLabel(e.getMessage());
          }
      }
  }
}
