package Mamatha;

import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Customnickname {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    private LoginManager loginManager;

    @Test
    public void NicknamePage() throws InterruptedException, IOException {
        loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
        driver = loginManager.getDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(60));

        executeNickNamecreation(driver);
        System.out.println("Custom Nickname creation,Edit and deletion test cases executed successfully");
    }

    public void executeNickNamecreation(WebDriver driver) throws InterruptedException, IOException {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
            WebElement navigationLabel = driver.findElement(By.id("navigationLabel"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navigationLabel);
            
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
            searchInput.clear();
            searchInput.sendKeys("Nickname");

            WebElement StandardnicknameLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Nicknames Custom")));
            StandardnicknameLink.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes"))).click();
            System.out.println("Navigating to Custom Nicknames Page.");
        } catch (Exception e) {
            System.out.println("Navigation to Custom Nicknames got failed");
        }

        try {
            WebElement commonTasks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
            Actions action = new Actions(driver);
            action.moveToElement(commonTasks).click().perform();
            System.out.println("Clicked on Common Tasks successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click on Common Tasks: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Error while clicking on Common Tasks: " + e.getMessage());
        }

        try {
            WebElement newUserAction = wait.until(ExpectedConditions.elementToBeClickable(By.id("NewActionComponent")));
            newUserAction.click();
            System.out.println("Navigating to Custom nickname creation Page.");
        } catch (Exception e) {
            System.out.println("Navigation to Custom nickname creation got failed");
        }

        try {
            String Nickname = "";
            System.out.println("Please enter the Custom nickname:");
            Scanner scanner = new Scanner(System.in);
            Nickname = scanner.nextLine();

            WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fcniNickname")));
            userInput.sendKeys(Nickname);
            System.out.println("Entered Custom Nickname successfully");
        } catch (Exception e) {
            System.out.println("Error while entering Custom Nickname: " + e.getMessage());
            Assert.fail("Entering Custom Nickname failed: " + e.getMessage());
        }

        try {
            String Description = "";
            System.out.println("Please enter the Description:");
            Scanner scanner = new Scanner(System.in);
            Description = scanner.nextLine();

            WebElement descriptionInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flicDescription")));
            descriptionInput.sendKeys(Description);
            System.out.println("Entered the Description successfully");
        } catch (Exception e) {
            System.out.println("Error while entering the Description: " + e.getMessage());
            Assert.fail("Error while entering Description: " + e.getMessage());
        }

        try {
            System.out.println("Please enter values for nickname-direct-value (comma separated if multiple):");
            Scanner scanner = new Scanner(System.in);
            String nicknames = scanner.nextLine();

            WebElement nicknameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nickname-direct-value")));
            nicknameField.clear();
            nicknameField.sendKeys(nicknames);
            System.out.println("Entered the first value for nickname-direct-value: " + nicknames);

            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nickname-add-value")));
            addButton.click();
        }
        catch (Exception e) {
            System.out.println("Error while entering nickname-direct-value: " + e.getMessage());
            Assert.fail("Error while entering nickname-direct-value: " + e.getMessage());
        }

        try {
            WebElement Savebutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("customNicknameDetail.save")));
            Savebutton.click();
            System.out.println("Saved the changes successfully");
        } catch (Exception e) {
            System.out.println("Error while saving the changes");
        }

        Thread.sleep(3000);

        try {
            WebElement Adavncefilterbutton =wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-filter-button")));
            Adavncefilterbutton.click();

            WebElement deletebutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolbar-button-image-new-adv-filter-clearAll")));
            deletebutton.click();

            driver.findElement(By.id("ok")).click();

            WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']")));
            inputElement.sendKeys("Name ");
            inputElement.sendKeys("=" + Keys.RETURN);

            System.out.println("Please enter the Nick name which you have created:");

            Scanner scanner = new Scanner(System.in);
            String Nickname1 = scanner.nextLine();

            WebElement rulenameElement = driver.findElement(By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']"));
            rulenameElement.sendKeys(Nickname1);
            rulenameElement.sendKeys(Keys.RETURN);

            System.out.println("Nick name entered and Enter key pressed.");
            driver.findElement(By.id("filter-search-button")).click();
            Thread.sleep(5000);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destDir = new File("C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Automation ss\\Custom_Nickname.png");
            FileUtils.copyFile(src, destDir);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            WebElement Nicknamebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@style='outline-style:none;']")));

            if (Nicknamebutton.isDisplayed() && Nicknamebutton.isEnabled()) {
                Actions a = new Actions(driver);
                a.contextClick(Nicknamebutton).perform();
                System.out.println("Right-clicked on Nickname successfully");
            } else {
                System.out.println("Element is not displayed or enabled for right-click.");
            }
        } catch (Exception e) {
            System.out.println("Failed to right-click on Nicknamebutton: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            WebElement Nicknameactionsbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Nickname Actions')]")));

            if (Nicknameactionsbutton.isDisplayed() && Nicknameactionsbutton.isEnabled()) {
                Actions a = new Actions(driver);
                a.contextClick(Nicknameactionsbutton).perform();
                System.out.println("Nickname actions button clicked successfully");
            } else {
                System.out.println("Element is not displayed or enabled to click.");
            }
        } catch (Exception e) {
            System.out.println("Failed to click on Nickname actions: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            WebElement Editbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Edit Contents')]")));

            if (Editbutton.isDisplayed() && Editbutton.isEnabled()) {
                Editbutton.click();
                System.out.println("Edit button clicked successfully");
            } else {
                System.out.println("Element is not displayed or enabled to click.");
            }
        } catch (Exception e) {
            System.out.println("Failed to click on Edit button: " + e.getMessage());
            e.printStackTrace();
        }

        Thread.sleep(2000);
        try {
            WebElement deletebox = driver.findElement(By.xpath("//td[contains(@style,'vertical-align: top;')]//div[@class='gwt-label-stack-delete-icon-holder']"));

            Actions a = new Actions(driver);

            while (true) {
                try {
                    // Perform the action of hovering and clicking the delete icon for the current deletebox
                    a.moveToElement(deletebox).build().perform();

                    By deleteIconLocator = By.cssSelector(".gwt-label-stack-delete-icon");

                    WebElement deleteIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteIconLocator));

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);

                    wait.until(ExpectedConditions.elementToBeClickable(deleteIconLocator)).click();

                    // Wait for the icon to disappear before checking the next one
                   

                    System.out.println("Delete icon clicked successfully and removed.");

                    // Re-fetch the deletebox to check if there are more delete icons
                    deletebox = driver.findElement(By.xpath("//td[contains(@style,'vertical-align: top;')]//div[@class='gwt-label-stack-delete-icon-holder']"));

                } catch (Exception e) {
                    // If we can't find any more delete icons, exit the loop
                    System.out.println("No more delete icons found or unable to find delete icon");
                    break;
                }
            }
            

        } catch (Exception e) {
            System.out.println("Error while deleting Nickname: " + e.getMessage());
        }
        try {
            // Get user input for multiple values for nickname-direct-value
            System.out.println("Please enter values for nickname-direct-value (comma separated if multiple):");
            Scanner scanner = new Scanner(System.in);
            String nicknames = scanner.nextLine();  // Split input by comma

            // Find the nickname input field by ID and enter the first nickname
            WebElement nicknameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nickname-direct-value")));
            nicknameField.clear();  // Clear any existing value
            nicknameField.sendKeys(nicknames);  // Trim to remove extra spaces
            System.out.println("Entered the first value for nickname-direct-value: " + nicknames);

            // Optionally handle additional values (if any) in separate fields or logic
            // Ensure the "Add Value" button is clickable and that the field is ready for input
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nickname-add-value")));
            addButton.click();  // Click "Add Value" button to add the next field
        }
        catch (Exception e) {
            System.out.println("Error while entering nickname-direct-value: " + e.getMessage());
            Assert.fail("Error while entering nickname-direct-value: " + e.getMessage());
        }

        try {
            WebElement updatebutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("updateButton")));
            updatebutton.click();
            System.out.println("update button clicked");
        } catch (Exception e) {
            System.out.println("Error while clicking the update button: " + e.getMessage());
        }
        try {
            WebElement okbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ok")));
            okbutton.click();
            System.out.println("ok button clicked");
        } catch (Exception e) {
            System.out.println("Error while clicking the ok button: " + e.getMessage());
        }
        
        try {
            WebElement minimizebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='roc-dialog-close']")));
            minimizebutton.click();
            System.out.println("minimizebutton button clicked");
        } catch (Exception e) {
            System.out.println("Error while clicking the minimizebutton button: " + e.getMessage());
        }
        try {
            // Wait for the search input box to be visible and type 'User' into it
            WebElement Nicknamebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@style='outline-style:none;']")));
           
            // Ensure the element is visible before performing any action
            if (Nicknamebutton.isDisplayed() && Nicknamebutton.isEnabled()) {
                // Initialize Actions class
                Actions a = new Actions(driver);
                
                // Perform right-click (context click) on the hotlist button
                a.contextClick(Nicknamebutton).perform();
                System.out.println("Right-clicked on Nickname successfully");
            } else {
                System.out.println("Element is not displayed or enabled for right-click.");
            }
        } catch (Exception e) {
            System.out.println("Failed to right-click on Nicknamebutton: " + e.getMessage());
            e.printStackTrace();
        }
        
        
        try {
            // Wait for the search input box to be visible and type 'User' into it
            WebElement Nicknamebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@style='outline-style:none;']")));
           
            // Ensure the element is visible before performing any action
            if (Nicknamebutton.isDisplayed() && Nicknamebutton.isEnabled()) {
                // Initialize Actions class
                Actions a = new Actions(driver);
                
                // Perform right-click (context click) on the hotlist button
                a.contextClick(Nicknamebutton).perform();
                System.out.println("Right-clicked on Nickname successfully");
            } else {
                System.out.println("Element is not displayed or enabled for right-click.");
            }
        } catch (Exception e) {
            System.out.println("Failed to right-click on Nicknamebutton: " + e.getMessage());
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
            System.out.println("Custom Nickname got deleted successfully");
        } catch (Exception e) {
        	System.out.println("failed while deleting the Custom Nickname");
        }
    }

    }
