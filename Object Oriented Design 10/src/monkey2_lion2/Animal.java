package monkey2_lion2;
public class Animal {
//	Animal class could be abstract

	String name;
	
	Animal(String newName){
		
		name = newName;
		
	}
	
	public String toString(){
		
		return "Animal named " + name;
		
	}
	
}