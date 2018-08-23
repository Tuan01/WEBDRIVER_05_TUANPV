package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic08_JavaScript  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
    
	public void TC_01_JavaScriptExecutor() {
		OpenAnyUrlByJS("http://live.guru99.com/");
		
		String homePageDomain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.guru99.com");
		
		String homePageUrL = (String) executeJSForBrowserElement("return document.URL;");
		Assert.assertEquals(homePageUrL, "http://live.guru99.com/");
		
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		clicktToElementByJS(mobileLink);
		
		
		WebElement samsungProduct = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		clicktToElementByJS(samsungProduct);
		
		String samsungAddedMsg = (String) executeJSForBrowserElement("return document.documentElement.innerText;");
		Assert.assertTrue(samsungAddedMsg.contains("Samsung Galaxy was added to your shopping cart."));
		
		WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		clicktToElementByJS(privacyLink);
		
		String privacyPageTitle = (String) executeJSForBrowserElement("return document.title;");
		Assert.assertEquals(privacyPageTitle, "Privacy Policy");
		
		scrollToBottomPage();
		
		WebElement wishlistTableContent =  driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(wishlistTableContent.isDisplayed());
		
		OpenAnyUrlByJS("http://demo.guru99.com/v4/");
		
		String demoPageDomain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals(demoPageDomain, "demo.guru99.com");
		
	}
	
	@Test
	public void TC_02_RemoveAttribute() {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		String firstName = "Automation", lastName = "Testing";
		
		WebElement resultIframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(resultIframe);
		
		driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(firstName);
		
		WebElement lastnameTextbox = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAnyAttributeInDOM(lastnameTextbox, "disabled");
		
		driver.findElement(By.xpath("//input[@name='lname']")).sendKeys(lastName);
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String messageSuccessful = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
		Assert.assertTrue(messageSuccessful.contains(firstName) && messageSuccessful.contains(lastName));
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public Object OpenAnyUrlByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}
	public Object highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("arguments[0].style.border='6px groove red'", element);
	}
	
	
	public Object executeJSForBrowserElement(String javasript) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
         return js.executeScript(javasript);
	}
	
	public Object clicktToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("arguments[0].click();", element);
	}
	
	public Object removeAnyAttributeInDOM(WebElement element, String attributeName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
	}
	
	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
		
}
