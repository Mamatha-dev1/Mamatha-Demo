package FMClient;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
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

public class Usertest {
	
	// Get the singleton instance of LoginManager
	private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

	@Test
	public void testUserpage() throws InterruptedException, IOException {

		// Use the driver from LoginManager
		executeCreateUser(loginManager.getDriver());
		//executeUsereandpasswordvalidation(loginManager.getDriver());
		executeUserexport(loginManager.getDriver());
		System.out.println("All user test cases passed successfully");
	}

	private void executeCreateUser(WebDriver driver) throws InterruptedException {
		String baseDir = System.getProperty("user.dir");
        ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
        ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);
		// Set timeout for the driver
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(4000);
		try {
			 // Navigate to Users section
            WebElement navigationLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='navigationLabel']")));
            navigationLabel.click();
			System.out.println("Navigating to Users Page.");
			
		} catch (Exception e) {
			System.out.println("Error navigating to Users section: " + e.getMessage());
			Assert.fail("Navigation to Users section failed: " + e.getMessage());
		}

		try {
			// Wait for the search input box to be visible and type 'User' into it
			WebElement searchInput = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type to search']")));
			searchInput.clear(); // Clear any pre-existing text
			searchInput.sendKeys("User");

			// Click the User link after typing
			WebElement userLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Users")));
			userLink.click();

			// Wait and click "Yes" on the confirmation button
//            WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='yes' and contains (@class, 'gwt-Button')]")));
//            yesButton.click();
			Thread.sleep(3000);
			// Wait for 'Common Tasks' and click on it
			WebElement commonTasks = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
			scrollIntoViewAndClick(driver, commonTasks);
			commonTasks.click();
			commonTasks.click();
			// Proceed to click the "New User" action
			WebElement newUserAction = wait.until(ExpectedConditions.elementToBeClickable(By.id("NewUserAction")));
			newUserAction.click();

		} catch (Exception e) {
			System.out.println("Error performing search and click actions: " + e.getMessage());

		}

		// Get user details from the user using Scanner input
		Scanner scanner = new Scanner(System.in);
		String username = "";
		try {
			// Enter username
			System.out.println("Please enter the Username:");
			username = scanner.nextLine();

			// Find the username input element and enter the username
			WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usrName")));
			usernameInput.clear(); // Clear any pre-existing text
			usernameInput.sendKeys(username);
		} catch (Exception e) {
			System.out.println("Error entering Username: " + e.getMessage());
			Assert.fail("Entering Username failed: " + e.getMessage());
		}

		String password = "";
		try {
			// Password validation loop
			while (true) {
				System.out.println(
						"Please make sure that password should not contain 3 or more consecutive characters of username");
				System.out.println("Please enter the Password:");
				password = scanner.nextLine();

				// Validate the password
				if (isPasswordValid(password)) {
					break;
				} else {
					System.out.println(
							"Password should be at least 6 characters long and must contain unique characters.");
				}
			}

			WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usrPassword")));
			passwordInput.clear(); // Clear any pre-existing text
			passwordInput.sendKeys(password);
			System.out.println("Password entered successfully.");
		} catch (Exception e) {
			System.out.println("Error entering Password: " + e.getMessage());
			Assert.fail("Entering Password failed: " + e.getMessage());
		}

		try {
			// Enter Firstname
			System.out.println("Please enter the Firstname:");
			String firstname = scanner.nextLine();
			WebElement firstnameInput = driver.findElement(By.id("usrForename"));
			firstnameInput.clear(); // Clear any pre-existing text
			firstnameInput.sendKeys(firstname);
		} catch (Exception e) {
			System.out.println("Error entering Firstname: " + e.getMessage());
			Assert.fail("Entering Firstname failed: " + e.getMessage());
		}

		try {
			// Enter Lastname
			System.out.println("Please enter the Lastname:");
			String lastname = scanner.nextLine();
			WebElement lastnameInput = driver.findElement(By.id("usrSurname"));
			lastnameInput.clear(); // Clear any pre-existing text
			lastnameInput.sendKeys(lastname);
		} catch (Exception e) {
			System.out.println("Error entering Lastname: " + e.getMessage());
			Assert.fail("Entering Lastname failed: " + e.getMessage());
		}

		try {
			// Enter Lastname
			System.out.println("Please enter the email address:");
			String emailaddress = scanner.nextLine();
			WebElement emailaddressinput = driver.findElement(By.id("usrEmailAddress"));
			emailaddressinput.clear(); // Clear any pre-existing text
			emailaddressinput.sendKeys(emailaddress);
		} catch (Exception e) {
			System.out.println("Error entering email: " + e.getMessage());
			Assert.fail("Entering email failed: " + e.getMessage());
		}
		Thread.sleep(3000);
		try {
			// Select user role (Roles And Partitions tab)
			WebElement rolesTab = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@class='gwt-tabLabel' and text()='Roles And Partitions']")));
			rolesTab.click();

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("CommoncheckBox_checked_column_field_image")));

			new Actions(driver).doubleClick(checkbox).perform();
		} catch (Exception e) {
			System.out.println("Error selecting user role: " + e.getMessage());
			Assert.fail("Selecting user role failed: " + e.getMessage());
		}

//        try {
//            // Select networks role (Networks tab)
//            WebElement networksTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='gwt-tabLabel' and text()='Networks']")));
//            networksTab.click();
//
//            WebElement checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("allowAccessEditor_checked_column_field_image")));
//            if (!checkbox1.isSelected()) {
//                new Actions(driver).moveToElement(checkbox1).doubleClick().perform();
//                System.out.println("Networks checkbox selected.");
//            } else {
//                System.out.println("Networks checkbox was already selected.");
//            }
//        } catch (Exception e) {
//            System.out.println("Error selecting networks role: " + e.getMessage());
//            Assert.fail("Selecting networks role failed: " + e.getMessage());
//        }
		        
		try {
			// Save user details
			WebElement saveButton = driver.findElement(By.id("userTblDetail.save"));
			saveButton.click();
			System.out.println("User saved successfully.");
		} catch (Exception e) {
			Assert.fail("Saving user failed: ");
		}
		try {
			System.out.println("Please enter the Username which you have created");
			String Username = scanner.nextLine();
			WebElement UsernameInput = driver.findElement(By.id("usrName"));
			UsernameInput.sendKeys(Username);
			driver.findElement(By.id("search")).click();
			Thread.sleep(3000);
			screenshotHelper.takeScreenshot(driver);
		} catch (Exception e) {
			System.out.println("Error saving user: " + e.getMessage());

		}

	}

	// Helper method to scroll into view and click
	private void scrollIntoViewAndClick(WebDriver driver, WebElement element) {
		try {
			// Scroll the element into view using JavaScript
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					element);

			// Wait for the element to be clickable and then click it
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element))
					.click();
		} catch (Exception e) {
			System.out.println("Error clicking the element: " + e.getMessage());
			Assert.fail("Click action failed: " + e.getMessage());
		}
	}

	// Password validation logic
	private boolean isPasswordValid(String password) {
		if (password.length() < 6) {
			return false;
		}

		// Check for three consecutive characters
		for (int i = 0; i < password.length() - 2; i++) {
			if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
				return false;
			}
		}

		return true;
	}

	private void executeUsereandpasswordvalidation(WebDriver driver) throws InterruptedException, IOException {
		String baseDir = System.getProperty("user.dir");
        ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
        ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);
        
		// Set timeout for the driver
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		Thread.sleep(4000); // Wait to ensure the page is loaded

		try {
			// Click on Export button
			WebElement SpecialactionsButton = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Special Actions')]")));
			SpecialactionsButton = wait.until(ExpectedConditions.elementToBeClickable(SpecialactionsButton)); // Ensure
																												// it's
																												// clickable
			SpecialactionsButton.click();
			SpecialactionsButton.click();
			System.out.println("Clicked on Specialactions  successfully");
		} catch (Exception e) {
			System.out.println("Error while clicking on Specialactions: " + e.getMessage());
			Assert.fail("Error while clicking on Specialactions: " + e.getMessage());
		}
		// user validation
		Thread.sleep(2000);
		try {
			WebElement UserValidationActionButton = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserValidationAction")));
			wait.until(ExpectedConditions.elementToBeClickable(UserValidationActionButton));

			// Use JavaScriptExecutor to click the element
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", UserValidationActionButton);
			System.out.println("Clicked on UserValidationAction successfully using JavaScript");
		} catch (Exception e) {
			System.out.println("Error while clicking on UserValidationAction");
			e.printStackTrace(); // Print the stack trace for debugging
			Assert.fail("Error while clicking on UserValidationAction");
		}

		try {
			// Click on 'Configured Rows' button
			WebElement Alphanumericcheckbox = driver
					.findElement(By.xpath("//span[@class='gwt-CheckBox roc-gwt-checkbox roc-property-field']"));
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
			e.printStackTrace(); // Print the stack trace to diagnose the issue more clearly
			Assert.fail("Error while clearing existing values");
		}
		Thread.sleep(3000);
		try {

			WebElement Maxlength1 = driver.findElement(By.id("UserNameMaximumLength_id"));

			// Ensure the field is focused and ready to accept input (wait for visibility
			// and interactivity)
			wait.until(ExpectedConditions.elementToBeClickable(Maxlength1));
			Maxlength1.click(); // Make sure it is focused

			// Optionally, scroll into view to ensure the element is in the viewport (if
			// needed)
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Maxlength1);

			// Ensure there's no JavaScript interference or delay (if needed, add a small
			// sleep)
			Thread.sleep(500); // Small delay to ensure input is ready (use cautiously in real tests)

			// Use Scanner to get input (while avoiding blocking the execution)
			System.out.println("Please make sure that User name maximum length<=255");
			System.out.println("Please enter the User name maximum length");
			Scanner scanner1 = new Scanner(System.in);
			String UsernamelengthName = scanner1.nextLine();

			// Send the new value to the input field
			WebElement usernamemaxlengthInput = driver.findElement(By.id("UserNameMaximumLength_id"));
			usernamemaxlengthInput.sendKeys(UsernamelengthName); // Enter the value into the field

			System.out.println("Entered User name maximum length successfully.");

		} catch (Exception e) {
			System.out.println("Error while entering User name maximum length");
			e.printStackTrace(); // Print the stack trace to diagnose the issue more clearly
			Assert.fail("Error while entering User name maximum length");
		}
		Thread.sleep(3000);
		try {
			// Click on 'Configured Rows' button

			WebElement Minlength = driver.findElement(By.id("UserNameMinimumLength_id"));
			Minlength.clear();
			System.out.println("cleared password");
		} catch (Exception e) {
			System.out.println("Error while clearing existing values");
			e.printStackTrace(); // Print the stack trace to diagnose the issue more clearly
			Assert.fail("Error while clearing existing values");
		}
		Thread.sleep(3000);
		try {
			// Click on 'Configured Rows' button
			WebElement Minlength1 = driver.findElement(By.id("UserNameMinimumLength_id"));
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
		try {
			screenshotHelper.takeScreenshot(driver);
		} catch (Exception e) {
			System.out.println("Error while taking screenshot:" + e.getMessage());

		}
		Thread.sleep(3000);
		try {
			// Click on 'Configured Rows' button
			WebElement PasswordValidationActionButton = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[normalize-space()='Password Validation']")));
			PasswordValidationActionButton.click();
			System.out.println("Clicked on PasswordValidationAction successfully");
		} catch (Exception e) {
			System.out.println("Error while clicking on PasswordValidationAction");
			Assert.fail("Error while clicking on PasswordValidationAction");
		}

		try {
			// Click on 'Configured Rows' button
			WebElement Regularexpressionpasswordvalidation = driver
					.findElement(By.xpath("//div[normalize-space()='Regular Expression Password Validation']"));

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
			WebElement field1 = driver.findElement(By.id("DisableAfterFailedLoginAttempts_id"));
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
			WebElement field2 = driver.findElement(By.id("DisableAfterInactivePeriod_id"));
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
			WebElement field3 = driver.findElement(By.id("PasswordPreviousReused_id"));
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
			WebElement field4 = driver.findElement(By.id("PasswordResetInterval_id"));
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
			WebElement field5 = driver.findElement(By.id("PasswordUniquePeriod_id"));
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
			WebElement field4 = driver.findElement(By.id("PasswordWarningInterval_id"));
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
			screenshotHelper.takeScreenshot(driver);
		} catch (Exception e) {
			System.out.println("Error while taking screenshot:" + e.getMessage());

		}
		try {
			// Click on 'Configured Rows' button

			WebElement okbutton = driver.findElement(By.id("passwordValidationDetail.save"));
			okbutton.click();
			System.out.println("ok button clicked successfully.");

		} catch (Exception e) {
			System.out.println("Error while clicking on ok button");
			Assert.fail("Error while clicking on ok button");
		}

		try {
			// Click on 'Configured Rows' button

			WebElement savebutton1 = driver.findElement(By.id("userValidationDetail.save"));
			savebutton1.click();
			System.out.println("Save button clicked successfully.");

		} catch (Exception e) {
			System.out.println("Error while saving the changes");
			Assert.fail("Error while saving the changes");
		}
	}

	private void executeUserexport(WebDriver driver) throws InterruptedException {
		// Set timeout for the driver
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

		Thread.sleep(4000); // Wait to ensure the page is loaded

		try {
			// Click on Export button
			WebElement exportButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Export")));
			exportButton = wait.until(ExpectedConditions.elementToBeClickable(exportButton)); // Ensure it's clickable
			exportButton.click();
			System.out.println("Clicked on Export option successfully");
		} catch (Exception e) {
			System.out.println("Error while clicking on Export option: " + e.getMessage());
			Assert.fail("Error while clicking on Export option: " + e.getMessage());
		}

		try {
			// Click on 'Configured Rows' button
			WebElement configuredRowsButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Configured Rows')]")));
			configuredRowsButton.click();
			System.out.println("Clicked on Configured rows successfully");

			// Separate try-catch for waiting for the file download
			String downloadDir = "C:\\Users\\mamatha.kp\\Downloads";
			// Set your download directory
			Thread.sleep(4000);
			try {
				waitForMostRecentFileDownload(downloadDir, ".txt"); // Check for the most recent .txt file
				System.out.println("File downloaded successfully to: " + downloadDir);
			} catch (Exception e) {
				System.out.println("Error while waiting for file download: " + e.getMessage());
				Assert.fail("Error while waiting for file download: " + e.getMessage());
			}

		} catch (Exception e) {
			System.out.println("Error while clicking on Configured rows");
			Assert.fail("Error while clicking on Configured rows: " + e.getMessage());
		}
	}

	// Method to wait for the most recently downloaded file in the specified
	// directory
	private static void waitForMostRecentFileDownload(String downloadDir, String fileExtension) {
		File dir = new File(downloadDir);
		File[] files = null;
		long startTime = System.currentTimeMillis();
		long timeout = 60000; // Timeout for file download (60 seconds)

		// Wait for the file to appear in the directory
		while (System.currentTimeMillis() - startTime < timeout) {
			// List all files in the directory that end with the given file extension (e.g.,
			// .txt)
			files = dir.listFiles((dir1, name) -> name.endsWith(fileExtension));

			if (files != null && files.length > 0) {
				// Sort files by last modified time in descending order (most recent first)
				File mostRecentFile = getMostRecentFile(files);

				// Check if the most recent file is downloaded
				System.out.println("Found the most recent file: " + mostRecentFile.getAbsolutePath());
				return; // Exit method after finding the most recent file
			}

			// Debugging: Log the current list of files in the directory
			System.out.println("Waiting for file download... Checking files in directory:");
			File[] currentFiles = dir.listFiles();
			if (currentFiles != null) {
				for (File file : currentFiles) {
					System.out.println(" - " + file.getName());
				}
			}

			try {
				Thread.sleep(1000); // Wait for 1 second before checking again
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// If file is not downloaded within the timeout, throw an error
		throw new RuntimeException("File download failed or timed out. Expected file with extension: " + fileExtension);
	}

	// Helper method to get the most recent file based on the last modified time
	private static File getMostRecentFile(File[] files) {
		File mostRecentFile = files[0]; // Assume the first file is the most recent initially
		for (File file : files) {
			if (file.lastModified() > mostRecentFile.lastModified()) {
				mostRecentFile = file;
			}
		}
		return mostRecentFile;
	}
}

