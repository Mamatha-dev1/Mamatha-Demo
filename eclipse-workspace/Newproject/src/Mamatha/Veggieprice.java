package Mamatha;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Veggieprice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.xpath("//span[@class='sort-icon sort-descending']")).click();
		
		List<WebElement> elementslist=driver.findElements(By.xpath("//tr/td[1]"));
		List<String> Originallist=elementslist.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> sortedList = Originallist.stream().sorted().collect(Collectors.toList());

		// compare original list vs sorted list
		Assert.assertTrue(Originallist.equals(sortedList));
		
		//To find the prize of Rice
		List<String> Price;
		 
		do
		{
			List<WebElement> rows=driver.findElements(By.xpath("//tr/td[1]"));
			Price=rows.stream().filter(s->s.getText().contains("Rice")).map(s->getpriceveggie(s)).collect(Collectors.toList());
			Price.forEach(s->System.out.println(s));
			if(Price.size()<1)
			{
				driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
			}
		} while (Price.size()<1);
			
	}
	
	public static String getpriceveggie(WebElement s)
	{
		String pricevalue=s.findElement(By.xpath("//tr/td[2]")).getText();
		return pricevalue;
	}

}
