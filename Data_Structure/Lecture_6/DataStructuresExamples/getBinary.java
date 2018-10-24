package DataStructuresExamples;

import java.util.Scanner;


public class getBinary {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a positive base-10 integer");
        int n = Integer.parseInt(s.nextLine());
        System.out.println("The binary equivalent is: "+getBinary(n));  //Return address 1
        System.out.println("The binary equivalent is: "+getBinaryIterative(n));  //Return address 1
    }
    
    public static String getBinary(int n){
        if(n<0){ throw new IllegalArgumentException();}
        
        if(n<=1){
            return Integer.toString(n);
        }
        
        return getBinary(n/2)+Integer.toString(n%2);    //Return address 2
    }
    
    public static String getBinaryIterative(int n){
        ArrayPureStack<Integer> myStack = new ArrayPureStack<Integer>();
        String result = new String();
        
        if(n<0){throw new IllegalArgumentException();}
        
        myStack.push(n%2);
        while(n>1){
            n/=2;
            myStack.push(n%2);
        }
        
        while(!myStack.isEmpty()){
            result+=myStack.pop().toString();
        }
        
        return result;
    }
}
