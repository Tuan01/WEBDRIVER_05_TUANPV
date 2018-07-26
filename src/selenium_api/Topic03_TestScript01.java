package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_TestScript01  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
    
	@Test
	public void TestScript01() {
		WebElement emailTexbox = driver.findElement(By.xpath("//input[@id='mail']")); 
		WebElement AgeCheckbox = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement EducationTextare = driver.findElement(By.xpath("//textarea[@id='edu']"));
		Assert.assertTrue(isControlDisplayed(emailTexbox));
		Assert.assertTrue(isControlDisplayed(AgeCheckbox));
		Assert.assertTrue(isControlDisplayed(EducationTextare));
		
		if(isControlDisplayed(emailTexbox) && isControlDisplayed(EducationTextare)) {
			emailTexbox.sendKeys("Automation Testing");
			EducationTextare.sendKeys("Automation Testing");
		}
		
		if (isControlDisplayed(AgeCheckbox)) {
			AgeCheckbox.click();
		}
	}
	
	public boolean isControlDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}