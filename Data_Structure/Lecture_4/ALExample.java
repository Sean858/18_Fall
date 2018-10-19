import java.util.ArrayList;
import java.util.Scanner;

public class ALExample{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		
		ArrayList<String> myList = new ArrayList<String>();
		
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
		System.out.println("Please enter a word in the sentence to remove");
		target = s.nextLine();
		int count=0;
		while(myList.remove(target)){
			count++;
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