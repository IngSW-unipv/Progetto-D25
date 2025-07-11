package it.unipv.ingsw.syzygy.excamp.modelDomain;

import java.sql.*;
import it.unipv.ingsw.syzygy.excamp.exceptions.*;
import it.unipv.ingsw.syzygy.excamp.modelDomain.user.Utente;

public class LoginModel {
	private Utente loggedUser;
	private int loginAttempts = 0;
  private Connection connection;
  public LoginModel(Connection connection) {
      this.connection = connection;
  }
  
  public void setLoggedUser(Utente user) {
      this.loggedUser = user;
  }

  public Utente getLoggedUser() {
      return this.loggedUser;
  }
  
  public void loginAmministratore(String username, String password) throws AccountNotFoundException, WrongPasswordException {
      String query = "SELECT p.pwd FROM PERSONA p " +
              "JOIN AMMINISTRATORE a ON p.id = a.persona_id " +
              "WHERE p.username = ?";
      try (PreparedStatement stmt = connection.prepareStatement(query)) {
          stmt.setString(1, username);
          ResultSet rs = stmt.executeQuery();
          if (!rs.next()) {
              throw new AccountNotFoundException();
          }
          String dbPassword = rs.getString("pwd");
          if (!dbPassword.equals(password)) {
              throw new WrongPasswordException();
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
  public void loginStaff(String username, String password) throws AccountNotFoundException, WrongPasswordException {
      String query ="SELECT p.pwd, s.nameST, s.surnameST FROM PERSONA p " +
              "JOIN STAFF s ON p.id = s.persona_id " +
              "WHERE p.username = ?";
      try (PreparedStatement stmt = connection.prepareStatement(query)) {
          stmt.setString(1, username);
          ResultSet rs = stmt.executeQuery();
          if (!rs.next()) {
              throw new AccountNotFoundException();
          }
          // Verifica prima volta login
          String correctPassword = rs.getString("nameST") + "." + rs.getString("surnameST");
          if (password.equals(correctPassword)) {
              // Prima volta, password = nome.cognome
              // Autorizza il login e forza il cambio della password
          } else {
              // Se non è la prima volta, verifica la password
              String dbPassword = rs.getString("pwd");
              if (!dbPassword.equals(password)) {
                  throw new WrongPasswordException();
              }
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
  public void loginUtente(String username, String password) throws AccountNotFoundException, WrongPasswordException, StayEndedException {
      String query = "SELECT p.pwd FROM PERSONA p " +
              "JOIN UTENTE u ON p.id = u.persona_id " +
              "WHERE p.username = ?";
      try (PreparedStatement stmt = connection.prepareStatement(query)) {
          stmt.setString(1, username);
          stmt.setString(2, password);
          ResultSet rs = stmt.executeQuery();
          if (!rs.next()) {
              throw new AccountNotFoundException();
          }
          // Verifica la scadenza del soggiorno
          Date endDate = rs.getDate("endDate");
          long currentTime = System.currentTimeMillis();
          if (currentTime - endDate.getTime() > 48 * 60 * 60 * 1000) {
              throw new StayEndedException();
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
  public void incrementLoginAttempts() throws AccountLockedException {
      loginAttempts++;
      if (loginAttempts > 3) {
          throw new AccountLockedException();
      }
  }
  public void resetLoginAttempts() {
      loginAttempts = 0;
  }
}
