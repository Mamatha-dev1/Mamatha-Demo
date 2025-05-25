package Practice;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Tocheckfilterfunctionality {
	public static void main(String[] args)
	{
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();
		driver.findElement(By.id("search-field")).sendKeys("Rice");
		List<WebElement> elementlist=driver.findElements(By.xpath("//tr/td[1]"));
		List<String> originallist=elementlist.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> filteredlist=originallist.stream().filter(s->s.contains("Rice")).collect(Collectors.toList());
		System.out.println(originallist.equals(filteredlist));
		
	}

}
