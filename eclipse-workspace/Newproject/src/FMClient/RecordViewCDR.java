package FMClient;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
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

import org.apache.commons.io.FileUtils;

public class RecordViewCDR {

	// Get the singleton instance of LoginManager
	private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));
	@Test
	public void testnewrulecreation() throws InterruptedException, IOException {
		
		// Use the driver from LoginManager
		testRecordView(loginManager.getDriver());
		System.out.println("Record view test cases executed succesfully");
	}

	// public static void main(String[] args) throws InterruptedException {

	
	public void testRecordView(WebDriver driver) throws InterruptedException, IOException {
		
//		
	Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(By.xpath("//div[@id='navigationLabel']")).click();
		Thread.sleep(2000);

		// To select search option
		// driver.findElement(By.cssSelector("#gwt-HTML ag-gwt-megamenu-label,
		// Search")).click();
		driver.findElement(By.xpath("//div[@id='id-Search']")).click();
		Thread.sleep(2000);

		// To select Record View CDR
		driver.findElement(By.xpath("//div[@id='id-CDR' and contains (@class, 'gwt-HTML')]")).click();
		Thread.sleep(2500);

		// To confirm cdr page popoup
		//driver.findElement(By.xpath("//button[@id='yes' and contains (@class, 'gwt-Button')]")).click();
		Thread.sleep(3000);

		// To remove all existing filters
		try {
			WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement rmfilter = wait4
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("Remove All Filters")));
			rmfilter.click();
			// driver.findElement(By.cssSelector("div.roc-filter-view-applied-btn-container")).click();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		Thread.sleep(3000);
		WebElement Basic_filter = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("basic-filter-button")));
		Basic_filter.click();

		// To select dropdown
		// try {
		WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement Service_type = wait6.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='SERVICE_TYPE_multi_sel_arrowLabel' and contains (@class, 'arrow-label-down')]")));
		Service_type.click();
		// } catch (Exception e){
		// System.out.println(e.getMessage());
		// }
		Thread.sleep(4000);
		
		System.out.println(driver.findElement(By.id("Select all_InputElement")).isSelected());
		driver.findElement(By.id("Select all_InputElement")).click();
		System.out.println(driver.findElement(By.id("Select all_InputElement")).isSelected());

		WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement dateBox = wait5.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(
				"//table[@id='RANGE_DATE_TYPE_abs_from_date']//input[@class='gwt-DateBox roc-field roc-trigger-field roc-datefield']"))));
		dateBox.click();

		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime parsedDateTime = null;

		while (parsedDateTime == null) {
			System.out.println("Enter the START datetime in the format dd/MM/yyyy HH:mm:ss:");
			String userInput = scanner.nextLine();
			try {
				parsedDateTime = LocalDateTime.parse(userInput, formatter);
			} catch (Exception e) {
				System.out.println("Invalid date format. Please use the format dd/MM/yyyy HH:mm:ss");
				// driver.quit();
				// return;
			}
		}
		String formattedTimestamp = parsedDateTime.format(formatter);
		dateBox.clear();
		dateBox.sendKeys(formattedTimestamp);

		// select the current timestamp

		driver.findElement(By.id("trigger-RANGE_DATE_TYPE_abs_to_date")).click();
		System.out.println("Clicked on end date button");

		Thread.sleep(3000);
		// String newBaseUrl = "http://" + hostname + ":" + port +
		// "/rocfm/themes/common/images/default/now.png";
		// String nPathXprsn = String.format("//img[@class='gwt-Image' and src='%s']",
		// newBaseUrl);
		String baseDir = System.getProperty("user.dir");
        ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
        ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);
		try {

//			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
//			String newBaseUrl = configLoader.getProperty("base.url")
//					+ "/rocfm/themes/common/images/default/now.png";
//			// String nPathXprsn = String.format("//img[@class='gwt-Image' and src='%s']",
//			// newBaseUrl);
//			String nPathXprsn = String.format("//img[@src='%s']", newBaseUrl);

			// WebElement select =
			// wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='http://10.113.207.37:8185/rocfm/themes/common/images/default/now.png']")));
//			WebElement select = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nPathXprsn)));
			// select.click();
//			wait2.until(ExpectedConditions.visibilityOf(select)).click();
			WebElement Startdate = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
            String Sdate = configLoader.getProperty("Enddate");
			Startdate.sendKeys(Sdate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// select phone number text box
		// Change the element if data needs to be fetched from any other columns
		WebElement phoneNumber = driver
				.findElement(By.xpath("//input[@id='PHONE_NUMBER_text_box' and @class='gwt-TextBox']"));

		// enter values to phone number text box

		System.out.println("Please enter the phone number:");
		Scanner scanner1 = new Scanner(System.in);
		String phoneNumber1 = scanner1.nextLine();

		phoneNumber.sendKeys(phoneNumber1);

		driver.findElement(By.id("filter-search-button")).click();

		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement Approve = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("no")));
		Approve.click();
		Thread.sleep(2000);

		try {
			WebElement button = driver
					.findElement(By.cssSelector("button.gwt-Button.roc-primary-button#basic-filter-button"));
			button.click();

			System.out.println("Search Button clicked successfully.");
		} catch (Exception e) {
			// Handle any exceptions that occur
			System.out.println("An error occurred: " + e.getMessage());
		}

//		try {
//			WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(30));
//			WebElement checkError = wait4.until(ExpectedConditions.visibilityOfElementLocated(
//					By.xpath("//div[@class='gwt-HTML roc-window-title' and text()='Error']")));
//
//			if (checkError.isDisplayed()) {
//				// System.out.println("Presto is not running, unable to fetch records from
//				// Record View");
//				System.out.println("Status 500 : see server log for details");
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//Record Count button.
		try {
			Thread.sleep(10000);
		WebElement recCount =	wait.until(ExpectedConditions.elementToBeClickable(By.id("recordCount")));
		recCount.click();
		System.out.println("Selected Record Count");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
//configureGrid button 		
		//"http://10.113.207.48:8180/rocfm/themes/common/images/default/icons/config-grid-icon.png"
		Thread.sleep(5000);
		try {
//			String baseUrl1 = "https://" + LoginManager.serverIp + ":" + LoginPage.serverPort
//					+ "/rocfm/themes/common/images/default/icons/config-grid-icon.png";
//			String pathXpn = String.format("//img[@src='%s']", baseUrl1);
//			// =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("http://"+Hostname+":"+Port+"/rocfm/themes/common/images/default/icons/config-grid-icon.png")));
// 
			WebElement confGrid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#configureGridIcon")));
			wait.until(ExpectedConditions.visibilityOf(confGrid)).click();
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destDir = new File("C:\\Users\\mamatha.kp\\OneDrive - Subex Limited\\Documents\\Automation ss\\confGrid.png");
			FileUtils.copyFile(src, destDir);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 
		try {
			WebElement confOk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("configureGrid.OK")));
			confOk.click();
			System.out.println("Selected Configure Grid");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(3000);
		try {
			WebElement sortOpt = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//button[normalize-space()='Sort Options']")));
			sortOpt.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 
		try {
			WebElement accName = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//div[@class='gwt-fms-widget-label' and text()='Account Name'])[1]")));
			accName.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// driver.findElement(By.name("SortOptionsModelAccount")).click();
		driver.findElement(By.id("sortOptionsDescendingRadio_InputElement")).click();
		driver.findElement(By.id("sortOptionsMoveToRightLabel")).click();
		driver.findElement(By.id("sortOptionsOkButton")).click();
		System.out.println("Records sorted succesfully");
	}
}
