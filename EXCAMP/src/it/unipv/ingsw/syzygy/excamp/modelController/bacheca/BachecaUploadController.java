package it.unipv.ingsw.syzygy.excamp.modelController.bacheca;

import it.unipv.ingsw.syzygy.excamp.exceptions.FileExceededException;
import it.unipv.ingsw.syzygy.excamp.exceptions.InvalidImageFormatException;
import it.unipv.ingsw.syzygy.excamp.modelDomain.BachecaModel;
import it.unipv.ingsw.syzygy.excamp.modelView.bacheca.BachecaUploadView;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class BachecaUploadController {
	private BachecaUploadView view;
	private BachecaModel model;
	
	public BachecaUploadController(BachecaUploadView view, BachecaModel model) {
		this.view = view;
		this.model = model;
		
		this.view.addUploadButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uploadPhoto();

			}
		});
	}
	
	private void uploadPhoto() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Seleziona la foto da caricare");
		int result = fileChooser.showOpenDialog(view);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				String albumDirectory = model.insertPhoto(selectedFile);
				view.showUploadSuccess(albumDirectory);
			} catch (InvalidImageFormatException ex) {
				view.showUploadError("Formato immagine non valido. Solo JPG, JPEG e PNG sono supportati.");
			} catch (FileExceededException ex) {
				view.showUploadError("Il file è troppo grande. Dimensione massima: 10 MB.");
			} catch (SQLException | IOException ex) {
				view.showUploadError("Errore durante il caricamento: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}
