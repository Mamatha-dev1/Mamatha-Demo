package Mamatha;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotlistFromFile {

	String hotlistGroupName = "";
	
	
	 @Test
	 public void HotlistPage() throws InterruptedException, IOException, TimeoutException {
	     // Get the singleton instance of LoginManager
	     LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
	     
	     // Use the driver from LoginManager (static method should be accessed via class name)
	     executeHotlistcreation(LoginManager.getDriver());
	     System.out.println("Hotlist created successfully");
	 }

	 public void executeHotlistcreation(WebDriver driver) throws InterruptedException, IOException, TimeoutException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80)); 
		 Thread.sleep(5000);
       try {
       	
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
           WebElement navigationLabel = driver.findElement(By.id("navigationLabel"));
           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navigationLabel);
           WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
           searchInput.clear();  // Clear any pre-existing text
           searchInput.sendKeys("Hotlist");

           // Click the User link after typing
           WebElement HotlistgroupLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Hotlist Groups")));
           HotlistgroupLink.click();
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes"))).click();
           System.out.println("Navigating to Hotlist Groups Page.");
       } catch (Exception e) {
       	System.out.println("Navigated to Hotlist Groups failed");
       }
       try {
           // Wait for the "Common Tasks" element to be visible
       	
       	WebElement commonTasks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
       	Actions action = new Actions(driver);
       	action.moveToElement(commonTasks).click().perform();
       	commonTasks.click();
       	commonTasks.click();
           System.out.println("Clicked on Common Tasks successfully.");
       } catch (Exception e) {
           System.out.println("Failed to click on Common Tasks: " + e.getMessage());
           e.printStackTrace();
           Assert.fail("Error while clicking on Common Tasks: " + e.getMessage());
       }
       try {
           // Wait for the search input box to be visible and type 'User' into it
       	
           // Proceed to click the "New User" action
           WebElement newUserAction = wait.until(ExpectedConditions.elementToBeClickable(By.id("NewActionComponent")));
           newUserAction.click();
           System.out.println("Navigating to Hotlist Groups creation Page.");
       } catch (Exception e) {
       	System.out.println("Navigation to Hotlist Groups creation got failed");
       }
       try {
           // Enter username
       	
           System.out.println("Please enter the Hotlist group name:");
           Scanner scanner = new Scanner(System.in);
           hotlistGroupName = scanner.nextLine();
           
           // Find the username input element and enter the username
           WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fhogName")));
          // Clear any pre-existing text
           userInput.sendKeys(hotlistGroupName);
           System.out.println("Eneterd Hotlist group name successfully");
       } catch (Exception e) {
           System.out.println("Error entering Hotlist group name: " + e.getMessage());
           Assert.fail("Entering Hotlist group name failed: " + e.getMessage());
       }
       try {
           // Enter username
       	driver.findElement(By.id("grouplistDetail.save")).click();
       	System.out.println("Saved Hotlist group successfully");
       } catch (Exception e) {
           System.out.println("Error while saving Hotlist group: " + e.getMessage());
           Assert.fail("Error while saving Hotlist group:" + e.getMessage());
       }
       Thread.sleep(5000);
try {
       	
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
           WebElement navigationLabel = driver.findElement(By.id("navigationLabel"));
           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navigationLabel);
           WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
           searchInput.clear();  // Clear any pre-existing text
           searchInput.sendKeys("Hotlist");

           // Click the User link after typing
           WebElement HotlistgroupLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Hotlist Entities")));
           HotlistgroupLink.click();
//           wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes"))).click();
           System.out.println("Navigating to Hotlist Entities Page.");
       } catch (Exception e) {
       	System.out.println("Navigated to Hotlist Entities failed");
       }
Thread.sleep(4000);
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
   // Wait for the search input box to be visible and type 'User' into it
	
   // Proceed to click the "New User" action
   WebElement newUserAction = wait.until(ExpectedConditions.elementToBeClickable(By.id("NewActionComponent")));
   newUserAction.click();
   System.out.println("Navigating to Hotlist Entities creation Page.");
} catch (Exception e) {
	System.out.println("Navigation to Hotlist Entities creation got failed");
}

try {
    // Wait for 5 seconds before interacting with the upload button (ensure the button is clickable)
    Thread.sleep(5000);

    WebElement uploadButton = driver.findElement(By.xpath("//form[@id='fileUpload']/table/tbody/tr/td/table/tbody/tr/td/div"));
    uploadButton.click();

    // Use Robot class to handle the Windows file picker dialog
    Robot robot = new Robot();

    String filePath = "C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Hotlist inputfiles\\Hotlist.txt";
    StringSelection selection = new StringSelection(filePath);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

    // Wait for the file picker dialog to appear
    robot.delay(2000);

    // Simulate "Ctrl+V" to paste the file path
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);

    // Press "Enter" to confirm the file selection
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    System.out.println("File uploaded successfully.");
} catch (Exception e) {
    // Handle any exceptions that might occur during the file upload process
    System.out.println("Error occurred while uploading the file: " + e.getMessage());
    e.printStackTrace();  // Print the full stack trace for debugging
}
try {
    // Enter username
	WebElement selectAllbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("expand-hotlistGroups")));
	   
	selectAllbutton.click();
	// Find all the hotlist group elements (assuming they're listed in <li> or similar)
    List<WebElement> hotlistGroups = driver.findElements(By.xpath("//div[@class='gwt-fms-widget-label']")); // Change the CSS selector based on the actual structure of your page

    boolean groupFound = false;

    // Loop through the list and select the matching hotlist group
    for (WebElement group : hotlistGroups) {
        if (group.getText().equalsIgnoreCase(hotlistGroupName)) {
            group.click();  // Click the group to select it
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
    e.printStackTrace();
    System.out.println("clicked on select all successuly");
}
try {
    // Wait for the search input box to be visible and type 'User' into it
	WebElement Donebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Done']")));
   
	Donebutton.click();
    
    // Proceed to click the "New User" action
    
    System.out.println("Clicked on done button successfully");
} catch (Exception e) {
	System.out.println("failed to click on done button");
}
try {
    // Wait for the search input box to be visible and type 'User' into it
	WebElement Savebutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("entitiesDetail.save")));
   
	Savebutton.click();
   
    System.out.println("Clicked on save button successfully");
} catch (Exception e) {
	System.out.println("failed to click on save button");
}

try {
    // Wait for the search input box to be visible and type 'User' into it
	WebElement Okbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("okbuttonId")));
   
	Okbutton.click();
   
    System.out.println("Clicked on Ok button successfully");
} catch (Exception e) {
	System.out.println("failed to click on Ok button");
}

	}

}
