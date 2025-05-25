package FMClient;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginManager {
    private static LoginManager instance;
    private WebDriver driver;
    private ConfigurationLoader configLoader;

    // Constructor that initializes WebDriver and loads config
    public LoginManager(String baseDir) {
        // Load configuration
        this.configLoader = new ConfigurationLoader(baseDir);

String browser = configLoader.getProperty("browser");  // Assuming the browser name is in config file (chrome/firefox)
        
        
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
            // Initialize FirefoxDriver (no need for options in this case)
            this.driver = new FirefoxDriver(); // Selenium Manager automatically downloads the driver
        } else {
            System.out.println("Unsupported browser: " + browser);
            return;
        }

        driver.manage().window().maximize(); // Maximize the browser window
        login(); // Perform login right after initializing the WebDriver
    }// Perform login right after initializing the WebDriver
    

    public static synchronized LoginManager getInstance(String baseDir) {
        if (instance == null) {
            instance = new LoginManager(baseDir);
        }
        return instance;
    }
    
    // Getter for WebDriver if needed in other classes
    public WebDriver getDriver() {
        return driver;
    }
    
    // Perform login based on loaded config details
    public void login() {
        String username = configLoader.getProperty("username");
        String password = configLoader.getProperty("password");
        String appUrl = configLoader.getProperty("app.url");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Wait time
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait time

        // Login logic using WebDriver
        driver.get(appUrl);
        System.out.println("Navigating to application URL...");
        try {
            // Wait for the username input to be clickable and enter username
            WebElement Advancedbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("details-button")));
            Advancedbutton.click();
            driver.findElement(By.id("proceed-link")).click();
            System.err.println("Clicked on proceed button successfully");
        } catch (Exception e) {
            System.err.println("An error occurred during login due to privacy " + e.getMessage());
            e.printStackTrace();
        }
    
        try {
            // Wait for the username input to be clickable and enter username
            WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("username-input-area")));
            driver.findElement(By.id("appNameInner")).click();
            driver.findElement(By.xpath("//option[normalize-space()='ROC Fraud Management']")).click();
            usernameInput.click();
            usernameInput.sendKeys(username);
            System.out.println("Entered username successfully.");

            // Wait for the password input to be clickable and enter password
            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("password-input-area")));
            passwordInput.click();
            passwordInput.sendKeys(password);
            passwordInput.sendKeys(Keys.ENTER);
            System.out.println("Entered password and logged in successfully.");  
                    
            // Wait for the page to load after login and cancel open alarm pop-up box if exists
            Thread.sleep(5000);
            waitTillLoading();
        
            // Handle Open Alarm pop-up if it appears
            handlePopUp(wait, "#open-alarm-popup-cancel", "Open Alarm Pop-up cancel button");

            // Handle Notifications pop-up if it appears
            handlePopUp(shortWait, "#closePopup > .gwt-Image", "Notifications Pop-up close button");
            
            //Close the License Expired Pop-up box
            closeLicenseExpiredPopUp(wait);

            driver.switchTo().defaultContent();
            
        } catch (Exception e) {
            System.err.println("An error occurred during login: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void waitTillLoading() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));  // Wait time
        String loadingText = "Please wait...";
        try {
            // Find the loading element and wait until it disappears
            if (driver.getPageSource().contains(loadingText)) {
            	WebElement loadingElement = driver.findElement(By.cssSelector(".gwt-HTML.phoenix-loading-label"));
                wait.until(ExpectedConditions.invisibilityOf(loadingElement));
            }
        } catch (NoSuchElementException e) {
            // If the loading element is not found, it means the loading screen is not present, so we can continue
            System.out.println("Loading screen not found, proceeding...");
        }
    }

    public void handlePopUp(WebDriverWait wait, String elementLocator, String elementName) {
        try {
            // Wait for the pop-up element and click the cancel/close button
            WebElement popUpElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementLocator)));
            WebElement popUpExitButton = wait.until(ExpectedConditions.elementToBeClickable(popUpElement));
            popUpExitButton.click();
            System.out.println("Clicked " + elementName + " successfully.");
        } catch (TimeoutException e) {
            System.out.println(elementName + " was not clickable or present, skipping click.");
        }
    }
    
    private void closeLicenseExpiredPopUp(WebDriverWait wait) {
        try {
            // Define the possible messages for "License Expired"
            String licenseExpiredText = "License Expired. Please contact Subex Ltd Support";
            String licenseExpiredTextAlt = "The license key has expired. Please contact Subex Ltd support.";

            // Get the page source once to avoid redundant calls
            String pageSource = driver.getPageSource();

            // Check if either of the license expired messages is present in the page source
            if (pageSource.contains(licenseExpiredText) || pageSource.contains(licenseExpiredTextAlt)) {
                WebElement licenseExpiredDiv;

                // Check which text is present and select the appropriate element
                if (pageSource.contains(licenseExpiredText)) {
                    // If the main text is present, locate the element using the main text
                    licenseExpiredDiv = driver.findElement(By.cssSelector(".gwt-HTML[title*='" + licenseExpiredText + "']"));
                } else {
                    // If the alternate text is present, locate the element using the alt text
                    licenseExpiredDiv = driver.findElement(By.cssSelector(".gwt-HTML[title*='" + licenseExpiredTextAlt + "']"));
                }

                // Extract the title attribute and clean up the HTML tags
                String licenseExpiredTitle = licenseExpiredDiv.getAttribute("title").replaceAll("<[^>]+>", "");
                System.out.println("Warning detected with the following message: " + licenseExpiredTitle);

                // Wait for and click the "OK" button on the pop-up
                WebElement okButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".gwt-Button#ok")));
                okButton.click();
                System.out.println("Closed License Expired pop-up successfully.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("License expired pop-up did not appear or could not be closed.");
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

