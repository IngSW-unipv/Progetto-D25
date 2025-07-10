package it.unipv.ingsw.syzygy.excamp.modelView.payment;

import it.unipv.ingsw.syzygy.excamp.modelController.payment.PaymentDataDWController;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.LowerBar;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.UpperBar;
import javax.swing.*;
import java.awt.*;

public class PaymentDataDWView extends JPanel {
   private final Font font = new Font("Helvetica", Font.BOLD, 15);
   private final JLabel emailLabel = new JLabel("Inserisci la tua E-mail:");
   private JTextField insertEmail = new JTextField();
   private final JButton nextButton = new JButton();
   private final JButton backButton = new JButton();
   private final JLabel errorLabel = new JLabel("Inserisci una mail valida");
   public PaymentDataDWView() {
       setLayout(new BorderLayout());
       initComponents();
   }
   private void initComponents() {
       // Set font for Labels
       emailLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
       insertEmail.setFont(font);
       errorLabel.setFont(font);
       errorLabel.setForeground(Color.RED);
       // Logo for payment method
       ImageIcon digitalWalletLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/resources/digitalWallet.png"));
       JLabel digitalWalletImage = new JLabel(digitalWalletLogo);
       digitalWalletImage.setPreferredSize(new Dimension(150, 150));
       // Back button
       ImageIcon backArrowIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/resources/backArrow.png"));
       backButton.setIcon(backArrowIcon);
       backButton.setBackground(new Color(145, 186, 214));
       // Next button
       ImageIcon nextArrowIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/resources/nextArrow.jpg"));
       nextButton.setIcon(nextArrowIcon);
       nextButton.setBackground(new Color(145, 186, 214));
       // Set up the layout
       JPanel textBox = new JPanel();
       textBox.setLayout(new BoxLayout(textBox, BoxLayout.Y_AXIS));
       textBox.setAlignmentX(Component.CENTER_ALIGNMENT);
       textBox.add(Box.createVerticalStrut(20));
       textBox.setFont(new Font("Helvetica", Font.PLAIN, 14));
       // Add components to the panel
       textBox.add(digitalWalletImage);
       textBox.add(emailLabel);
       textBox.add(insertEmail);
       textBox.add(errorLabel);
       textBox.add(nextButton);
       // Set up error label visibility
       errorLabel.setVisible(false);
       // Set up button panel
       JPanel buttonBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
       buttonBox.add(backButton);
       buttonBox.add(nextButton);
       // Set up main layout
       JPanel rootPanel = new JPanel(new BorderLayout());
       rootPanel.setBackground(new Color(145, 186, 214));
       rootPanel.add(textBox, BorderLayout.CENTER);
       rootPanel.add(buttonBox, BorderLayout.SOUTH);
       // Add top and bottom bars
       setLayout(new BorderLayout());
       add(UpperBar.getInstance(), BorderLayout.NORTH);
       UpperBar.getInstance().setForNoLogged();
       add(rootPanel, BorderLayout.CENTER);
       add(LowerBar.getInstance(), BorderLayout.SOUTH);
   
       // Gestisci il pulsante "Avanti"
       nextButton.addActionListener(e -> {
           PaymentDataDWController controller = new PaymentDataDWController();
           if (validateInputs(controller)) {
               // Continua il processo, ad esempio navigando alla schermata successiva
           } else {
               // Mostra il messaggio di errore
               JOptionPane.showMessageDialog(this, "Per favore correggi gli errori nei dati immessi.");
           }
       });
   }
   // Metodo che verifica la validazione dell'email
   private boolean validateInputs(PaymentDataDWController controller) {
       boolean valid = true;
       if (!controller.validateEmail(insertEmail.getText())) {
           errorLabel.setVisible(true);
           valid = false;
       } else {
           errorLabel.setVisible(false);
       }
       return valid;
   }
   public JButton getBackButton() {
       return backButton;
   }
   public JLabel getEmailLabel() {
       return emailLabel;
   }
   public JTextField getInsertEmail() {
       return insertEmail;
   }
   public JButton getNextButton() {
       return nextButton;
   }
   public JLabel getErrorLabel() {
       return errorLabel;
   }
}
