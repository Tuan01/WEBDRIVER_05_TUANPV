package selenium_api;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
    
    String forderName = "autoonline" + randomNumber();
    
    String email = "autoonline" + randomNumber() + "@gmail.com";
    
    String name = "Automation Tuan";
    		
    @BeforeClass
    public void beforeClass() {
    	
    	//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
    	//driver = new ChromeDriver();
    	
		driver = new FirefoxDriver();
    	
    	//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
    	//driver = new InternetExplorerDriver();
    	
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
	
	public void TC_02_AutoIT() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadElement = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadElement.click();
		Thread.sleep(3000);
		
		Runtime.getRuntime().exec(new String[] { chromeUpload , uploadFilePath });
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + fileName + "']")).isDisplayed());
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName + "']")).isDisplayed());
		
	}
	
	public void TC_03_Robot() throws InterruptedException, Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");	
		
		StringSelection select = new StringSelection(uploadFilePath);

		//Copy to clip board
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		WebElement uploadElement = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadElement.click();
		Thread.sleep(3000);

		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + fileName + "']")).isDisplayed());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName + "']")).isDisplayed());
	}
	
	@Test
	public void TC_04_UploadFileChecker() throws Exception {
		driver.get("https://encodable.com/uploaddemo/");	
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePath);
		
		WebElement uploadDropdown = driver.findElement(By.xpath("//select[@name='subdir1']"));
		Select select = new Select(uploadDropdown);
		select.selectByVisibleText("/uploaddemo/files/");
		
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(forderName);
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);
		
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
			
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dd[text()='Email Address: " + email + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dd[text()='First Name: " + name + "']")).isDisplayed());
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dt[contains(text(),'File 1 of 1:')]//a[text()='" + fileName + "']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		driver.findElement(By.xpath("//a[text()='" + forderName + "']")).click();
		//Thread.sleep(4000);
		//Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName + "']")).isDisplayed());		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
}