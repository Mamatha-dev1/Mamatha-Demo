package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Windowmangeactivities {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
            WebDriver driver= new ChromeDriver();
            driver.get("https://www.google.com");
            driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");
            driver.navigate().back();
            driver.navigate().forward();
	}

}
