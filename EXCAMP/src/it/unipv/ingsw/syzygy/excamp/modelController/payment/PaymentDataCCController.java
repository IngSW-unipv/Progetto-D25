package it.unipv.ingsw.syzygy.excamp.modelController.payment;

import javax.swing.JFrame;
import it.unipv.ingsw.syzygy.excamp.modelView.payment.PaymentDataCCView;

public class PaymentDataCCController implements PaymentMethodController {
   private PaymentDataCCView paymentView;  // La view per la carta di credito
   public PaymentDataCCController() {
       paymentView = new PaymentDataCCView();
       setupListeners();
   }
   
   private void setupListeners() {
       paymentView.getNextButton().addActionListener(e -> handleNextButtonAction());
       paymentView.getBackButton().addActionListener(e -> handleBackButtonAction());
   }
   
   @Override
   public void showPaymentView() {
       // Mostra la view per la carta di credito
       JFrame frame = new JFrame("Pagamento Carta di Credito");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(paymentView);
       frame.pack();
       frame.setVisible(true);
   }
   
   @Override
   public boolean validatePaymentData() {
       // Esegui la validazione specifica per la carta di credito
       boolean isValid = true;
      
       if (paymentView.getInsertNC().getText().isEmpty()) {
           paymentView.getErrorLabelNC().setVisible(true);
           isValid = false;
       }
      
       if (paymentView.getInsertCVC().getText().isEmpty()) {
           paymentView.getErrorLabelCVC().setVisible(true);
           isValid = false;
       }
       return isValid;
   }
   
   // Metodo per validare il numero della carta di credito
   public boolean validateCardNumber(String cardNumber) {
       return cardNumber.matches("\\d{16}"); // Verifica che il numero sia lungo 16 cifre
   }
   
   // Metodo per validare la data di scadenza della carta di credito
   public boolean validateExpirationDate(String month, String year) {
       try {
           int mm = Integer.parseInt(month);
           int yy = Integer.parseInt(year);
           // Verifica se MM è tra 01 e 12
           if (mm < 1 || mm > 12) return false;
           // Verifica se l'anno è futuro
           int currentYear = java.time.Year.now().getValue() % 100; // Prendi gli ultimi 2 numeri dell'anno
           return yy >= currentYear;
       } catch (NumberFormatException e) {
           return false;
       }
       
   }
   
   //
   // Metodo per validare il CVC della carta di credito
   public boolean validateCVC(String cvc) {
       return cvc.matches("\\d{3}"); // Verifica che il CVC contenga esattamente 3 numeri
   }
   
   private void handleNextButtonAction() {
       if (validatePaymentData()) {
           // Prosegui con il pagamento
           System.out.println("Dati carta validi!");
       }
   }
   private void handleBackButtonAction() {
       // Torna alla schermata precedente
       System.out.println("Tornato alla schermata precedente");
   }
}


