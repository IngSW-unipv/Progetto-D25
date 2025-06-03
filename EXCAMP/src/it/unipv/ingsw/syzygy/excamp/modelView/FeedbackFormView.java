package it.unipv.ingsw.syzygy.excamp.modelView;

import it.unipv.ingsw.syzygy.excamp.modelController.FeedbackFormController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FeedbackFormView extends JFrame{
	private FeedbackFormController controller;
	
	private int userId;
	private JLabel userFbIdLabel;
	private JPanel starsPanel;
	private JPanel attivitaLikedPanel;
	private JPanel attivitaDislikedPanel;
	private JPanel menuPanel;
	private JPanel alloggioPanel;
	private JPanel trasportiPanel;
	private JPanel commentsPanel;
	private JButton submitButton;
	
	private List<JRadioButton> starButtons = new ArrayList<>();
	
	public FeedbackFormView(FeedbackFormController controller, int userId) {
		this.controller = controller;
		this.userId = userId;
		initializeUI();
	}
	
	private void initializeUI() {
		setTitle("Modulo di Feedback");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLayout(new GridLayout(0, 1, 10, 10));
		
		userFbIdLabel = createUserIdLabel();
		starsPanel = createStarsPanel();
		attivitaLikedPanel = createActivityPanel("Attività preferita");
		attivitaDislikedPanel = createActivityPanel("Attività meno preferita");
		menuPanel = createSliderPanel("Voto al menù", 1, 10);
		alloggioPanel = createSliderPanel("Voto all'alloggio", 1, 10);
		trasportiPanel = createSliderPanel("Voto al servizio trasporti", 1, 10);
		commentsPanel = createCommentsPanel();
		
		submitButton = new JButton("Invia");
		submitButton.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.processFeedback();
			}
		});
		
		add(userFbIdLabel);
		add(starsPanel);
		add(attivitaLikedPanel);
		add(attivitaDislikedPanel);
		add(menuPanel);
		add(alloggioPanel);
		add(trasportiPanel);
		add(commentsPanel);
		add(submitButton);
		
		setVisible(true);
	}
	
	private JLabel createUserIdLabel() {
		int currentUserId = controller.getCurrentUserId(); 
		if (currentUserId == userId) {
			return new JLabel("Il tuo ID: " + userId);
		} else {
			return new JLabel("Utente Anonimo");
		}
	}

	
	// Getters per i dati del feedback
	public int getSelectedStars() {
		for (JRadioButton button : starButtons) {
			if (button.isSelected()) {
				return Integer.parseInt(button.getText());
			}
		}
		return 0;
	}
	
	public String getActivityFromComboBox(JPanel panel) {
		JComboBox<String> activityComboBox = (JComboBox<String>) panel.getComponent(1);
		return (String) activityComboBox.getSelectedItem();
	}
	
	public int getSliderValue(JPanel panel) {
		JSlider slider = (JSlider) panel.getComponent(1);
		return slider.getValue();
	}
	
	public String getComments() {
		JTextArea commentsArea = (JTextArea) ((JScrollPane) commentsPanel.getComponent(1)).getViewport().getView();
		return commentsArea.getText();
	}
	
	public JPanel getActivityLikedPanel() {
		return attivitaLikedPanel;
	}
	
	public JPanel getActivityDislikedPanel() {
		return attivitaDislikedPanel;
	}
	
	public JPanel getMenuPanel() {
		return menuPanel;
	}
	
	public JPanel getAlloggioPanel() {
		return alloggioPanel;
	}
	
	public JPanel getTrasportiPanel() {
		return trasportiPanel;
	}
	
	private JPanel createStarsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Esperienza complessiva (da 1 a 5)"));
		
		ButtonGroup starGroup = new ButtonGroup();
		for (int i = 1; i <= 5; i++) {
			JRadioButton starButton = new JRadioButton(Integer.toString(i));
			starGroup.add(starButton);
			panel.add(starButton);
			starButtons.add(starButton);
		}
		return panel;
	}
	
	private JPanel createActivityPanel(String label) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel(label + ":"));
		
		String[] activities = {"Corsa", "Nuoto", "Mountain Bike"};
		JComboBox<String> activityComboBox = new JComboBox<>(activities);
		panel.add(activityComboBox);
		
		return panel;
	}
	
	private JPanel createSliderPanel(String label, int min, int max) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel(label + "(da " + min + " a " + max + "):"));
		
		JSlider slider = new JSlider(min, max);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		panel.add(slider);
		
		return panel;
	}
	
	private JPanel createCommentsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Nota aggiuntiva o commenti"));
		
		JTextArea commentsArea = new JTextArea(5, 30);
		JScrollPane scrollPane = new JScrollPane(commentsArea);
		panel.add(scrollPane);
		
		return panel;
	}
}
