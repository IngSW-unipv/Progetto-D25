package it.unipv.ingsw.syzygy.excamp.exceptions;

public class FileExceededException extends Exception{
	public FileExceededException() {
		super("File troppo grande");
	}
}
