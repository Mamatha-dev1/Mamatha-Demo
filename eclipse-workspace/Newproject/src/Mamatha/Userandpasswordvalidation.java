package Mamatha;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Userandpasswordvalidation {

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

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
            searchInput.sendKeys("User");

            // Click the User link after typing
            WebElement userLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Users")));
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
            WebElement SpecialactionsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Special Actions')]")));
            SpecialactionsButton = wait.until(ExpectedConditions.elementToBeClickable(SpecialactionsButton));  // Ensure it's clickable
            SpecialactionsButton.click();
            SpecialactionsButton.click();
            System.out.println("Clicked on Specialactions  successfully");
        } catch (Exception e) {
            System.out.println("Error while clicking on Specialactions: " + e.getMessage());
            Assert.fail("Error while clicking on Specialactions: " + e.getMessage());
        }
        //user validation
        Thread.sleep(2000);
        try {
            WebElement UserValidationActionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserValidationAction")));
            wait.until(ExpectedConditions.elementToBeClickable(UserValidationActionButton));
            
            // Use JavaScriptExecutor to click the element
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", UserValidationActionButton);
            System.out.println("Clicked on UserValidationAction successfully using JavaScript");
        } catch (Exception e) {
            System.out.println("Error while clicking on UserValidationAction");
            e.printStackTrace();  // Print the stack trace for debugging
            Assert.fail("Error while clicking on UserValidationAction");
        }
    
    try {
        // Click on 'Configured Rows' button
    	WebElement Alphanumericcheckbox = driver.findElement(By.xpath("//span[@class='gwt-CheckBox roc-gwt-checkbox roc-property-field']"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Alphanumericcheckbox);
    	Alphanumericcheckbox.click();
    	
    		    System.out.println("Clicked on Alphanumericcheckbox successfully");
    		
        } catch (Exception e) {
            System.out.println("Error while clicking on Alphanumericcheckbox");
            Assert.fail("Error while clicking on Alphanumericcheckbox");
        }
    try {
        // Click on 'Configured Rows' button
        WebElement Maxlength = driver.findElement(By.id("UserNameMaximumLength_id"));
        
        // Clear the existing value
        Maxlength.clear();
        System.out.println("cleared user");
    } catch (Exception e) {
        System.out.println("Error while clearing existing values");
        e.printStackTrace();  // Print the stack trace to diagnose the issue more clearly
        Assert.fail("Error while clearing existing values");
    }
Thread.sleep(3000);
    try {
       
    	 WebElement Maxlength1 = driver.findElement(By.id("UserNameMaximumLength_id"));

        // Ensure the field is focused and ready to accept input (wait for visibility and interactivity)
        wait.until(ExpectedConditions.elementToBeClickable(Maxlength1));
        Maxlength1.click();  // Make sure it is focused

        // Optionally, scroll into view to ensure the element is in the viewport (if needed)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Maxlength1);
        
        // Ensure there's no JavaScript interference or delay (if needed, add a small sleep)
        Thread.sleep(500);  // Small delay to ensure input is ready (use cautiously in real tests)

        // Use Scanner to get input (while avoiding blocking the execution)
        System.out.println("Please make sure that User name maximum length<=255");
        System.out.println("Please enter the User name maximum length");
        Scanner scanner1 = new Scanner(System.in);
        String UsernamelengthName = scanner1.nextLine();

        // Send the new value to the input field
        WebElement usernamemaxlengthInput = driver.findElement(By.id("UserNameMaximumLength_id"));
        usernamemaxlengthInput.sendKeys(UsernamelengthName);  // Enter the value into the field

        System.out.println("Entered User name maximum length successfully.");

    } catch (Exception e) {
        System.out.println("Error while entering User name maximum length");
        e.printStackTrace();  // Print the stack trace to diagnose the issue more clearly
        Assert.fail("Error while entering User name maximum length");
    }
    Thread.sleep(3000);
    try {
        // Click on 'Configured Rows' button
       
        WebElement Minlength =driver.findElement(By.id("UserNameMinimumLength_id"));
    	Minlength.clear();
    	System.out.println("cleared password");
    } catch (Exception e) {
        System.out.println("Error while clearing existing values");
        e.printStackTrace();  // Print the stack trace to diagnose the issue more clearly
        Assert.fail("Error while clearing existing values");
    }
    Thread.sleep(3000);
    try {
        // Click on 'Configured Rows' button
    	 WebElement Minlength1 =driver.findElement(By.id("UserNameMinimumLength_id"));
    	wait.until(ExpectedConditions.elementToBeClickable(Minlength1));
    	System.out.println("Please make sure that User name minimum length>=1");
    	System.out.println("Please enter the User name minimum length");
        Scanner scanner1 = new Scanner(System.in);
        String UsernamelengthName = scanner1.nextLine();
        WebElement usernameminlengthInput = driver.findElement(By.id("UserNameMinimumLength_id"));
        usernameminlengthInput.sendKeys(UsernamelengthName);
        System.out.println("Entered User name maximum length successfully.");
        
        } catch (Exception e) {
            System.out.println("Error while entering User name minimum length");
            Assert.fail("Error while entering User name minimum length");
        }
            //password validation
    Thread.sleep(3000);
        try {
            // Click on 'Configured Rows' button
            WebElement PasswordValidationActionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Password Validation']")));
            PasswordValidationActionButton.click();
            System.out.println("Clicked on PasswordValidationAction successfully");
            } catch (Exception e) {
                System.out.println("Error while clicking on PasswordValidationAction");
                Assert.fail("Error while clicking on PasswordValidationAction");
            }
    
    
    try {
        // Click on 'Configured Rows' button
    	 WebElement Regularexpressionpasswordvalidation = driver.findElement(By.xpath("//div[normalize-space()='Regular Expression Password Validation']"));
    	    
    	    // Wait for the element to be clickable
    	   
    	    wait.until(ExpectedConditions.elementToBeClickable(Regularexpressionpasswordvalidation));

    	    // Perform double-click using Actions
    	    Actions a = new Actions(driver);
    	    a.doubleClick(Regularexpressionpasswordvalidation).perform();
    	    
    	    System.out.println("Clicked on Regular expression password validation successfully");
    	} catch (Exception e) {
    	    System.out.println("Failed to click on Regular expression password validation");
    	    e.printStackTrace();
    	    Assert.fail("Failed to click on Regular expression password validation");
        }

    try {
        // Click on 'Configured Rows' button
    	WebElement field1 =driver.findElement(By.id("DisableAfterFailedLoginAttempts_id"));
    	field1.clear();
    	wait.until(ExpectedConditions.elementToBeClickable(field1));
    	
    	System.out.println("Please enter the value for Disable After Failed Login Attempts");
        Scanner scanner1 = new Scanner(System.in);
        String Disableafterfailed = scanner1.nextLine();
        WebElement DisableafterfailedInput = driver.findElement(By.id("DisableAfterFailedLoginAttempts_id"));
        DisableafterfailedInput.sendKeys(Disableafterfailed);
        System.out.println("Entered the value for Disable After Failed Login Attempts");
        
        } catch (Exception e) {
            System.out.println("Error while entering the value for Disable After Failed Login Attempts");
            Assert.fail("Error while the value for Disable After Failed Login Attempts");
        }

    try {
        // Click on 'Configured Rows' button
    	WebElement field2 =driver.findElement(By.id("DisableAfterInactivePeriod_id"));
    	field2.clear();
    	wait.until(ExpectedConditions.elementToBeClickable(field2));
    	
    	System.out.println("Please enter the value for Disable After Inactive Period (days)");
        Scanner scanner1 = new Scanner(System.in);
        String DisableafterInactive = scanner1.nextLine();
        WebElement DisableafterInactiveInput = driver.findElement(By.id("DisableAfterInactivePeriod_id"));
        DisableafterInactiveInput.sendKeys(DisableafterInactive);
        System.out.println("Entered the value for Disable After Inactive Period (days)");
        
        } catch (Exception e) {
            System.out.println("Error while entering the value for Disable After Inactive Period (days)");
            Assert.fail("Error while entering the value for Disable After Inactive Period (days)");
        }
    try {
        // Click on 'Configured Rows' button
    	WebElement field3 =driver.findElement(By.id("PasswordPreviousReused_id"));
    	field3.clear();
    	wait.until(ExpectedConditions.elementToBeClickable(field3));
    	
    	System.out.println("Please enter the value for Number of previous passwords that cannot be reused");
        Scanner scanner1 = new Scanner(System.in);
        String PasswordPreviousReused = scanner1.nextLine();
        WebElement PasswordPreviousReusedInput = driver.findElement(By.id("PasswordPreviousReused_id"));
        PasswordPreviousReusedInput.sendKeys(PasswordPreviousReused);
        System.out.println("Entered the value for Number of previous passwords that cannot be reused");
        
        } catch (Exception e) {
            System.out.println("Error while entering the value for Number of previous passwords that cannot be reused");
            Assert.fail("Error while the value for Number of previous passwords that cannot be reused");
        }
    try {
        // Click on 'Configured Rows' button
    	WebElement field4 =driver.findElement(By.id("PasswordResetInterval_id"));
    	field4.clear();
    	wait.until(ExpectedConditions.elementToBeClickable(field4));
    	
    	System.out.println("Please enter the value for Password Reset Interval (days)");
        Scanner scanner1 = new Scanner(System.in);
        String PasswordResetInterval = scanner1.nextLine();
        WebElement PasswordResetIntervalInput = driver.findElement(By.id("PasswordResetInterval_id"));
        PasswordResetIntervalInput.sendKeys(PasswordResetInterval);
        System.out.println("Entered the value for Password Reset Interval (days)");
        
        } catch (Exception e) {
            System.out.println("Error while entering the value for Password Reset Interval (days)");
            Assert.fail("Error while the value for Password Reset Interval (days)");
        }
    try {
        // Click on 'Configured Rows' button
    	WebElement field5 =driver.findElement(By.id("PasswordUniquePeriod_id"));
    	field5.clear();
    	wait.until(ExpectedConditions.elementToBeClickable(field5));
    	
    	System.out.println("Please enter the value for Password Unique Period (days)");
        Scanner scanner1 = new Scanner(System.in);
        String PasswordUniquePeriod = scanner1.nextLine();
        WebElement PasswordUniquePeriodInput = driver.findElement(By.id("PasswordUniquePeriod_id"));
        PasswordUniquePeriodInput.sendKeys(PasswordUniquePeriod);
        System.out.println("Entered the value for Password Unique Period (days)");
        
        } catch (Exception e) {
            System.out.println("Error while entering the value for Password Unique Period (days)");
            Assert.fail("Error while the value for Password Unique Period (days)");
        }
    try {
        // Click on 'Configured Rows' button
    	WebElement field4 =driver.findElement(By.id("PasswordWarningInterval_id"));
    	field4.clear();
    	wait.until(ExpectedConditions.elementToBeClickable(field4));
    	
    	System.out.println("Please enter the value for Password Warning Interval (days)");
        Scanner scanner1 = new Scanner(System.in);
        String PasswordWarningInterval = scanner1.nextLine();
        WebElement PasswordWarningIntervalInput = driver.findElement(By.id("PasswordWarningInterval_id"));
        PasswordWarningIntervalInput.sendKeys(PasswordWarningInterval);
        System.out.println("Entered the value for Password Warning Interval (days)");
        
        } catch (Exception e) {
            System.out.println("Error while entering the value for Password Warning Interval (days)");
            Assert.fail("Error while the value for Password Warning Interval (days)");
        }
    try {
        // Click on 'Configured Rows' button
    	
        WebElement savebutton = driver.findElement(By.id("userValidationDetail.save"));
        savebutton.click();
        System.out.println("Save button clicked successfully.");
        
        } catch (Exception e) {
            System.out.println("Error while saving the changes");
            Assert.fail("Error while saving the changes");
        }
    }
    }
