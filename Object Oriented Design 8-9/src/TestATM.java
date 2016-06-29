public class TestATM {
//	Account <-> BankComputer <-> BankNetwork (<-> ATM)
// ~ (ATM sayesinde iki grup (database vs card/transaction) arasýndaki iliþki inceleniyor)
//	Card (<-> ATM) -> Transaction
	public static void main(String[] args){
		
		 String[] acctType = new String[]{"S", "C"};
//		 Savings'te 100, Checking'de 200 dolar olsaydý acctType ve acctBalance için hashmap kullanýlýrdý (?)
		 
		// Create a new account for a customer
//		!normalde 1den fazla account yaratýlmalý (array ile) ve kullanýcýlar pin nosuna göre kabul edilmeli
		Account theAccount = new Account(10, "Derek Banas", acctType, 100, 1234); // S: Savings Account
		
		// Generate an ATM card for the new customer/account
		
		Card customersATMCard = new Card(theAccount.getPIN(), theAccount.getStripNumber());
//		alt: zira pin kullanýlmadý
//		Card customersATMCard = new Card(theAccount.getStripNumber());
		
		// Create the Customer object and give it an ATM card
		
		Customer theCustomer = new Customer(customersATMCard);
		
		// Create the BankComputer that will hold every account
//		!normalde 1den fazla BankComputer yaratýlmalý (array ile) 
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
//		!aþaðýdaki 3 metod if sta içinde nested olsa daha iyi olur
		mainStreetATM.isStripReadable(customersATMCard);
		
		// Check with the BankNetwork to see if the cards member
		// bank is part of the network
		mainStreetATM.isATMCardsBankInNetwork(customersATMCard);
		
		// customersATMCard and the PIN are verified by the ATM
//		!kullanýcý pin numarasýný giriyor ve pin nosu sistem tarafýndan kontrol ediliyor
		mainStreetATM.insertPIN(customersATMCard, 1234);
//		***customersATMCard.getPIN() yerine ATM'ye girilen PIN numarasý hard-coded 
//		olarak (1234) yazýlmýþ*** 
		
		// Asks the customer whether to withdrawal money from checking or savings
		
		mainStreetATM.pickAcctToAccess();
		
		// Asks the customer how much money they want to withdrawal
		
		mainStreetATM.amountToWithdrawal();
		
		// Provides information on the transaction
		
		mainStreetATM.getTransactionInfo();
//		!yukarýdaki 3 metod if sta içinde nested olarak yazýlabilir
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