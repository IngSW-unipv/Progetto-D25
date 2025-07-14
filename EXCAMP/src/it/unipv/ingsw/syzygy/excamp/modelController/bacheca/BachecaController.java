package it.unipv.ingsw.syzygy.excamp.modelController.bacheca;

import it.unipv.ingsw.syzygy.excamp.modelDomain.BachecaModel;
import it.unipv.ingsw.syzygy.excamp.modelView.bacheca.BachecaView;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import java.util.*;

public class BachecaController {
	private BachecaView view;
	private BachecaModel model;
	
	public BachecaController(BachecaView view, BachecaModel model) {
		this.view = view;
		try {
			List<String> allWeeks = model.getAllWeeks();
			view.setAlbumList(allWeeks);
		} catch (SQLException e) {
			e.printStackTrace();
			view.showErrorMessage("Errore durante il caricamento delle settimane: " + e.getMessage());
		}
		this.model = model;
		
		this.view.addAlbumSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedAlbum = view.getSelectedAlbum();
				if (selectedAlbum != null) {
					showBacheca(selectedAlbum);
				}
			}
		});
	}
	
	private void showBacheca(String albumPath) {
		try {
			ResultSet rs = model.getAllPhotos();
			List<String> photoPaths = new ArrayList<>();
			
			while (rs.next()) {
				String album = rs.getString("album");
				if (album.equals(albumPath)) {
					photoPaths.add(rs.getString("file_path"));
				}
			}
			view.displayPhotos(photoPaths);
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			view.showErrorMessage("Errore durante il caricamento delle foto: " + ex.getMessage());
		}
	}
}
