package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_001 {

	@Test
	public void verifyRegisteringWithMandatoryFields() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//li//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Sandeep");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Bolisetti");
		driver.findElement(By.xpath("//input[@placeholder='E-Mail']")).sendKeys(generateEmailWithTime());
		driver.findElement(By.xpath("//input[@placeholder='Telephone']")).sendKeys("918687686");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("918687686");
		driver.findElement(By.xpath("//input[@placeholder='Password Confirm']")).sendKeys("918687686");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		//driver.findElement(By.xpath("//a[@normalize-space='Logout']")).isDisplayed();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("(//h1)[2]")).getText(),expectedHeading);
		String p1 = "Congratulations! Your new account has been successfully created!";
		String p2 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String p3 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String p4 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
		Assert.assertEquals(driver.findElement(By.xpath("(//p)[2]")).getText(), p1);
		Assert.assertEquals(driver.findElement(By.xpath("(//p)[3]")).getText(), p2);
		Assert.assertEquals(driver.findElement(By.xpath("(//p)[4]")).getText(), p3);
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		//Assert.assertEquals(driver.findElement(By.xpath("(//p)[5]")).getText(), p4);
		driver.close();
	}
	
	public static String generateEmailWithTime()
	{
		return new Date().toString().replaceAll("\\s", "").replaceAll(":", "")+"@gmail.com"; 
	}

}
