package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;


public class Relativelocators {
	public static void main(String[] args)
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().window().maximize();
		WebElement Nameeditbox=driver.findElement(By.name("name"));
		System.out.println(driver.findElement(with(By.tagName("label")).above(Nameeditbox)).getText());
		WebElement Studentradiobutton=driver.findElement(By.id("inlineRadio1"));
		System.out.println(driver.findElement(with(By.tagName("label")).toLeftOf(Studentradiobutton)).getText());
		WebElement checkbox=driver.findElement(By.id("exampleCheck1"));
		System.out.println(driver.findElement(with(By.tagName("label")).below(checkbox)).getText());
		System.out.println(Nameeditbox.getRect().getDimension().getHeight());
		System.out.println(Nameeditbox.getRect().getDimension().getWidth());
	}

}
