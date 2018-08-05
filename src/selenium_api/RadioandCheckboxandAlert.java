package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;	

public class RadioandCheckboxandAlert  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
    

	public void TC_01_Button() {
		driver.get("http://live.guru99.com/");
		// Click to link : My Account under footer
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		// Check URL of page after clicking
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.guru99.com/index.php/customer/account/login/");
		
		clickElementByJavascript("//a[@title='Create an Account']");
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.guru99.com/index.php/customer/account/create/");
	}
	
	public void TC_02_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		String dualZoneCheckbox = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		clickElementByJavascript(dualZoneCheckbox);
		Assert.assertTrue(isElementSelected(dualZoneCheckbox));
		
		//
		
		unCheckTheCheckbox(dualZoneCheckbox);
		Assert.assertFalse(isElementSelected(dualZoneCheckbox));
		
	}
	public void TC_03_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		String PetrolkwRadion = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		clickElementByJavascript(PetrolkwRadion);
		//Assert.assertTrue(isElementSelected(PetrolkwRadion));
		//unCheckTheRadiobutton(PetrolkwRadion);
		Assert.assertTrue(isElementSelected(PetrolkwRadion));
	//	Assert.assertFalse(isElementSelected(PetrolkwRadion));
		
	}
	
	public void TC_04_Alert() {
		driver.get("http://daominhdam.890m.com/#");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert alert = driver.switchTo().alert();		
		// Get text in alert	
		String alertJSMessage = alert.getText();
		Assert.assertTrue(alertJSMessage.equals("I am a JS Alert"));
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
		
	}
	
	public void TC_05_Alert() {
		driver.get("http://daominhdam.890m.com/#");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert alert = driver.switchTo().alert();		
		// Get text in alert	
		String alertJSMessage = alert.getText();
		Assert.assertTrue(alertJSMessage.equals("I am a JS Confirm"));
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");		
	}
	
	@Test
	public void TC_06_Alert() {
		driver.get("http://daominhdam.890m.com/#");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alert = driver.switchTo().alert();		
		// Get text in alert	
		String alertJSMessage = alert.getText();
		Assert.assertTrue(alertJSMessage.equals("I am a JS prompt"));
		alert.sendKeys("tuanvanpham");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: tuanvanpham");		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickElementByJavascript(String Locator) {
		WebElement element = driver.findElement(By.xpath(Locator));
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
	
	public boolean isElementSelected(String Locator) {
		WebElement element = driver.findElement(By.xpath(Locator));
		return element.isSelected();
	}
	
	public void unCheckTheCheckbox(String Locator) {
		if(isElementSelected(Locator)) {
			clickElementByJavascript(Locator);
		}
	}	
	
	public void unCheckTheRadiobutton(String Locator) {
		if(!isElementSelected(Locator)) {
			clickElementByJavascript(Locator);
		}
	}	

}