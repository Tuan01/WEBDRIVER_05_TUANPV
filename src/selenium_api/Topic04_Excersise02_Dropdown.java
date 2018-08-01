package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic04_Excersise02_Dropdown  {
    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeClass
    public void beforeClass() {
		//driver = new FirefoxDriver();
    	System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");
    	driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
	}	
    
    
    
	@Test
	public void Dropdownlist() {
		// Jquery
		//driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//selectCustomDropdownList("//span[@id='speed-button']", "//ul[@id='speed-menu']/li[@class='ui-menu-item']/div", "Faster");
		//Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text' and text()='Faster']")).isDisplayed());
		//selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "19");
		//Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		
		//Angular
		//driver.get("https://material.angular.io/components/select/examples");
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//selectCustomDropdownList("//mat-select[@placeholder='Favorite food']", "//mat-option/span","Tacos");
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Tacos']")).isDisplayed());
		
		//selectCustomDropdownList("//mat-select[@placeholder='State']", "//mat-option/span","Washington");
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Washington']")).isDisplayed());
		
		// Kendo
		
		//driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//selectCustomDropdownList("//span[@aria-labelledby='color_label']","//ul[@id='color_listbox']/li","Grey");
		//Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-labelledby='color_label']//span[@class='k-input' and text()='Grey']/parent::span")).isDisplayed());
		
		//mikerodham
		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		selectCustomDropdownList("//li[@class='dropdown-toggle']","//div[@class='btn-group']/ul/li","Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	
		
	}
	
	public void selectCustomDropdownList(String dropdown, String listItems, String valueItem) {
		WebElement dropdownList = driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",dropdownList);
		dropdownList.click();
		
		List<WebElement> allItems = driver.findElements(By.xpath(listItems));
		wait.until(ExpectedConditions.visibilityOfAllElements(allItems));
		for(WebElement item : allItems) {
			if(item.getText().trim().equals(valueItem)) {
				// Scroll to item
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",item);
				item.click();
				break;
			}
		}
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}