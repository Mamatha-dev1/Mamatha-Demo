package Mamatha;

import org.testng.annotations.Test;

public class PS3  {
int a;
public PS3(int a) {
	
this.a=a;
}
	
		@Test()
	    public int multiplyby2()
	    {
	   	 a=a*2;
	   	 return a;
	    }
		
	}


