package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;	

public class UserInteractions  {
    WebDriver driver;
    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
    
	public void TC_01_UserInteraction() {
		driver.get("http://daominhdam.890m.com/");
		WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		Actions action = new Actions(driver);
		// Hover Mouse
		action.moveToElement(hoverText).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']/div[@class='tooltip-inner']")).getText(), "Hooray!");
	}
	
	public void TC_02_UserInteraction() {
		driver.get("https://www.myntra.com/");
		WebElement hoverAccount = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		Actions action = new Actions(driver);
		// Hover Mouse
		action.moveToElement(hoverAccount).perform();
		
		driver.findElement(By.xpath("//a[@data-track='login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='mountRoot']//div[@class='login-box']")).isDisplayed());
	}
	

	public void TC_03_UserInteraction() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> selectable = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		Actions action = new Actions(driver);
		//action.clickAndHold(selectable.get(0)).moveToElement(selectable.get(3)).release().perform();
		//List<WebElement> select able selected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		//Assert.assertEquals(selectableselected.size(),4);
		action.keyDown(Keys.CONTROL).build().perform();
		selectable.get(0).click();
		selectable.get(2).click();
		selectable.get(4).click();
		selectable.get(6).click();
		action.keyUp(Keys.CONTROL).build().perform();
		
		List<WebElement> selectableselected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selectableselected.size(),4);
	}
	
	public void TC_04_UserInteraction() {
		driver.get("http://www.seleniumlearn.com/double-click");
		 WebElement doubleclickText = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		 // User interactions
		 Actions action = new Actions(driver);
		 // Double click 
		 action.doubleClick(doubleclickText).perform();
		 // Show alert 
		 Alert alert = driver.switchTo().alert();
		 // Verify text in alert
		 Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		 alert.accept();
		 
	}
	
	public void TC_05_UserInteraction() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement rightclickText = driver.findElement(By.xpath("//span[text()='right click me']"));
		// Action
		Actions action = new Actions(driver);
		// Right click to Element
		action.contextClick(rightclickText).perform();
		
		WebElement quitBefore = driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit')]"));
		// Hover to quit
		
		action.moveToElement(quitBefore).perform();
		// Verify hover success
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') and contains(@class, 'context-menu-hover') and contains(@class, 'context-menu-visible')]")).isDisplayed());
		
		// Click Quit
		
		action.click(quitBefore).perform();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"clicked: quit");
		alert.accept();
		 
	}
	
	@Test
	public void TC_06_UserInteraction() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Assert.assertEquals(targetElement.getText(), "Drag the small circle here.");
		
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();
		Assert.assertEquals(targetElement.getText(), "You did great!");
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}