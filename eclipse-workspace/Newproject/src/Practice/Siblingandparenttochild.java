package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Siblingandparenttochild {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         WebDriver driver= new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         driver.get("https://rahulshettyacademy.com/AutomationPractice/");
//         sibling to sibling
         String logintext=driver.findElement(By.xpath("//div/button[1]/following-sibling::button[1]")).getText();
        System.out.println(logintext);
        driver.findElement(By.xpath("//div/button[1]/following-sibling::button[1]")).getText();
//      sibling to sibling and child to parent to select whole page  
//        driver.findElement(By.xpath("//div/button[1]/following-sibling::button[1]/parent::div/parent::header/parent::body")).getText();
        String Practicetext=driver.findElement(By.xpath("//div/button[1]/following-sibling::button[1]/parent::div/button[1]")).getText();
        System.out.println(Practicetext);
        
	}

}
