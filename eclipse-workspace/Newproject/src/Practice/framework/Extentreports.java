package Practice.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreports {
	static ExtentReports extent;
	
	public static ExtentReports config()
	{
	String path=System.getProperty("user.dir")+"\\Extentreports"+"report.html";
	ExtentSparkReporter reporter= new ExtentSparkReporter(path);
	reporter.config().setDocumentTitle("Test results");
	reporter.config().setReportName("Extentreport");
	 
	 extent= new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Mamatha");
	return extent;
      
	}
	

}
