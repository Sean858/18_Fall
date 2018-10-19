import java.util.Scanner;

public class args3{
	public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a number >");
		int aNumber=in.nextInt();
		
		System.out.println("Enter another number >");
		int anotherNumber=in.nextInt();
		
		int sum = aNumber + anotherNumber;
		
		System.out.println("The sum is: "+sum);
	}
}