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

public class CheckenableandDisable  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
    
	@Test
	public void TestScript02() {
		WebElement emailTexbox = driver.findElement(By.xpath("//input[@id='mail']")); 
		WebElement AgeCheckbox = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement EducationTextare = driver.findElement(By.xpath("//textarea[@id='edu']"));
		WebElement JobDropdown = driver.findElement(By.xpath("//select[@id='job1']"));
		WebElement InterestCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
		WebElement SliderView = driver.findElement(By.xpath("//input[@id='slider-1']"));
		WebElement ButtonEnable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		WebElement PasswordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement BiographyTextarea = driver.findElement(By.xpath("//textarea[@id='bio']"));
		WebElement JobSelect = driver.findElement(By.xpath("//select[@id='job2']"));
		WebElement Interestcheckboxdisable = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		WebElement Sliderdisable = driver.findElement(By.xpath("//input[@id='slider-2']"));
		WebElement Buttondisable = driver.findElement(By.xpath("//button[@id='button-disabled']"));
		iscontrolEnable(emailTexbox);
		iscontrolEnable(AgeCheckbox);
		iscontrolEnable(EducationTextare);
		iscontrolEnable(JobDropdown);
		iscontrolEnable(InterestCheckbox);
		iscontrolEnable(SliderView);
		iscontrolEnable(ButtonEnable);
		iscontrolEnable(PasswordTextbox);
		iscontrolEnable(BiographyTextarea);
		iscontrolEnable(JobSelect);
		iscontrolEnable(Interestcheckboxdisable);
		iscontrolEnable(Sliderdisable);
		iscontrolEnable(Buttondisable);	
	}
		
	public void iscontrolEnable(WebElement element) {
		if(element.isEnabled()) {
			System.out.println("Element is enabled");
		}else {
			System.out.println("Element is disabled");
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}