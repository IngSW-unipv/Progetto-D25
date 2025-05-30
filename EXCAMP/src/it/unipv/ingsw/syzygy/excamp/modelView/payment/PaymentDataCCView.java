package it.unipv.ingsw.syzygy.excamp.modelView.payment;

import it.unipv.ingsw.syzygy.excamp.modelController.payment.PaymentDataCCController;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.LowerBar;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.UpperBar;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDataCCView extends JPanel {
   private final Font font = new Font("Helvetica", Font.BOLD, 15);
   private final JLabel nameLabel = new JLabel("Nome Intestatario Carta:");
   private final JLabel surnameLabel = new JLabel("Cognome Intestatario Carta:");
   private final JLabel ncLabel = new JLabel("N° Carta:");
   private final JLabel expirationLabel = new JLabel("Data Scadenza (MM/YY):");
   private final JLabel cvcLabel = new JLabel("CVC:");
   private JTextField insertName = new JTextField();
   private JTextField insertSurname = new JTextField();
   private JTextField insertNC = new JTextField();
   private JTextField insertMM = new JTextField();
   private JTextField insertYY = new JTextField();
   private JTextField insertCVC = new JTextField();
   private final JButton backButton = new JButton();
   private final JButton nextButton = new JButton();
   private final JLabel errorLabelNC = new JLabel("Inserisci una carta di credito valida ");
   private final JLabel errorLabelMM = new JLabel("MM non valido");
   private final JLabel errorLabelYY = new JLabel("YY non valido");
   private final JLabel errorLabelCVC = new JLabel("CVC non valido");
   public PaymentDataCCView() {
       setLayout(new BorderLayout());
       initComponents();
   }
   private void initComponents() {
       // Adding labels to list
       List<JLabel> labels = new ArrayList<>();
       labels.add(nameLabel);
       labels.add(surnameLabel);
       labels.add(ncLabel);
       labels.add(expirationLabel);
       labels.add(cvcLabel);
       // Set error label properties
       errorLabelNC.setFont(font);
       errorLabelNC.setForeground(Color.RED);
       errorLabelNC.setVisible(false);
       errorLabelMM.setFont(font);
       errorLabelMM.setForeground(Color.RED);
       errorLabelMM.setVisible(false);
       errorLabelYY.setFont(font);
       errorLabelYY.setForeground(Color.RED);
       errorLabelYY.setVisible(false);
       errorLabelCVC.setFont(font);
       errorLabelCVC.setForeground(Color.RED);
       errorLabelCVC.setVisible(false);
       // Set labels' font and color
       for (JLabel label : labels) {
           label.setFont(font);
           label.setForeground(Color.BLACK);
       }
       // Add text fields
       List<JTextField> textFields = new ArrayList<>();
       textFields.add(insertMM);
       textFields.add(insertYY);
       textFields.add(insertCVC);
       textFields.add(insertName);
       textFields.add(insertSurname);
       textFields.add(insertNC);
       // Set font for text fields
       for (JTextField textField : textFields) {
           textField.setFont(font);
       }
       // Configure nextButton
       ImageIcon nextArrowIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/NewBuyButton.png"));
       nextButton.setIcon(nextArrowIcon);
       nextButton.setBackground(new Color(145, 186, 214));
       // Configure backButton
       ImageIcon backArrowIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/backArrow.png"));
       backButton.setIcon(backArrowIcon);
       backButton.setBackground(new Color(145, 186, 214));
       // Logo for payment method
       ImageIcon creditCardLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/CreditCard.png"));
       JLabel creditCardImage = new JLabel(creditCardLogo);
       // Create panel for data input
       JPanel dataInput = new JPanel(new GridBagLayout());
       dataInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.anchor = GridBagConstraints.WEST;
       // Adding components to dataInput panel
       gbc.gridx = 0;
       gbc.gridy = 0;
       dataInput.add(nameLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertName, gbc);
       gbc.gridx = 0;
       gbc.gridy = 1;
       dataInput.add(surnameLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertSurname, gbc);
       gbc.gridx = 0;
       gbc.gridy = 2;
       dataInput.add(ncLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertNC, gbc);
       gbc.gridx = 2;
       dataInput.add(errorLabelNC, gbc);
       gbc.gridx = 0;
       gbc.gridy = 3;
       dataInput.add(expirationLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertMM, gbc);
       gbc.gridx = 2;
       dataInput.add(insertYY, gbc);
       dataInput.add(errorLabelMM, gbc);
       dataInput.add(errorLabelYY, gbc);
       gbc.gridx = 0;
       gbc.gridy = 4;
       dataInput.add(cvcLabel, gbc);
       gbc.gridx = 1;
       dataInput.add(insertCVC, gbc);
       gbc.gridx = 2;
       dataInput.add(errorLabelCVC, gbc);
       // Panel for logo
       JPanel rightPanel = new JPanel();
       rightPanel.setLayout(new BorderLayout());
       rightPanel.add(creditCardImage, BorderLayout.CENTER);
       // Panel for buttons
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       buttonPanel.add(backButton);
       buttonPanel.add(nextButton);
       // Main layout panel
       JPanel mainPanel = new JPanel(new BorderLayout());
       mainPanel.setBackground(new Color(145, 186, 214));
       mainPanel.add(dataInput, BorderLayout.CENTER);
       mainPanel.add(rightPanel, BorderLayout.EAST);
       mainPanel.add(buttonPanel, BorderLayout.SOUTH);
       // Add top and bottom bars
       setLayout(new BorderLayout());
       add(UpperBar.getInstance(), BorderLayout.NORTH);
       UpperBar.getInstance().setForNoLogged();
       add(mainPanel, BorderLayout.CENTER);
       add(LowerBar.getInstance(), BorderLayout.SOUTH);
   
       // Gestisci il pulsante "Avanti"
       nextButton.addActionListener(e -> {
           PaymentDataCCController controller = new PaymentDataCCController();
           if (validateInputs(controller)) {
               // Continua il processo, ad esempio navigando alla schermata successiva
           } else {
               // Mostra il messaggio di errore
               JOptionPane.showMessageDialog(this, "Per favore correggi gli errori nei dati immessi.");
           }
       });
   }
   // Metodo che verifica tutte le validazioni
   private boolean validateInputs(PaymentDataCCController controller) {
       boolean valid = true;
       // Esegui la validazione dei singoli campi
       if (!controller.validateCardNumber(insertNC.getText())) {
           errorLabelNC.setVisible(true);
           valid = false;
       } else {
           errorLabelNC.setVisible(false);
       }
       if (!controller.validateExpirationDate(insertMM.getText(), insertYY.getText())) {
           errorLabelMM.setVisible(true);
           errorLabelYY.setVisible(true);
           valid = false;
       } else {
           errorLabelMM.setVisible(false);
           errorLabelYY.setVisible(false);
       }
       if (!controller.validateCVC(insertCVC.getText())) {
           errorLabelCVC.setVisible(true);
           valid = false;
       } else {
           errorLabelCVC.setVisible(false);
       }
       return valid;
   }
   public JButton getBackButton() {
       return backButton;
   }
   public JButton getNextButton() {
       return nextButton;
   }
   public JTextField getInsertCVC() {
       return insertCVC;
   }
   public JTextField getInsertNC() {
       return insertNC;
   }
   public JTextField getInsertMM() {
       return insertMM;
   }
   public JTextField getInsertYY() {
       return insertYY;
   }
   public JLabel getErrorLabelNC() {
       return errorLabelNC;
   }
   public JLabel getErrorLabelMM() {
       return errorLabelMM;
   }
   public JLabel getErrorLabelYY() {
       return errorLabelYY;
   }
   public JLabel getErrorLabelCVC() {
       return errorLabelCVC;
   }
   public void setInsertNCText(String text) {
       this.insertNC.setText(text);
   }
}
