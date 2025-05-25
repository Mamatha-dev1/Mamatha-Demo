package Mamatha;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class New {
    private WebDriver driver;
    private WebDriverWait wait;

    // Initialize WebDriver and WebDriverWait only once for all tests
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();  // You can use WebDriverManager if needed to handle driver versions
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Login before all tests
        loginToApplication();
    }

    // Common login method to be used by all tests
    private void loginToApplication() {
        String url = "http://10.113.207.48:8180/rocfm/sparkLogin.jsp";
        String username = "Root";
        String password = "welcome567";

        driver.get(url);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username-input-area"))).sendKeys(username);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-input-area"))).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btn"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("open-alarm-popup-cancel"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("ok"))).click();
            System.out.println("Login successful.");
        } catch (Exception e) {
            Assert.fail("Login failed: " + e.getMessage());
        }
    }

    // Test case for Rule Management navigation
    @Test
    public void testTemplateruleNavigation() {
        navigateToRuleManagement();
        handlePopups();
        clickRuleAddButton();
        enterRuleName("DefaultRuleName");
        selectFraudType();
        selectMonitorMenu();
        selectFieldCategory();
        exitPopup();
        clickNextButton(3);
        selectFunctionType();
        enterThresholdValue();
        selectAnalystAction();
        selectSystemAction();
        saveRuleTemplate();
        System.out.println("Rule Management page exists.");
    }

    // Test case for User creation
    @Test
    public void testUserCreation() {
        navigateToUsers();
        createUser();
    }

    // Helper Methods for Rule Management Test Case
    private void navigateToRuleManagement() {
        try {
            WebElement navigationLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
            navigationLabel.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Rule Management"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Function and Combined Rules"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes"))).click();
            System.out.println("Navigated to Rule Management successfully.");
        } catch (Exception e) {
            Assert.fail("Navigation failed: " + e.getMessage());
        }
    }

    private void handlePopups() {
        try {
            WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='yes' and contains(@class, 'gwt-Button')]")));
            yesButton.click();
        } catch (Exception e) {
            System.out.println("Failed to click saved changes Yes button.");
        }
    }

    private void clickRuleAddButton() {
        try {
            WebElement ruleAddButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("template-rule-add-button")));
            ruleAddButton.click();
        } catch (Exception e) {
            Assert.fail("Failed to click Rule Add button: " + e.getMessage());
        }
    }

    private void enterRuleName(String ruleName) {
        try {
            WebElement ruleNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rule-name-input")));
            ruleNameInput.sendKeys(ruleName);
        } catch (Exception e) {
            Assert.fail("Failed to enter rule name: " + e.getMessage());
        }
    }

    private void selectFraudType() {
        try {
            WebElement fraudTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fraud-type-dropdown")));
            fraudTypeDropdown.click();
        } catch (Exception e) {
            Assert.fail("Failed to select Fraud Type: " + e.getMessage());
        }
    }

    private void selectMonitorMenu() {
        try {
            WebElement monitorMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("monitor-menu")));
            monitorMenu.click();
        } catch (Exception e) {
            Assert.fail("Failed to select Monitor Menu: " + e.getMessage());
        }
    }

    private void selectFieldCategory() {
        try {
            WebElement fieldCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-category")));
            fieldCategory.click();
        } catch (Exception e) {
            Assert.fail("Failed to select Field Category: " + e.getMessage());
        }
    }

    private void exitPopup() {
        try {
            WebElement exitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("exit-popup")));
            exitButton.click();
        } catch (Exception e) {
            Assert.fail("Failed to exit popup: " + e.getMessage());
        }
    }

    private void clickNextButton(int step) {
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("next-step-" + step)));
            nextButton.click();
        } catch (Exception e) {
            Assert.fail("Failed to click Next button: " + e.getMessage());
        }
    }

    private void selectFunctionType() {
        try {
            WebElement functionTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("function-type-dropdown")));
            functionTypeDropdown.click();
        } catch (Exception e) {
            Assert.fail("Failed to select Function Type: " + e.getMessage());
        }
    }

    private void enterThresholdValue() {
        try {
            WebElement thresholdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("threshold-value")));
            thresholdInput.sendKeys("50");
        } catch (Exception e) {
            Assert.fail("Failed to enter Threshold Value: " + e.getMessage());
        }
    }

    private void selectAnalystAction() {
        try {
            WebElement analystActionDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("analyst-action-dropdown")));
            analystActionDropdown.click();
        } catch (Exception e) {
            Assert.fail("Failed to select Analyst Action: " + e.getMessage());
        }
    }

    private void selectSystemAction() {
        try {
            WebElement systemActionDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("system-action-dropdown")));
            systemActionDropdown.click();
        } catch (Exception e) {
            Assert.fail("Failed to select System Action: " + e.getMessage());
        }
    }

    private void saveRuleTemplate() {
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("save-template-button")));
            saveButton.click();
            System.out.println("Rule template saved successfully!");
        } catch (Exception e) {
            Assert.fail("Failed to save Rule Template: " + e.getMessage());
        }
    }

    // Helper Methods for User Creation Test Case
    private void navigateToUsers() {
        try {
            driver.findElement(By.xpath("//div[@id='navigationLabel']")).click();
            waitFor(2000);
            driver.findElement(By.xpath("//input[@placeholder='Type to search']")).sendKeys("User");
            driver.findElement(By.id("id-Users")).click();
            driver.findElement(By.xpath("//button[@id='yes' and contains (@class, 'gwt-Button')]")).click();
            waitFor(3000);
            WebElement newQuery = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
            newQuery.click();
            driver.findElement(By.id("NewUserAction")).click();
        } catch (Exception e) {
            Assert.fail("Navigation to Users failed: " + e.getMessage());
        }
    }

    private void createUser() {
        try {
            System.out.println("Please enter the Username:");
            String username = "TestUser"; // Hardcoded for automation
            WebElement usernameInput = driver.findElement(By.id("usrName"));
            usernameInput.sendKeys(username);

            String password = enterPassword();
            WebElement passwordInput = driver.findElement(By.id("usrPassword"));
            passwordInput.sendKeys(password);

            System.out.println("Please enter the Firstname:");
            String firstname = "TestFirstName";  // Hardcoded for automation
            driver.findElement(By.id("usrForename")).sendKeys(firstname);

            System.out.println("Please enter the Lastname:");
            String lastname = "TestLastName"; // Hardcoded for automation
            driver.findElement(By.id("usrSurname")).sendKeys(lastname);

            selectUserRole();
            selectNetworks();
            saveUser();
        } catch (Exception e) {
            Assert.fail("User creation failed: " + e.getMessage());
        }
    }

    private String enterPassword() {
        return "Password123"; // Hardcoded password for automation
    }

    private void selectUserRole() {
        try {
            WebElement rolesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-tabLabel' and text()='Roles And Partitions']")));
            rolesTab.click();
            WebElement checkbox = driver.findElement(By.id("CommoncheckBox_checked_column_field_image"));
            checkbox.click();
        } catch (Exception e) {
            Assert.fail("Selecting user role failed: " + e.getMessage());
        }
    }

    private void selectNetworks() {
        try {
            WebElement networksTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='gwt-tabLabel' and text()='Networks']")));
            networksTab.click();
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("allowAccessEditor_checked_column_field_image")));
            checkbox.click();
        } catch (Exception e) {
            Assert.fail("Selecting networks role failed: " + e.getMessage());
        }
    }

    private void saveUser() {
        try {
            WebElement saveButton = driver.findElement(By.id("userTblDetail.save")); // Replace with actual ID
            saveButton.click();
            System.out.println("Save button clicked.");
        } catch (Exception e) {
            Assert.fail("Saving user failed: " + e.getMessage());
        }
    }

    // Wait helper method
    private void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("An error occurred during wait: " + e.getMessage());
        }
    }

    // Cleanup WebDriver after all tests
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}