package Mamatha;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Exportrulelist {
    @Test
    public void testexportlist() throws InterruptedException, IOException, TimeoutException {
        // Get the singleton instance of LoginManager
        LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
        
        // Use the driver from LoginManager
        WebDriver driver = loginManager.getDriver();
        NavigatetoFunctionandcombinedrules(driver);
        // Execute rule export
        executerulesexport(driver);
        
        // Download all files
        downloadAllFiles(driver);
        
        System.out.println("Exported Rulelist  successfully");
    }
    public void NavigatetoFunctionandcombinedrules(WebDriver driver) throws InterruptedException, IOException, TimeoutException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); 
    	Thread.sleep(6000);
    	
    	{
            try {
            	
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
                WebElement navigationLabel = driver.findElement(By.id("navigationLabel"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navigationLabel);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Rule Management"))).click();
                
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Function and Combined Rules"))).click();
                Thread.sleep(4000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes"))).click();
                System.out.println("Navigating to Functioned and combined rules Page.");
            } catch (Exception e) {
            	System.out.println("Navigated to Rule Management failed");
            }
    	}
    }

    public static void executerulesexport(WebDriver driver) throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Thread.sleep(6000); // Initial sleep for page load (this might need adjustment)
        
        configLoader configLoader = new configLoader("C:\\Users\\mamatha.kp\\eclipse-workspace\\Newproject\\src\\Mamatha\\config.properties");
        
        try {
            // Clicking on Export Options button
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Export Options"))).click();

            String format = configLoader.getExportFormat().toUpperCase().trim();
            System.out.println("Export format selected: " + format);
            
            // Validate the input format
            Assert.assertTrue(format.equals("CSV") || format.equals("TXT") || format.equals("PDF") || format.equals("RTF"),
                    "Invalid format entered. Please enter one of the following: CSV, TXT, PDF, RTF.");
            
            downloadFileBasedOnFormat(driver, format);

        } catch (Exception e) {
            System.out.println("Navigation to Rule Management failed");
        }

        try {
            // Attempt to click on the "All Pages" option
            WebElement allPagesOption = driver.findElement(By.xpath("//div[text()='All Pages']"));
            allPagesOption.click();
            System.out.println("Selected 'All Pages' for export.");
            
            // Click on the "OK" button to confirm the selection
            WebElement okButton = driver.findElement(By.id("ok"));
            okButton.click();
            System.out.println("Clicked OK to proceed.");
        } catch (Exception e) {
            System.out.println("An error occurred while selecting 'All Pages' or clicking 'OK': " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Failed to interact with 'All Pages' or 'OK' button: " + e.getMessage());
        }

        // After selecting the pages type and confirming with OK, now we need to download the files
        try {
            Thread.sleep(4000);
            downloadAllFiles(driver);
        } catch (Exception e) {
            System.out.println("Error while downloading files: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Failed to download files: " + e.getMessage());
        }
        
        
    }

    // Method to download all files based on available download buttons
    public static void downloadAllFiles(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Thread.sleep(5000);
        
        // Find all buttons that contain 'export' in their class names (can be CSV, PDF, TXT, etc.)
        List<WebElement> exportButtons = driver.findElements(By.cssSelector("[class*='export']"));
        
        if (exportButtons.isEmpty()) {
            System.out.println("No export buttons found for download.");
        } else {
            // Loop through all the buttons and click each one to trigger the download
            for (WebElement button : exportButtons) {
                try {
                    // Click the button to initiate the file download
                    button.click();
                    System.out.println("Downloading file: " + button.getText());
                    
                    // Optionally wait for some time between downloads (adjust based on download time)
                    Thread.sleep(2000);  // Adjust this sleep time depending on your download time
                    
                } catch (Exception e) {
                    System.out.println("Error while downloading file: " + button.getText());
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to download the file based on selected format (CSV, TXT, PDF, RTF)
    public static void downloadFileBasedOnFormat(WebDriver driver, String format) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement formatButton;

        switch (format) {
            case "CSV":
                try {
                    System.out.println("Waiting for the CSV button...");
                    formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportCsvActionComponent")));
                    System.out.println("CSV button found, clicking...");
                    formatButton.click();
                    System.out.println("CSV file format selected...");
                } catch (Exception e) {
                    System.out.println("Error while clicking CSV export button: " + e.getMessage());
                    e.printStackTrace();
                }
                break;

            case "TXT":
                formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportTextActionComponent")));
                formatButton.click();
                System.out.println("TXT file format selected...");
                break;

            case "PDF":
                formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportPdfActionComponent")));
                formatButton.click();
                System.out.println("PDF file format selected...");
                break;

            case "RTF":
                formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportRtfActionComponent")));
                formatButton.click();
                System.out.println("RTF file format selected...");
                break;

            default:
                System.out.println("Unsupported format: " + format);
                Assert.fail("Unsupported format: " + format);  // Fails if an unsupported format is selected
                return;
        }
        Thread.sleep(5000);  // Wait for 5 seconds, adjust based on your download speed
    }
}