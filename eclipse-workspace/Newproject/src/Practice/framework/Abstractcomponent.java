package Practice.framework;

import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstractcomponent {
	WebDriver driver;
	public Abstractcomponent(WebDriver driver)
	{    
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//i[@class='fa fa-shopping-cart']")
	WebElement cartbutton;
	public CartPage gotocartpage()
	{
		cartbutton.click();
		return new CartPage(driver);
	}
	
	public void waitforelementtoappear(By FindBy)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	public void waitforelementtodiappear(By FindBy)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));
	}
	
}