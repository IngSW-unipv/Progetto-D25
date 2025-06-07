package it.unipv.ingsw.syzygy.excamp.modelView.bacheca;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.*;

public class BachecaView extends JFrame{
	private JList<String> albumList;
	private JPanel photoPanel;
	
	public BachecaView() {
		setTitle("Bacheca");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		albumList = new JList<>();
		JScrollPane albumScrollPane = new JScrollPane(albumList);
		add(albumScrollPane, BorderLayout.WEST);
		
		photoPanel = new JPanel();
		photoPanel.setLayout(new FlowLayout());
		JScrollPane photoScrollPane = new JScrollPane(photoPanel);
		add(photoScrollPane, BorderLayout.CENTER);
	}
	
	public String getSelectedAlbum() {
		return albumList.getSelectedValue();
	}
	
	public void displayPhotos(java.util.List<String> photoPaths) {
		photoPanel.removeAll();
		for (String path : photoPaths) {
			ImageIcon thumbnail = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			JLabel label = new JLabel(thumbnail);
			photoPanel.add(label);
		}
		revalidate();
		repaint();
	}
	
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);
	}
	public void addAlbumSelectionListener(ListSelectionListener listSelectionListener) {
		albumList.addListSelectionListener(listSelectionListener);
	}
}
