package BinarySearchTree;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<E> extends AbstractSet<E> {
    protected Entry<E> root;
    protected int size;
    
    //Default constructor.
    //Creates an empty tree, with size=0;
    public BinarySearchTree(){
        root=null;
        size=0;
    }
    
    public int size(){
        return size;
    }
    
    public Iterator<E> iterator(){
        return new TreeIterator();
    }
    
    public boolean contains(Object obj){
        Entry<E> temp=root; //An object that will travel the tree.
        int comparison; //Holds the result of the compareTo method.
        
        while(temp!=null){
            //We cast obj as a Comparable to ensure compareTo can be perfomed.
            comparison=((Comparable)obj).compareTo(temp.element);
            
            //Element is found.
            if(comparison==0){
                return true;
            }
            //Element would be to the left.
            else if(comparison<0){
                temp=temp.left;
            }
            //Element would be to the right.
            else{
                temp=temp.right;
            }
        }
        //The loop ended without finding something equal to obj, 
        //therefore it is not in the tree.
        return false;
    }
    
    public boolean add(E element){
        //Case where the tree is empty.
        //Set root to point to a new entry object with the element in it.
        //Increment size.
        //End the method.
        if(root==null){
            root = new Entry<E>(element,null);
            size++;
            return true;
        }
        
        //If the tree is not empty.
        else{
            Entry<E> temp=root; //An object that will travel the tree.
            int comparison; //Holds the result of the compareTo method.

            //Loop forever until we reach a return statement.
            while(true){
                //We cast element as a Comparable to ensure compareTo can be perfomed.
                comparison=((Comparable)element).compareTo(temp.element);

                //Element is found in the tree.
                //We don't allow duplicates in BST so return false.
                //End the method.
                if(comparison==0){
                    return false;
                }
                
                //Case where element should be to the left.
                else if(comparison<0){
                    //Check to see if the left subtree is empty.
                    //If it is not empty, move there.
                    if(temp.left!=null){
                        temp=temp.left;
                    }
                    //If it is empty, put the element there.
                    //Set its parent as the current element pointed to by temp.
                    //Increment size.
                    //End the method.
                    else{
                        temp.left = new Entry<E>(element,temp);
                        size++;
                        return true;
                    }
                }
                
                //Case where element should be to the right.
                else{
                    //Check to see if the right subtree is empty.
                    //If it is not empty, move there.
                    if(temp.right!=null){
                        temp=temp.right;
                    }
                    //If it is empty, put the element there.
                    //Set its parent as the current element pointed to by temp.
                    //Increment size.
                    //End the method.
                    else{
                        temp.right = new Entry<E>(element,temp);
                        size++;
                        return true;
                    }
                }
            } 
        }
    }
    
    //Get a reference to the element to be removed.
    //If the element does not exist, return false.
    //Else, delete the element.
    public boolean remove(Object obj){
        Entry<E> e = getEntry(obj);
        if(e==null){
            return false;
        }
        deleteEntry(e);
        return true;
    }
    
    //Search the tree just like contains() and then return a reference to the element found.
    //If not found, return null.
    protected Entry<E> getEntry(Object obj){
        int comparison;
        
        Entry<E> e = root;
        while (e!=null){
            //Compare the current element to the target element.
            comparison=((Comparable)obj).compareTo(e.element);
            //We have found the element
            if(comparison==0){
                return e;
            }
            //Move to the left child.
            else if(comparison<0){
                e=e.left;
            }
            //Move to the right child.
            else{
                e=e.right;
            }
        }
        //We've searched and not found the target element.
        //Return null, there's nothing to delete.
        return null;
    }
    
    protected Entry<E> deleteEntry(Entry<E> p){
        //p is the Entry to be deleted from the tree.
        //First, decrement size.
        size--;
        
        //Determine the case we have.
        
        //Case where p has 2 children
        if(p.left!=null && p.right!=null){
            //Create a reference to p's successor.
            Entry<E> s = successor(p);
            //Replace p.element with s.element.
            p.element = s.element;
            //Make p point to s, because it needs to be deleted now.  Handled below.
            p=s;
        }
        
        //Handle further deletion.
        //At this point, p either has 1 or 0 children.
        Entry<E> replacement;
        if(p.left!=null){
            replacement = p.left;
        }
        else{
            replacement = p.right;
        }
        
        //At this point replacement is either pointing to the only child of p or to null.
        //Case where p has 1 child,
        if(replacement!=null){
            //Set that child's parent to p's parent.
            replacement.parent = p.parent;
            //It could be that p is the root. (whose parent is null)
            //In that case, set the root field to equal our replacement.
            if(p.parent==null){
                root=replacement;
            }
            //If p is not the root, determine if p is a left or right child.
            //p will equal its parent's child in the correct direction.
            //Once the correct direction is found, set p's parent's child in that direction to equal the replacement.
            else if(p==p.parent.left){
                p.parent.left=replacement;
            }
            else{
                p.parent.right=replacement;
            }
        }
        
        //Case where p has 0 children.
        else if(p.parent==null){
            //If p has no children and no parent, it is a lone root.
            //Therefore set the root field to null.
            root=null;  
        }
        else{
            //p has a parent but no children. (leaf)
            //Set its parent's child reference to null in the correct direction.
            if(p==p.parent.left){
                p.parent.left=null;
            }
            else{
                p.parent.right=null;
            }
        }
        //Finally, return the Entry that was deleted.
        return p;
    }
    
    
    protected Entry<E> successor(Entry<E> e){
        //Case of an empty tree
        if(e==null){
            return null;
        }
        //Case of a right child existing
        else if(e.right!=null){
            //Go to leftmost Entry in rightTree(e).
            //First move one to the right then loop til left is null.
            Entry<E> p = e.right;
            while(p.left!=null){
                p=p.left;
            }
            //p is now pointing to the Entry that is the successor to e, so return it.
            return p;
        }
        //Case of no right child.
        else{
            //Go up-left from e as far as possible.
            //Then go to the parent of that spot.
            Entry<E> p = e.parent;
            Entry<E> child = e;
            while(p!=null && child == p.right){
                child=p;
                p=p.parent;
            }
            //p is now pointing to the Entry that is the successor to e, so return it.
            return p;
        }
    }
    
    protected void rotateLeft(Entry p){
        //p is the element around which the rotation will take place.
        //No elements physically move, we simply adjust a lot of pointers.
        //Operates in O(1).
        Entry r = p.right;  //Reference to p's right child.
        p.right=r.left; //Right subtree of p becomes the left subtree of r.
        
        //Adjust parent links.
        if(r.left!=null){
            r.left.parent=p;
        }
        r.parent=p.parent;
        
        //If p is the root, we need to reassign our root field.
        if(p.parent==null){
            root=r;
        }
        
        //Case where p is a left child.
        else if(p.parent.left==p){
            p.parent.left=r;
        }
        
        //Case where p is a right child.
        else{
            p.parent.right=r;
        }
        
        r.left=p; //r's left child becomes p.
        p.parent=r; //p's parent becomes r.
    }
    
    protected void rotateRight(Entry p){
        //p is the element around which the rotation will take place.
        //No elements physically move, we simply adjust a lot of pointers.
        //Operates in O(1).
        Entry l = p.left;  //Reference to p's left child.
        p.left=l.right; //Left subtree of p becomes the right subtree of r.
        
        //Adjust parent links.
        if(l.right!=null){
            l.right.parent=p;
        }
        l.parent=p.parent;
        
        //If p is the root, we need to reassign our root field.
        if(p.parent==null){
            root=l;
        }
        
        //Case where p is a right child.
        else if(p.parent.right==p){
            p.parent.right=l;
        }
        
        //Case where p is a left child.
        else{
            p.parent.left=l;
        }
        
        l.right=p; //r's right child becomes p.
        p.parent=l; //p's parent becomes r.
    }
    
    public static void main(String[] args){
        BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
        tree1.add("yes");
        tree1.add("no");
        tree1.add("maybe");
        tree1.add("always");
        tree1.add("no");    //Should not be added, is a duplicate.
        
        if(tree1.remove("often")){
            //We should never get inside this block because the element "often" was not added to the tree.
            System.out.println("how did that happen?");
        }
        else{
            //What will this print?
            System.out.println(tree1.remove("maybe"));
        }
        
        //Print the whole tree.
        //The toString() method is inherited from AbstractCollection via AbstractSet.
        System.out.println(tree1);
        
        
        //A new tree of students.
        BinarySearchTree<Student> tree2 = new BinarySearchTree<Student>();
        
        //Create 3 students.
        Student s1=new Student("Jones",3.17);
        Student s2=new Student("Smith",3.82);
        Student s3=new Student("Jones",3.5);
        
        //Add them to the tree.
        tree2.add(s1);
        tree2.add(s2);
        tree2.add(s3);
        
        if(tree2.contains(s2)){
            System.out.println("The number of elements in tree2 is "+ tree2.size());
        }
        
        //Print out the tree.
        System.out.println(tree2);
    }
    
    //Nested class Entry, represents elements in the tree.
    protected static class Entry<E>{
        protected E element;
        protected Entry<E> left=null,right=null,parent;
        
        public Entry(E element,Entry<E> parent){
            this.element=element;
            this.parent=parent;
        }
    }
    
    protected class TreeIterator implements Iterator<E>{
        //A reference to the last element returned by next 
        //and the next element that will be returned by next.
        protected Entry<E> lastReturned=null,next;
        
        //Default constructor.
        //For inOrder traversal, start at leftmost Entry in the tree.
        protected TreeIterator(){
            //Root is the only element in the tree we have a direct reference to so we must start there.            
            next=root;
            //Then, move to the leftmost element in the tree.
            if(next!=null){
                while(next.left!=null){
                    next=next.left;
                }
            }
            //Next is now pointing to the leftmost element in the tree.
        }
        
        //If the next element to be returned is null, then there is nothing next.
        public boolean hasNext(){
            return next!=null;
        }
        
        //We can utilize the successor method here.
        public E next(){
            //A holder for our result.
            E result=null;
            //If there is another Entry ahead,
            if(this.hasNext()){
                //Set lastReturned to the next Entry in line.
                lastReturned=next;
                //Set next to it's successor.
                next=successor(next);
                //Set result to the element stored in lastReturned.
                result=lastReturned.element;
            }
            return result;
        }
        
        //Removes the entry that was last returned.
        //We can utilize the method deleteEntry and the field lastReturned.
        public void remove(){
            if(lastReturned==null){
                //We can't do anything, so throw an exception to avoid an error calling deleteEntry(null)
                throw new NoSuchElementException();
            }
            //If the Entry we want to delete has two children, then the deleteEntry method will end up with us having 
            //the next field pointing to an Entry no longer in the tree.  
            //Therefore, we must set next to lastReturned before calling that method in this case.
            if(lastReturned.left!=null && lastReturned.right!=null){
                next=lastReturned;
            }
            deleteEntry(lastReturned);
            
            //At this point, we set lastReturned to null because the Entry is no longer in the tree.
            lastReturned=null;
        }
    }
}
