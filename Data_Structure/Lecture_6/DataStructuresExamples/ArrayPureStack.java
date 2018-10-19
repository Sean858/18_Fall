package DataStructuresExamples;

public class ArrayPureStack<E> implements PureStack<E>{
	protected E[] data;		//An array to store data elements in.
	protected int size;		//A counter to keep track of how many elements are in the array.
	
	//Default constructor.
	public ArrayPureStack(){
		//Assign the default length as 10.
		final int DEFAULT_INITIAL_CAPACITY=10;
		
		//Initialize data as an array of type E and default length.
		data=(E[]) new Object[DEFAULT_INITIAL_CAPACITY];
		
		//Initialize size to 0 because it is empty.
		size=0;
	}
	
	//Copy constructor.
	public ArrayPureStack(ArrayPureStack<E> otherStack){
		//New capacity is 110% that of the old capacity.
		final int CAPACITY=(int)(otherStack.size*1.10);
		
		//Initialize data as an array of type E and length CAPACITY.
		data = (E[])new Object[CAPACITY];
		
		//Initialize size equal to the other stack's size.
		size=otherStack.size;
		
		//Copy all the elements from the other stack to our stack.
		System.arraycopy(otherStack.data,0,data,0,otherStack.size);
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	//Put a new element at the top of the stack, which corresponds to the smallest unallocated index in the array.
	//If the array is full, double its size then add the new element.
	//Finally, increment size.
	public void push(E element){
		//If the array is full,
		if(size==data.length){
			//Create a temp array of length 2x the current data array.
			E[] temp = (E[]) new Object[data.length *2];
			//Copy all the elements from data to temp.
			System.arraycopy(data,0,temp,0,size);
			//Point data to temp.
			data=temp;
		}
		data[size]=element;	//Put the new element at index size.  Size will always be the smallest unallocated index.
		size++;	//Increment size now we've added a new element.
	}
	
	//Decrement size.
	//Return what is at index size.
	//The index size is now 'unallocated' but the element is not explicitly removed.
	//It is just forgotten about.  The next push will overwrite it.
        //Board example***
	public E pop(){
		size--;
		return data[size];
	}
	
	//Return what is at the largest allocated index.
	public E peek(){
		return data[size-1];
	}
	
	public static void main(String[] args){
		ArrayPureStack<Integer> myStack= new ArrayPureStack<Integer>();
		
		myStack.push(28);
		myStack.push(13);
		myStack.push(17);
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		myStack.push(21);
		System.out.println(myStack.peek());;
	}
}