package Testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Abstractcomponents{

	
      WebDriver driver;
      
      public LandingPage(WebDriver driver)
      {   super(driver);
    	  this.driver=driver;
    	  PageFactory.initElements(driver,this);
    	  
      }
      @FindBy(id="userEmail")
      WebElement Email;
      
      @FindBy(id="userPassword")
      WebElement Password;
      
      @FindBy(id="login")
      WebElement Submit;
      
      public void LoginApplication(String Emailid,String Password1)
      {
    	  Email.sendKeys(Emailid);
    	  Password.sendKeys(Password1);
    	  Submit.click();
    	  
      }
      
      public void goTo()
      {
    	  driver.get("https://rahulshettyacademy.com/client");
      }
	 
	}


