package it.unipv.ingsw.syzygy.excamp.exceptions;

public class MissingMandatoryFieldsException extends Exception {
  public MissingMandatoryFieldsException(){
      super("Missing fields");
  }
}