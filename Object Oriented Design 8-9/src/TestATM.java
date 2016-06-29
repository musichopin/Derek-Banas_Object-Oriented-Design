public class TestATM {
//	Account <-> BankComputer <-> BankNetwork (<-> ATM)
// ~ (ATM sayesinde iki grup (database vs card/transaction) aras�ndaki ili�ki inceleniyor)
//	Card (<-> ATM) -> Transaction
	public static void main(String[] args){
		
		 String[] acctType = new String[]{"S", "C"};
//		 Savings'te 100, Checking'de 200 dolar olsayd� acctType ve acctBalance i�in hashmap kullan�l�rd� (?)
		 
		// Create a new account for a customer
//		!normalde 1den fazla account yarat�lmal� (array ile) ve kullan�c�lar pin nosuna g�re kabul edilmeli
		Account theAccount = new Account(10, "Derek Banas", acctType, 100, 1234); // S: Savings Account
		
		// Generate an ATM card for the new customer/account
		
		Card customersATMCard = new Card(theAccount.getPIN(), theAccount.getStripNumber());
//		alt: zira pin kullan�lmad�
//		Card customersATMCard = new Card(theAccount.getStripNumber());
		
		// Create the Customer object and give it an ATM card
		
		Customer theCustomer = new Customer(customersATMCard);
		
		// Create the BankComputer that will hold every account
//		!normalde 1den fazla BankComputer yarat�lmal� (array ile) 
		BankComputer isBankComputer = new BankComputer("IS Bank");
		
		// Add the customer account to the bank computer
//		(1'den fazla Account eklenebilirdi, 1'den fazla Account object pass edilerek)
		isBankComputer.addAcctToBank(theAccount);
		
		// Create a network that will hold every bank computer and all
		// the accounts they hold
		
		BankNetwork theBankNetwork = new BankNetwork();
		
		// Add the BankComputer to the BankNetwork
		
		theBankNetwork.addBankToNetwork(isBankComputer);
		
		// Create the ATM machine that will allow access to all
		// BankComputers in the BankNetwork
		// !BankNetwork is added to ATM
		
		ATM mainStreetATM = new ATM(theBankNetwork);
		
		// Check with the ATM to find out if the card is readable
//		!a�a��daki 3 metod if sta i�inde nested olsa daha iyi olur
		mainStreetATM.isStripReadable(customersATMCard);
		
		// Check with the BankNetwork to see if the cards member
		// bank is part of the network
		mainStreetATM.isATMCardsBankInNetwork(customersATMCard);
		
		// customersATMCard and the PIN are verified by the ATM
//		!kullan�c� pin numaras�n� giriyor ve pin nosu sistem taraf�ndan kontrol ediliyor
		mainStreetATM.insertPIN(customersATMCard, 1234);
//		***customersATMCard.getPIN() yerine ATM'ye girilen PIN numaras� hard-coded 
//		olarak (1234) yaz�lm��*** 
		
		// Asks the customer whether to withdrawal money from checking or savings
		
		mainStreetATM.pickAcctToAccess();
		
		// Asks the customer how much money they want to withdrawal
		
		mainStreetATM.amountToWithdrawal();
		
		// Provides information on the transaction
		
		mainStreetATM.getTransactionInfo();
//		!yukar�daki 3 metod if sta i�inde nested olarak yaz�labilir
	}

}
/*
What Account do you Want to Withdrawal From 
( 1 - Savings, 2 - Checking ) 
//2
How much do you want to withdrawal 
( Increments of 10 ) 
//45
Thank you Derek Banas for using the Is Bank ATM

Date / Time of Transaction: 2016/06/18 20:55:39

Transaction
Removed $45 from your Checking Account.
Your current balance is 55.0
*/