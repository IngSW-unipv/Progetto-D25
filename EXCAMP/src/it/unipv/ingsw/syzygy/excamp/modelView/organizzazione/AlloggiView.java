package it.unipv.ingsw.syzygy.excamp.modelView.organizzazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AlloggiView {
	
   private JFrame frame;
   private JTextField nomeField;
   private JTextField cognomeField;
   private JTextField hotelField;
   private JTextField locationField;
   private JTextField campField;
   private JTextField roomField;
   private JTextField cfpaField;
   private JTextArea textArea;
   private JButton aggiungiAlloggioPartecipanteButton;
   private JButton aggiungiAlloggioStaffButton;
   private JButton visualizzaAlloggioUtenteButton;
   private JButton visualizzaAlloggiButton;
   
   public AlloggiView() {
       // Imposta la finestra principale
       frame = new JFrame("Gestione Alloggi");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 400);
       frame.setLayout(new BorderLayout());
       // Pannello per i campi di input
       JPanel panel = new JPanel();
       panel.setLayout(new GridLayout(7, 2));
       // Creazione dei campi di testo
       nomeField = new JTextField();
       cognomeField = new JTextField();
       hotelField = new JTextField();
       locationField = new JTextField();
       campField = new JTextField();
       roomField = new JTextField();
       cfpaField = new JTextField();
       // Etichette per i campi
       panel.add(new JLabel("Nome:"));
       panel.add(nomeField);
       panel.add(new JLabel("Cognome:"));
       panel.add(cognomeField);
       panel.add(new JLabel("Hotel:"));
       panel.add(hotelField);
       panel.add(new JLabel("Location:"));
       panel.add(locationField);
       panel.add(new JLabel("Camp:"));
       panel.add(campField);
       panel.add(new JLabel("Room:"));
       panel.add(roomField);
       panel.add(new JLabel("CFPA:"));
       panel.add(cfpaField);
       // Aggiungi il pannello alla finestra
       frame.add(panel, BorderLayout.CENTER);
       // Area di testo per visualizzare messaggi
       textArea = new JTextArea(5, 30);
       textArea.setEditable(false);
       frame.add(new JScrollPane(textArea), BorderLayout.SOUTH);
       // Pannello per i pulsanti
       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new FlowLayout());
       // Creazione dei pulsanti
       aggiungiAlloggioPartecipanteButton = new JButton("Aggiungi Alloggio Partecipante");
       aggiungiAlloggioStaffButton = new JButton("Aggiungi Alloggio Staff");
       visualizzaAlloggioUtenteButton = new JButton("Visualizza Alloggio Utente");
       visualizzaAlloggiButton = new JButton("Visualizza Alloggi");
       // Aggiungi i pulsanti al pannello
       buttonPanel.add( aggiungiAlloggioPartecipanteButton);
       buttonPanel.add( aggiungiAlloggioStaffButton);
       buttonPanel.add(visualizzaAlloggioUtenteButton);
       buttonPanel.add(visualizzaAlloggiButton);
       // Aggiungi il pannello dei pulsanti alla finestra
       frame.add(buttonPanel, BorderLayout.NORTH);
       // Mostra la finestra
       frame.setVisible(true);
   }
   
   // Metodi per ottenere i valori inseriti
   public String getNome() {
       return nomeField.getText();
   }
   
   public String getCognome() {
       return cognomeField.getText();
   }
   
   public String getHotel() {
       return hotelField.getText();
   }
   
   public String getLocation() {
       return locationField.getText();
   }
   
   public String getCamp() {
       return campField.getText();
   }
   
   public int getRoom() {
       try {
           return Integer.parseInt(roomField.getText());
       } catch (NumberFormatException e) {
           return -1; // In caso di errore, restituisci -1
       }
   }
   
   public String getCFPA() {
       return cfpaField.getText();
   }
   
   public void addAggiungiAlloggioPartecipanteListener(ActionListener listener) {
       aggiungiAlloggioPartecipanteButton.addActionListener(listener);
   }
   
   public void addAggiungiAlloggioStaffListener(ActionListener listener) {
       aggiungiAlloggioStaffButton.addActionListener(listener);
   }
   
   public void addVisualizzaAlloggioUtenteListener(ActionListener listener) {
       visualizzaAlloggioUtenteButton.addActionListener(listener);
   }
   
   public void addVisualizzaAlloggiListener(ActionListener listener) {
       visualizzaAlloggiButton.addActionListener(listener);
   }
   
   // Metodo per aggiornare il testo nell'area di testo
   public void setTextArea(String text) {
       textArea.setText(text);
   }
   
   // Metodo per resettare i campi
   public void resetFields() {
       nomeField.setText("");
       cognomeField.setText("");
       hotelField.setText("");
       locationField.setText("");
       campField.setText("");
       roomField.setText("");
       cfpaField.setText("");
   }
   
}


