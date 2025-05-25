package Practice.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Confirmationpage extends Abstractcomponent{
	WebDriver driver;
	
	public Confirmationpage(WebDriver driver) {
		 super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);// TODO Auto-generated constructor stub
	}

	@FindBy(css=".hero-primary")
	WebElement confmesg;
	By confirmmesg=By.cssSelector(".hero-primary");
	public String getconfirmmessage()
	{
		waitforelementtoappear(confirmmesg);
	String confirmmesg1=confmesg.getText();
	return confirmmesg1;
	}
}
