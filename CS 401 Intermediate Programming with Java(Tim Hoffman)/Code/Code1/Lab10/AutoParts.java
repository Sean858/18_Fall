import java.io.*;
import java.util.TreeMap;

public class AutoParts
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader num2quantFile = new BufferedReader( new FileReader("num2quant.txt") );
		BufferedReader num2nameFile = new BufferedReader( new FileReader("num2name.txt") );
        TreeMap<String, String> num2name = new TreeMap<>();
        TreeMap<String, String> num2quant = new TreeMap<>();
		
		//  STEP #1: read num2name.txt into a map named num2name and print that map part#<TAB>partName sorted vertically by part#
		System.out.println("Map of part # => name:"); // LEAVE THIS IN HERE
		while(num2nameFile.ready()) {
		    String[] temp = num2nameFile.readLine().split("\t");
		    num2name.put(temp[0], temp[1]);
        }
        for(String s : num2name.keySet()) {
            System.out.println(s + "\t" + num2name.get(s));
        }

		//  STEP #2: read num2quant.txt into map of same name. DON'T PRINT map#2
		//  Use both 1st & 2nd map to print a joint of the two tables: part#<TAB>name<TAB>quantity sorted vertically again by part#
		System.out.println("Join of part# => name => quantity:"); // LEAVE THIS HERE
        while(num2quantFile.ready()) {
            String[] temp = num2quantFile.readLine().split("\t");
            num2quant.put(temp[0], temp[1]);
        }

        for(String s : num2name.keySet()) {
            System.out.println(s + "\t" + num2name.get(s) + "\t" + num2quant.get(s));
        }

	} // END MAIN

} // END CLASS