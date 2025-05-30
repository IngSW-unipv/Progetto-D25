package it.unipv.ingsw.syzygy.excamp.modelController.payment;

import javax.swing.JFrame;
import it.unipv.ingsw.syzygy.excamp.modelView.payment.PaymentDataBView;

public class PaymentDataBController implements PaymentMethodController {
   private PaymentDataBView paymentView;  // La view per il bonifico
   public PaymentDataBController() {
       paymentView = new PaymentDataBView();
       setupListeners();
   }
   private void setupListeners() {
       paymentView.getNextButton().addActionListener(e -> handleNextButtonAction());
       paymentView.getBackButton().addActionListener(e -> handleBackButtonAction());
   }
   @Override
   public void showPaymentView() {
       // Mostra la view per il bonifico
       JFrame frame = new JFrame("Pagamento Bonifico");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(paymentView);
       frame.pack();
       frame.setVisible(true);
   }
   @Override
   public boolean validatePaymentData() {
       // Esegui la validazione specifica per il bonifico bancario
       boolean isValid = true;
      
       if (paymentView.getInsertName().getText().isEmpty()) {
           paymentView.getErrorLabelName().setVisible(true);
           isValid = false;
       }
      
       if (paymentView.getInsertIBAN().getText().isEmpty()) {
           paymentView.getErrorLabelIBAN().setVisible(true);
           isValid = false;
       }
       return isValid;
   }
   
   // Altri controlli di validazione possono essere aggiunti qui…
   // Metodo per validare l'IBAN per il Bank Transfer
   public boolean validateIban(String iban) {
        return iban.matches("^[A-Z]{2}\\d{2}[A-Z0-9]{1,30}$"); // Verifica che l'IBAN sia valido
   }   
       
   
   private void handleNextButtonAction() {
       if (validatePaymentData()) {
           // Prosegui con il pagamento
           System.out.println("Dati bonifico validi!");
       }
   }
   private void handleBackButtonAction() {
       // Torna alla schermata precedente
       System.out.println("Tornato alla schermata precedente");
   }
}
