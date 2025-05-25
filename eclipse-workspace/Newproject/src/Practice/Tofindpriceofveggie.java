package Practice;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tofindpriceofveggie {
	public static void main(String[] args)
	{
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//th[@role='columnheader'][1]")).click();
		
		List<String> price; 
		do {
			List<WebElement> elementlist=driver.findElements(By.xpath("//tr/td[1]"));
			 price=elementlist.stream().filter(s -> s.getText().contains("Rice")).map(s->getveggieprice(s)).collect(Collectors.toList());
			 price.forEach(a->System.out.println(a));
			 if(price.size()==0)
			 {
				 driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
			 }
		}
			 while(price.size()==0);
			 
		 }
	
 
		 public static String getveggieprice(WebElement s)
		 {
			String value= s.findElement(By.xpath("following-sibling::td[1]")).getText();
			 return value;
		 }
	}


