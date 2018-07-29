package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;	

public class Topic04_ListDropdown  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
    
	@Test
	public void Dropdownlist() {
		driver.get("http://daominhdam.890m.com/");
		Select jobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertTrue(!jobRole.isMultiple());
		jobRole.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(),"Automation Tester");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jobRole.selectByValue("manual");
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(),"Manual Tester");
		jobRole.selectByIndex(3);
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Tester");
		int jobItems = jobRole.getOptions().size();
		Assert.assertEquals(jobItems,5);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}