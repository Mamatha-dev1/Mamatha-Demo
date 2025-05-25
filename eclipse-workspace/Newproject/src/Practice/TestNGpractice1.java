package Practice;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNGpractice1 {
	@AfterClass
	public void getdata10()
	{
		System.out.println("AfterClass");
	}
	@Test
	public void getdata1()
	{
		System.out.println("getdata1");
	}
	
	@Test
	public void getdata2()
	{
		System.out.println("getdata2");
		
	}
	@Test(dataProvider="getdata112")
	public void getdata3( String username,String password)
	{
		
		System.out.printf(username);
		System.out.printf(password);
		
	}
	@Test
	public void getdata()
	{    String text1="manu";
	String text2="anu";
	Assert.assertEquals(text1,text2);
		System.out.println("BeforeClass");
	}
	@DataProvider
	public Object[][] getdata112()
	{
		Object[][] data= new Object[2][2];
		data[0][0]="UN1";
				data[0][1]="PW1";	
				data[1][0]="UN2";	
				data[1][1]="PW2";	
				return data;
	}

}
