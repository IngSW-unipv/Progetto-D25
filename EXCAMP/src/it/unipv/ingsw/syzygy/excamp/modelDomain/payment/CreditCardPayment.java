package it.unipv.ingsw.syzygy.excamp.modelDomain.payment;
public class CreditCardPayment implements ICreditCardPayment {
	
	   public boolean ccPaymentMethod( double obPrice){
	       if(obPrice >= 0){
	           return true;
	       }
	       else {
	           System.out.println("Pagamento annullato");
	           return false;
	       }
	   }
	}
