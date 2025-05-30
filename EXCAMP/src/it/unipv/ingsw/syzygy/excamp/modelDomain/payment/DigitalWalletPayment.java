package it.unipv.ingsw.syzygy.excamp.modelDomain.payment;
public class DigitalWalletPayment implements IDigitalWalletPayment {
	
	   public boolean dwPaymentMethod( double obPrice){
	       if(obPrice >= 0){
	           return true;
	       }
	       else {
	           System.out.println("Pagamento annullato");
	           return false;
	       }
	   }
	}