package Practice;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGpractice2 {
	@BeforeMethod
	public void getdata4()
	{
		System.out.println("BeforeMethod");
	}
	@AfterMethod
	public void getdata5()
	{
		System.out.println("AfterMethod");
	}
	@Test(enabled=false)
	public void getdata6()
	{
		System.out.println("getdata6");
	}
	@Test
	public void getdata7()
	{
		System.out.println("getdata7");
	}

}
