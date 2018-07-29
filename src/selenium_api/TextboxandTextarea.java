package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;	

public class TextboxandTextarea  {
    WebDriver driver;
    
    String name, dob, address, city, state, pin, mobile, email, password, customerID = null, newaddress, newcity;
    
    By NameTextbox = By.xpath("//input[@name='name']");
    By genderTexbox = By.xpath("//input[@name='gender']");
    By dobTextbox = By.xpath("//input[@name='dob']");
    By AddTextarea = By.xpath("//textarea[@name='addr']");
    By citytTextbox = By.xpath("//input[@name='city']");
    By stateTextbox = By.xpath("//input[@name='state']");
    By pinTextbox = By.xpath("//input[@name='pinno']");
    By mobileTextbox = By.xpath("//input[@name='telephoneno']");
    By emailTextbox = By.xpath("//input[@name='emailid']");
    By passwordTextbox = By.xpath("//input[@name='password']");
    

    
    @BeforeClass
    public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// DATA TEST
		name="Tuan Pham";
		dob="1992-02-04";
		address="123 Nguyen Trai";
		city="Ha Noi";
		state="Xuan Dinh";
		pin="324667";
		mobile="0987997999";
		email="Tuan" + randomUniqueNumber() + "@gmail.com";
		password="123456";
		newaddress="356 Nguyen Tuan";
		newcity="Bac Giang";
	}	
    
	@Test
	public void Topic04_Textbox() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145634");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("YgusApy");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		driver.findElement(By.xpath("//a[text()=\"New Customer\"]")).click();
		
		// ENTER DATA and SUBMIT
		driver.findElement(NameTextbox).sendKeys(name);
		driver.findElement(dobTextbox).sendKeys(dob);
		driver.findElement(AddTextarea).sendKeys(address);
		driver.findElement(citytTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileTextbox).sendKeys(mobile);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		// GET Dynamic customer id
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		// Verify
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		
		//
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		// Check 3 fields (Customer name, gender, date of birth): disable
		Assert.assertFalse(driver.findElement(NameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTexbox).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());


		
		// Verify customer name / address equal input data
		Assert.assertEquals(driver.findElement(NameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(AddTextarea).getText(), address);
		
		// edit data and address/city
		driver.findElement(AddTextarea).clear();
		driver.findElement(AddTextarea).sendKeys(newaddress);
		driver.findElement(citytTextbox).clear();
		driver.findElement(citytTextbox).sendKeys(newcity);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		// Verify 2 address/ city when create new 
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),newaddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),newcity);

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomUniqueNumber() {
		Random rand = new Random();
		int number = rand.nextInt(999999) + 1;
		return number;
	}
}