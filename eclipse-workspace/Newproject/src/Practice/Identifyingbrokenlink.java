package Practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class Identifyingbrokenlink {
	public static void main(String[] args) throws IOException
	{
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		List<WebElement> Links=driver.findElements(By.xpath("//li/a"));
		SoftAssert a= new SoftAssert();
		for(WebElement link:Links)
		{
			String url=link.getAttribute("href");
			HttpURLConnection conn= (HttpURLConnection)new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int Responsecode=conn.getResponseCode();
			System.out.println(Responsecode);
			
			a.assertTrue(Responsecode<400, "The link with "+link.getText()+" with "+Responsecode+" is broken link");
		
		}
		a.assertAll();
		
	}

}
