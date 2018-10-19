import java.util.Scanner;
import java.util.Stack;

public class SymbolCheck {

    public static void main(String[] args) {
        boolean flag =false;
        Stack input = new Stack();
        System.out.println("Enter your String to check:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        // " num
        int dp = 0;
        // ' num
        int sp = 0;
        //  /* num
        int ss = 0;
        // ascertain whether have input
        int sys = 0;
        for (int i = 0; i < s.length(); i++) {
            if ( s.substring(i, i + 1).equals("{") || s.substring(i, i + 1).equals("(") || s.substring(i, i + 1).equals("[")) {
                input.push(s.substring(i, i + 1));
            }
            else if ( i < s.length() - 1 && s.substring(i, i + 2).equals("//")) {
                break;
            }
            else if ( i < s.length() - 1 && s.substring(i, i + 2).equals("/*")) {
                input.push("/*");
                ss++;
            }
            else if ( i < s.length() - 1 && s.substring(i, i + 2).equals("*/")) {
                if(ss != 0) {
                    while (input.pop().equals("/*")) {
                        break;
                    }
                    ss--;
                }
            }
            else if (s.substring(i, i + 1).equals("\'")) {
                if (sp % 2 == 1 ) {
                    if(input.pop().equals("\'")) {
                        flag = true;
                        sp--;
                        continue;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
                else {
                    input.push(s.substring(i, i + 1));
                    sp++;
                }
            }
            else if (s.substring(i, i + 1).equals("\"")) {
                if (dp % 2 == 1 ) {
                    if(input.pop().equals("\"")) {
                        flag = true;
                        dp--;
                        continue;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
                else {
                    input.push(s.substring(i, i + 1));
                    dp++;
                }
            }
            else if (s.substring(i, i + 1).equals("]")) {
                if (!input.isEmpty() && input.pop().equals("[")) {
                    flag = true;
                    continue;
                }
                else {
                    flag = false;
                    break;
                }
            }
            else if (s.substring(i, i + 1).equals(")")){
                if (!input.isEmpty() && input.pop().equals("(")) {
                    flag = true;
                    continue;
                }
                else {
                    flag = false;
                    break;
                }
            }
            else if (s.substring(i, i + 1).equals("}")) {
                if (!input.isEmpty() && input.pop().equals("{")) {
                    flag = true;
                    continue;
                }
                else {
                    flag = false;
                    break;
                }
            }
            else {
                sys++;
                continue;
            }
        }
        //if have input
        if(sys != 0) {
            flag = true;
        }
        //if there is still some variable in stack
        if (!input.isEmpty()) {
            flag = false;
        }

        if (flag == true)
            System.out.println("Valid String");
        else
            System.out.println("Invalid String");
        scanner.close();

    }

}
