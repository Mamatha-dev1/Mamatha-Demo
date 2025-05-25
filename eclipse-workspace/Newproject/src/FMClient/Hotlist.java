package FMClient;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import FMClient.ConfigurationLoader;
import FMClient.LoginManager;
import FMClient.ScreenshotHelper;
 
public class Hotlist {
	
	// Get the singleton instance of LoginManager
	private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
	
    String hotlistGroupName = "";
    Scanner scanner = new Scanner(System.in);
 
    @Test
    public void HotlistPage() throws InterruptedException, IOException, TimeoutException {
        executeHotlistCreation(loginManager.getDriver());
        System.out.println("Hotlist created successfully");
    }
 
    public void executeHotlistCreation(WebDriver driver) throws InterruptedException, IOException, TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Thread.sleep(5000);
 
        try {
            // Navigate to Hotlist Groups Page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
            WebElement navigationLabel = driver.findElement(By.id("navigationLabel"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navigationLabel);
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
            searchInput.clear();
            searchInput.sendKeys("Hotlist");
 
            WebElement hotlistGroupLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Hotlist Groups")));
            hotlistGroupLink.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes"))).click();
            System.out.println("Navigating to Hotlist Groups Page.");
        } catch (Exception e) {
            System.out.println("Navigated to Hotlist Groups failed");
        }
 
        try {
            // Click on Common Tasks
            WebElement commonTasks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
            commonTasks.click();
            commonTasks.click();
            System.out.println("Clicked on Common Tasks successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on Common Tasks: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Error while clicking on Common Tasks: " + e.getMessage());
        }
 
        try {
            // Proceed to click New User action
            WebElement newUserAction = wait.until(ExpectedConditions.elementToBeClickable(By.id("NewActionComponent")));
            newUserAction.click();
            System.out.println("Navigating to Hotlist Groups creation Page.");
        } catch (Exception e) {
            System.out.println("Navigation to Hotlist Groups creation failed");
        }
 
        try {
            // Enter Hotlist Group Name
            System.out.println("Please enter the Hotlist group name:");
            hotlistGroupName = scanner.nextLine();
            WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fhogName")));
            userInput.sendKeys(hotlistGroupName);
            System.out.println("Entered Hotlist group name successfully");
        } catch (Exception e) {
            System.out.println("Error entering Hotlist group name: " + e.getMessage());
            Assert.fail("Entering Hotlist group name failed: " + e.getMessage());
        }
 
        try {
            // Save Hotlist Group
            driver.findElement(By.id("grouplistDetail.save")).click();
            System.out.println("Saved Hotlist group successfully");
        } catch (Exception e) {
            System.out.println("Error while saving Hotlist group: " + e.getMessage());
            Assert.fail("Error while saving Hotlist group:" + e.getMessage());
        }
Thread.sleep(5000);
        try {
            // Navigate to Hotlist Entities Page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
            WebElement navigationLabel = driver.findElement(By.id("navigationLabel"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navigationLabel);
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
            searchInput.clear();
            searchInput.sendKeys("Hotlist");
            WebElement hotlistGroupLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Hotlist Entities")));
            hotlistGroupLink.click();
            System.out.println("Navigating to Hotlist Entities Page.");
        } catch (Exception e) {
            System.out.println("Navigating to Hotlist Entities failed");
            Assert.fail("Error while Navigating to Hotlist Entities: " + e.getMessage());
        }
 
        Thread.sleep(3000);
        try {
                   // Wait for the "Common Tasks" element to be visible
                        WebElement commonTasks1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Common Tasks']")));
                        Actions action = new Actions(driver);
                        action.moveToElement(commonTasks1).click().perform();
                        commonTasks1.click();
                   System.out.println("Clicked on Common Tasks successfully.");
                } catch (Exception e) {
                   System.out.println("Failed to click on Common Tasks: " + e.getMessage());
                   e.printStackTrace();
                   Assert.fail("Error while clicking on Common Tasks: " + e.getMessage());
                }
 
        try {
            // Proceed to click the "New Entity" action
            WebElement newEntityAction = wait.until(ExpectedConditions.elementToBeClickable(By.id("NewActionComponent")));
            newEntityAction.click();
            System.out.println("Navigating to Hotlist Entities creation Page.");
        } catch (Exception e) {
            System.out.println("Navigation to Hotlist Entities creation failed");
        }
 
        try {
            // Select Radio Button for Online Rule
            WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.gwt-RadioButton.roc-gwt-radio.fm-gwt-hotlist-entities-startValueRadio input#fileRadio_InputElement")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
            System.out.println("Successfully clicked the radio button for online rule.");
        } catch (Exception e) {
            System.out.println("Failed to click the radio button for online rule.");
        }
 
        try {
            // Enter Start Value
            System.out.println("Please enter the phone number start value:");
            String startValue = scanner.nextLine();
            WebElement startValueInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startValueTextbox")));
            startValueInput.sendKeys(startValue);
            System.out.println("phone number start value entered successfully");
        } catch (Exception e) {
            System.out.println("Error while entering phone number Start value: " + e.getMessage());
            Assert.fail("Error while entering phone number Start value: " + e.getMessage());
        }
        
        try {
            // Enter Start Value
            System.out.println("Please enter the phone number end value:");
            String EndValue = scanner.nextLine();
            WebElement EndValueInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endValue")));
            EndValueInput.sendKeys(EndValue);
            System.out.println("phone number end value entered successfully");
        } catch (Exception e) {
            System.out.println("Error while entering phone number end value: " + e.getMessage());
            Assert.fail("Error while entering phone number end value: " + e.getMessage());
        }
 
        try {
            // Select Hotlist Group
            WebElement selectAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("expand-hotlistGroups")));
            selectAllButton.click();
            List<WebElement> hotlistGroups = driver.findElements(By.xpath("//div[@class='gwt-fms-widget-label']"));
            boolean groupFound = false;
 
            for (WebElement group : hotlistGroups) {
                if (group.getText().equalsIgnoreCase(hotlistGroupName)) {
                    group.click();
                    System.out.println("Selected hotlist group: " + group.getText());
                    groupFound = true;
                    break;
                }
            }
 
            if (!groupFound) {
                System.out.println("Hotlist group not found: " + hotlistGroupName);
            }
        } catch (Exception e) {
            System.out.println("Error while selecting the hotlist group: " + e.getMessage());
        }
 
        try {
            // Click Done button
            WebElement doneButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Done']")));
            doneButton.click();
            System.out.println("Clicked on Done button successfully");
        } catch (Exception e) {
            System.out.println("Failed to click on Done button");
        }
 
        try {
            // Save Entity
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("entitiesDetail.save")));
            saveButton.click();
            System.out.println("Clicked on save button successfully");
        } catch (Exception e) {
            System.out.println("Failed to click on save button");
        }
 
        // Wait and take screenshot
        Thread.sleep(5000);
        String baseDir = System.getProperty("user.dir");
        ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
        ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);
        screenshotHelper.takeScreenshot(driver);
 
        try {
            // Clear Filters
            WebElement advancedFilterButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-filter-button")));
            advancedFilterButton.click();
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolbar-button-image-new-adv-filter-clearAll")));
            deleteButton.click();
            driver.findElement(By.id("ok")).click();
 
            WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']")));
            inputElement.sendKeys("Entity Start Value ");
            inputElement.sendKeys("=" + Keys.RETURN);
 
            System.out.println("Please enter the phone number start value:");
            String hotlistStartValue = scanner.nextLine();
            inputElement.sendKeys(hotlistStartValue);
            inputElement.sendKeys(Keys.RETURN);
 
            System.out.println("Phone number start value entered and Enter key pressed.");
            driver.findElement(By.id("filter-search-button")).click();
 
            Thread.sleep(5000);
           
            screenshotHelper.takeScreenshot(driver);
 
        } catch (Exception e) {
            System.out.println("Error during advanced filtering: " + e.getMessage());
        }
 
        try {
            // Right-click on hotlist
            WebElement hotlistButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Phone Number']")));
            if (hotlistButton.isDisplayed() && hotlistButton.isEnabled()) {
                Actions actions = new Actions(driver);
                actions.contextClick(hotlistButton).perform();
                System.out.println("Right-clicked on hotlist successfully");
            }
        } catch (Exception e) {
            System.out.println("Failed to right-click on hotlist: " + e.getMessage());
        }
        try {
                   
            WebElement commonTasks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
                Actions action = new Actions(driver);
                action.moveToElement(commonTasks).click().perform();
            // Proceed to click the "New User" action
            
            System.out.println("Clicked on common tasks successfully");
        } catch (Exception e) {
                System.out.println("failed to click on common tasks");
        }
        try {
            // Wait for the search input box to be visible and type 'User' into it
                WebElement Editbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Edit')]")));
           
                Editbutton.click();
            WebElement startvaluebox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startValueTextbox")));
            startvaluebox.clear();
            // Proceed to click the "New User" action
          
            System.out.println("Phone number start value cleared successfully");
        } catch (Exception e) {
                System.out.println("failed while clearing Phone number start value ");
        }
        String startvalue1 = "";
        try {
            // Enter username
            System.out.println("Please enter the start value:");
            startvalue1 = scanner.nextLine();
            
            // Find the username input element and enter the username
            WebElement StartvalueInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startValueTextbox")));
             // Clear any pre-existing text
            StartvalueInput.sendKeys(startvalue1);
            System.out.println("Start value entered successuly");
        } catch (Exception e) {
            System.out.println("Error while entering Start value:" + e.getMessage());
            Assert.fail("Error while entering Start value:" + e.getMessage());
        }
        try {
            // Wait for the search input box to be visible and type 'User' into it
                WebElement Savebutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("entitiesDetail.save")));
           
                Savebutton.click();
            
            // Proceed to click the "New User" action
            
            System.out.println("Clicked on save button successfully");
        } catch (Exception e) {
                System.out.println("failed to click on save button");
        }
        
        try {
            // Clear Filters
            WebElement advancedFilterButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-filter-button")));
            advancedFilterButton.click();
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolbar-button-image-new-adv-filter-clearAll")));
            deleteButton.click();
            driver.findElement(By.id("ok")).click();
 
            WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']")));
            inputElement.sendKeys("Entity Start Value ");
            inputElement.sendKeys("=" + Keys.RETURN);
 
            System.out.println("Please enter the phone number start value which you have given while editing the hotlist:");
            String hotlistStartValue = scanner.nextLine();
            inputElement.sendKeys(hotlistStartValue);
            inputElement.sendKeys(Keys.RETURN);
 
            System.out.println("Phone number start value entered and Enter key pressed.");
            driver.findElement(By.id("filter-search-button")).click();
 
            Thread.sleep(5000);
            screenshotHelper.takeScreenshot(driver);
 
        } catch (Exception e) {
            System.out.println("Error during advanced filtering: " + e.getMessage());
        }
        try {
            // Wait for the search input box to be visible and type 'User' into it
            WebElement hotlistbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Phone Number']")));
           
            // Ensure the element is visible before performing any action
            if (hotlistbutton.isDisplayed() && hotlistbutton.isEnabled()) {
                // Initialize Actions class
                Actions a = new Actions(driver);
                
                // Perform right-click (context click) on the hotlist button
                a.contextClick(hotlistbutton).perform();
                System.out.println("Right-clicked on hotlist successfully");
            } else {
                System.out.println("Element is not displayed or enabled for right-click.");
            }
        } catch (Exception e) {
            System.out.println("Failed to right-click on hotlist: " + e.getMessage());
            e.printStackTrace();
        }
        try {
           
            WebElement commonTasks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
                Actions action = new Actions(driver);
                action.moveToElement(commonTasks).click().perform();
            // Proceed to click the "New User" action
            
            System.out.println("Clicked on common tasks successfully");
        } catch (Exception e) {
                System.out.println("failed to click on common tasks");
        }
        try {
            // Wait for the search input box to be visible and type 'User' into it
                WebElement Deletebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Delete')]")));
           
                Deletebutton.click();
            WebElement yesbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes")));
            yesbutton.click();
            // Proceed to click the "New User" action
            WebElement okbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ok")));
            
            okbutton.click();
            System.out.println("Hotlist got deleted successfully");
        } catch (Exception e) {
                System.out.println("failed while deleting Hotlist");
        }
   }
}

