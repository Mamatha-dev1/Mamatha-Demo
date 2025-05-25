package Mamatha;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.StaleElementReferenceException;

public class Auditpage {

    @Test
    public void testAuditPage() throws InterruptedException, IOException {
        // Get the singleton instance of LoginManager
        LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
        
        // Use the driver from LoginManager
        testAuditPage(loginManager.getDriver());
        System.out.println("Audit page executed successfully");
    }

    public void testAuditPage(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        Thread.sleep(4000);

        try {
            WebElement navigationLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navigationLabel']")));
            navigationLabel.click();
            System.out.println("Navigated menu clicked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to open navigation menu.");
            Assert.fail("Navigation menu failed: " + e.getMessage());  // Assertion added
        }

        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='gwt-HTML ag-gwt-megamenu-label' and @id='id-Admin']")));
            element.click();
            System.out.println("Admin clicked successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred clicking admin button: " + e.getMessage());
            Assert.fail("Admin button click failed: " + e.getMessage());  // Assertion added
        }

        try {
            // Wait and click on the "Audit Trails" button
            WebElement auditTrail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Audit Trails")));
            auditTrail.click();
            System.out.println("Navigated to Audit Trail Screen");
        } catch (Exception e) {
            System.out.println("Error navigating to Audit Trail Screen: " + e.getMessage());
            Assert.fail("Navigation to Audit Trail failed: " + e.getMessage());  // Assertion added
        }

        // Handle StaleElementReferenceException by retrying the click operation on the filter button
       Thread.sleep(4000);
//        try {
//            // Wait for the filter button to be clickable
//            WebElement filter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='roc-grid-header-menu-filter roc-filter-showing']")));
//            
//            // Create an Actions object
//            filter.click();
//            
//            System.out.println("Filter button clicked successfully.");
//            
//        } catch (Exception e) {
//            System.out.println("Error clicking filter button: " + e.getMessage());
//            Assert.fail("Filter button click failed: " + e.getMessage());  // Assertion added
//        }
//    
//
//        try {
//            // Wait and click on the "Last 7 Days" option in the date picker
//            WebElement dateOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[div[@class='gwt-HTML' and text()='Last 7 Days']]")));
//
//            // Click the option if it becomes visible
//            if (dateOption.isDisplayed()) {
//                dateOption.click();
//                System.out.println("Last 7 Days option selected.");
//            } else {
//                System.out.println("The 'Last 7 Days' option is not visible.");
//            }
//        } catch (Exception e) {
//            System.out.println("Error selecting date options: " + e.getMessage());
//            Assert.fail("Date selection failed: " + e.getMessage());  // Assertion added
//        }

        try {
            // Start the search process by clicking on the 'start' button
            WebElement searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='search' and @class='gwt-Button roc-primary-button']")));
            searchbutton.click();
        } catch (Exception e) {
            System.out.println("Error starting the search: " + e.getMessage());
            Assert.fail("Search button click failed: " + e.getMessage());  // Assertion added
        }

        Thread.sleep(3000);

        // Take a screenshot
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destDir = new File("C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Automation ss\\Auditpage.png");
            FileUtils.copyFile(src, destDir);
        } catch (Exception e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
            Assert.fail("Screenshot capture failed: " + e.getMessage());  // Assertion added
        }
    }
}
