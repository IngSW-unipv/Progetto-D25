package it.unipv.ingsw.syzygy.excamp.modelView.bacheca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BachecaUploadView extends JFrame{
	private JButton uploadButton;
	
	public BachecaUploadView() {
		super("Photo Upload");
		
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		uploadButton = new JButton("Carica la foto");
		add(uploadButton);
	}
	
	public void showUploadError(String message) {
		JOptionPane.showMessageDialog(this, "Errore: " + message);
	}
	
	public void showUploadSuccess(String albumDirectory) {
		JOptionPane.showMessageDialog(this, "Foto caricata con successo: " + albumDirectory);
	}
	
	public void addUploadButtonActionListener(ActionListener listener) {
		uploadButton.addActionListener(listener);
	}
}
