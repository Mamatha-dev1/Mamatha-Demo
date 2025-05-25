package Mamatha;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginManager {
    private static LoginManager instance;
    private static WebDriver driver;
    private Mamatha.configLoader configLoader;

    // Constructor that initializes WebDriver and loads config
    public LoginManager(String baseDir) {
        // Load configuration
        this.configLoader = new Mamatha.configLoader(baseDir);

        // Get the desired browser (chrome/firefox) from config
        String browser = configLoader.getProperty("browser");  // Assuming the browser name is in config file (chrome/firefox)
        
        if (browser == null || browser.isEmpty()) {
            System.out.println("No browser specified in the config file.");
            return;
        }

        // Selenium Manager automatically handles the WebDriver binary, so no need for setting path manually
        if (browser.equalsIgnoreCase("chrome")) {
            // Initialize ChromeOptions
            ChromeOptions options = new ChromeOptions();
            
            // Example Chrome preferences (customize as needed)
            String downloadDir = "C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Automation ss"; // Set your preferred download directory
            options.addArguments("download.default_directory=" + downloadDir);  // Set the default download directory
            options.addArguments("download.prompt_for_download=false");  // Disable the 'Save As' prompt
            options.addArguments("safebrowsing.enabled=true");  // Enable safe browsing

            // Initialize ChromeDriver with the configured ChromeOptions
            this.driver = new ChromeDriver(options);  // Pass ChromeOptions to the driver initialization
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Initialize FirefoxDriver with Selenium Manager (no need for options in this case)
            this.driver = new FirefoxDriver(); // Selenium Manager automatically downloads the driver
        } else {
            System.out.println("Unsupported browser: " + browser);
            return;
        }

        // Perform login after WebDriver initialization
        login();
    }

    public static synchronized LoginManager getInstance(String baseDir) {
        if (instance == null) {
            instance = new LoginManager(baseDir);
        }
        return instance;
    }

    // Getter for WebDriver if needed in other classes
    public static WebDriver getDriver() {
        return driver;
    }

    // Perform login based on loaded config details
    public void login() {
        // Load username, password, and URL from config file
        String username = configLoader.getProperty("username");
        String password = configLoader.getProperty("password");
        String appUrl = configLoader.getProperty("app.url");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));  // Extended wait time

        // Ensure that driver is not null before navigating
        if (driver != null && appUrl != null && !appUrl.isEmpty()) {
            driver.get(appUrl);
            System.out.println("Navigating to application URL...");
             
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username-input-area"))).sendKeys(username);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-input-area"))).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btn"))).click();
            driver.manage().window().maximize();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("open-alarm-popup-cancel"))).click();
//           wait.until(ExpectedConditions.elementToBeClickable(By.id("ok"))).click();
//            wait.until(ExpectedConditions.elementToBeClickable(By.id("yes"))).click();
            System.out.println("Login successful.");
        } else {
            System.out.println("WebDriver is not initialized properly or app URL is missing.");
        }
    }

    // Clean up WebDriver
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            instance = null; // Optionally reset instance on close
        }
    }
}