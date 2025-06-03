package it.unipv.ingsw.syzygy.excamp.modelView.organizzazione;

import javax.swing.*;
import it.unipv.ingsw.syzygy.excamp.modelController.organizzazione.AutistaController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaContattiView {
	
  private JFrame frame;
  private JTextArea textArea;
  private JTextField nameAUField;
  private JTextField surnameAUField;
  private JTextField phoneNumberAUField;
  private JTextField locationField;
  private JButton addButton;
  private JButton showButton;
  private JLabel errorMessageLabel;  // Etichetta per i messaggi di errore
  
  public ListaContattiView() {
      frame = new JFrame("Lista contatti autisti");
      textArea = new JTextArea(15, 40);
      nameAUField = new JTextField(20);
      surnameAUField = new JTextField(20);
      phoneNumberAUField = new JTextField(15);
      locationField = new JTextField(20);
      addButton = new JButton("Aggiungi Autista");
      showButton = new JButton("Mostra Lista");
      errorMessageLabel = new JLabel("");  // Inizializzazione dell'etichetta per gli errori
      errorMessageLabel.setForeground(Color.RED);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      JPanel panel = new JPanel();
      panel.add(new JLabel("Nome:"));
      panel.add(nameAUField);
      panel.add(new JLabel("Cognome:"));
      panel.add(surnameAUField);
      panel.add(new JLabel("Telefono:"));
      panel.add(phoneNumberAUField);
      panel.add(new JLabel("Location:"));
      panel.add(locationField);
      panel.add(addButton);
      panel.add(showButton);
      // Aggiungi l'etichetta per gli errori alla finestra
      panel.add(errorMessageLabel);
      frame.setLayout(new BorderLayout());
      frame.add(panel, BorderLayout.NORTH);
      frame.add(scrollPane, BorderLayout.CENTER);
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
  }
  
  public void setController(AutistaController controller) {
      addButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String nameAU = nameAUField.getText();
              String surnameAU = surnameAUField.getText();
              String phoneNumberAU = phoneNumberAUField.getText();
              String location = locationField.getText();
              boolean result = controller.aggiungiAutista(nameAU, surnameAU, phoneNumberAU, location);
              if (!result) {
                  errorMessageLabel.setText("Errore nell'aggiunta dell'autista!");
              } else {
                  errorMessageLabel.setText(""); // Nessun errore, rimuovi il messaggio
              }
          }
      });
      showButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              controller.visualizzaListaAutisti();
          }
      });
  }
  
  public void mostraListaAutisti(String lista) {
      textArea.setText(lista);
  }
  
}
