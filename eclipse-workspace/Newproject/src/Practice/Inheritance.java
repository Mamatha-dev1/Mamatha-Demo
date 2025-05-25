package Practice;

import org.testng.annotations.Test;

public class Inheritance {
	@Test
	public void execute()
	{
	int a=3;
	PS1 ps1= new PS1(3);
	int addvalue=ps1.increment();
	System.out.println(addvalue);
	
	int subvalue=ps1.decrement();
	System.out.println(subvalue);
	
	int multivalue2=ps1.multiply2();
	System.out.println(multivalue2);
	
	int multivalue3=ps1.multiply3();
	System.out.println(multivalue3);
}

}