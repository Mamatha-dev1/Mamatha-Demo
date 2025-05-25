package Practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Takescreenshotofwebelement {
	public static void main(String[] args) throws IOException
	{
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().window().maximize();
		WebElement Nameeditbox=driver.findElement(By.name("name"));
		File file=Nameeditbox.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("namebox.png"));
	}

}
