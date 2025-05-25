package Testing;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Abstractcomponents {
		WebDriver driver;

		@FindBy(css = ".totalRow button")
		WebElement checkoutEle;

		@FindBy(css = ".cartSection h3")
		private List<WebElement> cartProducts;

		public CartPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);

		}

		public Boolean VerifyProductDisplay(String productName) {
			Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
			return match;

		}

		public CheckoutPage goToCheckout() {
			 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			    
			    // Execute JavaScript to click on the element
			    WebElement checkoutButton = checkoutEle;  // assuming checkoutEle is already defined as the WebElement for the button
			    jsExecutor.executeScript("arguments[0].click();", checkoutButton);
			    
			    // Return a new CheckoutPage object
			    return new CheckoutPage(driver);

		}

	}
