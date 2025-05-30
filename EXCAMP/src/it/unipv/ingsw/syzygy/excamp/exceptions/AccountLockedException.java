package it.unipv.ingsw.syzygy.excamp.exceptions;

public class AccountLockedException extends Exception{
  public AccountLockedException(){
      super("Account locked after 3 failed attempts");
  }
}
