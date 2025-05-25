package FMClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {

    private ConfigurationLoader configLoader;

    // Constructor to initialize ConfigurationLoader
    public ScreenshotHelper(ConfigurationLoader configLoader) {
        this.configLoader = configLoader;
    }

    // Method to generate a timestamp for the screenshot file name
    private String generateTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    // Method to take the screenshot with timestamp automatically appended to the file name
    public void takeScreenshot(WebDriver driver) throws IOException {
        // Get the current timestamp
        String timestamp = generateTimestamp();

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Use ConfigurationLoader to get the screenshot path
        String screenshotPath = configLoader.getProperty("screenshot.path");

        // Validate the screenshotPath
        if (screenshotPath == null || screenshotPath.trim().isEmpty()) {
            System.err.println("Screenshot path is not configured correctly in the properties file.");
            return;
        }

        File destinationDir = new File(screenshotPath);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs(); // Create the directory if it does not exist
        }

        // Create a file name with the timestamp
        String resultFileName = "result_screenshot_" + timestamp + ".png";
        File destinationFile = new File(destinationDir, resultFileName);

        // Copy the screenshot to the destination
        FileUtils.copyFile(sourceFile, destinationFile);
        System.out.println("Screenshot saved as " + destinationFile.getAbsolutePath());
    }
}