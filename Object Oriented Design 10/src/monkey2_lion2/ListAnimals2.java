package monkey2_lion2;
import java.util.ArrayList;

// inheritance and aggregation and composition used
public class ListAnimals2 {

	private ArrayList<Animal> theAnimals = new ArrayList<Animal>();
	// Animal arraylisti composition olarak düþünülebilirken, bu arrayliste 
	// (polymorphism ile oluþturulan ve) eklenen subclass objectleri aggregation olarak 
	// düþünülebilir
	
	public void addAnimalToList(Animal newAnimal){
		
		theAnimals.add(newAnimal);
		
	}
	
	// more coupled and complex r.ship: composition 
	//	(should be avoided in this example)
//	public void addAnimalToList(String a, String b){
//		
//		Animal monkey = new Monkey2(a);
//		
//		Animal lion = new Lion2(b);
//		
//		theAnimals.add(monkey);
//		
//		theAnimals.add(lion);
//		
//	}
	

	public void displayAnimals() {
		
		for(Animal animal : theAnimals){
			
			System.out.println(animal);
//			tostring metodu print edilir
			
		}
		
	}
	
	public static void main(String[] args){
		
		Animal monkey = new Monkey2("Max");
		
		Animal lion = new Lion2("Tom");
		
		Animal lion2 = new Lion2("Carl");
		
		ListAnimals2 listAnimals = new ListAnimals2();
		
		listAnimals.addAnimalToList(monkey);
//		list olduðu için önceki örneðin aksine yeni hayvan 
//		eklenmesi konusunda daha esnek
		// alt:
//		listAnimals.addAnimalToList(new Monkey2("Max"));
		
		listAnimals.addAnimalToList(lion);
		
		listAnimals.addAnimalToList(lion2);
		
		listAnimals.displayAnimals();
		
	}
	
//	public static void main(String[] args){
//		
//		ListAnimals2 listAnimals = new ListAnimals2();
//		
//		listAnimals.addAnimalToList("Max", "Tom");
//		
//		listAnimals.displayAnimals();
//		
//	}

	
}

/*
Monkey named Max
Lion named Tom
Lion named Carl
*/