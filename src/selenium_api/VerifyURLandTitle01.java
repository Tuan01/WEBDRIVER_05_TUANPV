package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyURLandTitle01  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
    
	@Test
	public void TC_01_VerifyURLandTitle() {
		driver.get("http://live.guru99.com/index.php/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle,"Home page");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account' and @class='button']")).click();
		driver.navigate().back();
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
		String loginURL = driver.getCurrentUrl();
		Assert.assertEquals(loginURL,"http://live.guru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
		String CreateaccountURL = driver.getCurrentUrl();
		Assert.assertEquals(CreateaccountURL,"http://live.guru99.com/index.php/customer/account/create/");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}