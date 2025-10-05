package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_004 {

	@Test
	public  void verifyRegisteringAccountWithoutFillingFields() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//li//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String str = driver.findElement(By.xpath("(//div[@class='text-danger'])[1]")).getText();
		Assert.assertEquals("First Name must be between 1 and 32 characters!", str);
		
		str = driver.findElement(By.xpath("(//div[@class='text-danger'])[2]")).getText();
		Assert.assertEquals("Last Name must be between 1 and 32 characters!", str);
		
		str = driver.findElement(By.xpath("(//div[@class='text-danger'])[3]")).getText();
		Assert.assertEquals("E-Mail Address does not appear to be valid!", str);
		
		str = driver.findElement(By.xpath("(//div[@class='text-danger'])[4]")).getText();
		Assert.assertEquals("Telephone must be between 3 and 32 characters!", str);
		
		str = driver.findElement(By.xpath("(//div[@class='text-danger'])[5]")).getText();
		Assert.assertEquals("Password must be between 4 and 20 characters!", str);
	}

}
