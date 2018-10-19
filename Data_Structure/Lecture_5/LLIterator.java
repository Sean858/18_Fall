import java.util.LinkedList;
import java.util.ListIterator;

public class LLIterator{
	public static void main(String[] args){
		//constructor
		LinkedList<String>  fruits=new LinkedList<String> ();
		
		//one paramater add
		fruits.add("apples");
		fruits.add("grapes");
		fruits.add("oranges");
		fruits.add("kiwis");
		
		//toString
		System.out.println(fruits);
		System.out.println();
		
		//get
		System.out.println(fruits.get(1));
		System.out.println();
		
		//set
		fruits.set(1,"bananas");
		System.out.println(fruits);
		System.out.println();
		
		//iterator constructors
		ListIterator<String> itr1 = fruits.listIterator();
		ListIterator<String> itr2 = fruits.listIterator(2);
		
		//iterating forwards
		System.out.println("\niterating forwards");
		while(itr1.hasNext()){
            System.out.println(itr1.next());
		}
		
		//iterating backwards from the middle of the list
		System.out.println("\niterating backwards from the middle of the list");
		while(itr2.hasPrevious()){
            System.out.println(itr2.previous());
		}

		//iterating backwards whole list
		System.out.println("\niterating backwards whole list");
		ListIterator itr3 = fruits.listIterator(fruits.size());
		//fruits.size() is the largest index +1
		while(itr3.hasPrevious()){
			System.out.println(itr3.previous());
		}
		
		//iterator adding to list
		System.out.println("iterator adding to list");
		ListIterator<String> itr4 = fruits.listIterator();
		while(itr4.hasNext()){
			itr4.next();
			itr4.add("pears");
		}
		System.out.println(fruits);

		//iterator removing from list
		System.out.println("\niterator removing from the list");
		ListIterator<String> itr5 = fruits.listIterator(1);	//position at the first instance of "pears"
		while(itr5.hasNext()){
			itr5.next();
			itr5.remove();
			if(itr5.hasNext()){itr5.next();}
		}
		System.out.println(fruits);
		
		//iterator modifying the list
		System.out.println("\niterator modifying the list");
		ListIterator<String> itr6 = fruits.listIterator();	//position at the first instance of "pears"
		while(itr6.hasNext()){
			String aFruit = itr6.next();	//the string at the current position
			char first = aFruit.toUpperCase().charAt(0);	//obtain its first character in uppercase
			aFruit=first+aFruit.substring(1);	//append that character to the rest of the word
			itr6.set(aFruit);	//put that word in the list at the current position
		}
		System.out.println(fruits);
	}
}