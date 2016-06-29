// Used as the fictional card passed in to check 
// if it is valid

public class Card {
	
	private int pin;
	private int stripNumber;
//	BankID yok ama, BankNetwork içerisinde stripNumber vasıtasıyla Card a ait BankID bulunuyor
	
	// Used to create a temp card to check for
	// a valid card strip number in a bank database
//	!not used: sadece strip no'yu test etmek için yaratıldı
	Card(int stripNumber){
			
		this.stripNumber = stripNumber;
			
	}
	
	// Used to create a temp card to check for
	// a valid card strip number and PIN in a 
	// bank database
//	(pin'in app için önemi yok)
	Card(int pin, int stripNumber){
		
		this.pin = pin;
		this.stripNumber = stripNumber;
		
	}
	
	public int getPIN(){ return pin; }
	public int getStripNumber(){ return stripNumber; }
	
//	used if the user wants to change the pin
//	!not used:
	public void setPIN(int pin){ 
		
		this.pin = pin; 
		
	}
//	used if the strip number is changed 
//	kullanılmadı:
	public void setStripNumber(int stripNumber){ 
		
		this.stripNumber = stripNumber; 
		
	}

}