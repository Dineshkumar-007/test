package com.Makemyng;


	import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
	import java.time.LocalDate;
	import java.time.LocalDateTime;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
	
	public class Flight1 {
		
	static WebDriver driver;
	
	@DataProvider (name ="alter")
	public 	Object[][] str() {
		return new Object[][] {{"Chennai"} };
		
	}
	
	@DataProvider (name ="alter1")
	public 	Object[][] str1() {
		return new Object[][] {{"Dubai"} };
	}
	@DataProvider (name ="dateEntry")
	public 	Object[][] st1() {
		return new Object[][] {{"Wed,Aug 14,2024"} };
	}

	@BeforeClass
		public void browserLaunch() {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option =new ChromeOptions();
			option.addArguments("--start-maximized");
			option.addArguments("--disable-popups");
			option.addArguments("--disable-notifications");
			
			driver = new ChromeDriver(option);
			driver.get("https://www.makemytrip.com");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			WebElement adClose = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
			adClose.click();
		}
		@AfterClass
		public void closeApp() {
			System.out.println("Completed");
		}
		@BeforeMethod
		public void beforeMethod() {
			System.out.println("Before Method");
		}
		@AfterMethod
		public void afterMethod() {
			System.out.println("After Method");
		}
		@Test(priority = 2 , dataProvider="alter")
		public void enterFromLocation(String d) {
			WebElement fromCity = driver.findElement(By.id("fromCity"));
			fromCity.click();
			WebElement enterFromCity = driver.findElement(By.xpath("//input[@placeholder='From']"));
			enterFromCity.sendKeys(d);
			WebElement resultValue = driver.findElement(By.xpath("//span[text()='"+d+"']"));
			resultValue.click();
			
		}
		@Test(priority = 3 ,dataProvider="alter1")
		public void enterToLocation(String d) {
			WebElement toCity = driver.findElement(By.id("toCity"));
			toCity.click();
			WebElement enterToCity = driver.findElement(By.xpath("//input[@placeholder='To']"));
			enterToCity.sendKeys(d);
			try {
				WebElement resultValue = driver.findElement(By.xpath("//span[text()='"+d+"']"));
				resultValue.click();
			}
			catch(Exception e){
				WebElement resultValue = driver.findElement(By.xpath("//span[text()='Bengaluru']"));
				resultValue.click();
			}
		}
		@Test(priority = 4)
		public void enterDepartureDate() {
			DateFormat dateformate = new SimpleDateFormat("dd");
			Date date =new Date();
			String today =dateformate.format(date);
			System.out.println(today);
//			WebElement departureDate = driver.findElement(By.xpath("//div[text()='August 2024']/parent::div/following-sibling::div//p[text()='14']"));
//			departureDate.click();
		}
		@Test(priority = 5)
		public void travellersAndClass() {
			WebElement travelandClass = driver.findElement(By.xpath("//span[text()='Travellers & Class']"));
			travelandClass.click();
			WebElement persons = driver.findElement(By.xpath("//p[text()='ADULTS (12y +)']/following-sibling::ul/li[text()='3']"));
			persons.click();
			try {
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			WebElement applybutton = driver.findElement(By.xpath("//button[@data-cy=\"travellerApplyBtn\"]"));
			JavascriptExecutor js =(JavascriptExecutor) driver;
			
			js.executeScript("arguments[0].click()", applybutton);
			
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		@Test(priority = 6)
		public void searchForFlights(){
			try {
			WebElement searchButton = driver.findElement(By.xpath("//p[@data-cy=\"submit\"]"));  
			searchButton.click();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		@Test(priority = 7)
		public void validateTheResult() {
			try {
			WebElement adClose2 = driver.findElement(By.xpath("//span[@class='bgProperties  overlayCrossIcon icon20']"));
			adClose2.click();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			String Expected1 = "One Way";
			WebElement element1 = driver.findElement(By.xpath("//div[text()='One Way']"));
			String Actual1 = element1.getText();
			SoftAssert s=new SoftAssert();
			s.assertEquals(Expected1, Actual1);
//			Assert.assertEquals(Expected1, Actual1);
			
			
			
			String Expected2 = "Chennai, India";
			WebElement element2 = driver.findElement(By.xpath("//input[@id='fromCity']"));
			String Actual2 = element2.getAttribute("value");
			Assert.assertEquals(Expected2, Actual2);
			
			String Expected3 = "Bengaluru, India";
			WebElement element3 = driver.findElement(By.xpath("//input[@id='toCity']"));
			String Actual3 = element3.getAttribute("value");
			SoftAssert s1=new SoftAssert();
			s1.assertEquals(Expected3, Actual3);
		//	Assert.assertEquals(Expected3, Actual3);
			
			String Expected4 = "Wed, Aug 14, 2024";
			WebElement element4 = driver.findElement(By.xpath("//input[@id='departure']"));
			String Actual4 = element4.getAttribute("value");
			Assert.assertEquals(Expected4, Actual4);
			
//			String Expected5 ="";
//			WebElement element5 = driver.findElement(By.xpath("//div[@class=\"hsw v2\"]"));
//			String  Actual15 =element5.getText();
//			Assert.assertEquals(Expected5, Actual15);
//			
			
			
			
		}

	}


	
	
	
	//<suite name="Suite">
//	  <test thread-count="5" name="Test">
//	  <parameter name ="para" value="kerla"/>
//	  <groups>
//	  <run>
//	  <include name="Taken"></include>
//	  </run>
//	  </groups>
//	  
//	    <classes>
//	      <class name="com.Makemyng.Hotel"/>
//	     
//	    </classes>
//	  </test> <!-- Test -->
//	</suite> <!-- Suite -->
//

