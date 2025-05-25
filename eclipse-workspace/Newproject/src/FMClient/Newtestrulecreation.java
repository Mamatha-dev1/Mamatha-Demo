package FMClient;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import FMClient.ConfigurationLoader;
import FMClient.LoginManager;
import FMClient.ScreenshotHelper;
public class Newtestrulecreation {
	protected static WebDriver driver;
	protected static WebDriverWait wait;

	// Get the singleton instance of LoginManager
	private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

	@Test
	public void Testrulecreation() throws InterruptedException, IOException, TimeoutException {
		driver = loginManager.getDriver(); // Ensure driver is initialized here
		if (driver == null) {
			System.out.println("Driver initialization failed.");
			return;
		}
		executeNewtestrulecreation(driver);
		System.out.println("Test rule created successfully");
	}

	public void executeNewtestrulecreation(WebDriver driver)
			throws InterruptedException, IOException, TimeoutException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Thread.sleep(3000);

		{
			try {

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navigationLabel")));
				WebElement navigationLabel = driver.findElement(By.id("navigationLabel"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", navigationLabel);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Rule Management"))).click();

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Function and Combined Rules")))
						.click();
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yes"))).click();
				System.out.println("Navigating to Functioned and combined rules Page.");
			} catch (Exception e) {
				System.out.println("Navigated to Rule Management failed");
			}
		}

		{
			try {
				WebElement ruleAddButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("test-rule-add-button")));
				ruleAddButton.click();
				System.out.println("Clicked test Rule Add button successfully.");
			} catch (Exception e) {
				Assert.fail("Failed to click Rule Add button: " + e.getMessage());
			}
		}
		Thread.sleep(2000);
		try {
			WebElement testRuleName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frulName")));
			System.out.println("Please enter the Test Rulename:");
			Scanner scanner1 = new Scanner(System.in);
			String ruleName = scanner1.nextLine();
			WebElement usernameInput = driver.findElement(By.id("frulName"));
			usernameInput.sendKeys(ruleName);
			System.out.println("Entered rule name successfully.");
		} catch (Exception e) {
			System.out.println("Failed to enter rule name.");
		}

		Thread.sleep(2000);

//		try {
//			WebElement monitormenu = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(By.id("expand-monitoringFieldMultiSelect")));
//			monitormenu.click();
//			System.out.println("Clicked on monitor menu successfully.");
//		} catch (Exception e) {
//			System.out.println("Failed to click on monitor menu.");
//		}

		try {
			WebElement fieldCategory = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("FieldCategoryModelPhone Number2")));
			fieldCategory.click();
			System.out.println("Clicked on Field Category successfully.");
		} catch (Exception e) {
			System.out.println("Failed to click on Field Category.");
		}

		try {
			WebElement exitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and contains(text(), 'Done')]")));
			exitButton.click();
			System.out.println("Exit button 'test done' clicked successfully.");
		} catch (Exception e) {
			System.out.println("Failed to interact with the navigation menu or exit button.");
		}

		try {
			WebElement dataStreamType = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("RecConfModelCDR1")));
			dataStreamType.click();
			System.out.println("Clicked on Data Stream Type successfully.");
		} catch (Exception e) {
			System.out.println("Failed to click on Data Stream Type.");
		}
		Thread.sleep(2000);
		try {
			WebElement monitormenu = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("expand-groupsMultiSelect")));
			monitormenu.click();
			System.out.println("Clicked on monitor menu successfully.");
		} catch (Exception e) {
			System.out.println("Failed to click on monitor menu.");
		}

		try {
			WebElement groupType = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubsGroupModelDefault8")));
			groupType.click();
			System.out.println("Clicked on Group Type successfully.");
		} catch (Exception e) {
			System.out.println("Failed to click on Group Type.");
		}

		Thread.sleep(2000);

		try {
			WebElement exitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and contains(text(), 'Done')]")));
			exitButton.click();
			System.out.println("Exit button 'test done' clicked successfully.");
		} catch (Exception e) {
			System.out.println("Failed to interact with the navigation menu or exit button.");
		}

		Thread.sleep(2000);
		try {
			WebElement exitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//img[@src='http://10.113.206.63:8180/rocfm/themes/common/images/popup-close.png']")));
			exitButton.click();
			System.out.println("closed popup successfully.");
		} catch (Exception e) {
			System.out.println("Failed to close popup.");
		}
		{
			try {
				WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
				nextButton.click();
				System.out.println("Clicked on 'Next' button successfully.");
			} catch (Exception e) {
				System.out.println("Failed to click on 'Next' button.");
				e.printStackTrace();
			}

			Thread.sleep(2000);

			try {
				WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
				nextButton.click();
				System.out.println("Clicked on 'Next' button successfully.");
			} catch (Exception e) {
				System.out.println("Failed to click on 'Next' button.");
				e.printStackTrace();
			}
		}
		{
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("PseudoFunctionModelCount Of Records6")))
						.click();
				System.out.println("Selected Function Type successfully.");
			} catch (Exception e) {
				Assert.fail("Failed to select Function Type: " + e.getMessage());
			}
		}

		{
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("baseThreshold"))).sendKeys("1");
				System.out.println("Entered threshold value successfully.");

			} catch (Exception e) {
				Assert.fail("Failed to enter threshold value: " + e.getMessage());
			}
		}

		{
			try {
				WebElement nextButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
				nextButton1.click();
				System.out.println("Clicked on 'Next' button successfully.");
			} catch (Exception e) {
				System.out.println("Failed to click on 'Next' button.");
				e.printStackTrace();
			}

			Thread.sleep(2000);

			try {
				WebElement nextButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
				nextButton2.click();
				System.out.println("Clicked on 'Next' button successfully.");
			} catch (Exception e) {
				System.out.println("Failed to click on 'Next' button.");
				e.printStackTrace();
			}

			Thread.sleep(2000);

			try {
				WebElement nextButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
				nextButton2.click();
				System.out.println("Clicked on 'Next' button successfully.");
			} catch (Exception e) {
				System.out.println("Failed to click on 'Next' button.");
				e.printStackTrace();
			}

			try {
				// Locate and click on system action type element
				WebElement systemactiontype = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.id("SystemActionModelGenerate Test Alert61")));
				systemactiontype.click();
				System.out.println("Clicked on System Action type successfully.");
			} catch (Exception e) {
				System.out.println("Failed to click on System Action type.");
				e.printStackTrace();
			}

			Thread.sleep(2000);

			try {
				WebElement nextButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//button[@type='button' and contains(@class, 'gwt-Button') and contains(@class, 'roc-primary-button') and @id='nextButton']")));
				nextButton2.click();
				System.out.println("Clicked on 'Next' button successfully.");
			} catch (Exception e) {
				System.out.println("Failed to click on 'Next' button.");
				e.printStackTrace();
			}

			Thread.sleep(2000);

			try {
				WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
						"span.gwt-RadioButton.roc-gwt-radio.gwt-test-rule-online-button input#onlineRadioButton_InputElement")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
				System.out.println("Successfully clicked the radio button which presents online rule.");
			} catch (Exception e) {
				System.out.println("Failed to click the radio online rule.");
				e.printStackTrace();
			}

			Thread.sleep(2000);
			try {
				WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//button[@type='button' and contains(@class, 'gwt-Button roc-primary-button') and @id='ruleDetail.save']")));
				saveButton.click();
				System.out.println("Test rule Saved  successfully.");

			} catch (Exception e) {
				System.out.println("Failed to save the Test rule.");
				e.printStackTrace();
			}

			Thread.sleep(7000);
			try {
				// Wait for and click on the "advanced-filter-button"
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-filter-button")));
				driver.findElement(By.id("advanced-filter-button")).click();

				// Wait until the delete button is clickable and click it
				WebElement deletebutton = wait.until(
						ExpectedConditions.elementToBeClickable(By.id("toolbar-button-image-new-adv-filter-clearAll")));
				deletebutton.click();

				// Confirm by clicking the "OK" button
				driver.findElement(By.id("ok")).click();

				// Wait for the "adv-filter-help-label" (input field) to be visible
				WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']")));

				// Send the initial text and '=' symbol
				inputElement.sendKeys("Rule Name "); // Press ENTER after "Rule Name"
				inputElement.sendKeys("=" + Keys.RETURN); // Press ENTER after "="

				// Ask the user to enter the rule name
				System.out.println("Please enter the rule name which you have created:");

				// Read the rule name from user input
				Scanner scanner = new Scanner(System.in);
				String rulename1 = scanner.nextLine();

				// Enter the rule name
				WebElement rulenameElement = driver.findElement(
						By.xpath("//input[@type='text' and @class='gwt-SuggestBox original-token-input']"));
				rulenameElement.sendKeys(rulename1); // Type the rule name

				// After entering the rule name, press ENTER to submit
				rulenameElement.sendKeys(Keys.RETURN); // Simulate the Enter key press

				System.out.println("Rule name entered and Enter key pressed.");
				driver.findElement(By.id("filter-search-button")).click();
				Thread.sleep(5000);
				String baseDir = System.getProperty("user.dir");
		        ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
		        ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);
		        screenshotHelper.takeScreenshot(driver);

			} catch (NoSuchElementException e) {
				System.out.println("Error: Element not found - " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

}
