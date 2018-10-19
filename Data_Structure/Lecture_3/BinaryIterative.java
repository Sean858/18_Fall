import java.util.Scanner;

public class BinaryIterative {
	public static void main(String []args) {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the decimal number you would like to convert");
		String askForDecimal = s.nextLine();
		int decimalNumber = Integer.parseInt(askForDecimal);
		String result="";
		
		int remainder = 0;
		while (decimalNumber >0) {
				remainder = decimalNumber % 2;
				decimalNumber /= 2;
				result=remainder+result;
		}
	System.out.println(result);
    }
}