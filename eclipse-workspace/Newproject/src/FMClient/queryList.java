package FMClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import FMClient.ConfigurationLoader;
import FMClient.LoginManager;
import FMClient.ScreenshotHelper;

public class queryList {

	// Get the singleton instance of LoginManager
    private static LoginManager loginmanager = LoginManager.getInstance(System.getProperty("user.dir"));
	
	@Test
	public void testQueryList() throws InterruptedException, IOException {

//		LoginPage startPage = new LoginPage(driver);
//		//startPage.goTo();
//		startPage.login();
//		startPage.popups();
////		startPage.notipopups();

		// To close password expiration notification

//		WebElement infopass = driver.findElement(By.className("roc-window-title"));
//		if (infopass.isDisplayed()) {
//			// <button type="button" class="gwt-Button" id="ok" style="width:
//			// 50px;">OK</button>
//			driver.findElement(By.xpath("//button[@id='ok']")).click();
//		}
		//LoginPage loginmanager = LoginPage.getInstance();
		WebDriver driver = loginmanager.getDriver();
		String newName = null;

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
//		try {
//			// Wait for the "infopass" element to be visible
//			WebElement infopass = wait1
//					.until(ExpectedConditions.visibilityOfElementLocated(By.className("roc-window-title")));
//
//			if (infopass.isDisplayed()) {
//				// If displayed, click the "OK" button
//				WebElement okButton = wait1
//						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok']")));
//				okButton.click();
//				System.out.println("Clicked OK button");
//			}
//		} catch (Exception e) {
//			System.out.println("infopass element not displayed or OK button not found: " + e.getMessage());
//		}

		try {
			WebElement navigate = wait1
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navigationLabel']")));
			navigate.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			WebElement search = wait1.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(".gwt-TextBox.roc-field.roc-trigger-field")));
			search.clear();
			search.sendKeys("query");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
Thread.sleep(2000);
		try {
			WebElement queryList = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-Query List")));
			queryList.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {

			WebElement select = wait1.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//button[@type='button' and @class='gwt-Button' and @id='yes']")));
			select.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(4000);
		try {

			WebElement newQuery = wait1.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='Common Tasks']")));
//			WebElement newQuery = wait1.until(ExpectedConditions
//					.visibilityOfElementLocated(By.id("Common Tasks")));
//			WebElement newQuery = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Common Tasks'][1]")));
			newQuery.click();
			System.out.println("clicked on new query");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			WebElement newQL = wait1.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='gwt-HTML' and text()='New']")));
//			WebElement newQL = wait1.until(ExpectedConditions
//				.visibilityOfElementLocated(By.className("roc-context-menu-item.roc-menu-item-enabled")));
			newQL.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			WebElement name = wait1.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//input[@class='gwt-TextBox roc-field-invalid' and @id='fqutvName']")));
			System.out.println("Enter Query List Name :");
			Scanner queryname = new Scanner(System.in);
			newName = queryname.nextLine();
			name.sendKeys(newName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {

			WebElement fraudType = wait1.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("FraudTypeTblModelAdjustment Fraud504")));
			fraudType.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		driver.findElement(By.id("expand-dataStreamMultiSelect")).click();
		try {

			WebElement dataStream = wait1.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='gwt-fms-widget-label' and @id='RecConfModelCDR1']")));
			dataStream.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td/button[@class='gwt-Button roc-primary-button' and text()='Done']")).click();

//<input type="text" class="gwt-DateBox roc-field roc-trigger-field roc-datefield" placeholder="From">
		try {

			WebElement queryFrom = wait1.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector(".gwt-DateBox.roc-field.roc-trigger-field.roc-datefield")));
			DateTimeFormatter timestamp = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
			LocalDateTime parsedTimestamp = null;

			while (parsedTimestamp == null) {
				System.out.println("Enter the date and time in this format MM/dd/yyyy HH:mm:ss:");
				Scanner fromDate = new Scanner(System.in);
				String fDate = fromDate.nextLine();
				try {
					parsedTimestamp = LocalDateTime.parse(fDate, timestamp);
				} catch (Exception e) {
					System.out.println("Invalid date format. Please use the format MM/dd/yyyy HH:mm:ss");

				}
			}
			String newTimestamp = parsedTimestamp.format(timestamp);
			queryFrom.clear();
			queryFrom.sendKeys(newTimestamp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		driver.findElement(By.id("trigger-aqm-query-dates_abs_to_date")).click();
		String baseDir = System.getProperty("user.dir");
		ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
		ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);

		String baseUrl = configLoader.getProperty("base.url")
				+ "/rocfm/themes/common/images/default/now.png";
		String xpathExpression = String.format("//img[@class='gwt-Image' and @src='%s']", baseUrl);

		WebElement dynamicElement = wait1
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
		dynamicElement.click();

		// Locate the scrollable element by its id
		WebElement scrollableElement = wait1
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("bottomScrollablePanel")));

		// Use JavaScript to scroll within the element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll down within the element
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableElement);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

// Scroll up within the element
//js.executeScript("arguments[0].scrollTop = 0;", scrollableElement);

//<button type="button" class="gwt-Button roc-primary-button" id="nextButton">Next</button>

		try {
			// WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement nextButton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("nextButton")));
			nextButton.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);

		try {
//TO scroll top of screen
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0],scrollTop = 0;", scrollableElement);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Select first column of table i.e hardcoded
		/*
		 * try { // Locate the element by its ID WebElement firstColumn =
		 * wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(
		 * "availableColumnsTree-8768"))); firstColumn.click(); } catch (Exception e) {
		 * System.out.println(e.getMessage()); }
		 */

//		try {
//			WebElement searchPh = wait1.until(ExpectedConditions
//					.presenceOfElementLocated(By.xpath("//input[@class='gwt-TextBox roc-dual-list-filter']")));
//			System.out.println("Enter the column for query Configuration");
//			Scanner qCol = new Scanner(System.in);
//			String instCol = qCol.nextLine();
//			searchPh.sendKeys(instCol);
//			// searchPh.sendKeys("phone number");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		try {
			WebElement selectPhoneNumber = wait1
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Phone Number']")));
			selectPhoneNumber.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement arrow = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("addButton")));
			arrow.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

//<button type="button" class="gwt-Button roc-primary-button" id="queryManagementDetail.save">Save</button>

		try {
			WebElement saveButton = wait1.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[@class='gwt-Button roc-primary-button' and @id='queryManagementDetail.save']")));
			saveButton.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(3000);

		//Record Count button.
				try {
				WebElement recCount =	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("recordCount")));
				recCount.click();
				System.out.println("Clicked on Record Count button");
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				//configureGrid button 		
				//"http://10.113.207.48:8180/rocfm/themes/common/images/default/icons/config-grid-icon.png"
				try {
					
					String baseUrl1 = configLoader.getProperty("base.url")
								+ "/rocfm/themes/common/images/default/icons/config-grid-icon.png";
						String pathXpn = String.format("//img[@src='%s']", baseUrl1);
						// =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("http://"+Hostname+":"+Port+"/rocfm/themes/common/images/default/icons/config-grid-icon.png")));

						WebElement confGrid = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pathXpn)));
						wait1.until(ExpectedConditions.visibilityOf(confGrid)).click();
						screenshotHelper.takeScreenshot(driver);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

					try {
						WebElement confOk = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("configureGrid.OK")));
						confOk.click();
						System.out.println("Naviagted to configure Grid");
					} catch (Exception e) {
						System.out.println(e.getMessage());
						
					}
			
				
				//Sort Options 
				// <button type="button" class="gwt-Button roc-primary-button" id="basic-filter-button">Sort Options</button>
				try {
				WebElement sortOpt = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'roc-primary-button') and text()='Sort Options']")));
				sortOpt.click();
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				try {
				WebElement createdate=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'SortOptionsModelCreated Date')]")));
				createdate.click();
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				driver.findElement(By.id("sortOptionsDescendingRadio_InputElement")).click();
				driver.findElement(By.id("sortOptionsMoveToRightLabel")).click();
				driver.findElement(By.id("sortOptionsOkButton")).click();
				Thread.sleep(2000);
				System.out.println("Sort Options Performed");
				
// Remove All Filters
				
				driver.findElement(By.id("Remove All Filters")).click();
				Thread.sleep(2000);
//Basic Filter
		try {
			WebElement basicFilter = wait1
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("basic-filter-button")));
			basicFilter.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			WebElement entName = wait1
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("FQUTV_NAME_text_box")));
			entName.sendKeys(newName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			WebElement drpd1 = wait1
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("FQUTV_STATUS_multi_sel_arrowLabel")));
			drpd1.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			WebElement selectdd1 = wait1.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("gwtcheckboxlistitem_Enabled_InputElement")));
			selectdd1.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			WebElement search = wait1
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter-search-button")));
			search.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		screenshotHelper.takeScreenshot(driver);
	}
}
