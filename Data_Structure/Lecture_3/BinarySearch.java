import java.util.Scanner;

public class BinarySearch{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		Integer[] integers = {1,2,3,4,5,6,7,8,9};
		for(int i=0;i<integers.length;i++){
			System.out.print(integers[i]+" ");
		}
		
		System.out.println("\nWhat value do you want to search for?");
		Integer key = s.nextInt();
		int searchResult=binarySearch(integers,0,integers.length,key);
		System.out.println("The value "+key+" was found at position "+searchResult);
	}
	
	public static int binarySearch(Object[] myArray, int first, int last, Object key){
		int mid = (first+last)/2;
		
		Comparable midVal=(Comparable) myArray[mid];
		int compareResult=midVal.compareTo(key);
		if(compareResult<0){
			return binarySearch(myArray, mid+1,last,key);	//key must be in the last half
		}
		if(compareResult>0){
			return binarySearch(myArray, first, mid-1,key);	//key must be in the first half
		}
		return mid;	//key was in the middle position.
	}
}