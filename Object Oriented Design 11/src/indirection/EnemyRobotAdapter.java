package indirection; // adapter: uyarlayýcý
// The adapter calls the method jumpOnEnemy when
// an EnemyAttacker is supposed to call attack method
// which is defined in the interface

public class EnemyRobotAdapter implements EnemyAttacker{

	EnemyRobot theRobot;
	
	public EnemyRobotAdapter(EnemyRobot newRobot){
		
		theRobot = newRobot;
		
	}

	// When this method is called call the correct 
	// method for the EnemyRobot
	public void attack() {
		
		theRobot.jumpOnEnemy();
		
	}
	
}