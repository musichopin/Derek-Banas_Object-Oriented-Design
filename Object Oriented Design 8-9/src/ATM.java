import java.util.Scanner;

public class ATM {
	
	private Transaction theTransaction = null;
	private BankNetwork networkOfBanksOnATM = null;
//	local variable olmad��� i�in null denmek zorunda de�il
	
	// Used to get input from the customer
	
	static Scanner userInput = new Scanner(System.in);
	
//	creates itself by connecting itself to a network of banks
	ATM(BankNetwork networkOfBanksOnATM){
		
		this.networkOfBanksOnATM = networkOfBanksOnATM;
		
	}
	
	public boolean isStripReadable(Card theCard){
		
		// Returns the number of digits in stripNumber
		
		int numberOfDigitsInStrip = (int) (Math.log10(theCard.getStripNumber())+1); 
		// ka� haneli oldu�unu buluyoruz (8)
		
//		transactiona kart�n bankID sini (strip number vas�tas�yla) veriyor
//		***BankComputer'daki verifyTheStripNumber metodu
//		�zerinden condition sa�lanabilirdi ve o zaman ATM'deki isStripReadable
//		metodu isStripVerified olurdu.
//		Bu haliyle isATMCardsBankInNetwork metodu  veya a�a��daki condition 
//		i�erisinde yer alsa daha do�ru olur(?)***
		theTransaction = new Transaction(theCard.getStripNumber());
		
//		***return edilen boolean'�n �nemi olmad�
//		(main metodda if sta i�inde olsa i�e yarard�)***
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
//		!ama� atm'yi network'e devretmek ve network i�erisindeki computerlar�n 
//		(ve dolay�s�yla computerlar i�erisindeki accountlar�n)
//		atm'ye �imdi sokulan karttaki bankid ile e�le�ip e�le�medi�ini test etmek
		if(networkOfBanksOnATM.isATMCardsBankInNetwork(theCard)){
			
			theTransaction.setStripNumber(theCard.getStripNumber());
			// kart�n strip nosunu transaction�n strip nosuna uyarlar 
			
			// Sets that the stripNumber has been verified so it is ok 
			// to access account info to verify pin later
			
			theTransaction.setDidTheCardVerify(true); 
			// ***sonraki metodlar i�in �nemli,
			// zira BankNetwork'de ve BankComputer'da 
			// sonraki metodlar i�in condition g�revi g�r�yor***
			
//			***return edilen boolean'�n �nemi olmad�
//			(main metodda if sta i�inde olsa i�e yarard�)***
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
//			sta i�erisinden �a��r�labilir ve insertPIN metodu boolean return edebilirdi
			System.exit(0);
			
		} else {
			
			theTransaction.setPIN(pin);
//			***account ile �nce transaction pin set ediliyor ard�ndan alttaki 
//			metod arac�l���yla database'deki pin ile transactiondaki pinin 
//			denkli�i test ediliyor.
//			(alttaki metoda if ile ba�lanmas� hata olurdu, zira �nce transactiona 
//			pin no verip ard�ndan test etmesi gerekiyor database ile transaction�n denkli�ini)***
			
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
//			kullan�c� integer girmemi�se
			
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
//			*insertPIN metodu misali �nce set ediyor ard�ndan test ediyor*
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