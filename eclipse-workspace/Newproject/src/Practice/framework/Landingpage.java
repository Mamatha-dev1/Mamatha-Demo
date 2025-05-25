package Practice.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

public class Landingpage extends Abstractcomponent {
	WebDriver driver;
	public Landingpage(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userid;
	@FindBy(id="userPassword")
	WebElement pwd;
	@FindBy(id="login")
	WebElement loginbutton;
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement mesg1;
	By message5=(By.xpath("//div[@aria-label='Incorrect email or password.']")); 
	
	public void Loginurl()
	{
	driver.get("https://rahulshettyacademy.com/client");
	}
	
	public Productcatalog logintoapplication(String username,String password)
	{
		userid.sendKeys(username);
		pwd.sendKeys(password);
		loginbutton.click();
		return new Productcatalog(driver);
	}
	public String login1() throws InterruptedException
	{    waitforelementtoappear(message5);
		String errormesg=mesg1.getText();
		return errormesg;
	}
	
	
}