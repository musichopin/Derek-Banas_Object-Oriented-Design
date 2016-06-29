import java.util.Scanner;

public class CoinFlippingGame {

	public static void main(String[] args){
		
		// Create a coin game with the 2 players provided
		
		CoinGame theCoinGame = new CoinGame("Mark", "Tom");
		
		String usersAnswer;
		
		do {
			
			theCoinGame.startGame();
			
			System.out.println("Play Again? ");
			
			Scanner playGameAgain = new Scanner(System.in);
			
			usersAnswer = playGameAgain.nextLine();
			
		} while ((usersAnswer.startsWith("y")) || (usersAnswer.startsWith("Y")));
		
//		alt: while loop
		
//		System.out.println("Wanna Play? ");
//		
//		Scanner playGameAgain = new Scanner(System.in);
//		
//		String usersAnswer = playGameAgain.nextLine();
//		
//		while ((usersAnswer.startsWith("y")) || (usersAnswer.startsWith("Y"))) 
//		
//		{
//			theCoinGame.startGame();
//			
//			System.out.println("Wanna Play? ");
//			
//			playGameAgain = new Scanner(System.in);
//			
//			usersAnswer = playGameAgain.nextLine();
//		}
		
	}
	
}
/*
Mark lost with a flip of Heads
Tom won with a flip of Tails
Play Again? 
y
Mark won with a flip of Tails
Tom lost with a flip of Heads
Play Again? 
y
Mark won with a flip of Heads
Tom lost with a flip of Tails
Play Again? 
y
Mark lost with a flip of Tails
Tom won with a flip of Heads
Play Again? 
*/