package monkey_lion;
public class ListAnimals {
//	Association is used 

	private Monkey theMonkey;
	private Lion theLion;

	ListAnimals(Monkey monkey, Lion lion) {
		theMonkey = monkey;
		theLion = lion;
	}

	public void displayAnimals() {
		
		System.out.println(theMonkey);
		System.out.println(theLion);
		
	}
	
//	***WRONG WAY TO DO, High Coupling Situation***
//	if we get to add a new animal, there is lots of hard-coding to manage
//	since ListAnimals is highly coupled to animal types (monkey and lion)
	public static void main(String[] args){
		
		Monkey monkey = new Monkey("Max");
		
		Lion lion = new Lion("Tom");
		
		ListAnimals listAnimals = new ListAnimals(monkey, lion);
		
		listAnimals.displayAnimals();
		
	}
	
}
/*
Monkey named Max
Lion named Tom
*/