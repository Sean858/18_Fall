package DataStructuresExamples;


public class LinkedPureStack<E> implements PureStack<E>{
    protected Entry<E> top; //The first entry in the linked list and also the top of the stack
    protected int size; //Number of elements in the stack.
    
    //Default constructor.
    public LinkedPureStack(){
        top=null;   //Point the top to nothing.
        size=0;     //Set size to zero.
    }
    
    //Copy constructor
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return size==0;
    }
    
    public void push(E element){
        //Create a new Entry object to hold the new element to be pushed.
        Entry<E> newEntry = new Entry<E>();
        
        //Set the element field of the new entry to the element passed in.
        newEntry.element=element;
        
        //Set the next field of the new entry to what top is pointing to.
        newEntry.next=top;
        
        //Point top at the new entry.  Now the new entry is at the top of the stack and the first item in the list.
        top=newEntry;
        
        //Increment the size field.
        size++;
    }
    
    public E pop(){
        //Store the element field of the first entry in the list, which top points to.
        E element = top.element;
        
        //Tell top to point to the next entry.
        top=top.next;
        
        //Decrement size field.
        size--;
        
        //Return the element stored earlier.
        return element;
    }
    
    public E peek(){
        //Return the element field of the first entry in the list, which top points to.
        return top.element;
    }
    
    protected static class Entry<E>{
        protected E element;
        protected Entry<E> next;
    }
    
    
    public static void main(String[] args){
                LinkedPureStack<Integer> myStack= new LinkedPureStack<Integer>();
		
		myStack.push(28);
		myStack.push(13);
		myStack.push(17);
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		myStack.push(21);
		System.out.println(myStack.peek());;
    }
}
