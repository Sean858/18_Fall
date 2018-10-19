
package arraylistdemo;

/**
 *
 * @author garrardw
 */
public class ArrayListDemo<E>{

    private Object[] elementData;
    private int size;
    private final int DEFAULT_INIT_CAPACITY=10;
    private final double DEFAULT_EXPANSION_FACTOR=1.5;
    
    public ArrayListDemo(int initialCapacity){
        this.elementData = new Object[initialCapacity];
        this.size=0;
    }
    
    public ArrayListDemo(){
        this.elementData = new Object[this.DEFAULT_INIT_CAPACITY];
        this.size=0;
    }
    
    public int size(){
        return this.size;
    }
    
    public boolean isEmpty(){
        return this.size==0;
    }
    
    public E get(int index){
        return (E)this.elementData[index];
    }
    
    public E set(int index, E element){
        E temp = (E)this.elementData[index];
        this.elementData[index]=element;
        return temp;
    }
    
    public int indexOf(Object element){
        int result=-1;
        for(int i=0;i<this.elementData.length;i++){
            if(this.elementData.equals(element)){
                result=i;
                break;
            }
        }
        return result;
    }
    
    public void add(E element){
        ensureCapacity(this.size+1);
        this.elementData[size]=element;
        this.size++;
    }

    public void ensureCapacity(int minCapacity){
        int oldCapacity=this.elementData.length;
        if(minCapacity>oldCapacity){
            //Do an expansion and a copy.
            Object[] oldData = this.elementData;
            int newCapacity = (int)((double)oldCapacity*this.DEFAULT_EXPANSION_FACTOR+1);
            if(newCapacity<minCapacity){newCapacity=minCapacity;}//Should never happen.
            this.elementData=new Object[newCapacity];
            System.arraycopy(oldData, 0, this.elementData, 0, this.size);
        }
    }
    
    public E remove(int index){
        E result = (E)this.elementData[index];
        
        for(int i=index+1;i<this.size();i++){
            this.elementData[i-1]=this.elementData[i];
        }
        size--;
        
        return result;
    }
    
    public String toString(){
        String result = "[";
        for(int i=0;i<this.size;i++){
            result+=this.elementData[i].toString()+",";
        }
        result=result.substring(0,result.length()-1);//Remove last comma.
        result+="]";
        return result;
    }
    
    public static void main(String[] args) {
        ArrayListDemo<String> myList = new ArrayListDemo<>(3);
        myList.add("apple");
        myList.add("banana");
        myList.add("carrot");
        myList.add("durian");
        myList.add("elephant");
        System.out.println(myList);
        System.out.println("Size: "+myList.size());
        
        myList.remove(2);
        System.out.println(myList);
        System.out.println("Size: "+myList.size());
        
        
    }

    

}
