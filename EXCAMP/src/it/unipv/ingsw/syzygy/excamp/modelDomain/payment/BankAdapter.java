package it.unipv.ingsw.syzygy.excamp.modelDomain.payment;
public class BankAdapter implements IPaymentAdapter{
	
	   private IBankPayment bankPayment;
	   public BankAdapter(){
	       this.bankPayment = new BankPayment();
	   }
	
	   @Override
	   public boolean paymentMethod(double obPrice){
	       boolean result = bankPayment.bPaymentMethod(obPrice);
	       return result;
	   }
	}
