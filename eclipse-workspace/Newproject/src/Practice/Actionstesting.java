package Practice;

import java.awt.RenderingHints.Key;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Actionstesting {

	public static void main(String[] args) throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		Thread.sleep(10000);
		WebElement hellobutton= driver.findElement(By.id("nav-link-accountList"));
		Actions a = new Actions(driver);
		a.moveToElement(hellobutton).contextClick().build().perform();
		WebElement searchbox=driver.findElement(By.id("twotabsearchtextbox"));
		a.moveToElement(searchbox).click().keyDown(Keys.SHIFT).sendKeys("helllo").build().perform();
	}
	
}
