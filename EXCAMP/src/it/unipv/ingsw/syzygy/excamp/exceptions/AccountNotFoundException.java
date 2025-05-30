package it.unipv.ingsw.syzygy.excamp.exceptions;

public class AccountNotFoundException extends Exception{
  public AccountNotFoundException() {
      super("Invalid account");
  }
}
