import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class VeryLongInt{
	protected ArrayList<Integer> digits;
	
	public VeryLongInt(String s){
		digits=new ArrayList<Integer>(s.length());
		char c;
		int digit;
		
		for(int i=0;i<s.length();i++){	//for each char in the input string
			c=s.charAt(i);	//get the current character
			if(Character.isDigit(c)){	//exclude non-numerics
				digit=c-'0';	//subtract 32 from the ASCII value of the char to get real numeric value.
				digits.add(digit);	//boxed to Integer, added to ArrayList field
			}
		}
	}
	
	public String toString(){
		return digits.toString();
	}
	
	public void add (VeryLongInt otherVeryLong){	//Arithmatic addition of two very long ints
		//First add each object in each VeryLongInt together separately starting with ones place
		//Then add each partial sum to the result mod 10 (because we are in base 10)
		
		final int BASE=10;
		
		int largerSize,partialSum,carry=0;
		
		ArrayList<Integer> sumDigits = new ArrayList<Integer>();	//our result
		
		//Determine which input has more digits.
		if(digits.size() > otherVeryLong.digits.size()){
			largerSize=digits.size();
		}
		else{
			largerSize=otherVeryLong.digits.size();
		}
		
		for(int i=0;i<largerSize;i++){
			partialSum=least(i) + otherVeryLong.least(i) + carry;	//the result of the addition of the two least-ordered elements not already added
			carry = partialSum/BASE;	//the tens portion of the addition.
			sumDigits.add(partialSum%BASE);	//arraylist add.  append the ones place of the addition to the result arraylist
		}
		
		if(carry==1){	//if the last addition resulted in a carry
			sumDigits.add(carry);
		}
		
		//At this point sumDigits has the least significant digit of the sum at index 0.
		//Reverse it to fit our scheme
		Collections.reverse(sumDigits);
		
		//Set our data field to equal our result
		digits = sumDigits;
		
	}
	
	protected int least(int i){
		if(i>=digits.size()){
			return 0;
		}
		return digits.get(digits.size()-i-1);
	}
	
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
        
		int choice=0;
		while(choice!=2){
			System.out.println("Enter 1 to continue or 2 to quit");
			choice=Integer.parseInt(keyboard.nextLine());
			if(choice==1){
				System.out.println("Enter an integer");
				VeryLongInt vli = new VeryLongInt(keyboard.nextLine());
				
				System.out.println("The integer entered is: "+vli);
				
				System.out.println("Enter an integer to add to it");
				vli.add(new VeryLongInt(keyboard.nextLine()));
				
				System.out.println("The result of the addition is: "+vli);
			}
			else if(choice==2){
				System.out.println("Goodbye");
				continue;
			}
			else{
				System.out.println("Invalid choice");
			}
		}
	}
}