import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SinglyLinkedList<E> implements List<E>{
    protected Entry<E> head;	//reference to the first entry

    public SinglyLinkedList(){
        head=null;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public boolean add(E element){
        Entry<E> newEntry = new Entry<E>();
        newEntry.element=element;
        newEntry.next=head;
        head=newEntry;
        return true;
    }

    public int size(){
        int count=0;

        for(Entry<E> current = head; current!=null; current=current.next){
            count++;
        }
        return count;
    }

    public boolean contains(Object obj){
        if(obj==null){
            for(Entry<E> current=head;current!=null;current=current.next){
                if(current.element==null){
                    return true;
                }
            }
        }
        else {
            for(Entry<E> current=head;current!=null;current=current.next){
                if(obj.equals(current.element)){
                    return true;
                }
            }
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    //Nested class
    protected class SinglyLinkedListIterator implements Iterator<E>{
        protected Entry<E> next;

        public SinglyLinkedListIterator(){
            next=head;
        }
        public boolean hasNext(){
            return next!=null;
        }
        public E next(){
            E theElement = next.element;
            next=next.next;
            return theElement;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }  
    
    //Nested class
    protected class Entry<E>{
	protected E element;		//element
	protected Entry<E> next;	//link
    }
    
    
    public static void main(String[] args){
        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        
        sll.add("a");
        sll.add("b");
        sll.add("c");
        Iterator<String> i = sll.iterator();
        System.out.println(sll);
        
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
}