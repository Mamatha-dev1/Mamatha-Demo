package Practice.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class CartPage extends Abstractcomponent{
	WebDriver driver;
	public CartPage (WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkoutbutton;
	 By cartprodlist = By.cssSelector(".cartSection h3");
	 @FindBy(css=".cartSection h3")
	 List<WebElement> cartproducts;
 public List<WebElement> getcartproductlist()
 {      waitforelementtoappear(cartprodlist);
	 return cartproducts;
	 
	 
 }
 public Boolean checkcartprod(String productname)
 {
	 Boolean match=cartproducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
	 return match;
	 
 }
 public Checkoutpage gotocheckoutpage()
 {
	 Actions actions = new Actions(driver);
	 actions.moveToElement(checkoutbutton).click().perform();
	 return new Checkoutpage(driver);
 }
}
