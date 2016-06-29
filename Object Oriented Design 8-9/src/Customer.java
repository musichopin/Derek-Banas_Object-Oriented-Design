public class Customer {
	
	private Card customersATMCard;

	// Replaces public Card insertATMCard()

	Customer(Card newATMCard){
		
		customersATMCard = newATMCard; 
		
	}
	
//	!not used: Customer classý gereksiz gibi duruyor
	public Card getATMCard() { return customersATMCard; }

}