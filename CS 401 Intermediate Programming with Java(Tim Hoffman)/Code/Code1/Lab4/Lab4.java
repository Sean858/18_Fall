import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lab4 {

    public static void main(String[] args) throws Exception{
         if (args.length < 1 )
        {
            System.out.println("\nEnter: java Lab4 <input filename>\n\n"); // i.e. C:\> java Lab4 input.txt
            System.exit(0);
        }
        BufferedReader infile = new BufferedReader (new FileReader(args[0]));
        ArrayList<String> dictList = new ArrayList<String>();
        while(infile.ready()){
            dictList.add(infile.readLine());
        }
        Collections.sort(dictList);

        for(String tmp : dictList){       
            System.out.println(tmp + " " + canonical(tmp));
        }
    }

    public static String canonical(String tmp){
        char[] atmp = tmp.toCharArray();
        Arrays.sort(atmp);
        return new String(atmp);
    }
}
