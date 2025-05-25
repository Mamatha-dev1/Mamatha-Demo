package FMClient;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper1 {
    
    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        
        // Path to save the screenshot in OneDrive
        String oneDrivePath = "C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Automation ss";
        
        File destinationDir = new File(oneDrivePath);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs(); // Create the directory if it does not exist
        }
        File destinationFile = new File(destinationDir, fileName);
        FileUtils.copyFile(sourceFile, destinationFile);
        System.out.println("Screenshot saved as " + destinationFile.getAbsolutePath());
    }
}
