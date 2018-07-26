package selenium_api;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test03_Chromedrive  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		//driver = new FirefoxDriver();
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\Tuan\\Downloads\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.get("http://daominhdam.890m.com/");
	}
    
	@Test
	public void TestScript03() {
		WebElement AgeCheckbox = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement InterestCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
		isControlSelected(AgeCheckbox);
		isControlSelected(InterestCheckbox);
	}
	
	public void isControlSelected(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}