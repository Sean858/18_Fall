import java.util.ArrayList;

public class Exercise2{
	public static void main (String[] args){
		ArrayList<Character> letters = new ArrayList<Character>();

		letters.add('f');
		letters.add(1,'i');
		letters.add('e');
		letters.add(1,'r');
		letters.add('e');
		letters.add(4,'z');
		System.out.println(letters);
		
		
		letters.remove((Character)'i');
		int index= letters.indexOf('e');
		letters.remove(index);
		letters.add(2,'o');
		System.out.println(letters);
	}
}