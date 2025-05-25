package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage extends Abstractcomponents {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".action__submit")
    private WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    private WebElement selectCountry;

    private By results = By.cssSelector(".ta-results");

    // Improved method to select country
    public void selectCountry(String countryName) {
        // Send the country name to the input field
        country.sendKeys(countryName);

        // Wait for the dropdown to appear
        waitForElementToAppear(results);

        // Locate the country option dynamically based on the country name (with normalization of spaces)
        WebElement countryOption = driver.findElement(By.xpath("//button[contains(@class,'ta-item') and normalize-space(text())='" + countryName + "']"));

        // Click the country option from the dropdown
        countryOption.click();
    }

    // Method to submit the order and go to the confirmation page
    public ConfirmationPage submitOrder() {
        // Click on the submit button
        submit.click();

        // Return the new ConfirmationPage object
        return new ConfirmationPage(driver);
    }

    // Wait until an element appears
    public void waitForElementToAppear(By countryOption) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryOption));
    }
}