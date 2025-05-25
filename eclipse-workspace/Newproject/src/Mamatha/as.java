package Mamatha;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class as {

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
            wait.until(ExpectedConditions.elementToBeClickable(By.id("open-alarm-popup-cancel"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ok"))).click();
            System.out.println("Login successful.");
        } catch (Exception e) {
            System.out.println("Login failed: Invalid username or password.");
            driver.quit();
            scanner.close();
            return;
        }

        

        try {
            WebElement navigationLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navigationLabel']")));
            navigationLabel.click();
            System.out.println("Navigated menu clicked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to open navigation menu.");
        }

        try {
            WebElement ruleManagement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Rule Management")));
            ruleManagement.click();
            System.out.println("Navigated to Rule Management successfully.");
        } catch (Exception e) {
            System.out.println("Failed to navigate to Rule Management.");
        }

        try {
            WebElement functionAndCombinedRules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Function and Combined Rules")));
            functionAndCombinedRules.click();
            System.out.println("Navigated to Function and Combined Rules successfully.");
        } catch (Exception e) {
            System.out.println("Failed to navigate to Function and Combined Rules.");
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
        
        Thread.sleep(4000);

        try {
            WebElement ruleAddButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("test-rule-add-button")));
            ruleAddButton.click();
            System.out.println("Clicked Rule Add button successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click Rule Add button.");
        }
        
        Thread.sleep(2000);
        
        try {
            WebElement testRuleName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frulName")));
            System.out.println("Please enter the Rulename:");
            Scanner scanner1 = new Scanner(System.in);
            String Username1 = scanner1.nextLine();
            WebElement usernameInput = driver.findElement(By.id("frulName"));
            usernameInput.sendKeys(Username1);
            System.out.println("Entered rule name successfully.");
        } catch (Exception e) {
            System.out.println("Failed to enter rule name.");
        }

       
        try {
            WebElement monitormenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expand-monitoringFieldMultiSelect")));
            monitormenu.click();
            System.out.println("Clicked on monitor menu successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on monitor menu.");
        }

        try {
            WebElement fieldCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FieldCategoryModelPhone Number2")));
            fieldCategory.click();
            System.out.println("Clicked on Field Category successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on Field Category.");
        }
        
        try {
            WebElement exitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and contains(text(), 'Done')]")));
            exitButton.click();
            System.out.println("Exit button 'test done' clicked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to interact with the navigation menu or exit button.");
        }

        
        
        try {
            WebElement dataStreamType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RecConfModelCDR1")));
            dataStreamType.click();
            System.out.println("Clicked on Data Stream Type successfully.");
            
        } catch (Exception e) {
            System.out.println("Failed to click on Data Stream Type.");
        }

        try {
            WebElement groupType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".gwt-fms-multiselect-select-all")));
            groupType.click();
            System.out.println("Clicked on Group Type successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on Group Type.");
        }
        
        Thread.sleep(2000);
        
        try {
            // First click
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
            if (nextButton.isDisplayed() && nextButton.isEnabled()) {
                nextButton.click();
                System.out.println("Clicked on 'Next' button successfully.");
            } else {
                System.out.println("Next button is not clickable.");
            }
            
            // Wait before the second click
            Thread.sleep(2000);
            
            // Second click
            nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
            if (nextButton.isDisplayed() && nextButton.isEnabled()) {
                nextButton.click();
                System.out.println("Clicked on 'Next' button successfully.");
            } else {
                System.out.println("Next button is not clickable.");
            }
        } catch (Exception e) {
            System.out.println("Failed to click on 'Next' button.");
            e.printStackTrace();
        }
        
        try {
        	WebElement functionType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PseudoFunctionModelCount Of Records6")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", functionType);
            functionType.click();
            System.out.println("Selected function type successfully.");
        } catch (Exception e) {
            System.out.println("Failed to select function type.");
            e.printStackTrace();
        }

        try {
            WebElement thresholdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("baseThreshold")));
            thresholdInput.sendKeys("1");
            System.out.println("Entered threshold value successfully.");
        } catch (Exception e) {
            System.out.println("Failed to enter threshold value.");
            e.printStackTrace();
        }
        
        Thread.sleep(2000);

        try {
            WebElement nextButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
            nextButton1.click();
            System.out.println("Clicked on 'Next' button successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on 'Next' button.");
            e.printStackTrace();
        }
        
        Thread.sleep(2000);
       
        try {
            WebElement nextButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
            nextButton2.click();
            System.out.println("Clicked on 'Next' button successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on 'Next' button.");
            e.printStackTrace();
        }
        
        Thread.sleep(2000);
        
        try {
            WebElement nextButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
            nextButton2.click();
            System.out.println("Clicked on 'Next' button successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on 'Next' button.");
            e.printStackTrace();
        }

        try {
            // Locate and click on system action type element
            WebElement systemactiontype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SystemActionModelGenerate Test Alert61")));
            systemactiontype.click();
            System.out.println("Clicked on System Action type successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on System Action type.");
            e.printStackTrace();
        }
        
        Thread.sleep(2000);
        
        try {
            WebElement nextButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
            nextButton2.click();
            System.out.println("Clicked on 'Next' button successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on 'Next' button.");
            e.printStackTrace();
        }
        
        Thread.sleep(2000);
        
        try {
        	WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(
        		    By.cssSelector("span.gwt-RadioButton.roc-gwt-radio.gwt-test-rule-online-button input#onlineRadioButton_InputElement")
        		));
        		((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
        	System.out.println("Successfully clicked the radio button using XPath.");
        } catch (Exception e) {
            System.out.println("Failed to click the radio button");
            e.printStackTrace();
       }
        
        
        
       Thread.sleep(2000);
       try { 
            WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and contains(@class, 'gwt-Button roc-primary-button') and @id='ruleDetail.save']")));
            saveButton.click();
            System.out.println("Saved Rule successfully.");
       } catch (Exception e) {
            System.out.println("Failed to save the rule.");
            e.printStackTrace();
       }
       
       
       try {
            Thread.sleep(14000);
            System.out.println("Rule Management page exists.");
       } catch (InterruptedException e) {
            System.out.println("An error occurred during the sleep period.");
       }
	}
}
