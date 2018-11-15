import java.util.*;
import java.io.*;

public class Potus
{
    public static void main( String[] args ) throws Exception
    {
        BufferedReader infile1 = new BufferedReader( new FileReader("state2Presidents.txt") );
        BufferedReader infile2 = new BufferedReader( new FileReader("allPresidents.txt") );
        BufferedReader infile3 = new BufferedReader( new FileReader("allStates.txt") );

        // YOUR CODE HERE TO DECLARE MAPS OR SETS TO HOLD THE DATA OF THESE THREE INPUT FILES
        TreeMap<String, TreeSet<String>> state2Presidents = new TreeMap<>();
        TreeMap<String, String> president2State = new TreeMap<>();
        TreeSet<String> all_presidents = new TreeSet<>();
        TreeSet<String> presidents = new TreeSet<>();
        TreeSet<String> all_states = new TreeSet<>();

        // ######### STEP #1: READ INFILE 1 INTO A MAP AND PRINT IT FORMATTED. worth 70% ##########

        // YOUR CODE HERE TO READ INFILE1 INTO A MAP AND PRINT IT
        while (infile1.ready()) {
            presidents = new TreeSet<>();
            String[] president = infile1.readLine().split(" ");
            for (int i = 1; i < president.length; i++) {
                presidents.add(president[i]);
                president2State.put(president[i], president[0]);
            }
            state2Presidents.put(president[0], presidents);
        }
        for(String s : state2Presidents.keySet()) {
            System.out.print(s);
            for (String p : state2Presidents.get(s)) {
                System.out.print(" " + p);
            }
            System.out.println();
        }

        // ######### STEP #2: PRINT THE INVERSION OF THE MAP. worth 15% #############
        System.out.println(); // LEAVE THIS HERE TO PUT A BLANK LINE BETWEEN SECTIONS

        // YOUR CODE HERE TO PRINT THE INVERSION OF THE MAP

        for(String s : president2State.keySet()) {
            System.out.println(s + " " + president2State.get(s));
        }

        // ############ STEP #3: PRINT THE NAMES OF PRESIDENTS BORN BEFORE STATES FORMED worth 10% ##########
        System.out.println(); // LEAVE THIS HERE TO PUT A BLANK LINE BETWEEN SECTIONS

        // YOUR CODE HERE TO PRINT THE NAMES OF ALL PRESIDENTS BORN BEFORE STATES FORMED
        while(infile2.ready()) {
            all_presidents.add(infile2.readLine());
        }
        for(String s : all_presidents) {
            if (!president2State.keySet().contains(s)) {
                System.out.println(s);
            }
        }

        // ###STEP #4 PRINT THE NAME(S) OF ANY STATE(s) WHICH HAD NO PRESIDENT BORN IN THEM worth 5% #######
        System.out.println(); // LEAVE THIS HERE TO PUT A BLANK LINE BETWEEN SECTIONS

        // YOUR CODE HERE TO PRINT THE NAME(S) OF ANY STATE(s) WHICH HAD NO PRESIDENT BORN IN THEM
        while(infile3.ready()) {
            all_states.add(infile3.readLine());
        }
        for(String s : all_states) {
            if (!state2Presidents.keySet().contains(s)) {
                System.out.println(s);
            }
        }
        
    } // END MAIN

    //              - - - - - - - - - - -  H E L P E R    M E T H O D S     D O W N    H E R E  - - - - - - - - - -

}   // END POTUS CLASS