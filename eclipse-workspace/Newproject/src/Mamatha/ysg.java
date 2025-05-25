package Mamatha;



import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ysg {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the URL: ");
        String url = "http://10.113.207.48:8180/rocfm/sparkLogin.jsp";

        System.out.print("Enter the username: ");
        String username = "Root";

        System.out.print("Enter the password: ");
        String password = "welcome567";

        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get(url);
            driver.manage().window().maximize();
            if (driver.getTitle().contains("404") || driver.findElements(By.xpath("//*[contains(text(), 'Not Found')]")).size() > 0) {
                System.out.println("Error: The URL is not correct.");
                driver.quit();
                scanner.close();
                return;
            }
        } catch (WebDriverException e) {
            System.out.println("Error: URL does not exist.");
            driver.quit();
            scanner.close();
            return;
        }
        try {
            // Attempt to log in
            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username-input-area")));
            usernameInput.sendKeys(username);

            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-input-area")));
            passwordInput.sendKeys(password);

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn")));
            loginButton.click();

            // Check if login was successful by checking for the presence of a specific element that appears after login
            wait.until(ExpectedConditions.elementToBeClickable(By.className("roc-dialog-close")));
            System.out.println("Login successful.");
        } catch (Exception e) {
            System.out.println("Login failed: Invalid username or password.");
            driver.quit();
            scanner.close();
            return;
        }

        try {
            WebElement dialogCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("roc-dialog-close")));
            dialogCloseButton.click();
            System.out.println("Closed dialog successfully.");
        } catch (Exception e) {
            System.out.println("Failed to close the dialog.");
        }

        try {
            WebElement navigationLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navigationLabel']")));
            navigationLabel.click();
            System.out.println("Navigated menu clicked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to open navigation menu.");
        }
        
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='gwt-HTML ag-gwt-megamenu-label' and @id='id-Admin']")));
            element.click();
            System.out.println("Admin clicked successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred clicking admin button: " + e.getMessage());
        }
        
        
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='gwt-HTML' and @id='id-Global Exceptions']")));
            element.click();
            System.out.println("Element clicked successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
        try {
            WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='yes' and contains(@class, 'gwt-Button')]")));
            yesButton.click();
            System.out.println("Clicked saved changes Yes button successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click saved changes Yes button.");
        }
        
        try {
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='closePopup' and contains(@class, 'notification-popup-close-button')]/img")));
            closeButton.click();
            System.out.println("Clicked on the close button inside the notification popup successfully.");
        } catch (Exception e) {
            System.out.println("Could not click on the close button inside the notification popup.");
        }
        
        Thread.sleep(4000);
        try
        { 
            WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']")));
            inputElement.sendKeys("Phone Number"+ Keys.RETURN);
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
        }
        
        
        try {
            WebElement buttonElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and @class='gwt-Button roc-primary-button' and @id='globalExceptionsDetail.save']")));
            buttonElement.click();
            System.out.println("Button clicked successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}
}
