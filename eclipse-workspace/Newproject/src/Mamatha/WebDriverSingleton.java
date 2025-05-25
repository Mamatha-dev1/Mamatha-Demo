package Mamatha;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {

    private static WebDriver driver;
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\mamatha.kp\\eclipse-workspace\\Newproject\\src\\Mamatha\\config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Return the WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = properties.getProperty("browser");

            // Initialize WebDriver based on browser type
            switch (browser.toLowerCase()) {
                case "chrome":
                    
                    driver = new ChromeDriver();
                    break;
                
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.get(properties.getProperty("url"));  // Navigate to the URL
        }
        return driver;
    }

    // Quit WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}