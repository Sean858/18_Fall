import java.util.LinkedList;
import java.util.Queue;

//A simple binary tree class.
//Does not do anything fancy other than imitate the abstract binary tree data type.
public class SimpleBinaryTree {
    public Object value;                    //The value of an element.
    private SimpleBinaryTree leftChild;     //The element's left child.
    private SimpleBinaryTree rightChild;    //The element's right child.

    //Create an element and assign it a value and two children.
    public SimpleBinaryTree(Object value, SimpleBinaryTree leftChild, SimpleBinaryTree rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    //Leaf constructor.
    //Create an element and assign it no children.
    public SimpleBinaryTree(Object value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Object getValue() {
        return value;
    }

    public SimpleBinaryTree getLeftChild() {
        return leftChild;
    }

    public SimpleBinaryTree getRightChild() {
        return rightChild;
    }

    public void setLeftChild(SimpleBinaryTree subtree){
        this.leftChild=subtree;
    }

    public void setRightChild(SimpleBinaryTree subtree){
        this.rightChild=subtree;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    //If an element's children are null, it is a leaf.
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public static void inOrder(SimpleBinaryTree t){
        //IMPLEMENT ME
        if (t == null) return;

        inOrder(t.getLeftChild());
        System.out.print(t.value + ", ");
        inOrder(t.getRightChild());

    }
    public static void postOrder(SimpleBinaryTree t){
        //IMPLEMENT ME
        if (t == null) return;

        postOrder(t.getLeftChild());
        postOrder(t.getRightChild());
        System.out.print(t.value + ", ");
    }
    public static void preOrder(SimpleBinaryTree t){
        //IMPLEMENT ME
        if (t == null) return;

        System.out.print(t.value + ", ");
        preOrder(t.getLeftChild());
        preOrder(t.getRightChild());
    }
    public static void breadthFirst(SimpleBinaryTree t){
        //IMPLEMENT ME
        if (t == null) return;
        SimpleBinaryTree node = t;
        Queue myQueue = new LinkedList();
        myQueue.add(node);
        while (!myQueue.isEmpty()) {
            node = (SimpleBinaryTree)myQueue.remove();
            System.out.print(node.value + ", ");
            if(node.getLeftChild() != null) {
                myQueue.add(node.getLeftChild());
            }
            if(node.getRightChild() != null) {
                myQueue.add(node.getRightChild());
            }
        }
    }

    public static void main(String[] args){
        //Create several leaf elements.
        //At first, they are actually 8 different binary trees of one element each.
        SimpleBinaryTree t = new SimpleBinaryTree("A");
        SimpleBinaryTree t1 = new SimpleBinaryTree("B");
        SimpleBinaryTree t2 = new SimpleBinaryTree("C");
        SimpleBinaryTree t3 = new SimpleBinaryTree("D");
        SimpleBinaryTree t4 = new SimpleBinaryTree("E");
        SimpleBinaryTree t5 = new SimpleBinaryTree("F");
        SimpleBinaryTree t6 = new SimpleBinaryTree("G");
        SimpleBinaryTree t7 = new SimpleBinaryTree("H");

        //Link the elements together to form one large binary tree.
        t3.setLeftChild(t7);
        t1.setLeftChild(t3);
        t1.setRightChild(t4);
        t2.setLeftChild(t5);
        t2.setRightChild(t6);
        t.setLeftChild(t1);
        t.setRightChild(t2);

        //Print out the traversals of the tree.
        System.out.println("inOrder: ");
        inOrder(t);
        System.out.println("\npostOrder: ");
        postOrder(t);
        System.out.println("\npreOrder: ");
        preOrder(t);
        System.out.println("\nbreadthFirst: ");
        breadthFirst(t);
        System.out.println();
    }
}
