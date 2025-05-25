package Practice;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tofindcountoflinks {

	public static void main(String[] args)
	{
		WebDriver driver =new ChromeDriver();
		driver.get("https://qaclickacademy.com/practice.php");
		driver.manage().window().maximize();
		System.out.println(driver.findElements(By.tagName("a")).size());
		
		WebElement footerdriver= driver.findElement(By.xpath("//div[@class=' footer_top_agile_w3ls gffoot footer_style']"));
		System.out.println(footerdriver.findElements(By.tagName("a")).size());
		WebElement Coloumndriver=footerdriver.findElement(By.xpath("//div[@id='gf-BIG']/table/tbody/tr/td[1]"));
		System.out.println(Coloumndriver.findElements(By.tagName("a")).size());
		for(int i=1;i<Coloumndriver.findElements(By.tagName("a")).size();i++)
		{
		String clickonlinktab=Keys.chord(Keys.CONTROL,Keys.ENTER);
		Coloumndriver.findElements(By.tagName("a")).get(i).sendKeys(clickonlinktab);
	
	}
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		while(it.hasNext())
		{
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}
	}	
}
