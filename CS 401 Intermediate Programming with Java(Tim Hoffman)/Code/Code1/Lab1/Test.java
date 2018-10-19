import java.util.*;
import java.io.*;
public class Test{
	public static void main(String[] args){
	    Scanner kbd = new Scanner(System.in);
	    String name;
	    int age;
	    System.out.print("The name is:");
	    name = kbd.next();
	    System.out.print("The age is:");
	    age = kbd.nextInt();
		System.out.println(name + "'s age is:" + age);
	}
}

