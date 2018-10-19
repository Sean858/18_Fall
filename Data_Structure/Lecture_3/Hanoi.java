import java.util.Scanner;

public class Hanoi{
	static int count=0;
	
	public static void main (String[] args){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter how many disks you want to play with: ");
		int disks = s.nextInt();
		System.out.println(move(disks,'A','B','C'));
		System.out.println("Total moves: "+count);
	}
	public static String move(int n, char orig, char dest, char temp){
		final String DIRECT_MOVE="Move disk "+ n +" from " + orig + " to " + dest + "\n";
		
		if(n<1){throw new IllegalArgumentException();}
		
		if(n==1){
			count++;
			return DIRECT_MOVE;
		} 
		
		String result=move(n-1,orig,temp,dest);
		result+=DIRECT_MOVE;
		result+= move(n-1,temp,dest,orig);
		count ++;
		return result;
	}
}
