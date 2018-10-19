import java.util.ArrayList;

public class Exercise3{
	public static void main (String[] args){
		ArrayList<String> list = new ArrayList<String>();

		list.add("ONE");
		list.add("FOUR");
		list.add(2,"THREE");
		list.add(2,"TWO");
		list.add(2,"FIVE");
		
		System.out.println(list);
	}
} 