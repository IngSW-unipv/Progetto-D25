package it.unipv.ingsw.syzygy.excamp.exceptions;

public class AccountAlreadyExistsException extends Exception {
  public AccountAlreadyExistsException() {
      super("Account already exists");
  }
}
