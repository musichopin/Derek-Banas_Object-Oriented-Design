package polymorphism;
import java.util.ArrayList;

public class SilverPrices {
	
	public static void main(String[] args){
		
		ArrayList<GetSilverPrice> silverSellers = new ArrayList<GetSilverPrice>();
		
		silverSellers.add(new ABCSilver());
//		alt:
//		GetSilverPrice gsp = new ABCSilver();
//		silverSellers.add(gsp);
		
		silverSellers.add(new XYZSilver());
		
		for(GetSilverPrice silverPrice : silverSellers){
			
			System.out.println(silverPrice.getName() + ": " +
			silverPrice.getPriceOfSilver());
			
		}	
	}
}
/*
ABC Silver: 30.66
XYZ Silver: 30.67
*/