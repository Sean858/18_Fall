package Carwash;

import java.util.Scanner;

public class Carwash {
    final int MAXSIZE=5;    //Maximum line length.
    final int WASHTIME=10;  //Time to wash a car.
    final int INFINITY=999999999; //A real long time.
    LinkedListPureQueue<Car> carQueue;
    StringBuilder results;
    
    int currentTime,
        nextDepartureTime,
        numberOfCars,
        waitingTime,
        sumWaitingTime;
    
    public Carwash(){
        carQueue=new LinkedListPureQueue<Car>();
        results = new StringBuilder();
        results.append("Time\tEvent\t\t\tWaiting Time\n");
        currentTime=0;
        numberOfCars=0;
        waitingTime=0;
        sumWaitingTime=0;
        nextDepartureTime=INFINITY; //Nothing being washed, therefore next departure time is infinitely far away.
    }
    
    //Decides whether to process an arrival or departure.  Departures are given preference.
    public void process(int nextArrivalTime){
        final String BADTIME="The time of the next arrival cannot be before the current time.";
        
        if(nextArrivalTime<currentTime){
            throw new IllegalArgumentException(BADTIME);
        }
        while(nextArrivalTime>=nextDepartureTime){
            processDeparture();
        }
        processArrival(nextArrivalTime);
    }
    
    //Moves a newly arrived car into the queue.
    //Turns a car away if queue is full.
    public void processArrival(int nextArrivalTime){
        final String OVERFLOW= "(Overflow)\n";
        final String ARRIVAL = "\tArrival";
        
        currentTime=nextArrivalTime;
        results.append(Integer.toString(currentTime)+ARRIVAL);
        
        if(carQueue.size()==MAXSIZE){
            //This is an overflow car.
            //Append a message to the results then disregard the car.
            results.append(OVERFLOW);
        }
        else{
            //This is not an overflow car.
            numberOfCars++;
            if(nextDepartureTime==INFINITY){ //no car being washed right now
                nextDepartureTime = currentTime+WASHTIME;
            }
            else{
                carQueue.enqueue(new Car(nextArrivalTime)); //the newly arrived car begins to wait in line.
            }
            results.append("\n");
        }
    }
    
    //Updates simulation to show a car has finished being washed.
    public void processDeparture(){
        final String DEPARTURE = "\tDeparture\t\t";
        int arrivalTime;
        
        currentTime=nextDepartureTime;
        
        results.append(Integer.toString(currentTime)+DEPARTURE+Integer.toString(waitingTime)+"\n");
        
        //If the queue is not empty.
        if(!carQueue.isEmpty()){
            //Remove the next car from the queue and put it into the wash.
            Car car = carQueue.dequeue();
            arrivalTime = car.getArrivalTime();
            waitingTime = currentTime-arrivalTime;
            sumWaitingTime+=waitingTime;
            nextDepartureTime = currentTime+WASHTIME;
        }
        //The queue is empty
        else{
            waitingTime=0;
            nextDepartureTime=INFINITY;
        }
    }
    
    public void finishUp(){
        while(nextDepartureTime<INFINITY){  //continue to process departures while there are unwashed cars.
            processDeparture();
        }
    }
    
    public String getResults(){
        final String NOCARS = "There were no cars\n";
        final String AVERAGE = "\n\nAverage wait time(m) was: ";
        
        if(numberOfCars==0){results.append(NOCARS);}
        else{
            results.append(AVERAGE+Double.toString((double)sumWaitingTime/numberOfCars));
        }
        return results.toString();
    }
    
    
    public static void main(String[] args){
        Carwash myCarwash = new Carwash();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the arrival times of the cars or -1 to begin the simulation");	
        int arrivalTime = Integer.parseInt(s.nextLine());
        
        while(arrivalTime != -1){
            if(arrivalTime<0){System.out.println("Out of range");}
            else{myCarwash.process(arrivalTime);}
            arrivalTime = Integer.parseInt(s.nextLine());
        }
        myCarwash.finishUp();
            
        System.out.println(myCarwash.getResults());
    }
}

class Car{
    int arrivalTime;
    
    public Car(int at){
        arrivalTime=at;
    }
    
    public int getArrivalTime(){
        return arrivalTime;
    }
}
