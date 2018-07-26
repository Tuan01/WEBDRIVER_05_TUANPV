package selenium_api;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test01_Chormedrive  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		//driver = new FirefoxDriver();
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\Tuan\\Downloads\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.get("http://daominhdam.890m.com/");
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