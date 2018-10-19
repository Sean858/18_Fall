import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Jumbles {

    public static void main(String[] args) throws Exception{
         if (args.length < 2 )
        {
            System.out.println("\nEnter: java Jumbles <input filename> <input filename>\n\n"); // i.e. C:\> java Lab4 input.txt
            System.exit(0);
        }
        BufferedReader dfile = new BufferedReader (new FileReader(args[0]));
        BufferedReader jfile = new BufferedReader (new FileReader(args[1]));

        ArrayList<String> pairs = new ArrayList<String>();
        while(dfile.ready()){
            String dWord = dfile.readLine();
            pairs.add(canonical(dWord) + " " + dWord);
        }
        Collections.sort(pairs);
        
        ArrayList<String> dCanons = new ArrayList<String>();
        ArrayList<String> dWords = new ArrayList<String>();

        for(String line : pairs){       
            String[] pair = line.split("\\s+");
            dCanons.add(pair[0]);
            dWords.add(pair[1]);
        }

        ArrayList<String> jWords = new ArrayList<String>();
        while(jfile.ready()){
            jWords.add(jfile.readLine());
        }
        Collections.sort(jWords);

        printFinalRes(dCanons, dWords, jWords);

    }

    public static void printFinalRes(ArrayList<String> dCanons, ArrayList<String> dWords, ArrayList<String> jWords){
        for(String jWord : jWords){
            System.out.print(jWord);
            String jCanon = canonical(jWord);
            int index = Collections.binarySearch(dCanons, jCanon);
        
            if(index < 0) {
                System.out.println();
            }
            else{
                ArrayList<String> jRes = new ArrayList<String>();
                jRes.add(dWords.get(index));
                int i = index;
                int j = index;
                while(i > 0){
                    if(dCanons.get(--i).equals(jCanon)){
                        jRes.add(dWords.get(i));
                    }
                    else break;          
                }
                while(j < dCanons.size() - 1){
                    if(dCanons.get(++j).equals(jCanon)){
                        jRes.add(dWords.get(j));
                    }
                    else break; 
                }
                Collections.sort(jRes);
                for(String result : jRes){
                    System.out.print(" " + result);
                }
                System.out.println();
            }
        }
    }

    public static String canonical(String tmp){
        char[] atmp = tmp.toCharArray();
        Arrays.sort(atmp);
        return new String(atmp);
    }

}
