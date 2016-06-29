import java.util.ArrayList;

public class BankComputer {
	
	private int bankID = 9;
	
	private String bankName ="";
//	not used
	
//	normalde Account super class olmal� ve Account a ba�l� subclasslar olmal�yd� 
//	(a�a��daki Account list de�i�meden)
//	her account yarat�ld���nda ise polymorphism ile object yarat�larak BankComputer'a eklenmeliydi
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
//	ATM class'daki isStripReadable metodu i�in tasarlanm��t�
	// Verifies that a card with the strip number is in a bank
	public Boolean verifyTheStripNumber(Card theCard){
		
		Boolean cardVerification = false;
		
		for(Account account : accounts){
			
			if(account.getStripNumber() == theCard.getStripNumber()){
//			!ATM'ye yerle�tirilen kart ile hesaplardaki stripnumber� k�yasl�yor
				
				cardVerification = true;
				
			}
			
		}
		
		return cardVerification;
		
	}
	
	
//	!isATMCardsBankInNetwork metodu i�in tasarland� 
//	*(ATM: isATMCardsBankInNetwork -> BankNetwork: isATMCardsBankInNetwork)*
	public int getBankID(){ return bankID; }
//	account�n bankID'sini looplamadan return ediyor
//	alt:
//	int id;
//	for(Account account : accounts){
//			id = account.getBankID();
//	}
//	return id;
	
	
	// Verifies that a card with the strip number and PIN is in a bank
//	*!insert pin metodu i�in tasarland� (ATM: insertPIN -> BankNetwork: verifyThePIN)*
	public Boolean verifyThePIN(Transaction theTransaction){
		
		Boolean cardVerification = false;
		
//		ATM'de isATMCardsBankInNetwork metodunda true olmu�tu
		if(theTransaction.getDidCardVerify()){
		
			for(Account account : accounts){
				
//				kart�n strip number� transactiona atanm��t� (isATMCardsBankInNetwork metodunda).
//				kullan�c�n�n ATM'de girdi�i pinin database'deki hesaplar ile denkli�i (Card olmadan) 
//				do�rudan transaction �zerinden ger�ekle�tiriliyor 
				if((account.getPIN() == theTransaction.getPIN()) && (account.getStripNumber() == theTransaction.getStripNumber())){
				
				cardVerification = true;
				
				theTransaction.setCustomerName(account.getCustomerName());
				
				} else {
					
					System.out.println("The PIN doesn't match any in the system");
					
					System.exit(0);
					
				}
			
			}
			
		}
//		!return edilenin t�r�n�n etkisi yok
		return cardVerification;
		
	}
//	!amountToWithdrawal metodu i�in tasarland� 
//	*(ATM: amountToWithdrawal -> BankNetwork: requestWithdrawalAmt)*
	public void requestWithdrawalAmt(Transaction theTransaction){
		
		for(Account account : accounts){
//			BankComputer i�erisindeki accountlar� ayr��t�r�r
			
//			returns an array, which we ll need to loop through to manipulate data inside
			int[] getAcctNumber = account.getAcctNumber();
			
//			her bir account i�erisindeki farkl� account numberlar� ayr��t�r�r
//			(account numberlar account type'a g�re belirlenmi�ti)
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