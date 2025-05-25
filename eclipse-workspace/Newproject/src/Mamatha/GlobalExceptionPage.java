package Mamatha;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
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

public class GlobalExceptionPage {

    @Test
    public void testglobalexception() throws InterruptedException, IOException {
        // Get the singleton instance of LoginManager
        LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
        
        // Use the driver from LoginManager
        executeGlobalException(loginManager.getDriver());
        System.out.println("Global exception created successfully");
    }

    public void executeGlobalException(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Thread.sleep(4000);
        // Wait for navigationLabel to be  (no sleep required)
        System.out.println("Navigating to Global Exceptions...");
        try {
            WebElement navigationLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='navigationLabel']")));
            navigationLabel.click();

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
            searchInput.sendKeys("global");

            WebElement globalExceptionsElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id-Global Exceptions")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", globalExceptionsElement);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", globalExceptionsElement);

            System.out.println("Navigation to Global Exceptions successful.");
//           WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='yes' and contains(@class, 'gwt-Button')]")));
//            yesButton.click();
        } catch (Exception e) {
        	System.out.println("Navigation to Global Exceptions Failed.");
        }
        try {
            // Now, handling phone number input
            // Adjust time as needed
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolbar-button-image-new-adv-filter-clearAll"))); // Update with correct locator
            
            // Click the "Delete" button
            deleteButton.click();
            
            // Wait for the "OK" button to be visible and 
            WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok")));
            wait.until(ExpectedConditions.elementToBeClickable(okButton));
            okButton.click();
            WebElement okButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok")));
            wait.until(ExpectedConditions.elementToBeClickable(okButton1));
            okButton.click();
            
            System.out.println("Delete action was successful!");
            
        } catch (Exception e1) {
            // Catch any exceptions and print them
            System.out.println("An error occurred while deleting existing global exception: " + e1.getMessage());
            Assert.fail("An error occurred while deleting existing global exception: " + e1.getMessage());
        }
       Thread.sleep(6000);
            // Click the OK button if it appears
            try {
                
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
                Assert.fail("An error  while creating global exception: " + e.getMessage());
            }
        // Save button click
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @class='gwt-Button roc-primary-button' and @id='globalExceptionsDetail.save']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            saveButton.click();
            System.out.println("Changes saved successfully!");
            Thread.sleep(5000);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       		File destDir = new File("C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Automation ss\\Globalexception.png");
       		FileUtils.copyFile(src, destDir);
        } catch (Exception e) {
            System.out.println("Error while clicking save button: " + e.getMessage());
            Assert.fail("An error occurred while saving changes: " + e.getMessage());
        }
    }
}