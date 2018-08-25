package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_UploadFile  {
    WebDriver driver;
    //String uploadfilePath = "D:\Online_Class_05\WEBDRIVER_05_TUANPV\images\IMG_2040.JPG";
    String projectDirectory = System.getProperty("user.dir");
    String fileName = "IMG_2040.JPG";
    String uploadFilePath = projectDirectory + "\\images\\" + fileName;
    
    String chromeUpload = projectDirectory + "\\upload\\chrome.exe";
    String firefoxUpload = projectDirectory + "\\upload\\firefox.exe";
    String ieUpload = projectDirectory + "\\upload\\ie.exe";
    		
    @BeforeClass
    public void beforeClass() {
    	
    	System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    	driver = new ChromeDriver();
    	
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC_01_SendkeyAPI() {
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		
		uploadElement.sendKeys(uploadFilePath);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + fileName + "']")).isDisplayed());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName + "']")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_02_AutoIT() {
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
		
}