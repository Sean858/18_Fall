public class getBinary{
	public static String getBinary(int n){
		if(n<0){throw new IllegalArgumentException();}
		if(n<=1) {return Integer.toString(n);}
		return getBinary(n/2)+Integer.toString(n%2);
	}
	
	public static void main(String[] args){
		System.out.println(getBinary(25));
	
	}
}