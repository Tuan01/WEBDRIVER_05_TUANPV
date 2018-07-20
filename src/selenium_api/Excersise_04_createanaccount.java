package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Excersise_04_createanaccount  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
    public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
		
	}
	@Test
	public void TC_04_createanAccount() {
		driver.get("http://live.guru99.com/index.php/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account' and @class='button']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Pham");
		driver.findElement(By.id("middlename")).sendKeys("Tuan");
		driver.findElement(By.id("lastname")).sendKeys("Van");
		driver.findElement(By.id("email_address")).sendKeys("phamtuan" + randomEmail() + "@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12345678");
		driver.findElement(By.id("confirmation")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		String messageSuccessfully = driver.findElement(By.xpath("//li[@class='success-msg']//span[contains(text(),'Thank you for registering with Main Website Store.')]")).getText();
		Assert.assertEquals("Thank you for registering with Main Website Store.", messageSuccessfully );
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//div[@class='links']//a[@title='Log Out']")).click();
		driver.navigate().to("http://live.guru99.com/index.php/");

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}