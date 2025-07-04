package it.unipv.ingsw.syzygy.excamp.modelDomain.user;

import it.unipv.ingsw.syzygy.excamp.modelDomain.LoginModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.Menu;
import it.unipv.ingsw.syzygy.excamp.exceptions.AccountLockedException;

abstract class Persona {
  private int id;
  private String username;
  private String password;
  private boolean isLoggedIn;  // Stato di login dell'utente

  public Persona(int id, String username, String password) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.isLoggedIn = false;  // L'utente è inizialmente disconnesso
  }
   // Metodo login
  public abstract boolean login(LoginModel loginModel) throws AccountLockedException;

  // Metodo logout
  public void logout() {
      if (!isLoggedIn) {
          System.out.println("Non sei loggato.");
          return;
      }
    
      isLoggedIn = false;
      System.out.println("Logout effettuato per " + username);
  }
  
  // Getter e setter per username e password
  public int getid() {
      return id;
  }
  public void setId(int id) {
      this.id = id;
  }
  public String getUsername() {
      return username;
  }
  public void setUsername(String username) {
      this.username = username;
  }
  public String getPassword() {
      return password;
  }
  public void setPassword(String password) {
      this.password = password;
  }
   // Verifica se l'utente è loggato
  public boolean isLoggedIn() {
      return isLoggedIn;
  }
  
  public void setLoggedIn(boolean loggedIn) {
      this.isLoggedIn = loggedIn;
  }

}
