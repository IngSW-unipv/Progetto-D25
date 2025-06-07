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
				model.insertPhoto(selectedFile);
				String albumDirectory = organizePhoto(selectedFile);
				view.showUploadSuccess(albumDirectory);
			} catch (Exception ex) {
				view.showUploadError("Errore" + ex.getMessage());
			}
		}
	}
	
	private String organizePhoto(File selectedFile) throws FileNotFoundException, IOException {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(selectedFile.lastModified());
	    int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
	    int year = calendar.get(Calendar.YEAR);
	    
	    String albumPath = "photos/" + year + "/Week_" + weekOfYear;
	    File albumDirectory = new File(albumPath);
	    if (!albumDirectory.exists()) {
	        albumDirectory.mkdirs();
	    }
	    
	    File destination = new File(albumDirectory, selectedFile.getName());
	    if (destination.exists()) {
	        // Genera un nome unico per evitare la sovrascrittura
	        String newName = System.currentTimeMillis() + "_" + selectedFile.getName();
	        destination = new File(albumDirectory, newName);
	    }
	    
	    copyFile(selectedFile, destination);
	    return albumPath;
	}

	
	
	private void copyFile(File source, File destination) throws FileNotFoundException, IOException {
		try (InputStream in = new FileInputStream(source);
			 OutputStream out = new FileOutputStream(destination)) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		}
	}
}
