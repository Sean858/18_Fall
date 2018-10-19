package BinarySearchTree;

//A simple class representing a student.
public class Student implements Comparable{
    protected String name;  //The student's name.
    protected double gpa;   //The student's grade point average.
    
    //Default constructor.
    public Student(){}
    
    //Two-parameter constructor.
    public Student(String n,double g){
        this.name=n;
        this.gpa=g;
    }
    
    //Overriding the compareTo method because we implemented Comparable interface.
    //This method puts students in alphabetical order by name first then descending gpa.
    public int compareTo(Object obj) {
        Student other = (Student) obj;
        if (name.compareTo(other.name) < 0) {
            return -1;
        }
        if (name.compareTo(other.name) > 0) {
            return 1;
        }
        if (gpa > other.gpa) {
            return -1;
        }
        if (gpa < other.gpa) {
            return 1;
        }
        return 0;
    }
    
    //Formats name and gpa as one string separated by a space.
    public String toString(){
        return name+" "+gpa;
    }
}
