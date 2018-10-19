public class SomeObject{
	
	private int value;
	public SomeObject(int v){value=v;}
	
	
	public static void main(String[] args){
			SomeObject so = new SomeObject(5);
			System.out.println(so);		//What will this give us?
			System.out.println(so.toString());	//What will this give us?
			
	}
	/*
	public String toString(){
		return ("Hello I am an object and my value is: "+this.value);
	}
	*/
}