package it.unipv.ingsw.syzygy.excamp.modelDomain.payment;
public class DigitalWalletAdapter implements IPaymentAdapter{
	 
	   private IDigitalWalletPayment digitalWalletPayment;
	   public DigitalWalletAdapter(){
	       this.digitalWalletPayment = new DigitalWalletPayment();
	   }
	
	   @Override
	   public boolean paymentMethod(double obPrice){
	       boolean result = digitalWalletPayment.dwPaymentMethod(obPrice);
	       return result;
	   }
	}
