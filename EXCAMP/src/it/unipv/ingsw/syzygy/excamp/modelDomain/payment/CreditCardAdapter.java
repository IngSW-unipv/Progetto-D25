package it.unipv.ingsw.syzygy.excamp.modelDomain.payment;
public class CreditCardAdapter implements IPaymentAdapter{
	 
	   private ICreditCardPayment creditCardPayment;
	   public CreditCardAdapter(){
	       this.creditCardPayment = new CreditCardPayment();
	   }
	
	   @Override
	   public boolean paymentMethod(double obPrice){
	       boolean result = creditCardPayment.ccPaymentMethod(obPrice);
	       return result;
	   }
	}