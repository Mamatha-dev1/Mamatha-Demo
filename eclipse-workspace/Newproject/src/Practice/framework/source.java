package Practice.framework;

import org.openqa.selenium.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class source extends Hashmapdata{
	public WebDriver driver;
	
	public WebDriver Initializedriver() throws IOException {
     Properties prop= new Properties();
     
     FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\Practice\\framework\\config.properties");
     prop.load(fis);
     String browserName= System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
     if (browserName.toLowerCase().contains("chrome")) {
    	    ChromeOptions options = new ChromeOptions();
    	    WebDriverManager.chromedriver().setup();

    	    if (browserName.toLowerCase().contains("headless")) {
    	        options.addArguments("--headless=new"); // Use 'new' mode for Chrome 109+
    	        options.addArguments("--window-size=1440,900");
    	    }

    	    driver = new ChromeDriver(options);
    	} else if (browserName.equalsIgnoreCase("firefox")) {
    	    WebDriverManager.firefoxdriver().setup();
    	    driver = new FirefoxDriver();
    	} else if (browserName.equalsIgnoreCase("edge")) {
    	    WebDriverManager.edgedriver().setup();
    	    driver = new EdgeDriver();
    	} else {
    	    throw new IllegalArgumentException("Unsupported browser: " + browserName);
    	}

    	// Only maximize if not headless
    	if (!browserName.toLowerCase().contains("headless")) {
    	    driver.manage().window().maximize();
    	}

    	return driver;
	}
     public Landingpage launchapplication() throws IOException
     { 
    	 this.driver=Initializedriver();
     Landingpage landingpage= new Landingpage(driver);
		landingpage.Loginurl();
		return landingpage;
		
	}
     
	
     public List<HashMap<String,String>> getjsondatatoMap(String filepath) throws IOException
 	{
 		String jsoncontent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
 	    ObjectMapper mapper= new ObjectMapper();
 		List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
 	return data;
 	}
     public String getscreenshot(String methodName, WebDriver driver) throws IOException {
    	    TakesScreenshot ts = (TakesScreenshot) driver;
    	    File source = ts.getScreenshotAs(OutputType.FILE);
    	    String destinationFile = System.getProperty("user.dir") + "/reports/" + methodName + ".png";
    	    FileUtils.copyFile(source, new File(destinationFile));
    	    return destinationFile;
    	}
}
