package Practice.framework;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.ITestResult;





public class Basetest extends source{
	String productname="ZARA COAT 3";
	@Test(dataProvider="getdata")
	public  void test(HashMap<String,String> input) throws InterruptedException, IOException
	{ 
		Landingpage landingpage=launchapplication();
        
		Productcatalog productcatalog=landingpage.logintoapplication(input.get("email"),input.get("password"));
//		driver.findElement(By.xpath("//button[normalize-space()='Sign Out']")).click();
		Thread.sleep(5000);
		List<WebElement> products=productcatalog.getprodlist();
		productcatalog.addprodtocart(productname);	
		CartPage cartPage=productcatalog.gotocartpage();
		cartPage.getcartproductlist();
	 Boolean match=cartPage.checkcartprod(productname);
	 Assert.assertTrue(match);
	 Thread.sleep(5000);
	 Checkoutpage checkoutpage=cartPage.gotocheckoutpage();
	 checkoutpage.putcountryname();
	 checkoutpage.selectcountry();
	 Confirmationpage confirmationpage=checkoutpage.submitorder();
	 String ordermesg=confirmationpage.getconfirmmessage();
	Assert.assertEquals(ordermesg, "THANKYOU FOR THE ORDER.");
	 
	}
	
	@DataProvider
 	public Object[][] getdata() throws IOException
 	{    
    	 List<HashMap<String,String>> data=getjsondatatoMap("C:\\Users\\mamatha.kp\\eclipse-workspace\\Newproject\\src\\Practice\\framework\\data.json");
  
 		return new Object[][] {{data.get(0)},{data.get(1)}};
 	}

}