package Mamatha;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.time.Duration;  // Import the Duration class
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Activealarmsexport {
    WebDriver driver = new ChromeDriver();

    @Test
    // Method to navigate to the Active Alarms page
    public void executeNavigationToActiveAlarms() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.get("http://10.113.206.63:8185/rocfm/app.html");
        driver.findElement(By.name("userName")).sendKeys("Root");
        driver.findElement(By.name("password")).sendKeys("welcome123");
        driver.findElement(By.cssSelector(".login")).click();
        Thread.sleep(20000); // Initial wait for login page to load

        // Close any popups or dialogs, if they exist
       // Initial sleep for page load (adjust as necessary)

        try {
            // Wait for and click the Export Options button
            WebElement exportOptionsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Export Options")));
            exportOptionsButton.click();

            // Prompt the user for the export format (CSV, TXT, PDF, RTF)
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the file format to download (CSV, TXT, PDF, RTF): ");
            String format = scanner.nextLine().toUpperCase().trim();  // Read the input format

            // Validate the format input
            Assert.assertTrue(format.equals("CSV") || format.equals("TXT") || format.equals("PDF") || format.equals("RTF"),
                    "Invalid format entered. Please enter one of the following: CSV, TXT, PDF, RTF.");
            
            // Download the file based on the selected format
            downloadFileBasedOnFormat(format);
        } catch (Exception e) {
            System.out.println("Error navigating to export options.");
            e.printStackTrace();
        }

        try {
            // Wait and select the "All Pages" option for export
            WebElement allPagesOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='All Pages']")));
            allPagesOption.click();
            System.out.println("Selected 'All Pages' for export.");

            // Confirm by clicking OK button
            WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok")));
            okButton.click();
            System.out.println("Clicked OK to proceed.");
        } catch (Exception e) {
            System.out.println("Error selecting 'All Pages' or clicking 'OK': " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Failed to interact with 'All Pages' or 'OK' button: " + e.getMessage());
        }

        // Trigger the download
        try {
            Thread.sleep(4000); // Wait for confirmation
            downloadAllFiles();
        } catch (Exception e) {
            System.out.println("Error downloading files: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Failed to download files: " + e.getMessage());
        }
    }

    // Method to download all available files (CSV, TXT, PDF, RTF)
    public void downloadAllFiles() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Updated constructor
        Thread.sleep(10000); // Wait for export options to appear

        // Find all export buttons (CSV, TXT, PDF, RTF, etc.)
        List<WebElement> exportButtons = driver.findElements(By.cssSelector("[class*='export']"));
        
        if (exportButtons.isEmpty()) {
            System.out.println("No export buttons found for download.");
        } else {
            // Loop through and click each export button
            for (WebElement button : exportButtons) {
                try {
                    // Click to trigger the file download
                    button.click();
                    System.out.println("Downloading file: " + button.getText());

                    // Sleep for a few seconds between downloads (adjust based on your download time)
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("Error downloading file: " + button.getText());
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to download a file based on the selected format (CSV, TXT, PDF, RTF)
    public void downloadFileBasedOnFormat(String format) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Updated constructor
        WebElement formatButton;

        switch (format) {
            case "CSV":
                try {
                    formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportCsvActionComponent")));
                    formatButton.click();
                    System.out.println("CSV file format selected...");
                } catch (Exception e) {
                    System.out.println("Error clicking CSV export button: " + e.getMessage());
                    e.printStackTrace();
                }
                break;

            case "TXT":
                try {
                    formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportTextActionComponent")));
                    formatButton.click();
                    System.out.println("TXT file format selected...");
                } catch (Exception e) {
                    System.out.println("Error clicking TXT export button: " + e.getMessage());
                    e.printStackTrace();
                }
                break;

            case "PDF":
                try {
                    formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportPdfActionComponent")));
                    formatButton.click();
                    System.out.println("PDF file format selected...");
                } catch (Exception e) {
                    System.out.println("Error clicking PDF export button: " + e.getMessage());
                    e.printStackTrace();
                }
                break;

            case "RTF":
                try {
                    formatButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExportRtfActionComponent")));
                    formatButton.click();
                    System.out.println("RTF file format selected...");
                } catch (Exception e) {
                    System.out.println("Error clicking RTF export button: " + e.getMessage());
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Unsupported format: " + format);
                Assert.fail("Unsupported format: " + format);
                return;
        }

        // Wait for download completion (adjust this sleep time as needed)
        Thread.sleep(5000);
    }
}