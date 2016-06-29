import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
// we need date library to get the date
// and another date library to format the date

public class Transaction {
	// transaction ATM'de son yap�lan i�lemle ilgili
	
	// Formats the date of the transaction
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//	i�lemin (transaction�n) ger�ekle�ti�i tarih print edilece�i i�in 
//	Transaction class�nda kullan�lm��

	private int bankID;
	private Date currentDateTime;
	private String customerName;
	private double acctBalance;
	private double withdrawalAmt;
	private int accountNumberUsed;
	private int pin;
	private int stripNumber;
	private int acctToWithdrawalFrom;
//	acctToWithdrawalFrom, account type olarak kullan�l�yor
	
	// I decided to add verification that the card is allowed
	// to access the bank accounts
	private boolean didCardVerify = false;
	
//	setBankID yok zira, constructor ile set ediliyor
	public int getBankID() { return bankID; }
//	!Bank Network class verifyThePIN metodu ile requestWithdrawalAmt 
//	metodlar�nda test ama�l� kullan�l�yor
	
	public String getCustomerName() { return customerName; }
	
	public void setCustomerName(String customerName){
		
		this.customerName = customerName;
		
	}
	
	// returns the current date and time as a String
	public String getCurrentDateTime() { return dateFormat.format(currentDateTime); }
	
	public int getPIN(){ return pin; }
	public void setPIN(int pin){
		
		this.pin = pin;
		
	}
	
	public void setStripNumber(int stripNumber){
		
		this.stripNumber = stripNumber;
		
	}
	
	public int getStripNumber(){ return stripNumber; }
	
	// Sets that the card has a valid stripNumber
	
	public void setDidTheCardVerify(boolean cardVerified){
		
		didCardVerify = (cardVerified)?true:false;
		
		// alt:
//		didCardVerify = (cardVerified==true)?true:false;
		
	}
	
	public boolean getDidCardVerify(){ return didCardVerify; }
	
	// Gets sent a 1 for savings or a 2 for checking
	// That number is added to the end of the accountNumberUsed
	
	public void setAcctToWithdrawalFrom(int acctToWithdrawalFrom){
		
//		saving veya checking olmas�na g�re acctToWithdrawalFrom say� olarak yaz�ld�
		this.acctToWithdrawalFrom = acctToWithdrawalFrom;
//		acctToWithdrawalFrom, account type �n g�revini g�r�r
		
//		***1 for savings, 2 for checkings
//		acctToWithdrawalFrom, 1 veya 2 de�erini al�r user inputa g�re***
		this.accountNumberUsed = (stripNumber * 10) + acctToWithdrawalFrom;
//	    transaction strip number�, ATM'ye sokulan kart network'te bulununca 
//		(ATM class�, isATMCardsBankInNetwork metodu)
//		kart�n strip number�na e�itlenmi�ti
		
	}
	
	public int getAcctToWithdrawalFrom(){ return acctToWithdrawalFrom; }
	
	public void setWithdrawalAmt(int withdrawalAmt){
		
		this.withdrawalAmt = withdrawalAmt;
		
	}
	
	public double getWithdrawalAmt(){ return withdrawalAmt; }

// BankComputer: requestWithdrawalAmt i�in kullan�ld�
	public int getAccountNumberUsed() { return accountNumberUsed; }
	
	public void setAcctBalance(double newAcctBalance){
		
		this.acctBalance = newAcctBalance;
		
	}
	
	public double getAcctBalance(){ return acctBalance; }

	
	Transaction(int stripNumber){
		
		bankID = BankNetwork.getFirstTwoDigits(stripNumber);
		
		currentDateTime = new Date();
		
		// Holds the account number minus either 1 for savings, or
		// 2 for checking at the end until that is added by 
		// setAccountNumberUsed() below
//		***not used: sa�ma geldi
		accountNumberUsed = stripNumber;
		
	}
	
}