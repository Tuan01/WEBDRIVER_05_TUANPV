package selenium_api;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_WebDriver_Wait {
    WebDriver driver;
    WebDriverWait waitExplicit;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath(".//*[@id='start']/button")).click();
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='finish']/h4")).getText(), "Hello World!");
		
	}
	
	@Test
	public void TC_02_ExplicitWait() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='ctl00_ContentPlaceholder1_Panel1']/div")));
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='ctl00_ContentPlaceholder1_Panel1']/div")));
		// icon loading
		/* Presence - Passed */
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='raDiv']")));
		
		
		WebElement todayBefore = driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(todayBefore.getText(), "No Selected Dates to display.");
		
		// Step 04 
		driver.findElement(By.xpath("//a[text()=9]/parent::td")).click();
		
		// Step 05
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		
		
		// Step 06
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=9]/parent::td[@class='rcSelected']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()=9]/parent::td[@class='rcSelected']")).isDisplayed());
		
		// Step 07
		WebElement todayAfter = driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(todayAfter.getText(), "Sunday, September 09, 2018");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}