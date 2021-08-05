package demoCode;
 
public class Hello{

    public int addition(int x, int y){
	int z = x + y;
	return z;
	
    }
	public static void main(String[] args){
	    System.out.println("Hello, Yao");

	    int a = 5;
	    int b = 6;
	    Hello h = new Hello();

	    int c = h.addition(a,b);

	    System.out.println("Hello, the add result is =" + c);
	}   
}

