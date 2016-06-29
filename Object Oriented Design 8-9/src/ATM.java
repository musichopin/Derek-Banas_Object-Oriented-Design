import java.util.Scanner;

public class ATM {
	
	private Transaction theTransaction = null;
	private BankNetwork networkOfBanksOnATM = null;
//	local variable olmadýðý için null denmek zorunda deðil
	
	// Used to get input from the customer
	
	static Scanner userInput = new Scanner(System.in);
	
//	creates itself by connecting itself to a network of banks
	ATM(BankNetwork networkOfBanksOnATM){
		
		this.networkOfBanksOnATM = networkOfBanksOnATM;
		
	}
	
	public boolean isStripReadable(Card theCard){
		
		// Returns the number of digits in stripNumber
		
		int numberOfDigitsInStrip = (int) (Math.log10(theCard.getStripNumber())+1); 
		// kaç haneli olduðunu buluyoruz (8)
		
//		transactiona kartýn bankID sini (strip number vasýtasýyla) veriyor
//		***BankComputer'daki verifyTheStripNumber metodu
//		üzerinden condition saðlanabilirdi ve o zaman ATM'deki isStripReadable
//		metodu isStripVerified olurdu.
//		Bu haliyle isATMCardsBankInNetwork metodu  veya aþaðýdaki condition 
//		içerisinde yer alsa daha doðru olur(?)***
		theTransaction = new Transaction(theCard.getStripNumber());
		
//		***return edilen boolean'ýn önemi olmadý
//		(main metodda if sta içinde olsa iþe yarardý)***
		if(numberOfDigitsInStrip == 8){
			
			return true; 
			
		} else {
			
//			alt 2: main metodda if sta olmadan:
			
//			System.out.println("Strip Number could not be verified");
			
//			System.exit(0);
			
			return false;
			
		}
		
	}
	
	// Sends the card inserted to BankNetwork to see if there
	// are any matching bankIDs
	
	public boolean isATMCardsBankInNetwork(Card theCard){
		
		// is ATM card's bank member of the bank network?
//		!amaç atm'yi network'e devretmek ve network içerisindeki computerlarýn 
//		(ve dolayýsýyla computerlar içerisindeki accountlarýn)
//		atm'ye þimdi sokulan karttaki bankid ile eþleþip eþleþmediðini test etmek
		if(networkOfBanksOnATM.isATMCardsBankInNetwork(theCard)){
			
			theTransaction.setStripNumber(theCard.getStripNumber());
			// kartýn strip nosunu transactionýn strip nosuna uyarlar 
			
			// Sets that the stripNumber has been verified so it is ok 
			// to access account info to verify pin later
			
			theTransaction.setDidTheCardVerify(true); 
			// ***sonraki metodlar için önemli,
			// zira BankNetwork'de ve BankComputer'da 
			// sonraki metodlar için condition görevi görüyor***
			
//			***return edilen boolean'ýn önemi olmadý
//			(main metodda if sta içinde olsa iþe yarardý)***
			return true;
			
		} else {
			
//			alt 2: main metodda if sta olmadan:
			
//			System.out.println("ATM Card could not be verified");
			
//			System.exit(0);
		
			return false;
		
		}
		
	}
	
	public void insertPIN(Card theCard, int pin){
		
		// Finds the number of digits in the PIN
		
		int numberOfDigitsInPIN = (int) (Math.log10(pin)+1);
		
		if(numberOfDigitsInPIN != 4){
			
			System.out.println("You have to enter 4 digits for a PIN");
			
//			alt olarak insertPIN metodu (system.exit olmadan) main metodda if 
//			sta içerisinden çaðýrýlabilir ve insertPIN metodu boolean return edebilirdi
			System.exit(0);
			
		} else {
			
			theTransaction.setPIN(pin);
//			***account ile önce transaction pin set ediliyor ardýndan alttaki 
//			metod aracýlýðýyla database'deki pin ile transactiondaki pinin 
//			denkliði test ediliyor.
//			(alttaki metoda if ile baðlanmasý hata olurdu, zira önce transactiona 
//			pin no verip ardýndan test etmesi gerekiyor database ile transactionýn denkliðini)***
			
			// I decided to pass the transaction instead of the card
			// like I did in the sequence diagram
			
			networkOfBanksOnATM.verifyThePIN(theTransaction);
//			tests if the transaction pin equals to the account pin 
			
		}
		
	}
	
	public void pickAcctToAccess(){
		
		System.out.println("What Account do you Want to Withdrawal From ");
		System.out.println("( 1 - Savings, 2 - Checking ) ");
		
		if (userInput.hasNextInt()){
			
			int numberEntered = userInput.nextInt();
			
			if ((numberEntered == 1) || (numberEntered == 2)) {
			
				theTransaction.setAcctToWithdrawalFrom(numberEntered);
			
			} else {
				
				System.out.println("You entered an invalid number");
				
				System.exit(0);
			
			}
			
		} else {
			
			System.out.println("You entered an invalid option");
//			kullanýcý integer girmemiþse
			
			System.exit(0);
			
		}

	}
	
	public void amountToWithdrawal(){
		
		System.out.println("How much do you want to withdrawal");
		System.out.println("(Increments of 10)");
		
		if (userInput.hasNextInt()){
			
			int numberEntered = userInput.nextInt();
			
			int formattedNumberEntered = numberEntered - (numberEntered % 10);
			
//			alt:
//			int formattedNumberEntered = ((int) (numberEntered / 10))*10;
			
			theTransaction.setWithdrawalAmt(formattedNumberEntered);
//			*insertPIN metodu misali önce set ediyor ardýndan test ediyor*
			networkOfBanksOnATM.requestWithdrawalAmt(theTransaction);
			
		} else {
			
			System.out.println("You Entered an Invalid Amount");
			
			System.exit(0);
			
		}
		
	}
	
	public void getTransactionInfo(){
		
		System.out.println("Thank you " + theTransaction.getCustomerName() + " for using the Is Bank ATM\n");
		
		System.out.println("Date / Time of Transaction: " + theTransaction.getCurrentDateTime());
		
		System.out.println("\nTransaction");
		
		System.out.print("Removed $" + theTransaction.getWithdrawalAmt() + " from your ");
		
		if(theTransaction.getAcctToWithdrawalFrom() == 1){ // 1: savings, 2: checking
			
			System.out.print("Savings Account.\nYour current balance is $" + theTransaction.getAcctBalance());
			
		} else {
			
			System.out.print("Checking Account.\nYour current balance is $" + theTransaction.getAcctBalance());
			
		}
		
	}

}