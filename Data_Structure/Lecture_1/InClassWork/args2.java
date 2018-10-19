public class args2{
	public static void main (String[] args){
		int aNumber=Integer.parseInt(args[0]);
		int anotherNumber=Integer.parseInt(args[1]);
		int sum = aNumber + anotherNumber;
		
		System.out.println("The sum is: "+sum);
	}
}