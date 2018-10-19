import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LLExample{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		LinkedList<String> myList = new LinkedList<String>();
		
		//Adding
		System.out.println("Please write a sentence");
		String[] words = s.nextLine().split(" ");
		
		for(String word : words){
			myList.add(word);
		}
		
		System.out.println(myList);
		
		//Searching
		System.out.println("Please enter a word in the sentence to search for");
		String target = s.nextLine();
		if(myList.indexOf(target)>=0){
			System.out.println(target+" was found");
		}
		else{
			System.out.println(target+" was not found");
		}
		
		//Removing
		//For removing all instances of a target from a LinkedList, 
		//the iterator remove method is better to use than the 
		//LinkedList remove method because the iterator remove is O(1) 
		//while the LinkedList remove is O(n).
		System.out.println("Please enter a word in the sentence to remove");
		target = s.nextLine();
		int count=0;
		ListIterator<String> itr = myList.listIterator();
		while(itr.hasNext()){
			if(itr.next().equals(target)){
				itr.remove();
				count++;
			}
		}
		System.out.println("removed "+count+" instances of "+target);
		System.out.println(myList);
		
		//Inserting
		System.out.println("Please enter a word to add to the sentence");
		target = s.nextLine();
		System.out.println("Please enter a position to add the word to");
		int index = s.nextInt();
		
		myList.set(index,target);
		System.out.println(myList);
	}
}
