package FMClient;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import FMClient.ConfigurationLoader;
import FMClient.LoginManager;
import FMClient.ScreenshotHelper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class ClosedAlarms {

	// Get the singleton instance of LoginManager
	private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

	@Test
	public void testClosedAlarms() throws InterruptedException {

		// Use the driver from LoginManager
		executeClosedAlarms(loginManager.getDriver());
		System.out.println("Closed Alarms test executed");
	}
	
	public static void executeClosedAlarms(WebDriver driver) throws InterruptedException {
    	    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));  // Wait time
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Short wait time
        WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(120));  // Extended wait time
        // Initialize ConfigurationLoader and ScreenshotHelper
        String baseDir = System.getProperty("user.dir");
        ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
        ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);

        try {

            // Locate and click the navigation label
            WebElement navigationLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Navigation Menu')]")));
            navigationLabel.click();
            System.out.println("Clicked navigation label successfully.");

            // Find the search input field, enter "Alarms Closed"
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to search']")));
            searchInput.sendKeys("Alarms Closed");
            System.out.println("Entered 'Alarms Closed' in search input successfully.");

            // Locate and click the "id-Alarms Closed" element
            WebElement alarmsClosedElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("id-Alarms Closed")));
            alarmsClosedElement.click();
            // Wait up to 5 seconds for the "yes" button to become clickable, otherwise skip
            try {
                WebElement yesButton = shortWait.until(ExpectedConditions.elementToBeClickable(By.id("yes")));
                yesButton.click();
            } catch (TimeoutException e) {
                System.out.println("'Yes' button was not clickable within 5 seconds, skipping click.");
            }
            try {
                // Wait for the element to be present
                WebElement closedAlarmsLabel = extendedWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("div.gwt-HTML.layout-title-bar-text#filterpanel-header-label")));
                
                // Check if the text is "Closed Alarms Search"
                String labelText = closedAlarmsLabel.getText();
                if ("Closed Alarms Search".equals(labelText)) {
                    System.out.println("Navigated to 'Closed Alarms' successfully.");
                } else {
                    System.out.println("Unexpected label found: " + labelText);
                }
            } catch (TimeoutException e) {
                System.out.println("'Closed Alarms Search' label was not found, navigation might have failed.");
            }
            Thread.sleep(3000);
            loginManager.waitTillLoading();
            // Handle Notifications pop-up if it appears
            loginManager.handlePopUp(shortWait, "#closePopup > .gwt-Image", "Notifications Pop-up close button");     
            
            // Remove existing filters before applying Basic Filter
            WebElement basicFilterButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Basic Filter']")));
            basicFilterButton = wait.until(ExpectedConditions.elementToBeClickable(basicFilterButton));
            basicFilterButton.click();
            WebElement removeAllFiltersButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Remove All Filters")));
            removeAllFiltersButton.click();
            Thread.sleep(3000);
            loginManager.waitTillLoading();
            System.out.println("Removed all filters successfully");
            Thread.sleep(2000);
            
            // Apply the basic filter
            basicFilterButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Basic Filter']")));
            basicFilterButton = wait.until(ExpectedConditions.elementToBeClickable(basicFilterButton));
            basicFilterButton.click();
            System.out.println("Basic filter button clicked successfully.");
            
            String fieldNameBasic= configLoader.getProperty("alarm.basic.field.name");
            String fieldTextBoxXpathExpressionBasic = String.format(
            	    "//div[@class='gwt-HTML gwt-fms-basic-filter-label' and normalize-space(text())='%s']/ancestor::tr/following-sibling::tr//input[@type='text' and contains(@id, '%s')]",
            	    fieldNameBasic,
            	    fieldNameBasic.replace(" ", "_").toUpperCase()
            	); 
            String fieldValueBasic= configLoader.getProperty("alarm.basic.field.value");
            String createdFromDate = configLoader.getProperty("alarm.created.from.date");
            String createdToDate = configLoader.getProperty("alarm.created.to.date");
            String modifiedFromDate = configLoader.getProperty("alarm.modified.from.date");
            String modifiedToDate = configLoader.getProperty("alarm.modified.to.date");
                                                
            WebElement createdFromDateButtonBasic = wait.until(ExpectedConditions.elementToBeClickable(By.id("trigger-FALV_CREATED_DATE_abs_from_date")));
            createdFromDateButtonBasic.click();
            WebElement clearDateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".gwt-Button.gwt-button-secondary.roc-datepicker-clear")));
            clearDateButton.click();
            driver.findElement(By.cssSelector("#FALV_CREATED_DATE_abs_from_date .gwt-DateBox")).sendKeys(createdFromDate);
            WebElement createdToDateButtonBasic = wait.until(ExpectedConditions.elementToBeClickable(By.id("trigger-FALV_CREATED_DATE_abs_to_date")));
            createdToDateButtonBasic.click();
            clearDateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".gwt-Button.gwt-button-secondary.roc-datepicker-clear")));
            clearDateButton.click();
            driver.findElement(By.cssSelector("#FALV_CREATED_DATE_abs_to_date .gwt-DateBox")).sendKeys(createdToDate);
            WebElement modifiedFromDateButtonBasic = wait.until(ExpectedConditions.elementToBeClickable(By.id("trigger-FALV_MODIFIED_DATE_abs_from_date")));
            modifiedFromDateButtonBasic.click();
            clearDateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".gwt-Button.gwt-button-secondary.roc-datepicker-clear")));
            clearDateButton.click();
            driver.findElement(By.cssSelector("#FALV_MODIFIED_DATE_abs_from_date .gwt-DateBox")).sendKeys(modifiedFromDate);
            WebElement modifiedToDateButtonBasic = wait.until(ExpectedConditions.elementToBeClickable(By.id("trigger-FALV_MODIFIED_DATE_abs_to_date")));
            modifiedToDateButtonBasic.click();
            clearDateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".gwt-Button.gwt-button-secondary.roc-datepicker-clear")));
            clearDateButton.click();
            
            driver.findElement(By.cssSelector("#FALV_MODIFIED_DATE_abs_to_date .gwt-DateBox")).sendKeys(modifiedToDate);
            WebElement fieldTextBoxBasic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(fieldTextBoxXpathExpressionBasic)));
            fieldTextBoxBasic.click();           
            fieldTextBoxBasic.sendKeys(fieldValueBasic);
            
            WebElement filterSearchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-search-button")));
            filterSearchButton.click(); 
            Thread.sleep(3000);
            loginManager.waitTillLoading();
            Thread.sleep(5000);
            screenshotHelper.takeScreenshot(driver);
            Thread.sleep(2000);
            
            WebElement viewAppliedFiltersButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("View Applied Filters")));
            viewAppliedFiltersButton.click();
            Thread.sleep(7000);
            System.out.println("View applied filter");
            Thread.sleep(2000);
            
            screenshotHelper.takeScreenshot(driver);
            Thread.sleep(5000);
            WebElement closeViewAppliedFiltersButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".roc-dialog-close")));
            closeViewAppliedFiltersButton.click();

            // Handle Notifications pop-up if it appears
            loginManager.handlePopUp(shortWait, "#closePopup > .gwt-Image", "Notifications Pop-up close button");
            
            // Apply the advanced filter
            WebElement advFilterButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("advanced-filter-button")));
            advFilterButton.click();
            System.out.println("Advanced filter button clicked successfully.");

            // Using Actions class to move mouse to top left of the body element
            WebElement bodyElement = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(bodyElement, 0, 0).perform();

            // Ensure the 'clear all' button is visible and click it
            WebElement clearAllButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toolbar-button-image-new-adv-filter-clearAll > .gwt-Image")));
            clearAllButton.click();
            System.out.println("Clear all button clicked successfully.");

            // Ensure the 'OK' button is visible and click it
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ok")));
            okButton.click();
            System.out.println("OK button clicked successfully.");

            // Click on the 'adv-filter-help-label'
            WebElement advFilterHelpLabel = wait.until(ExpectedConditions.elementToBeClickable(By.id("adv-filter-help-label")));
            advFilterHelpLabel.click();
            System.out.println("Advanced filter help label clicked successfully.");
            
            String fieldNameAdvanced = configLoader.getProperty("alarm.advanced.field.name");
            String operatorFieldAdvanced = configLoader.getProperty("alarm.advanced.field.operator");
        	String fieldValueAdvanced = configLoader.getProperty("alarm.advanced.field.value");

        	// Click on the left hand field      
            try {
            	Actions action = new Actions(driver);
                // Create XPath dynamically based on the fieldNameAdvanced value
                String fieldNameXpathExpressionAdvanced = String.format("//td[contains(@class, 'item') and normalize-space(.)='%s']", fieldNameAdvanced);
                // Open the dropdown with CONTROL + SPACE
	            action.keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).keyUp(Keys.CONTROL).perform();
                WebElement fieldOption;
                fieldOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fieldNameXpathExpressionAdvanced)));
                // If the element is found, proceed with interaction
                if (fieldOption != null) {
                    // Scroll to the correct option if needed
                    //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fieldOption);
                    Thread.sleep(500); 
                    // Click the correct option
                    new Actions(driver).moveToElement(fieldOption).click().perform();
                    System.out.println("Element with text '" + fieldNameAdvanced + "' clicked.");
                } else {
                    System.out.println("Element with text '" + fieldNameAdvanced + "' not found.");
                }

            } catch (NoSuchElementException e) {
                System.out.println("Element not found: " + e.getMessage());
            } catch (TimeoutException e) {
                System.out.println("Timeout waiting for element: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.out.println("Thread was interrupted: " + e.getMessage());
            }
            Thread.sleep(1000);

            // Click on the operator of selected left hand field
            try {                               
            	Actions action = new Actions(driver);
            	// Create XPath dynamically based on the operatorFieldAdvanced value
                String operatorXpathExpression = String.format("//td[contains(@class, 'item') and normalize-space(.)='%s']", operatorFieldAdvanced);
                // Open the dropdown with CONTROL + SPACE
	            action.keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).keyUp(Keys.CONTROL).perform();
                System.out.println("Entered Operator: "+operatorFieldAdvanced);
                System.out.println("Entered Operator Expression: "+operatorXpathExpression);
                WebElement operatorOption;
                operatorOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(operatorXpathExpression)));
                // If the element is found, proceed to scroll and click
                if (operatorOption != null) {
                    Thread.sleep(500); 

                    // Click the correct option
                    new Actions(driver).moveToElement(operatorOption).click().perform();
                    System.out.println("Element with text '" + operatorFieldAdvanced + "' clicked.");
                } else {
                    System.out.println("Element with text '" + operatorFieldAdvanced + "' not found.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Element not found: " + e.getMessage());
            } catch (TimeoutException e) {
                System.out.println("Timeout waiting for element: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.out.println("Thread was interrupted: " + e.getMessage());
            }
            Thread.sleep(1000);
            
            // To enter the right hand field
            WebElement tokenInput = driver.findElement(By.cssSelector(".original-token-input"));
            tokenInput.sendKeys(fieldValueAdvanced);
            tokenInput.sendKeys(Keys.ENTER);
            System.out.println("Filter criteria entered successfully.");
                                
            filterSearchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-search-button")));
            filterSearchButton.click();
            System.out.println("Filter applied successfully");
            Thread.sleep(5000);
            loginManager.waitTillLoading();
            Thread.sleep(5000);
            // Take screenshot of results after applying the filter
            screenshotHelper.takeScreenshot(driver);

            viewAppliedFiltersButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("View Applied Filters")));
            viewAppliedFiltersButton.click();
            Thread.sleep(7000);
            System.out.println("View applied filter");
            Thread.sleep(2000);
            
            // Take screenshot of applied filter
            screenshotHelper.takeScreenshot(driver);
            Thread.sleep(5000);
            closeViewAppliedFiltersButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".roc-dialog-close")));
            closeViewAppliedFiltersButton.click();
            
         // Extracting the count of fetched records
            WebElement recordCountElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retrieved-items-text")));
            // Extract text from the element
	        String recordCountText = recordCountElement.getText().trim();
	        int totalRecords = 0;
	        try {
	            if (recordCountText.contains("of")) {
	                // Extract the last number from "1 - 29 of 29" format
	                totalRecords = Integer.parseInt(recordCountText.replaceAll(".*of\\s+", "").trim());
	            } else {
	                // Directly parse the number when it contains only a single value
	                totalRecords = Integer.parseInt(recordCountText);
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Error parsing total records: " + e.getMessage());
	        }
	        System.out.println("Total Records Fetched: " + totalRecords);
	        // Check if the total record count is 0 to skip Jump to alarm details action
	        if (totalRecords == 0) {
	            System.out.println("No records fetched. Discontinuing the Jump to alarm details action.");
	        }
	        else {
	        	// Fetch Alarm Details
	        	WebElement firstAlarmRecord = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr.NG")));
	        	firstAlarmRecord.click();
	        	Thread.sleep(2000);
                screenshotHelper.takeScreenshot(driver);
                WebElement alarmActionsButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Alarm Actions")));
                alarmActionsButton.click();
                WebElement alarmDetailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Jump to Alarm Details']")));
                alarmDetailsButton.click();
                Thread.sleep(5000);
                loginManager.waitTillLoading();
                Thread.sleep(3000);
                // Check if there is an error in alarm details page
    	        String errorText = "The call failed on the server; see server log for details";
    	        if (driver.getPageSource().contains(errorText)) {
    	        	WebElement errorDiv = driver.findElement(By.cssSelector(".gwt-HTML[title*='" + errorText + "']"));
    	        	String errorTitle = (errorDiv.getAttribute("title")).replaceAll("<[^>]+>", "");
    	        	System.out.println("Error detected with the following message: " + errorTitle);
    	            screenshotHelper.takeScreenshot(driver);
    	        	okButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ok")));
    	            okButton.click();
    	            Thread.sleep(2000);
    	            screenshotHelper.takeScreenshot(driver);
    	        }
    	        else {
    	        	System.out.println("Fetched the alarm details successfully");
    	            screenshotHelper.takeScreenshot(driver);
    	        }
                WebElement closeAlarmView = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".gwt-alarm-closeAlarmLabel")));
                closeAlarmView.click();
	        }
                        
            //To click Sort Options
            WebElement sortOptionsButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Sort Options']")));
            sortOptionsButton = wait.until(ExpectedConditions.elementToBeClickable(sortOptionsButton));
            sortOptionsButton.click();
            Thread.sleep(5000);
             
            // Check if the 'Modified Date' column is already sorted in ascending or descending order
            boolean isDescendingSorted = driver.findElements(By.xpath("//div[@class='gwt-fms-widget-label' and text()='Modified Date(DESC)']")).size() > 0;
            boolean isAscendingSorted = driver.findElements(By.xpath("//div[@class='gwt-fms-widget-label' and text()='Modified Date(ASC)']")).size() > 0;

            if (isDescendingSorted) {
            	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='gwt-fms-widget-label' and text()='Modified Date(DESC)']"))).click();
                // Move the sorted column back to the left
                WebElement moveToLeftButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#sortOptionsMoveToLeftLabel.sortOptionsMoveToLeftBtn")));
                moveToLeftButton.click();
            }
            else if(isAscendingSorted) {
            	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='gwt-fms-widget-label' and text()='Modified Date(ASC)']"))).click();
            	WebElement moveToLeftButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#sortOptionsMoveToLeftLabel.sortOptionsMoveToLeftBtn")));
                moveToLeftButton.click();
            }

            // Wait for the descending radio button and click it
            WebElement descendingRadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#sortOptionsDescendingRadio_InputElement")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", descendingRadioButton);

            // Wait for the 'Modified Date' label to become clickable and click it
            WebElement selectModifiedDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='gwt-fms-widget-label' and text()='Modified Date']")));
            selectModifiedDate.click();
            System.out.println("Selected Modified Date successfully.");

            // Wait for the 'Move to Right' button to become clickable and click it
            WebElement moveToRightButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#sortOptionsMoveToRightLabel.sortOptionsMoveToRightBtn")));
            moveToRightButton.click();
            screenshotHelper.takeScreenshot(driver);

            WebElement sortOptionsOkButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#sortOptionsOkButton")));
            sortOptionsOkButton.click();
            System.out.println("Successfully selected the sort option.");
            loginManager.waitTillLoading();
            screenshotHelper.takeScreenshot(driver);
            Thread.sleep(3000);
            
            //Configure grid validation
            WebElement configureGridButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#configureGridIcon .gwt-Image")));
            configureGridButton.click();
            System.out.println("Configure Grid button clicked successfully.");
            WebElement visibleCheckBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".NG #grcVisibleFl_checked_column_field_image")));
            visibleCheckBox.click();
            screenshotHelper.takeScreenshot(driver);
            WebElement configureGridOKButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("configureGrid.OK")));
            configureGridOKButton.click();	
            Thread.sleep(5000);
            loginManager.waitTillLoading();
            screenshotHelper.takeScreenshot(driver);
            Thread.sleep(3000);
            //Reverting back the Configure Grid settings
            configureGridButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#configureGridIcon .gwt-Image")));
            configureGridButton.click();
            visibleCheckBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".NG #grcVisibleFl_checked_column_field_image")));
            visibleCheckBox.click();
            configureGridOKButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("configureGrid.OK")));
            configureGridOKButton.click();
            Thread.sleep(5000);
            loginManager.waitTillLoading();
            System.out.println("Successfully reverted back the Configure Grid settings.");
            Thread.sleep(3000);
            
            //Check record count
            WebElement recordCountButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("recordCount")));
            recordCountButton.click();
            System.out.println("Record Count button clicked successfully.");
            Thread.sleep(3000);
            loginManager.waitTillLoading();
            Thread.sleep(2000);
            screenshotHelper.takeScreenshot(driver);
            
	        // Check if the total record count is 0 to skip Export
	        if (totalRecords == 0) {
	            System.out.println("No records fetched. Skipping the alarm page export action.");
	        }
            else {
            	//Exporting the fetched records
            	WebElement exportOptionsButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Export Options")));
                exportOptionsButton.click();
                String exportFileFormat = configLoader.getProperty("alarm.export.file.format");
                String exportFileFormatXpathExpression = String.format("//div[@class='gwt-HTML' and normalize-space(text())='%s']", exportFileFormat);
                WebElement exportFileFormatButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(exportFileFormatXpathExpression)));
                exportFileFormatButton.click();
                Thread.sleep(3000);
                loginManager.waitTillLoading();
                String exportPageType = configLoader.getProperty("alarm.export.page.type");
                String exportPageTypeXpathExpression = String.format("//div[contains(@class, 'gwt-fms-widget-label') and normalize-space(text())='%s']", exportPageType);
                WebElement exportPageTypeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(exportPageTypeXpathExpression)));
                exportPageTypeButton.click();
                okButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ok")));
                okButton.click();
                Thread.sleep(3000);
                loginManager.waitTillLoading();
                screenshotHelper.takeScreenshot(driver);
                String exportDownloadOption = exportFileFormat.toLowerCase();
                String exportDownloadOptionXpathExpression = String.format("//button[contains(@class, '%s-export-file-button')]", exportDownloadOption);
                WebElement exportDownloadOptionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(exportDownloadOptionXpathExpression)));
                exportDownloadOptionButton.click();
                Thread.sleep(5000);
                System.out.println("Downloaded the exported alarm page successfully");
                
                // Get the window handle of the current (original) window
                String originalWindowHandle = driver.getWindowHandle();

                // Get all window handles and switch to the newly opened window
                Set<String> allWindowHandles = driver.getWindowHandles();
                for (String windowHandle : allWindowHandles) {
                    if (!windowHandle.equals(originalWindowHandle)) {
                        driver.switchTo().window(windowHandle);
                        break;  // Switch to the new window/tab and break the loop
                    }
                }
                // Switch back to the original window
                driver.switchTo().window(originalWindowHandle);
                
                WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".roc-dialog-close")));
                closeButton.click();           	
            }
                                                                    
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}
