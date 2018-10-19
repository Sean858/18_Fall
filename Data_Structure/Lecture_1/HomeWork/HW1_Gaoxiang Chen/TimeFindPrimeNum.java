/**
 *
 * @author Gaoxiang Chen
 */

import java.util.ArrayList;

public class TimeFindPrimeNum {
    private long startTime;
    private long endTime;
    private int totalNum;
    private int primeCount;
    private StringBuilder numList;
    private final int stepLength = 10000;
    private final double nanoToS = Math.pow(10, 9);

    public TimeFindPrimeNum(){
    }

    private void setTotalNum(int i){
        this.totalNum = i * stepLength;
    }

    public int getTotalNum(){
        return totalNum;
    }

    public int getPrimeCount(){
        return primeCount;
    }

    private boolean isPrime(int num, ArrayList<Integer> list){
        //We can save time for decrease the total num need to count

        //Approach 1 Only consider half of num
        /*
        if(num == 1) return false;        
        for(int i = 2; i < num / 2 + 1; i++){
            if(num % i == 0) return false;
        }
        return true;
        */

        //Approach 2: Only consider number smaller than sqrt(num)
        /*
        if(num == 1) return false;
        if(num == 2) return true;     
        for(int i = 2; i < Math.sqrt((double)num) + 1; i++){
            if(num % i == 0) return false;
        }
        return true;
        */

        //Approach 3: Only consider 2 and odd number smaller than sqrt(num)
        /* 
        if(num == 1) return false;
        if(num == 2) return true;     
        for(int i = 2; i < Math.sqrt((double)num) + 1; i++){
            if(i != 2 && i % 2 == 0) continue;
            if(num % i == 0) return false;
        }
        return true;
        */

        //Approach 4: Only consider prime number smaller than sqrt(num), use Arraylist to store prime numbers
                     
        if(num == 1) return false;
        if(num == 2) {
            list.add(2);
            return true;
        }      
        double max = Math.sqrt((double)num) + 1;
        for(int i = 0; i < list.size() && (int)list.get(i) < max; i++){
            int j = (int) list.get(i);
            if(num % j == 0) return false;
        }    
        return true;
        
    }

    private void countPrimeNumAndTime() {
        numList = new StringBuilder();
        this.primeCount = 0;
        this.startTime = System.nanoTime();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i < totalNum; i++) {
            if (isPrime(i,list)) {
                numList.append(i);
                numList.append(" ");
                primeCount++;
                list.add(i);
            }
        }
        this.endTime = System.nanoTime();
    }

    public StringBuilder getPrimeNumList() {
        return numList;
    }

    private double getCostSeconds(){
        //Return (double)(endTime - startTime) * 1000 / (nanoToS * 1000.0);
        //Return the number with three decimals.
        return Math.round(((double)(endTime - startTime)/nanoToS) * 1000) / 1000.0;
    }

    public static void main(String[] args){
        TimeFindPrimeNum s = new TimeFindPrimeNum();
        //Set the step from 1-10, represent 10000 - 100000, stepLength is 10000
        for(int i = 1; i <= 10; i ++) {
            s.setTotalNum(i);
            s.countPrimeNumAndTime();
            System.out.println(s.getPrimeCount() + " primes in 1 - " + s.getTotalNum() +
                            ", it takes " + s.getCostSeconds() + "s to find them all.");
            /*If need, it can list the num
            System.out.println("List here: " + s.getPrimeNumList().toString());*/
        }
	}
}





