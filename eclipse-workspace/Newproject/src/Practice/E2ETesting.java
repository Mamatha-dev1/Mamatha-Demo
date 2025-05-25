package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class E2ETesting {
	
	public static void main (String[] args) throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		String UserName="Mamatha";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys("rahulsetty");
		driver.findElement(By.name("inputPassword")).sendKeys("guduie");
		driver.findElement(By.className("signInBtn")).click();
		Thread.sleep(2000);
		String Errortext=driver.findElement(By.cssSelector("p.error")).getText();
		System.out.println(Errortext);
		driver.findElement(By.xpath("//a[text()='Forgot your password?']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Mamatha");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("mamathakp2017@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Phone Number']")).sendKeys("8674653798");
		driver.findElement(By.className("reset-pwd-btn")).click();
		String info=driver.findElement(By.cssSelector("p.infoMsg")).getText();
		String password=info.split("'")[1].split("'")[0];
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Go to Login']")).click();
		driver.findElement(By.id("inputUsername")).sendKeys(UserName);
		driver.findElement(By.name("inputPassword")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.id("chkboxTwo")).click();
		driver.findElement(By.className("signInBtn")).click();
		String mesg=driver.findElement(By.xpath("//div/h2")).getText();
		Assert.assertEquals(mesg,"Hello "+UserName+",");
		String Welcomemesg=driver.findElement(By.xpath("//div/h1")).getText();
		String Successmesg=driver.findElement(By.xpath("//p[text()='You are successfully logged in.']")).getText();
		System.out.println(mesg);
		System.out.println(Welcomemesg);
		System.out.println(Successmesg);
	}
}
