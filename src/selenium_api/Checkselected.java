package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Checkselected  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
    
	@Test
	public void TestScript03() {
		WebElement AgeCheckbox = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement InterestCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
		//isControlSelected(AgeCheckbox);
		//isControlSelected(InterestCheckbox);
		
		if(isControlSelected(AgeCheckbox)) {
			System.out.println("Age is selected");
		}else {
			AgeCheckbox.click();
		}
		if(isControlSelected(InterestCheckbox)) {
			System.out.println("Interest is selected");
		}else {
			InterestCheckbox.click();
		}
	}
	/*
	public void isControlSelected(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}else {
			System.out.println("Element is selected");
		}
	}
	*/
	public boolean isControlSelected(WebElement element) {
		return element.isSelected();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}