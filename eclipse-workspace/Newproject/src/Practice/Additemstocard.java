package Practice;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Additemstocard {
	public static void main(String[] args ) throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		String[] itemsNeeded= {"Cucumber","Beetroot","Tomato"};
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(12));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		driver.manage().window().maximize();
		
		List<WebElement> names=driver.findElements(By.cssSelector("h4.product-name"));
		int j=0;
		for(int i=0;i<names.size();i++)
		{
			String formattedName=names.get(i).getText().split("-")[0].trim();
			List itemsNeededList=Arrays.asList(itemsNeeded);
			if (itemsNeededList.contains(formattedName))
					{j++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if(j==names.size())
					break;
					}
			
		}
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter promo code']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter promo code']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		WebElement staticdropdown=driver.findElement(By.xpath("//div/select"));
		Select dropdown= new Select(staticdropdown);
		dropdown.selectByValue("India");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='wrapperTwo']")).getText());
	}
	
}