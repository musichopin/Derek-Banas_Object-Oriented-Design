public class Account {
//	bankID -> stripNumber + accountType (array) -> accountNumber (array)
	private int bankID;
	private int[] acctNumber;
	private String customerName;
	private String[] acctType;
	private double acctBalance;
	private int pin;
	private int stripNumber;
	
	static int accountNumberIncrementor = 100000;
	
	Account(int bankID, String customerName, String[] acctType, double acctBalance, int pin){
		
		this.bankID = bankID;
		this.customerName = customerName;
		this.acctBalance = acctBalance;
		this.pin = pin;
		stripNumber = generateStripNumber(bankID);
		
		this.acctType=new String[2];
//		sets the account types based on constructor
		for(int x = 0; x < acctType.length; x++) {
			this.acctType[x]=acctType[x];
	     }
		
		this.acctNumber=new int[2];
//		generates the account numbers no based on strip no and account type
		this.acctNumber = generateAccountNumber(stripNumber, this.acctType);
		
	}
//	not used
	public int getBankID(){ return bankID; }
	
	public int[] getAcctNumber(){ return acctNumber; }
	
	public String getCustomerName(){ return customerName; }

//	not used: ama account numberýn belirlenmesinde rol oynadý
	public String[] getAcctType(){ return acctType; }
	
	public double getAcctBalance(){ return acctBalance; }
	
//	!withdrawal sonrasý bakiyeyi yeniden hesaplamak için  
	public void setAcctBalance(double newAcctBalance){
		
		this.acctBalance = newAcctBalance;
		
	}
	
	public int getPIN(){ return pin; }
	
	public int getStripNumber(){ return stripNumber; }
	
	// Generates strip numbers by adding the bankID to the front
	// of the automatically generated middle part of each account
	// number taken from accountNumberIncrementor
	
	public int generateStripNumber(int bankID){
		// StripNumber is on the back side of the card 
		
		accountNumberIncrementor++;
		
		int newStripNumber = (bankID * 1000000) + accountNumberIncrementor;
//							... = 10 000 000 + 100 001			
		
		return newStripNumber;
		
	}
	
	public int[] generateAccountNumber(int stripNumber, String[] acctType){
		
		for(String acct: acctType) {
			
//			account number, account type'a göre üretilmiþ
			if((acct.startsWith("s")) || (acct.startsWith("S"))){
				
				// Savings is stripNumber with a 1 at the end
				
				acctNumber[0] = (stripNumber * 10) + 1;
//				1 for savings
				
				
			} else {
				
				// Checking is stripNumber with a 2 at the end
				
				acctNumber[1] = (stripNumber * 10) + 2;
//				2 for checkings
				
			}
		
		}
		
		return acctNumber;
			
	}

}