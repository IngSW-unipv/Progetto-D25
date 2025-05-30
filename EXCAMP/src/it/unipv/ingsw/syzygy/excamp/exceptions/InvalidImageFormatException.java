package it.unipv.ingsw.syzygy.excamp.exceptions;

public class InvalidImageFormatException extends Exception{
	public InvalidImageFormatException() {
		super("Formato immagine non supportato");
	}
}
