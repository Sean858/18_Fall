interface Employee{
	boolean makesMoreThan(Employee e);
	String salary();
	String name();
	String status();
}

class PartTimeEmployee implements Employee{
	private String name;
	private int pay;
	public PartTimeEmployee(String n, int p){
		name=n;
		pay=p;
	}
	
        public boolean makesMoreThan(Employee e){
            if(!(e instanceof PartTimeEmployee)){
                return false;
            }
            PartTimeEmployee f = (PartTimeEmployee)e;
            return this.pay>f.pay;
        }
        
	public String salary(){
            return " makes "+this.pay;
        }
        
	public String name(){
            return this.name;
        }
	public String status(){
            return "Part Time Employee ";
        }
}

class FullTimeEmployee implements Employee{
	private String name;
	private int pay;
	public FullTimeEmployee(String n, int p){
		name=n;
		pay=p;
	}
	
        public boolean makesMoreThan(Employee e){
            if(!(e instanceof FullTimeEmployee)){
                return false;
            }
            FullTimeEmployee f = (FullTimeEmployee)e;
            return this.pay>f.pay;
        }
        
	public String salary(){
            return " makes "+this.pay;
        }
        
	public String name(){
            return this.name;
        }
	public String status(){
            return "Full Time Employee ";
        }
        
        public static void main(String[] args){
            Employee ft1=new FullTimeEmployee("Dave",200);
            Employee ft2=new FullTimeEmployee("Jane",300);
            Employee pt1=new PartTimeEmployee("Tom",100);
            Employee pt2=new PartTimeEmployee("Mary",50);
            
            System.out.println(ft1.status()+ft1.name()+ft1.salary());
            System.out.println(ft2.status()+ft2.name()+ft2.salary());
            System.out.println(pt1.status()+pt1.name()+pt1.salary());
            System.out.println(pt2.status()+pt2.name()+pt2.salary());
        }
}