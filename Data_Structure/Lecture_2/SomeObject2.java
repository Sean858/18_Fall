public class SomeObject2{
	
	private int value;
	public SomeObject2(int v){value=v;}
	
	
	public static void main(String[] args){
			SomeObject2 so1 = new SomeObject2(5);
			SomeObject2 so2 = new SomeObject2(5);
			SomeObject2 so3 = new SomeObject2(3);
			
			if(so1==so2){System.out.println("The first comparison is true");}
			else{System.out.println("The first comparison is false");}
			
			if(so1==so3){System.out.println("The second comparison is true");}
			else{System.out.println("The second comparison is false");}
			
			if(so1.equals(so2)){System.out.println("The third comparison is true");}
			else{System.out.println("The third comparison is false");}	
			
			if(so1.equals(so3)){System.out.println("The fourth comparison is true");}
			else{System.out.println("The fourth comparison is false");}	
	}

	public boolean equals(SomeObject2 a){
		if(this.value==a.value){return true;}
		else{return false;}
	}
	
}