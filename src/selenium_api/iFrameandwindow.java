package selenium_api;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class iFrameandwindow  {
    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
    
	
	public void TC_01_iFrame() {
		driver.get("https://www.hdfcbank.com/");
		// Issue 01: Check element is displayed//
		// Step 02
		List <WebElement> notificattionIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if(notificattionIframe.size() > 0) {
			driver.switchTo().frame(notificattionIframe.get(0));
			WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
			// Java script click to element //
			JavascriptExecutor Je = (JavascriptExecutor) driver;
			Je.executeScript("arguments[0].click();", closeIcon);
			System.out.println("Closed popup!");
			
			// Switch to top windows
			driver.switchTo().defaultContent();
		}
		
		// Step 03 - Verify text is displayed.
		WebElement lookingforIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingforIframe);
		// Check text displayed
		
		String lookingForText = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
		Assert.assertEquals(lookingForText, "What are you looking for?");
		
		// Switch default contents
		
		driver.switchTo().defaultContent();
		
		// Step 04 - switch to banner image
		WebElement bannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(bannerIframe);
		
		// verify banner image
		By bannerImagesXpath = By.xpath("//div[@id='bannercontainer']//img");
		
		List <WebElement> bannerImages = driver.findElements(bannerImagesXpath);
		int bannerImageNumber = bannerImages.size();
		
		// Check image = 6
		
		Assert.assertEquals(bannerImageNumber, 6);
		
		// Check all image are displayed
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImagesXpath));
		
		// Switch default contents
		
		driver.switchTo().defaultContent();
		
		// Step 08
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
		
		List <WebElement> flipBannerImage = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		int FlipBannerImageNumber = flipBannerImage.size();
		Assert.assertEquals(FlipBannerImageNumber, 8);
		int i = 0;
		
		for(WebElement image: flipBannerImage ) {
			Assert.assertTrue(image.isDisplayed());
			i++; 
			System.out.println("Image [" + i + "] displayed!");
		}		
		
	}
	
	public void TC_02_Windown() {
		driver.get("http://daominhdam.890m.com/");
		
		/*CASE 01 - 2 windows / 2 tab: switch via GUID*/
		// Get GUID of current page (parent page)
		String parentGUID = driver.getWindowHandle();
		
		// click to window
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		//  Switch to New tab or new window
		//switchToChildWindowByGUID(parentGUID);
		switchToWindowByTitle("Google");
		
		// Verify 
		String googleTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "Google");
		
		closeAllWithoutParentWindows(parentGUID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		/*CASE 02 - 2 windows / 2 tab: switch via GUID*/
	}
	
	@Test
	public void TC_03_Windown() {
		driver.get("http://www.hdfcbank.com/");
		String parentGUID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentGUID);
		
		// Step 01
		overideGlobalWait(10);
		List <WebElement> notificattionIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if(notificattionIframe.size() > 0) {
			driver.switchTo().frame(notificattionIframe.get(0));
			WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
			JavascriptExecutor Je = (JavascriptExecutor) driver;
			Je.executeScript("arguments[0].click();", closeIcon);
			System.out.println("Closed popup!");
			driver.switchTo().defaultContent();
		}
		
		overideGlobalWait(30);
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		// Click Account detail
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		// Switch to footer frame
		
		WebElement footerFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(footerFrame);
		
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		
		// Click CSR
		
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		closeAllWithoutParentWindows(parentGUID);
		
		Assert.assertEquals(driver.getTitle(), "HDFC Bank: Personal Banking Services");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void switchToChildWindowByGUID(String parentID) {
		// Get all current window	
        Set <String> allWindows = driver.getWindowHandles();
        // 
        for (String runWindow : allWindows) {
                    if (!runWindow.equals(parentID)) {
                                driver.switchTo().window(runWindow);
                                break;
                    }
        }
	}
	
	public void switchToWindowByTitle(String expectedTitle) {
		 
         Set <String> allWindows = driver.getWindowHandles();
         for (String runWindows : allWindows) {
                     driver.switchTo().window(runWindows);
                     String currentWin = driver.getTitle();
                     if (currentWin.equals(expectedTitle)) {
                                 break;
                     }
         }
	 }
	 
	public boolean closeAllWithoutParentWindows(String parentGUID) {
         Set<String> allWindows = driver.getWindowHandles();
         for (String runWindows : allWindows) {
                     if (!runWindows.equals(parentGUID)) {
                                 driver.switchTo().window(runWindows);
                                 driver.close();
                     }
         }
         driver.switchTo().window(parentGUID);
         if (driver.getWindowHandles().size() == 1)
                    return true;
         else
                    return false;
	 }

	public void overideGlobalWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
}