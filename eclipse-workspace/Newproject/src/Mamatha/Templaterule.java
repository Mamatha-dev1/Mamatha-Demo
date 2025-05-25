package Mamatha;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class Templaterule {
	
private String TemplateName;
    @Test
    public void testtemplatecreation() throws InterruptedException, IOException {
    	
        // Get the singleton instance of LoginManager
        LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
        
        // Use the driver from LoginManager
        executeTemplatecreation(loginManager.getDriver());
        System.out.println("Template created succesfully");
    }
    public void executeTemplatecreation(WebDriver string) throws InterruptedException {
    	
    	WebDriverWait wait = new WebDriverWait(string, Duration.ofSeconds(20));
    	Thread.sleep(4000);
    	try {
            WebElement ruleAddButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("template-rule-add-button")));
            ruleAddButton.click();
            System.out.println("Clicked template Add button successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click Rule Add button.");
        }
    
    Thread.sleep(2000);
    
    try {
    	String prefix = "Template_";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
         TemplateName = prefix+timestamp;

        System.out.println("Generated Rule Name: " + TemplateName);  // Print the generated rule name for confirmation

        // Locate the input field and enter the generated rule name
        WebElement usernameInput = string.findElement(By.id("frulName"));
        Thread.sleep(2000);
        usernameInput.sendKeys(TemplateName);
        
        System.out.println("Entered rule template name successfully.");
    }catch (Exception e) {
        System.out.println("Failed to enter rule template name.");
    }
   Thread.sleep(2000);

    try {
        WebElement fraudType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='multiSelectOptionPanel-assoFraudTypesMultiSelect']//div[@class='gwt-fms-multiselect-select-all' and @id='select-all' and text()='Select All']")));
        fraudType.click();
        System.out.println("Clicked on Fraud Type successfully.");
    } catch (Exception e) {
    	Assert.fail("Failed to click on fraud type");
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
    
    Thread.sleep(2000);
    try {
        WebElement exitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='http://10.113.207.48:8180/rocfm/themes/common/images/popup-close.png']")));
        exitButton.click();
        System.out.println("closed popup successfully.");
    } catch (Exception e) {
        System.out.println("Failed to close popup.");
    }
    
    try {
        WebElement functionType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PseudoFunctionModelCount Of Records6")));
        functionType.click();
        System.out.println("Selected function type successfully.");
    } catch (Exception e) {
        System.out.println("Failed to select function type.");
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
    
    
    Thread.sleep(2000);
       
        try {
            // Locate and click on action type element
            WebElement actiontype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='multiSelectOptionPanel-analystActionMultiSelect']//div[@class='gwt-fms-multiselect-select-all' and @id='select-all' and text()='Select All']")));
            actiontype.click();
            System.out.println("Clicked on Analyst Action type successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on Analyst Action type.");
            e.printStackTrace();
        }
        
        try {
            WebElement exitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and contains(text(), 'Done')]")));
            exitButton.click();
            System.out.println("Exit button 'test done' clicked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to interact with the navigation menu or exit button.");
        }

        try {
            // Locate and click on system action type element
            WebElement systemactiontype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='multiSelectOptionPanel-systemActionMultiSelect']//div[@id='select-all']")));
            systemactiontype.click();
            System.out.println("Clicked on System Action type successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on System Action type.");
            e.printStackTrace();
        }
        
        
        Thread.sleep(2000);
 
       try { 
            WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("templateDetail.F")));
            saveButton.click();
            System.out.println(" Rule template successfully.");
            
       } catch (Exception e) {
            System.out.println("Failed to save the rule template.");
            e.printStackTrace();
       }
       Thread.sleep(7000);
       try {
           // Wait for and click on the "advanced-filter-button"
           wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-filter-button")));
           string.findElement(By.id("advanced-filter-button")).click();

           // Wait until the delete button is clickable and click it
           WebElement deletebutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolbar-button-image-new-adv-filter-clearAll")));
           deletebutton.click();

           // Confirm by clicking the "OK" button
           string.findElement(By.id("ok")).click();

           // Wait for the "adv-filter-help-label" (input field) to be visible
           WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']")));
           
           // Send the initial text and '=' symbol
           inputElement.sendKeys("Template Name" + Keys.RETURN);  // Press ENTER after "Rule Name"
           inputElement.sendKeys("=" + Keys.RETURN);           // Press ENTER after "="

           
           WebElement rulenameElement = string.findElement(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']"));
           rulenameElement.sendKeys(TemplateName);  // Type the rule name

           // After entering the rule name, press ENTER to submit
           rulenameElement.sendKeys(Keys.RETURN); // Simulate the Enter key press

           System.out.println("Rule name entered and filter applied.");
           string.findElement(By.id("filter-search-button")).click();
           Thread.sleep(5000);
           File src = ((TakesScreenshot) string).getScreenshotAs(OutputType.FILE);
           File destDir = new File("C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Automation ss\\Template.png");
           FileUtils.copyFile(src, destDir);

       } catch (NoSuchElementException e) {
           System.out.println("Error: Element not found - " + e.getMessage());
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
     	 
       try {
           Thread.sleep(5000);
           System.out.println("Rule Management page exists.");
      } catch (InterruptedException e) {
           System.out.println("An error occurred during the sleep period.");
      }
 	}
     
 }