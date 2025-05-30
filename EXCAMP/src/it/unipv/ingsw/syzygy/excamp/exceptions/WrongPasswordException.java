package it.unipv.ingsw.syzygy.excamp.exceptions;

public class WrongPasswordException extends Exception{
  public WrongPasswordException(){
      super("Incorrect password");
  }
}
