package it.unipv.ingsw.syzygy.excamp.modelView;

import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;
import it.unipv.ingsw.syzygy.excamp.modelController.*;
import it.unipv.ingsw.syzygy.excamp.modelController.RegistrationFormController;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RegistrationFormView extends JPanel {
   private JFrame frame;
   private CardLayout cardLayout;
   private JPanel cardPanel;
   private JTextField usernameField;
   private JTextField confirmUsernameField;
   private JPasswordField passwordField;
   private JPasswordField confirmPasswordField;
   private JTextField nameGEField;
   private JTextField surnameGEField;
   private JTextField namePAField;
   private JTextField surnamePAField;
   private JTextField agePAField;
   private JTextField CFPAField;
   private JTextField allergiesPAField;
   private JTextField medicalIssuesPAField;
   private JTextField sensitiveInfoPAField;
   private JTextField parentsInfoPAField;
   private JTextField genderPAField;
   private JTextField paeseField;
   private JTextField indirizzoField;
   private JTextField civicoField;
   private JTextField CAPField;
   private JTextField provinciaField;
   private JTextField localitaField;
   private JSpinner dateOfBirthField;
   private JTextField placeOfBirthField;
   private JSpinner participationWeekField;
   private JTextField notesField;
   private JLabel priceLabel;
   private JCheckBox regolamentoCheckBox;
   private JCheckBox conditionsCheckBox;
   private JCheckBox privacyCheckBox;
   private JComboBox<Departure> pointOfDepartureComboBox;
   private JComboBox<Transportation> transportationComboBox;
   private JComboBox<Camp> campComboBox;
   private RegistrationFormController controller;
   private MainWindow mainWindow;
   
   public RegistrationFormView(RegistrationFormController controller) {
       
	   if (controller == null) {
	        throw new IllegalArgumentException("Il controller non può essere null");
	    }
	   this.controller = controller;
	   
	   frame = new JFrame("Modulo di Iscrizione");
       cardLayout = new CardLayout();
       cardPanel = new JPanel(cardLayout);
       
       pointOfDepartureComboBox = new JComboBox<>(Departure.values());
       transportationComboBox = new JComboBox<>(Transportation.values());
       campComboBox = new JComboBox<>(Camp.values());
 
       
       createFase1();
       createFase2();
       createFase3();
       createFase4();
       
       frame.add(cardPanel);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(2000, 1000);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
   }
   
   public void setMainWindow(MainWindow mainWindow) {
	    this.mainWindow = mainWindow;
	}
   
  
   public void setRegistrationFormController(RegistrationFormController controller) {
	    this.controller = controller;
	}
   

   private void createFase1() {
	    JPanel fase1 = new JPanel();
	    fase1.setLayout(new GridLayout(0, 2));

	    // Campi di input
	    nameGEField = new JTextField();
	    surnameGEField = new JTextField();
	    usernameField = new JTextField();
	    confirmUsernameField = new JTextField();
	    passwordField = new JPasswordField();
	    confirmPasswordField = new JPasswordField();
	    parentsInfoPAField = new JTextField();

	    // Aggiunta componenti con font più grande
	    fase1.add(createLabel("Nome del genitore/tutore*:"));
	    fase1.add(nameGEField);

	    fase1.add(createLabel("Cognome del genitore/tutore*:"));
	    fase1.add(surnameGEField);

	    fase1.add(createLabel("Email*:"));
	    fase1.add(usernameField);

	    fase1.add(createLabel("Conferma email*:"));
	    fase1.add(confirmUsernameField);

	    fase1.add(createLabel("Password*:"));
	    fase1.add(passwordField);

	    fase1.add(createLabel("Conferma password*:"));
	    fase1.add(confirmPasswordField);

	    fase1.add(createLabel("Numero cellulare del genitore/tutore*:"));
	    fase1.add(parentsInfoPAField);

	    
	    JButton nextButton1 = new JButton("Avanti");
	    nextButton1.setFont(new Font("Arial", Font.BOLD, 16)); 
	    nextButton1.addActionListener(e -> controller.validateFase1(
	        nameGEField.getText(),
	        surnameGEField.getText(),
	        usernameField.getText(),
	        confirmUsernameField.getText(),
	        new String(passwordField.getPassword()),
	        new String(confirmPasswordField.getPassword()),
	        parentsInfoPAField.getText()
	    ));
	    fase1.add(new JLabel()); 
	    fase1.add(nextButton1);

	    cardPanel.add(fase1, "Fase 1");
	}

	
	private JLabel createLabel(String text) {
	    JLabel label = new JLabel(text);
	    label.setFont(new Font("Arial", Font.PLAIN, 16)); 
	    return label;
	}
   
   private void createFase2() {
       JPanel fase2 = new JPanel();
       fase2.setLayout(new GridLayout(9, 2));
       namePAField = new JTextField();
       fase2.add(new JLabel("Nome del partecipante*:"));
       fase2.add(namePAField);
       surnamePAField = new JTextField();
       fase2.add(new JLabel("Cognome del partecipante*:"));
       fase2.add(surnamePAField);
       dateOfBirthField = new JSpinner(new SpinnerDateModel());
       fase2.add(new JLabel("Data di nascita*:"));
       JSpinner.DateEditor editor = new JSpinner.DateEditor(dateOfBirthField, "yyyy-MM-dd");
       dateOfBirthField.setEditor(editor);
       fase2.add(dateOfBirthField);
       placeOfBirthField = new JTextField();
       fase2.add(new JLabel("Luogo di nascita:"));
       fase2.add(placeOfBirthField);
       agePAField = new JTextField();
       fase2.add(new JLabel("Età del partecipante:"));
       fase2.add(agePAField);
       CFPAField = new JTextField();
       fase2.add(new JLabel("Codice fiscale del partecipante*:"));
       fase2.add(CFPAField);
       allergiesPAField = new JTextField();
       fase2.add(new JLabel("Allergie del partecipante:"));
       fase2.add(allergiesPAField);
       medicalIssuesPAField = new JTextField();
       fase2.add(new JLabel("Medical issues del partecipante:"));
       fase2.add(medicalIssuesPAField);
       sensitiveInfoPAField = new JTextField();
       fase2.add(new JLabel("Informazioni ulteriori sul partecipante:"));
       fase2.add(sensitiveInfoPAField);
       genderPAField = new JTextField();
       fase2.add(new JLabel("Sesso partecipante (F/M)*:"));
       fase2.add(genderPAField);
       paeseField = new JTextField();
       fase2.add(new JLabel("Paese:"));
       fase2.add(paeseField);
       indirizzoField = new JTextField();
       fase2.add(new JLabel("Via:"));
       fase2.add(indirizzoField);
       civicoField = new JTextField();
       fase2.add(new JLabel("Civico:"));
       fase2.add(civicoField);
       CAPField = new JTextField();
       fase2.add(new JLabel("CAP:"));
       fase2.add(CAPField);
       provinciaField = new JTextField();
       fase2.add(new JLabel("Provincia:"));
       fase2.add(provinciaField);
       localitaField = new JTextField();
       fase2.add(new JLabel("Località:"));
       fase2.add(localitaField);
       
       JButton previousButton2 = new JButton("Indietro");
       previousButton2.addActionListener(e -> showPhase(1));
       fase2.add(previousButton2);
       
       JButton nextButton2 = new JButton("Avanti");
       nextButton2.addActionListener(e -> controller.validateFase2(
               namePAField.getText(), surnamePAField.getText(),
               (Date) dateOfBirthField.getValue(), placeOfBirthField.getText(),
               Integer.parseInt(agePAField.getText()), CFPAField.getText(),
               allergiesPAField.getText(), medicalIssuesPAField.getText(),
               sensitiveInfoPAField.getText(), genderPAField.getText(), paeseField.getText(),
               indirizzoField.getText(), civicoField.getText(), CAPField.getText(), provinciaField.getText()));
       fase2.add(nextButton2);
       
       cardPanel.add(fase2, "Fase 2");
   }
   private void createFase3() {
   JPanel fase3 = new JPanel();
   fase3.setLayout(new GridLayout(0, 2)); 
   fase3.add(new JLabel("Camp*:"));
   fase3.add(campComboBox);
   fase3.add(new JLabel("Mezzo di trasporto*:"));
   fase3.add(transportationComboBox);
   fase3.add(new JLabel("Punto di partenza:"));
   fase3.add(pointOfDepartureComboBox);
   participationWeekField = new JSpinner(new SpinnerDateModel());
   fase3.add(new JLabel("Settimana desiderata*:"));
   JSpinner.DateEditor editor = new JSpinner.DateEditor(participationWeekField, "yyyy-MM-dd");
   participationWeekField.setEditor(editor);
   fase3.add(participationWeekField);
   notesField = new JTextField(100);
   fase3.add(new JLabel("Note aggiuntive:"));
   fase3.add(notesField);
   regolamentoCheckBox = new JCheckBox("Accetto il regolamento");
   fase3.add(regolamentoCheckBox);
   conditionsCheckBox = new JCheckBox("Accetto le condizioni generali");
   fase3.add(conditionsCheckBox);
   privacyCheckBox = new JCheckBox("Accetto il trattamento dei dati");
   fase3.add(privacyCheckBox);
   priceLabel = new JLabel("Totale:");
   fase3.add(priceLabel);
   
   JButton previousButton3 = new JButton("Indietro");
   previousButton3.addActionListener(e -> showPhase(2));
   fase3.add(previousButton3);
   
   JButton submitButton = new JButton("Concludi");
   submitButton.addActionListener(e -> {
       try {
           controller.submitForm(
                   (Camp) campComboBox.getSelectedItem(), (Transportation) transportationComboBox.getSelectedItem(),
                   (Departure) pointOfDepartureComboBox.getSelectedItem(), (Date) participationWeekField.getValue(), notesField.getText(),
                   regolamentoCheckBox.isSelected(), conditionsCheckBox.isSelected(),
                   privacyCheckBox.isSelected());
           showPhase(4);
       } catch (Exception e1) {
           JOptionPane.showMessageDialog(frame, "Errore durante la sottomissione del modulo: " + e1.getMessage());
           e1.printStackTrace();
       }
   });
   fase3.add(submitButton);
   
   cardPanel.add(fase3, "Fase 3");
}
   private void createFase4() {
	    JPanel fase4 = new JPanel();
	    fase4.setLayout(new GridLayout(2, 1));

	    JButton paymentButton = new JButton("Procedere al pagamento");
	    paymentButton.addActionListener(e -> {
	        System.out.println("Procedi al pagamento cliccato");  // Debug
	        RegistrationFormController registrationFormController = new RegistrationFormController(null, null);
			registrationFormController.proceedToPayment();
	    });
	    fase4.add(paymentButton);

	    JButton previousButton4 = new JButton("Indietro");
	    previousButton4.addActionListener(e -> showPhase(3));
	    fase4.add(previousButton4);

	    cardPanel.add(fase4, "Fase 4");
	}

   public void showPhase(int fase) {
       switch (fase) {
           case 1:
               cardLayout.show(cardPanel, "Fase 1");
               break;
           case 2:
               cardLayout.show(cardPanel, "Fase 2");
               break;
           case 3:
               cardLayout.show(cardPanel, "Fase 3");
               break;
           case 4:
               cardLayout.show(cardPanel, "Fase 4");
               break;
       }
   }
   public JFrame getFrame() {
       return frame;
   }
   public MainWindow getMainWindow() {
	    return mainWindow;
	}
}
