package Practice.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Productcatalog extends Abstractcomponent{
	WebDriver driver;
	public Productcatalog (WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div/h5/b")
	List<WebElement> products;

	By toast=By.id("toast-container");
	By spinner=By.id("ng-animating");
	By productslist=By.xpath("//div/h5/b");
	
	public List<WebElement> getprodlist()
	{   waitforelementtoappear(productslist);
		return products;
	}
	
	public WebElement getproductbyname(String productname)
	{
		WebElement product1= getprodlist().stream().filter(product->product.getText().
				equalsIgnoreCase(productname)).findFirst().orElse(null);
		return product1;
	}
	public void addprodtocart(String productname)
	{   WebElement product1=getproductbyname(productname);
		product1.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		waitforelementtoappear(toast);
		waitforelementtodiappear(spinner);
	}
}