package it.unipv.ingsw.syzygy.excamp.modelDomain.payment;
public class BankPayment implements IBankPayment {
  @Override
  public boolean bPaymentMethod(double obPrice){
      if (obPrice >= 0) {
          return true;
      }
      else {
          System.out.println("Pagamento annullato");
          return false;
      }
  }
}
