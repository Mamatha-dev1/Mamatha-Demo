package Practice;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class E2Eflowforaddtocart {
	public static void main(String[] args) throws InterruptedException
	{
		WebDriver driver= new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//button[normalize-space()='Sign Out']")).click();
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		Thread.sleep(5000);
		List<WebElement> products=driver.findElements(By.xpath("//div/h5/b"));
	WebElement product1= products.stream().filter(product->product.getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
	 product1.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ng-animating")));
	 driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']")).click();
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
	 List<WebElement> cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
	 Boolean match=cartproducts.stream().anyMatch(s->s.getText().equalsIgnoreCase("ZARA COAT 3"));
	 Assert.assertTrue(match);
	 Thread.sleep(5000);
	 Actions actions = new Actions(driver);
	 WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
	 actions.moveToElement(checkoutButton).click().perform();
	 Actions a= new Actions(driver);
	 a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"ind").build().perform();
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']")));
	 driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Place Order']")));
	 WebElement placeOrderButton = driver.findElement(By.xpath("//a[normalize-space()='Place Order']"));
	 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
	String ordermesg= driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertEquals(ordermesg, "THANKYOU FOR THE ORDER.");
	 
	}

}
