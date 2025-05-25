package Mamatha;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class PS {
@Test
public void PS1() {
  
  {
	int a=3;
	PS2 ps2=new PS2(3);
	System.out.println(ps2.increment());
	System.out.println(ps2.decrement());
	System.out.println(ps2.multiplyby2());
	}
}
}
