package indirection;
public class TestAdapter {
	
	public static void main(String[] args){
		
		EnemyAttacker theTank = new EnemyTank();
		
		EnemyRobot theRobot = new EnemyRobot();
		
		EnemyAttacker robotAdapter = new EnemyRobotAdapter(theRobot);
//		robotadapter acts as the robot itself from this moment.
		
//		this is an example of using indirection through an adapter between two different 
//		unlike objects and make them work inside of our system
		
		theTank.attack();
		
		robotAdapter.attack();
		
	}

}
/*
Tank fires 2 missiles
Robot jumps on the enemy
*/