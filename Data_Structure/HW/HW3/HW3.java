import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HW3 {
    public static void main(String[] args) {
        final int NUMBER = 1000000;
        final int second = 1000000000;
        int[] testData = new int[NUMBER];
        for(int i = 0; i < testData.length; i++) {
            testData[i] = (int)(1000 * Math.random());
        }

        ArrayList testAL = new ArrayList();
        LinkedList testLL = new LinkedList();


        long time1 = System.nanoTime();
        for(int i = 0; i < NUMBER; i++){
            testAL.add(testData[i]);

        }
        long time2 = System.nanoTime();
        System.out.println("ArrayList add: " + String.format("%.9f", (double)(time2 - time1) / second) + "s");
        for(int i = 0; i < NUMBER; i++){
            testLL.add(testData[i]);
        }
        long time3 = System.nanoTime();
        System.out.println("LinkedList add: " + String.format("%.9f", (double)(time3 - time2) / second) + "s");


        Iterator alit = testLL.iterator();
        while(alit.hasNext()) {
            alit.next();
        }
        long time4 = System.nanoTime();
        System.out.println("ArrayList get(Iterator): " + String.format("%.9f", (double)(time4 - time3) / second) + "s");


        for(int i = 0; i < NUMBER; i++){
            testAL.get(i);
        }
        long time5 = System.nanoTime();
        System.out.println("ArrayList get: " + String.format("%.9f", (double)(time5 - time4) / second) + "s");



        Iterator llit = testAL.iterator();
        while(llit.hasNext()) {
            llit.next();
        }
        long time6 = System.nanoTime();
        System.out.println("LinkedList get(Iterator): " + String.format("%.9f", (double)(time6 - time5) / second) + "s");

        for(int i = 0; i < NUMBER; i++){
            testLL.get(i);
        }
        long time7 = System.nanoTime();
        System.out.println("LinkedList get: " + String.format("%.9f", (double)(time7 - time6) / second) + "s");



        while(!testAL.isEmpty()) {
            testAL.remove(0);
        }
        long time8 = System.nanoTime();
        System.out.println("ArrayList remove(front to end): " + String.format("%.9f", (double)(time8 - time7) / second));


        for(int i = 0; i < NUMBER; i++){
            testAL.add(testData[i]);
        }
        long time9 = System.nanoTime();
        for(int i = NUMBER - 1; i >= 0; i--){
            testAL.remove(i);
        }
        long time10 = System.nanoTime();
        System.out.println("ArrayList remove(end to front): " + String.format("%.9f", (double)(time10 - time9) / second) + "s");




        while(!testLL.isEmpty()) {
            testLL.remove(0);
        }
        long time11 = System.nanoTime();
        System.out.println("LinkedList remove(front to end): " + String.format("%.9f", (double)(time11 - time10) / second));

        for(int i = 0; i < NUMBER; i++){
            testLL.add(testData[i]);
        }
        long time12 = System.nanoTime();
        for(int i = NUMBER - 1; i >= 0; i--){
            testLL.remove(i);
        }
        long time13 = System.nanoTime();
        System.out.println("LinkedList remove(end to frond)）: " + String.format("%.9f", (double)(time13 - time12) / second) + "s");
    }
}
