package it.unipv.ingsw.syzygy.excamp.modelController.bacheca;

import it.unipv.ingsw.syzygy.excamp.modelDomain.BachecaModel;
import it.unipv.ingsw.syzygy.excamp.modelView.bacheca.BachecaUploadView;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;

public class BachecaUploadController {
	private BachecaUploadView view;
	private BachecaModel model;
	
	public BachecaUploadController(BachecaUploadView view, BachecaModel model) {
		this.view = view;
		this.model = model;
		
		this.view.addUploadButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					uploadPhoto();
					}catch (SQLException e1) {
					    view.showUploadError("Errore nel database: " + e1.getMessage());
					    e1.printStackTrace();  
					} catch (IOException e1) {
					    view.showUploadError("Errore durante il caricamento del file: " + e1.getMessage());
					    e1.printStackTrace();
					}

			}
		});
	}
	
	private void uploadPhoto() throws SQLException, IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Seleziona la foto da caricare");
		int result = fileChooser.showOpenDialog(view);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				String albumDirectory = model.insertPhoto(selectedFile);
				view.showUploadSuccess(albumDirectory);
			} catch (Exception ex) {
				view.showUploadError("Errore" + ex.getMessage());
			}
		}
	}
}
