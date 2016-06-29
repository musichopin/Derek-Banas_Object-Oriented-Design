import java.util.ArrayList;

public class BankNetwork {
	
//	normalde BankComputer super class olmalý ve BankComputer a baðlý subclasslar olmalýydý 
//	(aþaðýdaki BankComputer list deðiþmeden)
//	her BankComputer yaratýldýðýnda ise polymorphism ile object yaratýlarak BankNetwork'e eklenmeliydi
	private ArrayList<BankComputer> banksInDatabase = new ArrayList<BankComputer>();
	
	public void addBankToNetwork(BankComputer newBank){
		
		this.banksInDatabase.add(newBank);
		
	}
	
	// Returns the first 2 digits from the number passed to it
	
	static int getFirstTwoDigits(int stripNumber){
		
		String stringOfStripNumber = Integer.toString(stripNumber);
		
		// Get the first 2 numbers from the stripNumber and save them as an int
		
		int bankIDFromStrip = Integer.parseInt(stringOfStripNumber.substring(0, 2));
		
		return bankIDFromStrip;
		
	}
	

//	kart ile bankanýn bankID leri karþýlaþtýrýlýr
	public boolean isATMCardsBankInNetwork(Card theCard){
		
		Boolean cardVerification = false;
		
		int cardBankID = getFirstTwoDigits(theCard.getStripNumber());
		
		for(BankComputer bank : banksInDatabase){
			
			if(bank.getBankID() == cardBankID){
//		 	!ATM'ye yerleþtirilen kart ile bankalardaki id'yi kýyaslýyor
			
//			***iþlem sýrasý:
//				a. kart ~ database (bankalar)
//				b. kart -> transaction (theTransaction.setStripNumber(theCard.getStripNumber());)
//				c. transaction ~ database (bankalar ve hesaplar)
				
//				kart-> atm -> transaction ~ Account -> BankComputer -> BankNetwork -> ATM***
				
				cardVerification = true;
				
			}
			
		}
		
		return cardVerification;
		
	}
	

	public boolean verifyThePIN(Transaction theTransaction){
		
		boolean cardVerification = false;
		
		if(theTransaction.getDidCardVerify()){
			
			for(BankComputer bank : banksInDatabase){ // kademeli
//				computerdaki id ile transactiondaki id birbirine eþit ise...
				if(bank.getBankID() == theTransaction.getBankID()){
					
					bank.verifyThePIN(theTransaction);
					
					cardVerification = true;
					
				} else {
					
					System.out.println("The card Bank ID doesn't match any in the system");
					
					System.exit(0);
					
				}
				
			}
		}
		
//		!return edilenin türünün etkisi yok
		return cardVerification;
		
	}
	
	
	public void requestWithdrawalAmt(Transaction theTransaction){
		
		if(theTransaction.getDidCardVerify()){ // kademeli
			
			for(BankComputer bank : banksInDatabase){
//				computerdaki id ile transactiondaki id birbirine eþit ise...
				if(bank.getBankID() == theTransaction.getBankID()){
					
					bank.requestWithdrawalAmt(theTransaction);
					
				}
				
			}
			
		} else {
		
			System.out.println("An Error Occurred During the Withdrawal");
			
			System.exit(0);
			
		}
			
		
	}

}