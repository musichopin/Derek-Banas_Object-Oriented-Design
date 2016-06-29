package monkey2_lion2;
public class Lion2 extends Animal {

	private String name;
	
	Lion2(String newName){
		
		super(newName);
		
		name = newName;
		
	}
	
	
	public String toString(){
		
		return "Lion named " + name;
		
	}
	
}