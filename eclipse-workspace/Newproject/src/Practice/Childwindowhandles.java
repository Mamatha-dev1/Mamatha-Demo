package Practice;

import java.util.Iterator;
import java.util.Set;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Childwindowhandles {
	public static void main(String[] args)
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
		driver.findElement(By.cssSelector("a.blinkingText")).click();
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it= window.iterator();
		String Parentid=it.next();
		String Childid=it.next();
		driver.switchTo().window(Childid);
		String text=driver.findElement(By.xpath("//div/p[2]/strong")).getText();
		String username=text.split("@")[1].split("\\.")[0];;
		
		driver.switchTo().window(Parentid);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys("learning");
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();
		
	}

}
