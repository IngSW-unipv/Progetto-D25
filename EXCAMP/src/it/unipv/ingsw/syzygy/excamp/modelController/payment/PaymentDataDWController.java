package it.unipv.ingsw.syzygy.excamp.modelController.payment;

import javax.swing.JFrame;
import it.unipv.ingsw.syzygy.excamp.modelView.payment.PaymentDataDWView;

public class PaymentDataDWController implements PaymentMethodController {
   private PaymentDataDWView paymentView;  // La view per il digital wallet
   public PaymentDataDWController() {
       paymentView = new PaymentDataDWView();
       setupListeners();
   }
   private void setupListeners() {
       paymentView.getNextButton().addActionListener(e -> handleNextButtonAction());
       paymentView.getBackButton().addActionListener(e -> handleBackButtonAction());
   }
   @Override
   public void showPaymentView() {
       // Mostra la view per il digital wallet
       JFrame frame = new JFrame("Pagamento Digital Wallet");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(paymentView);
       frame.pack();
       frame.setVisible(true);
   }
   
   @Override
   public boolean validatePaymentData() {
       // Esegui la validazione specifica per il digital wallet
       boolean isValid = true;
      
       if (paymentView.getInsertEmail().getText().isEmpty()) {
           paymentView.getErrorLabel().setVisible(true);
           isValid = false;
       }
       return isValid;
   }

   // Metodo per validare l'email per Digital Wallet
   public  boolean validateEmail(String email) {
    	   return email.matches("^[A-Za-z0-9+_.-]+@(.+)$"); // Regex per validare una email
   }
   
   private void handleNextButtonAction() {
       if (validatePaymentData()) {
           // Prosegui con il pagamento
           System.out.println("Email wallet valida!");
       }
   }
   private void handleBackButtonAction() {
       // Torna alla schermata precedente
       System.out.println("Tornato alla schermata precedente");
   }
}
