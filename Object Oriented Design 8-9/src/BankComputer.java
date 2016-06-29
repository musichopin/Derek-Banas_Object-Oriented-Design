import java.util.ArrayList;

public class BankComputer {
	
	private int bankID = 9;
	
	private String bankName ="";
//	not used
	
//	normalde Account super class olmalý ve Account a baðlý subclasslar olmalýydý 
//	(aþaðýdaki Account list deðiþmeden)
//	her account yaratýldýðýnda ise polymorphism ile object yaratýlarak BankComputer'a eklenmeliydi
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public static int numberOfBanks = 0;
	
	BankComputer(String newBankName){
		
		bankName = newBankName;
		
		numberOfBanks++;
		
		this.bankID+= numberOfBanks;
		
	}
	
	public void addAcctToBank(Account newAccount){

		this.accounts.add(newAccount);
	
	}
	
//	***NOT USED***
//	ATM class'daki isStripReadable metodu için tasarlanmýþtý
	// Verifies that a card with the strip number is in a bank
	public Boolean verifyTheStripNumber(Card theCard){
		
		Boolean cardVerification = false;
		
		for(Account account : accounts){
			
			if(account.getStripNumber() == theCard.getStripNumber()){
//			!ATM'ye yerleþtirilen kart ile hesaplardaki stripnumberý kýyaslýyor
				
				cardVerification = true;
				
			}
			
		}
		
		return cardVerification;
		
	}
	
	
//	!isATMCardsBankInNetwork metodu için tasarlandý 
//	*(ATM: isATMCardsBankInNetwork -> BankNetwork: isATMCardsBankInNetwork)*
	public int getBankID(){ return bankID; }
//	accountýn bankID'sini looplamadan return ediyor
//	alt:
//	int id;
//	for(Account account : accounts){
//			id = account.getBankID();
//	}
//	return id;
	
	
	// Verifies that a card with the strip number and PIN is in a bank
//	*!insert pin metodu için tasarlandý (ATM: insertPIN -> BankNetwork: verifyThePIN)*
	public Boolean verifyThePIN(Transaction theTransaction){
		
		Boolean cardVerification = false;
		
//		ATM'de isATMCardsBankInNetwork metodunda true olmuþtu
		if(theTransaction.getDidCardVerify()){
		
			for(Account account : accounts){
				
//				kartýn strip numberý transactiona atanmýþtý (isATMCardsBankInNetwork metodunda).
//				kullanýcýnýn ATM'de girdiði pinin database'deki hesaplar ile denkliði (Card olmadan) 
//				doðrudan transaction üzerinden gerçekleþtiriliyor 
				if((account.getPIN() == theTransaction.getPIN()) && (account.getStripNumber() == theTransaction.getStripNumber())){
				
				cardVerification = true;
				
				theTransaction.setCustomerName(account.getCustomerName());
				
				} else {
					
					System.out.println("The PIN doesn't match any in the system");
					
					System.exit(0);
					
				}
			
			}
			
		}
//		!return edilenin türünün etkisi yok
		return cardVerification;
		
	}
//	!amountToWithdrawal metodu için tasarlandý 
//	*(ATM: amountToWithdrawal -> BankNetwork: requestWithdrawalAmt)*
	public void requestWithdrawalAmt(Transaction theTransaction){
		
		for(Account account : accounts){
//			BankComputer içerisindeki accountlarý ayrýþtýrýr
			
//			returns an array, which we ll need to loop through to manipulate data inside
			int[] getAcctNumber = account.getAcctNumber();
			
//			her bir account içerisindeki farklý account numberlarý ayrýþtýrýr
//			(account numberlar account type'a göre belirlenmiþti)
			for(int acct: getAcctNumber){
				
				if(acct == theTransaction.getAccountNumberUsed()){
					
						if(account.getAcctBalance() >= theTransaction.getWithdrawalAmt()){
							
							double newAcctBalance = account.getAcctBalance() - theTransaction.getWithdrawalAmt();
							
	//						not used but necessary for the following transactions
							account.setAcctBalance(newAcctBalance);
							
							theTransaction.setAcctBalance(newAcctBalance);
							
						} else {
							
							System.out.println("You can't withdrawal that much money");
							
							System.out.println("Your account balance is only " + account.getAcctBalance());
							
							System.exit(0);
							
						}
						
				}
				
			}

		}
		
	}

}