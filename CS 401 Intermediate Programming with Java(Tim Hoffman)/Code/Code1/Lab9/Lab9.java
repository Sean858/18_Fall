import java.util.HashSet;
import java.util.*;
import java.io.*;

public class Lab9
{
	public static void main(String args[]) throws Exception
	{
		HashSet<String> set = new HashSet<String>();
		Scanner infile = new Scanner( new File( args[0] ) );
		long sTime = System.nanoTime();
		String word = ""; // infile1.txt
		while ( infile.hasNext() ) {
			word = infile.next();
			if (set.add(word)) {
				continue;
			}
			System.out.println("NOT UNIQUE");
			System.exit(0);
		}
		System.out.println("UNIQUE");
		infile.close();	

		
	} // END MAIN
} // CLASS WOODCHUCKSET

