package Practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dropdownhandsons {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
      driver.manage().window().maximize();
      WebElement staticdropdown= driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
      Select dropdown = new Select(staticdropdown);
      dropdown.selectByValue("AED");
      driver.findElement(By.id("divpaxinfo")).click();
      int i=1;
      while (i<4)
      {
    	  driver.findElement(By.id("hrefIncAdt")).click();
    	  i++;
      }
      driver.findElement(By.xpath("//input[@value='Done']")).click();
      driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
      driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
      driver.findElement(By.xpath("//a[@value='GOI']")).click();
      driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
      driver.findElement(By.xpath("//a[@value='DEL']")).click();
      driver.findElement(By.cssSelector("a.ui-state-default.ui-state-active")).click();
      driver.findElement(By.id("autosuggest")).click();
      driver.findElement(By.id("autosuggest")).sendKeys("ind");
      List<WebElement> options= driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
      for (WebElement option:options)
      {
    	 if (option.getText().equalsIgnoreCase("India"));
    	 {    Thread.sleep(4000);
    		 wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    		 break;
    	 }
      }
    	 driver.findElement(By.id("Div1")).getAttribute("style"); 
    	 driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
    	 driver.findElement(By.id("Div1")).getAttribute("style"); ; 
    	 if (driver.findElement(By.id("Div1")).getAttribute("style").contains("1"));
    	 {
    		 System.out.println("Round trip is enabled");
    		 
    	 }
    	 
      driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
      
	}

}
