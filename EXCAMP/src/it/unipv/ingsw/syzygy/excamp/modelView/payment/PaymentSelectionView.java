package it.unipv.ingsw.syzygy.excamp.modelView.payment;

import it.unipv.ingsw.syzygy.excamp.modelDomain.RegistrationFormModel;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.LowerBar;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.UpperBar;
import it.unipv.ingsw.syzygy.excamp.modelView.payment.PaymentDataCCView;
import it.unipv.ingsw.syzygy.excamp.modelView.payment.PaymentDataBView;
import it.unipv.ingsw.syzygy.excamp.modelView.payment.PaymentDataDWView;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentSelectionView extends JPanel {
	
   private final Font font = new Font("Helvetica", Font.BOLD, 15);
   private final JLabel totalStringLabel = new JLabel("Totale:");
   private final JLabel paySelectionLabel = new JLabel("Scegli un metodo con cui pagare:");
   private final JLabel totalAmountLabel = new JLabel();
   private final JLabel numberOfOrdersLabel = new JLabel();
   private final List<JLabel> labels = new ArrayList<>();
   private final JLabel errMessage = new JLabel("Selezionare un metodo di pagamento");
   private JButton backButton = new JButton();
   private JButton nextButton = new JButton();
   private JRadioButton DWButton = new JRadioButton("Digital Wallet");
   private JRadioButton CCButton = new JRadioButton("Credit Card");
   private JRadioButton BButton = new JRadioButton("Bank Transfer");
   private final CardLayout cardLayout = new CardLayout();
   private final JPanel cardPanel = new JPanel(cardLayout);
   private final PaymentDataCCView ccView = new PaymentDataCCView();
   private final PaymentDataBView bankView = new PaymentDataBView();
   private final PaymentDataDWView dwView = new PaymentDataDWView();
   public PaymentSelectionView() {
       setLayout(new BorderLayout());
       initComponents();
   }
   private void initComponents() {
       // Setup del gruppo di pulsanti per la selezione del metodo di pagamento
       ButtonGroup payMethodGroup = new ButtonGroup();
       payMethodGroup.add(DWButton);
       payMethodGroup.add(CCButton);
       payMethodGroup.add(BButton);
       // Impostazione dei font
       totalStringLabel.setFont(font);
       totalAmountLabel.setFont(font);
       paySelectionLabel.setFont(font);
       errMessage.setFont(font);
       DWButton.setFont(font);
       CCButton.setFont(font);
       BButton.setFont(font);
       // Icone per i pulsanti
       ImageIcon digitalWalletLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/DigitalWallet.png"));
       ImageIcon creditCardLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/CreditCard.png"));
       ImageIcon bankLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/Bank.png"));
       ImageIcon backArrowLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/backArrow.png"));
       ImageIcon nextArrowLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/nextArrow.png"));
       // Pulsanti con icone
       backButton.setIcon(backArrowLogo);
       backButton.setBackground(new Color(145, 186, 214));
       nextButton.setIcon(nextArrowLogo);
       nextButton.setBackground(new Color(145, 186, 214));
       // Messaggio di errore
       errMessage.setForeground(Color.RED);
       errMessage.setOpaque(false);
       // Layout dei pulsanti
       JPanel buttonBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
       buttonBox.add(backButton);
       buttonBox.add(nextButton);
       // Layout per i contenuti
       JPanel gridPanel = new JPanel(new GridLayout(7, 2, 10, 10));
       gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       gridPanel.add(totalStringLabel);
       gridPanel.add(totalAmountLabel);
       gridPanel.add(numberOfOrdersLabel);
       gridPanel.add(paySelectionLabel);
       gridPanel.add(DWButton);
       gridPanel.add(new JLabel(digitalWalletLogo));
       gridPanel.add(CCButton);
       gridPanel.add(new JLabel(creditCardLogo));
       gridPanel.add(BButton);
       gridPanel.add(new JLabel(bankLogo));
       gridPanel.add(errMessage);
       // Pannello principale
       JPanel rootPanel = new JPanel(new BorderLayout());
       rootPanel.setBackground(new Color(145, 186, 214));
       rootPanel.add(gridPanel, BorderLayout.CENTER);
       rootPanel.add(buttonBox, BorderLayout.SOUTH);
       // Aggiunta della visualizzazione dei metodi di pagamento
       cardPanel.add(ccView, "CC");
       cardPanel.add(bankView, "Bank");
       cardPanel.add(dwView, "Digital Wallet");
       // Aggiunta delle barre superiori e inferiori
       JPanel layout = new JPanel(new BorderLayout());
       layout.setBackground(new Color(145, 186, 214));
       layout.add(UpperBar.getInstance(), BorderLayout.NORTH);
       UpperBar.getInstance().setForNoLogged();
       layout.add(rootPanel, BorderLayout.CENTER);
       layout.add(LowerBar.getInstance(), BorderLayout.SOUTH);
       add(layout, BorderLayout.CENTER);
       // Aggiungi azioni ai pulsanti per visualizzare il metodo di pagamento selezionato
       DWButton.addActionListener(e -> cardLayout.show(cardPanel, "Digital Wallet"));
       CCButton.addActionListener(e -> cardLayout.show(cardPanel, "CC"));
       BButton.addActionListener(e -> cardLayout.show(cardPanel, "Bank"));
   }
   // Imposta il prezzo
   public void setPriceComponent(double price) {
       totalAmountLabel.setText("€ " + String.valueOf(price));
   }
   // Imposta il numero degli ordini
   public void setNumOrders(int number) {
       numberOfOrdersLabel.setText("(" + number + ((number == 1) ? " ordine)" : " ordini)"));
   }
   // Ottieni il prezzo
   public double getPrice() {
       String priceString = totalAmountLabel.getText().substring(2);
       return Double.parseDouble(priceString);
   }
   
   
   // Metodo per reimpostare la barra superiore e inferiore
   public void reSetBars() {
       JPanel temp = new JPanel();
       setLayout(new BorderLayout());
       add(temp, BorderLayout.CENTER);
       add(UpperBar.getInstance(), BorderLayout.NORTH);
       UpperBar.getInstance().setForNoLogged();
       add(LowerBar.getInstance(), BorderLayout.SOUTH);
   }
   // Getter methods
   public JRadioButton getDWButton() {
       return DWButton;
   }
   public JRadioButton getCCButton() {
       return CCButton;
   }
   public JRadioButton getBButton() {
       return BButton;
   }
   public JButton getNextButton() {
       return nextButton;
   }
   public JButton getBackButton() {
       return backButton;
   }
   public List<JLabel> getLabels() {
       return labels;
   }
   public JLabel getErrMessage() {
       return errMessage;
   }
}
