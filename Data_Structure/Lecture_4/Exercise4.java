import java.util.ArrayList;

public class Exercise4{
	public static void main(String[] args){
		ArrayList<String> names = new ArrayList<String>();
		
		names.add("Karen");
		names.add("Don");
		names.add("Mark");
		
		ArrayList<String> temp = new ArrayList<String> (names);
		ArrayList<String> sameList = names;
		
		names.add(1,"Courtney");
		
		System.out.println("names: "+names);
		System.out.println("temp: "+temp);
		System.out.println("sameList: "+sameList);
	}
}