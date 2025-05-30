package it.unipv.ingsw.syzygy.excamp.modelView.payment;

import it.unipv.ingsw.syzygy.excamp.modelController.payment.PaymentDataBController;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.LowerBar;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.UpperBar;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDataBView extends JPanel {
   private final Font font = new Font("Helvetica", Font.BOLD, 15);
   private final JLabel nameLabel = new JLabel("Nome Beneficiario:");
   private final JLabel surnameLabel = new JLabel("Cognome Beneficiario:");
   private final JLabel ibanLabel = new JLabel("IBAN Beneficiario:");
   private final JLabel importoLabel = new JLabel("Importo:");
   private final JLabel causaleLabel = new JLabel("Causale:");
   private JTextField insertName = new JTextField();
   private JTextField insertSurname = new JTextField();
   private JTextField insertIBAN = new JTextField();
   private JTextField insertImporto = new JTextField();
   private JTextField insertCausale = new JTextField();
   private final JButton backButton = new JButton();
   private final JButton nextButton = new JButton();
   private final JLabel errorLabelName = new JLabel("Dati beneficiario errati");
   private final JLabel errorLabelSurname = new JLabel("Dati beneficiario errati");
   private final JLabel errorLabelIBAN = new JLabel("IBAN non coincide");
   public PaymentDataBView() {
       setLayout(new BorderLayout());
       initComponents();
   }
   private void initComponents() {
       // Set font for labels
       nameLabel.setFont(font);
       surnameLabel.setFont(font);
       ibanLabel.setFont(font);
       importoLabel.setFont(font);
       causaleLabel.setFont(font);
       errorLabelName.setFont(font);
       errorLabelName.setForeground(Color.RED);
       errorLabelName.setVisible(false);
       errorLabelSurname.setFont(font);
       errorLabelSurname.setForeground(Color.RED);
       errorLabelSurname.setVisible(false);
       errorLabelIBAN.setFont(font);
       errorLabelIBAN.setForeground(Color.RED);
       errorLabelIBAN.setVisible(false);
       // Set font for text fields
       insertName.setFont(font);
       insertSurname.setFont(font);
       insertIBAN.setFont(font);
       insertImporto.setFont(font);
       insertCausale.setFont(font);
       // Configure backButton
       backButton.setIcon(new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/backArrow.png")));
       backButton.setBackground(new Color(145, 186, 214));
       // Configure nextButton
       nextButton.setIcon(new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/NewBuyButton.png")));
       nextButton.setBackground(new Color(145, 186, 214));
       // Create GridBagLayout for input fields
       JPanel dataInput = new JPanel(new GridBagLayout());
       dataInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.anchor = GridBagConstraints.WEST;
       gbc.gridx = 0;
       gbc.gridy = 0;
       dataInput.add(nameLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertName, gbc);
       gbc.gridx = 2;
       dataInput.add(errorLabelName, gbc);
       gbc.gridx = 0;
       gbc.gridy = 1;
       dataInput.add(surnameLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertSurname, gbc);
       gbc.gridx = 2;
       dataInput.add(errorLabelSurname, gbc);
       gbc.gridx = 0;
       gbc.gridy = 2;
       dataInput.add(ibanLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertIBAN, gbc);
       gbc.gridx = 2;
       dataInput.add(errorLabelIBAN, gbc);
       gbc.gridx = 0;
       gbc.gridy = 3;
       dataInput.add(importoLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertImporto, gbc);
       gbc.gridx = 0;
       gbc.gridy = 4;
       dataInput.add(causaleLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertCausale, gbc);
       // Panel for the bank logo
       JPanel rightPanel = new JPanel();
       rightPanel.setLayout(new BorderLayout());
       JLabel bankImage = new JLabel(new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/BankLogo.png")));
       rightPanel.add(bankImage, BorderLayout.CENTER);
       // Create HBox for buttons
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       buttonPanel.add(backButton);
       buttonPanel.add(nextButton);
       // Add components to main panel
       JPanel innerPanel = new JPanel(new BorderLayout());
       innerPanel.setBackground(new Color(145, 186, 214));
       innerPanel.add(dataInput, BorderLayout.CENTER);
       innerPanel.add(rightPanel, BorderLayout.EAST);
       innerPanel.add(buttonPanel, BorderLayout.SOUTH);
       // Add top and bottom bars
       setLayout(new BorderLayout());
       add(UpperBar.getInstance(), BorderLayout.NORTH);
       UpperBar.getInstance().setForNoLogged();
       add(innerPanel, BorderLayout.CENTER);
       add(LowerBar.getInstance(), BorderLayout.SOUTH);
   
       // Gestisci il pulsante "Avanti"
       nextButton.addActionListener(e -> {
           PaymentDataBController controller = new PaymentDataBController();
           if (validateInputs(controller)) {
               // Continua il processo, ad esempio navigando alla schermata successiva
           } else {
               // Mostra il messaggio di errore
               JOptionPane.showMessageDialog(this, "Per favore correggi gli errori nei dati immessi.");
           }
       	  
       	});
	}

   // Metodo che verifica la validazione dell'IBAN
   private boolean validateInputs(PaymentDataBController controller) {
       boolean valid = true;
       if (!controller.validateIban(insertIBAN.getText())) {
           errorLabelIBAN.setVisible(true);
           valid = false;
       } else {
           errorLabelIBAN.setVisible(false);
       }
       return valid;
   }
   
   public JButton getBackButton() {
       return backButton;
   }
   public JButton getNextButton() {
       return nextButton;
   }
   public JTextField getInsertName() {
       return insertName;
   }
   public JTextField getInsertSurname() {
       return insertSurname;
   }
   public JTextField getInsertIBAN() {
       return insertIBAN;
   }
   public JLabel getErrorLabelName() {
       return errorLabelName;
   }
   public JLabel getErrorLabelSurname() {
       return errorLabelSurname;
   }
   public JLabel getErrorLabelIBAN() {
       return errorLabelIBAN;
   }
   public void setInsertIBANText(String text) {
       this.insertIBAN.setText(text);
   }
}
