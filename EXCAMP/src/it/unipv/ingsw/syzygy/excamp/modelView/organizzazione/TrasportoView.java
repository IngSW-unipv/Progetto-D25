package it.unipv.ingsw.syzygy.excamp.modelView.organizzazione;

import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

public class TrasportoView {
	
   private JTextField idTRField = new JTextField(20);
   private JTextField placeOfDepartureField = new JTextField(20);
   private JTextField destinationField = new JTextField(50);
   private JTextField modalitaTRField = new JTextField(20);
   private JTextField etaField = new JTextField(20);
   private JTextField dateField = new JTextField(20);
   private JTextField timeField = new JTextField(20);
   private JButton addButton = new JButton("Aggiungi Trasporto");
   private JButton showButton = new JButton("Visualizza Trasporti");
   private JTextArea textArea = new JTextArea(10, 40);
   
   public TrasportoView() {
       JFrame frame = new JFrame("Gestione Trasporti");
       frame.setLayout(new FlowLayout());
       frame.add(new JLabel("ID TRASPORTO:"));
       frame.add(idTRField);
       frame.add(new JLabel("Modalità:"));
       frame.add(modalitaTRField);
       frame.add(new JLabel("Partenza:"));
       frame.add(placeOfDepartureField);
       frame.add(new JLabel("Destinazione:"));
       frame.add(destinationField);
       frame.add(new JLabel("Data:"));
       frame.add(dateField);
       frame.add(new JLabel("Orario:"));
       frame.add(timeField);
       frame.add(new JLabel("ETA:"));
       frame.add(etaField);
       frame.add(addButton);
       frame.add(showButton);
       frame.add(new JScrollPane(textArea));
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(600, 400);
       frame.setVisible(true);
   }
   
   // Imposta i listener per i pulsanti
   public void addButtonListener(ActionListener listener) {
       addButton.addActionListener(listener);
   }
   
   public void showButtonListener(ActionListener listener) {
       showButton.addActionListener(listener);
   }
   
   public void updateTextArea(String text) {
       textArea.setText(text);
   }
   
   public String getIdTR() {
       return idTRField.getText();
   }
   
   public Transportation getModalitaTR() {
       try {
           return Transportation.valueOf(modalitaTRField.getText().toUpperCase());
       } catch (IllegalArgumentException e) {
           JOptionPane.showMessageDialog(null, "Modalità di trasporto non valida.");
           return null;
       }
   }
   
   public Departure getPlaceOfDeparture() {
       try {
           return Departure.valueOf(placeOfDepartureField.getText().toUpperCase());
       } catch (IllegalArgumentException e) {
           JOptionPane.showMessageDialog(null, "Partenza non valida.");
           return null;
       }
   }
   public Location getDestination() {
       try {
           return Location.valueOf(destinationField.getText().toUpperCase());
       } catch (IllegalArgumentException e) {
           JOptionPane.showMessageDialog(null, "Destinazione non valida.");
           return null;
       }
   }
   
   public Time getEta() {
       try {
           return Time.valueOf(etaField.getText());
       } catch (IllegalArgumentException e) {
           JOptionPane.showMessageDialog(null, "Formato ETA non valido.");
           return null;
       }
   }
   
   public Date getDate() {
       try {
           return Date.valueOf(dateField.getText());
       } catch (IllegalArgumentException e) {
           JOptionPane.showMessageDialog(null, "Formato data non valido.");
           return null;
       }
   }
  
   public Time getTime() {
       try {
           return Time.valueOf(timeField.getText());
       } catch (IllegalArgumentException e) {
           JOptionPane.showMessageDialog(null, "Formato orario non valido.");
           return null;
       }
   }
   
}

