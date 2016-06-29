public class CoinGame {
	
	Player[] players = new Player[2]; // 2 elemanlý array yaratýlýyor
	
	CoinGame(String player1Name, String player2Name){
		
		players[0] = new Player(player1Name);
		players[1] = new Player(player2Name);
		// array elemanlarý initialize ediliyor
		
	}
	
	public void startGame(){
		
		Coin theCoin = new Coin();
		// startGame metodunun içerisinde olmasaydý 
		// (classa ait olsaydý)
		// bozuk para bir kere flip edilmiþ olacaktý
		
		// Pick a random player to choose the face value guess
		
		int randIndex = (Math.random() < 0.5)?0:1;
		String playersPick = players[randIndex].getRandCoinOption();
		
		// Set the opponents coinOption to the opposite value
		
		int opponentsIndex = (randIndex == 0)?1:0;
		players[opponentsIndex].setCoinOption(playersPick);
		
		// Flip the coin to find the winning side
		
		String winningFlip = theCoin.getCoinOption();
		
		// See the results of the flip
		
		players[0].didPlayerWin(winningFlip);
		
		players[1].didPlayerWin(winningFlip);
		
		// alt: for loop
//		for (Player pl: players) {
//			pl.didPlayerWin(winningFlip);
//		}
		
		// alt: boolean
//		if (players[0].didPlayerWin(winningFlip)) {
//			
//			System.out.println(players[0].name + " won with a flip of " + players[0].coinOption);
//			System.out.println(players[1].name + " lost with a flip of " + players[1].coinOption);
//			
//		}
//		
//		else {
//			
//			System.out.println(players[0].name + " lost with a flip of " + players[0].coinOption);
//			System.out.println(players[1].name + " won with a flip of " + players[1].coinOption);
//			
//		}
		
	}

}