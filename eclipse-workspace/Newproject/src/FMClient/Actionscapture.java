package FMClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import Mamatha.configLoader;

public class Actionscapture {
	private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

	@AfterClass
    public void testglobalexception() throws InterruptedException, IOException {
                
        // Use the driver from LoginManager
		executeactions(loginManager.getDriver());
        System.out.println("Actions logs captured successfully successfully");
    }
	
	public void executeactions(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@id='navigationLabel']")).click();

		WebElement actionpage = driver.findElement(By.xpath("//input[@placeholder='Type to search']"));
		
		actionpage.sendKeys("Action");
		driver.findElement(By.xpath("//div[text()='Actions' and @class='gwt-HTML']")).click();

		
		           Thread.sleep(2000);
		
		             System.out.println("Action Page opened Successfully");
		             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		             //WebElement filterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basic-filter-button")));
		             WebElement filterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='basic-filter-button' and text()='Basic Filter']")));
                     filterButton.click();
                     Properties properties = new Properties();
                       File file = new File("C:\\Users\\mamatha.kp\\eclipse-workspace\\Newproject\\src\\FMClient\\config.properties");
         			FileInputStream fis = new FileInputStream(file);

         			properties.load(fis);
		
		            driver.findElement(By.xpath("//*[@id=\"Remove All Filters\"]/img")).click(); 
		            Thread.sleep(2000);
		            filterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='basic-filter-button' and text()='Basic Filter']")));
                    filterButton.click();
		            Thread.sleep(3000);
		            WebElement Startdate = driver.findElement(By.cssSelector("#FAUT_LOGGED_DATE_abs_from_date > tbody > tr > td:nth-child(1) > input"));
		            String Sdate = properties.getProperty("Sdate");
					Startdate.sendKeys(Sdate);
					Thread.sleep(3000);
		            driver.findElement(By.xpath("//div[text()='Set now']")).click();
		            Thread.sleep(3000);
//		            WebElement Computer = driver.findElement(By.xpath("//*[@id=\"FAUT_IP_ADDRESS_text_box\"]"));
//		            String ipaddress = properties.getProperty("ipaddress");
//					Computer.sendKeys(ipaddress);
		            WebElement Userfield = driver.findElement(By.xpath("//table[@id='FAUT_USER_IDE_multi_sel_labelPanel']"));
		            Userfield.click();
		            WebElement Usernameinput = driver.findElement(By.xpath("//input[@placeholder='Type to search']"));
		            String Username = properties.getProperty("User");
		            Usernameinput.sendKeys(Username);
		            Thread.sleep(2000);
		            driver.findElement(By.id("gwtcheckboxlistitem_sampath_InputElement")).click();
					Thread.sleep(3000);
//					TakesScreenshot screenshot = (TakesScreenshot) driver;
//		            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
//		            String timestamp = String.valueOf(System.currentTimeMillis());
//		            File destFile = new File(System.getProperty("user.dir")+"//ROCFM Core Sanity results//" + File.separator + "ActionsBeforedatafetch_" + timestamp + ".png");
//		            try {
//		                FileUtils.copyFile(srcFile, destFile); 
//		                System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
//		            } catch (IOException e) {
//		                System.err.println("Failed to save screenshot: " + e.getMessage());
//		            }
		            driver.findElement(By.xpath("//*[@id=\"filter-search-button\"]")).click();
		            System.out.println("Data is fetched successfully");
		            Thread.sleep(4000);
		            //Screenshot path
		            TakesScreenshot screenshot1 = (TakesScreenshot) driver;
		            File srcFile1 = screenshot1.getScreenshotAs(OutputType.FILE);
		            String timestamp1 = String.valueOf(System.currentTimeMillis());
		            File destFile1 = new File(System.getProperty("user.dir") + "//ROCFM Core Sanity results//" +File.separator + "ActionsPage_" + timestamp1 + ".png");
		            try {
		                FileUtils.copyFile(srcFile1, destFile1); 
		                System.out.println("Screenshot saved to: " + destFile1.getAbsolutePath());
		            } catch (IOException e) {
		                System.err.println("Failed to save screenshot: " + e.getMessage());
		            }
		            
		              
	}

}
