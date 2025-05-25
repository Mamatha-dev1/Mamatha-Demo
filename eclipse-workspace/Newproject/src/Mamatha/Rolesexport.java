package Mamatha;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Mamatha.LoginManager;

public class Rolesexport {

    @Test
    public void testUsercreate() throws InterruptedException, IOException {
        // Get the singleton instance of LoginManager
        LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

        // Use the driver from LoginManager
        executeUserexport(loginManager.getDriver());
        System.out.println("Roles list exported successfully");
    }

    private void executeUserexport(WebDriver driver) throws InterruptedException {
        // Set timeout for the driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

        // Allow page loading and initialization
        Thread.sleep(4000);

        try {
            // Navigate to Users section
            WebElement navigationLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='navigationLabel']")));
            navigationLabel.click();
        } catch (Exception e) {
            System.out.println("Error navigating to Users section: " + e.getMessage());
            Assert.fail("Navigation to Users section failed: " + e.getMessage());
        }

        try {
            // Wait for the search input box to be visible and type 'User' into it
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
            searchInput.clear();  // Clear any pre-existing text
            searchInput.sendKeys("Role");

            // Click the User link after typing
            WebElement userLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Roles")));
            userLink.click();
//            wait.until(ExpectedConditions.elementToBeClickable(By.id("yes"))).click();

            // Log status
            System.out.println("Navigating to Roles page: ");
        } catch (Exception e) {
            System.out.println("Error while Navigating to Roles page");
            Assert.fail("Error while Navigating to Roles page: " + e.getMessage());
        }

        Thread.sleep(4000);  // Wait to ensure the page is loaded

        try {
            // Click on Export button
            WebElement exportButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Export")));
            exportButton = wait.until(ExpectedConditions.elementToBeClickable(exportButton));  // Ensure it's clickable
            exportButton.click();
           
            System.out.println("Clicked on Export option successfully");
        } catch (Exception e) {
            System.out.println("Error while clicking on Export option: " + e.getMessage());
            Assert.fail("Error while clicking on Export option: " + e.getMessage());
        }

        try {
            // Click on 'Configured Rows' button
            WebElement configuredRowsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Configured Rows')]")));
            configuredRowsButton.click();
            System.out.println("Clicked on Configured rows successfully");
            
            // Separate try-catch for waiting for the file download
            String downloadDir = "C:\\Users\\mamatha.kp\\Downloads";  
            // Set your download directory
            Thread.sleep(4000);
            try {
                waitForMostRecentFileDownload(downloadDir, ".txt");  // Check for the most recent .txt file
                System.out.println("File downloaded successfully to: " + downloadDir);
            } catch (Exception e) {
                System.out.println("Error while waiting for file download: " + e.getMessage());
                Assert.fail("Error while waiting for file download: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error while clicking on Configured rows");
            Assert.fail("Error while clicking on Configured rows: " + e.getMessage());
        }
    }

    // Method to wait for the most recently downloaded file in the specified directory
    private static void waitForMostRecentFileDownload(String downloadDir, String fileExtension) {
        File dir = new File(downloadDir);
        File[] files = null;
        long startTime = System.currentTimeMillis();
        long timeout = 60000;  // Timeout for file download (60 seconds)

        // Wait for the file to appear in the directory
        while (System.currentTimeMillis() - startTime < timeout) {
            // List all files in the directory that end with the given file extension (e.g., .txt)
            files = dir.listFiles((dir1, name) -> name.endsWith(fileExtension));

            if (files != null && files.length > 0) {
                // Sort files by last modified time in descending order (most recent first)
                File mostRecentFile = getMostRecentFile(files);

                // Check if the most recent file is downloaded
                System.out.println("Found the most recent file: " + mostRecentFile.getAbsolutePath());
                return; // Exit method after finding the most recent file
            }

            // Debugging: Log the current list of files in the directory
            System.out.println("Waiting for file download... Checking files in directory:");
            File[] currentFiles = dir.listFiles();
            if (currentFiles != null) {
                for (File file : currentFiles) {
                    System.out.println(" - " + file.getName());
                }
            }

            try {
                Thread.sleep(1000);  // Wait for 1 second before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // If file is not downloaded within the timeout, throw an error
        throw new RuntimeException("File download failed or timed out. Expected file with extension: " + fileExtension);
    }

    // Helper method to get the most recent file based on the last modified time
    private static File getMostRecentFile(File[] files) {
        File mostRecentFile = files[0];  // Assume the first file is the most recent initially
        for (File file : files) {
            if (file.lastModified() > mostRecentFile.lastModified()) {
                mostRecentFile = file;
            }
        }
        return mostRecentFile;
    }
}
