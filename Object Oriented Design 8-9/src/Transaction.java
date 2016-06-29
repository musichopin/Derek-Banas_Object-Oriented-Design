import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
// we need date library to get the date
// and another date library to format the date

public class Transaction {
	// transaction ATM'de son yapýlan iþlemle ilgili
	
	// Formats the date of the transaction
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//	iþlemin (transactionýn) gerçekleþtiði tarih print edileceði için 
//	Transaction classýnda kullanýlmýþ

	private int bankID;
	private Date currentDateTime;
	private String customerName;
	private double acctBalance;
	private double withdrawalAmt;
	private int accountNumberUsed;
	private int pin;
	private int stripNumber;
	private int acctToWithdrawalFrom;
//	acctToWithdrawalFrom, account type olarak kullanýlýyor
	
	// I decided to add verification that the card is allowed
	// to access the bank accounts
	private boolean didCardVerify = false;
	
//	setBankID yok zira, constructor ile set ediliyor
	public int getBankID() { return bankID; }
//	!Bank Network class verifyThePIN metodu ile requestWithdrawalAmt 
//	metodlarýnda test amaçlý kullanýlýyor
	
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
		
//		saving veya checking olmasýna göre acctToWithdrawalFrom sayý olarak yazýldý
		this.acctToWithdrawalFrom = acctToWithdrawalFrom;
//		acctToWithdrawalFrom, account type ýn görevini görür
		
//		***1 for savings, 2 for checkings
//		acctToWithdrawalFrom, 1 veya 2 deðerini alýr user inputa göre***
		this.accountNumberUsed = (stripNumber * 10) + acctToWithdrawalFrom;
//	    transaction strip numberý, ATM'ye sokulan kart network'te bulununca 
//		(ATM classý, isATMCardsBankInNetwork metodu)
//		kartýn strip numberýna eþitlenmiþti
		
	}
	
	public int getAcctToWithdrawalFrom(){ return acctToWithdrawalFrom; }
	
	public void setWithdrawalAmt(int withdrawalAmt){
		
		this.withdrawalAmt = withdrawalAmt;
		
	}
	
	public double getWithdrawalAmt(){ return withdrawalAmt; }

// BankComputer: requestWithdrawalAmt için kullanýldý
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
//		***not used: saçma geldi
		accountNumberUsed = stripNumber;
		
	}
	
}