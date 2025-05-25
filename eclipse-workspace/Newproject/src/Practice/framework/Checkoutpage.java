package Practice.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Checkoutpage extends Abstractcomponent{
	WebDriver driver;
	public Checkoutpage (WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	By Countrylist=By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']");
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectindia;
	@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement submitbutton;
	By submitbutton1=By.xpath("//a[normalize-space()='Place Order']");
	public void putcountryname()
	{
		Actions a= new Actions(driver);
		 a.sendKeys(country,"ind").build().perform();
	}
	public void selectcountry()
	{
		waitforelementtoappear(Countrylist);
		selectindia.click();
		 	
	}
	public Confirmationpage submitorder()
	{ waitforelementtoappear(submitbutton1);
	((JavascriptExecutor) driver).executeScript("arguments[0].click();",submitbutton);
	return new Confirmationpage(driver);
		 	
	}
	
}
