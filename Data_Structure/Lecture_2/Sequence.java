import java.util.Scanner;
public class Sequence<E>{
    protected Object[] data;
    protected int size;

    public Sequence(int n) throws IllegalArgumentException{
        if(n<0){
            throw new IllegalArgumentException();}
        this.data=new Object[n];
        this.size=0;
    }

    public int size(){
        return this.size;
    }

    public void append(E element){
        if(this.size<this.data.length){
            this.data[this.size]=element;
        }
        else{
            Object[] temp=new Object[this.size+1];
            System.arraycopy(this.data, 0, temp, 0, this.size);
            this.data=temp;
            this.data[this.size]=element;
        }
        this.size++;
    }

    public Object get(int k) throws IndexOutOfBoundsException{
        if(k<0 || k>=this.size){
            throw new IndexOutOfBoundsException();}
        return this.data[k];
    }

    public String toString(){
        /*
        System.out.print("\nCurrent Sequence: ");
        for(int i=0;i<this.size();i++){
            System.out.print(this.get(i)+" ");
        }
        System.out.println();
        */
        String s = "\nCurrent Sequence: ";
        for(int i=0;i<this.size();i++){
            s += this.get(i)+ " ";
        }
        s += "\n";
        return s;

    }

    public void set(int index, E newElement) throws IndexOutOfBoundsException{
        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException();}
        this.data[index] = newElement;//IMPLEMENT ME
    }

    public void delete(int index){
        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException();}
        for(int i = index; i < this.size() - 1; i++){
            this.data[i] = this.data[i + 1];
        }
        this.size--;
        //IMPLEMENT ME
    }

    public static void main (String[] args){
        Sequence<Object> s = new Sequence<Object>(5);
        s.append(5);
        s.append('s');
        s.append("ss");
        //Add some values with append.

        Scanner kbd = new Scanner(System.in);
        while(kbd.hasNextLine()){
            String str = kbd.nextLine();
            if(str.equals("")) break; //Enter an empty line for end the loop.
            s.append(str);
        }

        System.out.println(s.toString());
        //Write control loop that takes input from the keyboard.

    }   //End main
}   //End class
