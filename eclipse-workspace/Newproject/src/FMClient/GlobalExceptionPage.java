package FMClient;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import FMClient.ConfigurationLoader;
import FMClient.LoginManager;
import FMClient.ScreenshotHelper;

public class GlobalExceptionPage {
	
	// Get the singleton instance of LoginManager
    private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

    @Test
    public void testglobalexception() throws InterruptedException, IOException {
                
        // Use the driver from LoginManager
        executeGlobalException(loginManager.getDriver());
        System.out.println("Global exception created successfully");
    }

    public void executeGlobalException(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Thread.sleep(4000);
        // Wait for navigationLabel to be clickable (no sleep required)
        
        try {
            WebElement navigationLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='navigationLabel']")));
            navigationLabel.click();

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
            searchInput.sendKeys("global");

            WebElement globalExceptionsElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id-Global Exceptions")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", globalExceptionsElement);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", globalExceptionsElement);

            System.out.println("Navigation to Global Exceptions successful.");
            //WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='yes' and contains(@class, 'gwt-Button')]")));
            //yesButton.click();
        } catch (Exception e) {
            Assert.fail("Navigation to global exception failed: " + e.getMessage());
        }

        // Now, handling phone number input
       Thread.sleep(3000);

            // Click the OK button if it appears
            try {
                // Click the delete button
                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolbar-button-image-new-adv-filter-clearAll")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
                deleteButton.click();
                wait.until(ExpectedConditions.elementToBeClickable(By.id("ok")));
                // Click the OK button if it appears
                driver.findElement(By.id("ok")).click();
                

                // Wait for the input field to be present and visible
                WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']")));
                inputElement.sendKeys("Phone Number ");
                
                inputElement.sendKeys("="+ Keys.RETURN);
                System.out.println("Please enter phone number");
                Scanner scanner1 = new Scanner(System.in);
                String phonu1 = scanner1.nextLine();
                WebElement phoneno = driver.findElement(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']"));
                phoneno.sendKeys(phonu1);
                System.out.println("Input sent successfully!");
                
            }
            catch (Exception e) {
            	
                System.out.println("An error occurred: " + e.getMessage());
            }
        // Save button click
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @class='gwt-Button roc-primary-button' and @id='globalExceptionsDetail.save']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            saveButton.click();
            System.out.println("Changes saved successfully!");
            Thread.sleep(3000);
            String baseDir = System.getProperty("user.dir");
            ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);
            screenshotHelper.takeScreenshot(driver);
        } catch (Exception e) {
            System.out.println("Error while clicking save button: " + e.getMessage());
            Assert.fail("An error occurred while saving changes: " + e.getMessage());
        }
    }
}
