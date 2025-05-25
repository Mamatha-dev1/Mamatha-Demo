package FMClient;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;	
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import FMClient.ConfigurationLoader;
	import FMClient.LoginManager;
	import FMClient.ScreenshotHelper;

	public class RecordView2Adavncedfilter {
		
		protected static WebDriver driver;
		protected static WebDriverWait wait;

		// Get the singleton instance of LoginManager
			private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

		@Test
		public void Recordviewtestcase() throws InterruptedException, IOException {

			driver = loginManager.getDriver();
			wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(60));

			executerecordview(driver);
			System.out.println("Advanced filter test case executed successfully");
		}
	public  void executerecordview(WebDriver driver) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		// Setting up the WebDriver
        Thread.sleep(3000);
		
		//Goto Alarms active
		driver.findElement(By.id("navigationLabel")).click();
		
		
		driver.findElement(By.cssSelector("#id-Search > b1")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("id-CDR")).click();
		Thread.sleep(2000);
				
		//Close pop-up
		//driver.findElement(By.id("yes")).click();
		Thread.sleep(5000);
		System.out.println("CDR record view page loaded successfully");
		
		 // Remove existing filters before applying new ones
        driver.findElement(By.id("Remove All Filters")).click();
        Thread.sleep(7000);
        System.out.println("Removed all filters successfully");
		
		//Advanced Filter
		driver.findElement(By.id("advanced-filter-button")).click();
		Thread.sleep(2000);
		 // Locate the date input field using CSS selector
        WebElement dateInput = driver.findElement(By.cssSelector("#DATE_RANGE_VAL_abs_from_date .gwt-DateBox"));
        // Set the desired date value
        dateInput.sendKeys("09/11/2024 00:00:00");
		
		//Date
/*		driver.findElement(By.id("trigger-DATE_RANGE_VAL_abs_from_date")).click();
		driver.findElement(By.cssSelector(".datePickerDayIsHighlighted")).click();
		driver.findElement(By.id("trigger-DATE_RANGE_VAL_abs_to_date")).click();
		driver.findElement(By.cssSelector(".roc-datepicker-today")).click();
*/
        
        // Locate the date input field using CSS selector
        WebElement dateInput2 = driver.findElement(By.cssSelector("#DATE_RANGE_VAL_abs_to_date .gwt-DateBox"));
        // Set the desired date value
        dateInput2.sendKeys("09/11/2025 00:00:00");
        
     // Apply the user-provided filter value
        driver.findElement(By.cssSelector(".original-token-input")).sendKeys("called number");
        driver.findElement(By.cssSelector(".original-token-input")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(".original-token-input")).sendKeys("like");
        driver.findElement(By.cssSelector(".original-token-input")).sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector(".original-token-input")).sendKeys("+%");
        driver.findElement(By.cssSelector(".original-token-input")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        
        driver.findElement(By.id("filter-search-button")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("no")).click();
        Thread.sleep(2000);
        System.out.println("Filter applied successfully");
        
        System.out.println("Advanced filter applied successfully");
        String baseDir = System.getProperty("user.dir");
        ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
        ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);
        screenshotHelper.takeScreenshot(driver);
       
	}
}

