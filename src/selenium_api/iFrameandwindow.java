package selenium_api;
import java.util.List;
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
    
	@Test
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}