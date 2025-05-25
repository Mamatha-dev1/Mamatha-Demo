package FMClient;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Mamatha.configLoader;

import java.time.Duration;

import org.testng.annotations.Test;

public class ActiveAlarms {

    private static LoginManager loginManager = LoginManager.getInstance(System.getProperty("user.dir"));

    @Test
    public void testnewrulecreation() {
        try {
            WebDriver driver = loginManager.getDriver();
            executeactivealarms(driver);
            System.out.println("Active alarm test cases executed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Test failed: " + e.getMessage());
        }
    }

    public static void executeactivealarms(WebDriver driver) {
        try {
        	String baseDir = System.getProperty("user.dir");
            ConfigurationLoader configLoader = new ConfigurationLoader(baseDir);
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(configLoader);

            String selectedFilter = configLoader.getProperty("filter.selected");
            String selectedOperator = configLoader.getProperty("filter.operator");
            String filterValue = configLoader.getProperty("filter.value");
            String fromDate = configLoader.getProperty("filter.fromdate");
            String toDate = configLoader.getProperty("filter.todate");
            String pageSelection = configLoader.getProperty("page.selection");
            String fileFormat = configLoader.getProperty("file.format");

            System.out.println("filter.selected: " + selectedFilter);
            System.out.println("filter.operator: " + selectedOperator);
            System.out.println("filter.value: " + filterValue);

            driver.findElement(By.id("navigationLabel")).click();
            driver.findElement(By.cssSelector("#id-Alarm\\ Management > b1")).click();
            sleep(2000);
            driver.findElement(By.id("id-Alarms Active")).click();
            sleep(25000);

            closePopUpYesIfPresent(driver);
            System.out.println("Active alarm page loaded successfully");

            sleep(10000);
            driver.findElement(By.id("Remove All Filters")).click();
            sleep(10000);
            System.out.println("Removed all filters successfully");

            try {
                applyAdvancedFilter(driver, selectedFilter, selectedOperator, filterValue);
            } catch (IOException e) {
                System.err.println("Error applying advanced filter: " + e.getMessage());
            }

            exportData(driver, fileFormat, pageSelection);
            viewAppliedFilters(driver);
            recordCount(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Sleep interrupted");
        }
    }

    private static void closePopUpYesIfPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("yes")));
            closeButton.click();
            System.out.println("Pop-up closed successfully.");
        } catch (Exception e) {
            System.out.println("No pop-up appeared.");
        }
    }

    private static void applyAdvancedFilter(WebDriver driver, String selectedFilter, String selectedOperator, String filterValue) throws IOException {
        try {
            driver.findElement(By.id("advanced-filter-button")).click();
            sleep(1000);

            driver.findElement(By.cssSelector(".gwt-SuggestBox.original-token-input")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SPACE));

            String fieldNameXpath = String.format("//td[contains(text(), '%s')]", selectedFilter);
            driver.findElement(By.xpath(fieldNameXpath)).click();

            driver.findElement(By.cssSelector(".original-token-input")).sendKeys(Keys.ENTER);
            sleep(5000);

            driver.findElement(By.cssSelector(".original-token-input")).sendKeys(selectedOperator);
            driver.findElement(By.cssSelector(".original-token-input")).sendKeys(Keys.ENTER);

            driver.findElement(By.cssSelector(".original-token-input")).sendKeys(filterValue);
            driver.findElement(By.cssSelector(".original-token-input")).sendKeys(Keys.ENTER);

            sleep(4000);
            driver.findElement(By.id("filter-search-button")).click();
            sleep(10000);

            ScreenshotHelper1.takeScreenshot(driver, "filter_applied_screenshot_" + System.currentTimeMillis() + ".png");

        } catch (Exception e) {
            throw new IOException("Failed to apply advanced filter: " + e.getMessage(), e);
        }
    }

    private static void exportData(WebDriver driver, String fileFormat, String pageSelection) {
        try {
            driver.findElement(By.xpath("//div[@class='gwt-HTML' and text()='Export Options']")).click();
            String xpath = getExportXpath(fileFormat);
            driver.findElement(By.xpath(xpath)).click();
            sleep(2000);

            selectPageOption(driver, pageSelection);

            driver.findElement(By.xpath("//button[@class='gwt-Button roc-primary-button' and text()='OK']")).click();
            sleep(3000);

            driver.findElement(By.xpath("//button[@class='csv-export-file-button' and text()='Active Alarms 1']")).click();
            sleep(5000);
            System.out.println("Export completed");

            String mainWindowHandle = driver.getWindowHandle();
            Set<String> allWindowHandles = driver.getWindowHandles();

            for (String handle : allWindowHandles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            driver.switchTo().window(mainWindowHandle);

            driver.findElement(By.className("roc-dialog-close")).click();
            sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getExportXpath(String fileFormat) {
        switch (fileFormat.toUpperCase()) {
            case "CSV": return "//div[@class='gwt-HTML' and text()='CSV']";
            case "PDF": return "//div[@class='gwt-HTML' and text()='PDF']";
            case "RTF": return "//div[@class='gwt-HTML' and text()='RTF']";
            case "TEXT": return "//div[@class='gwt-HTML' and text()='TEXT']";
            default:
                throw new IllegalArgumentException("Invalid file format: " + fileFormat);
        }
    }

    private static void selectPageOption(WebDriver driver, String pageSelection) {
        try {
            if ("current".equalsIgnoreCase(pageSelection)) {
                driver.findElement(By.className("gwt-fms-widget-label-selected")).click();
            } else if ("all".equalsIgnoreCase(pageSelection)) {
                driver.findElement(By.className("gwt-fms-widget-label")).click();
            } else {
                System.out.println("Invalid page selection. Use 'current' or 'all'.");
            }
        } catch (Exception e) {
            System.err.println("Error selecting page option: " + e.getMessage());
        }
    }

    private static void viewAppliedFilters(WebDriver driver) {
        try {
            driver.findElement(By.id("View Applied Filters")).click();
            sleep(7000);
            ScreenshotHelper1.takeScreenshot(driver, "view_filter_applied_screenshot.png");
            driver.findElement(By.cssSelector(".roc-dialog-close")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recordCount(WebDriver driver) {
        try {
        	sleep(5000);
            driver.findElement(By.id("recordCount")).click();
            sleep(5000);
            ScreenshotHelper1.takeScreenshot(driver, "record_count_screenshot.png");

            driver.findElement(By.id("Remove All Filters")).click();
            sleep(7000);

            driver.findElement(By.id("basic-filter-button")).click();
            sleep(2000);

            WebElement fromDateField = driver.findElement(By.xpath("//input[@placeholder='From']"));
            fromDateField.sendKeys("01/01/2025");

            WebElement toDateField = driver.findElement(By.xpath("//input[@placeholder='To']"));
            toDateField.sendKeys("12/31/2025");
            sleep(3000);

            driver.findElement(By.id("filter-search-button")).click();
            sleep(10000);

            ScreenshotHelper1.takeScreenshot(driver, "Basic_Filter_screenshot.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}