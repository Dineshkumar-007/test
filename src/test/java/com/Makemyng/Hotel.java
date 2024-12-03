package com.Makemyng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Hotel {


	static WebDriver driver;
	@BeforeSuite
	public void beforeSuit() {
		System.out.println("before suit");
	}
	@BeforeSuite
	public void AftereSuit() {
		System.out.println("After suit");
	}
	@AfterTest
	public void After() {
		System.out.println("After suit");
	}
	@BeforeTest
	public void before() {
		System.out.println("Before suit");
	}


	@DataProvider (name ="pass")
	public 	Object[][] str() {
		return new Object[][] {{"kerala"} };
		
	}
	@BeforeClass
	public static void browserLaunch() throws InterruptedException {

		WebDriverManager.edgedriver().setup();
//		}
//		catch(Exception e) {
		EdgeOptions option = new EdgeOptions();
		option.addArguments("start-maximized");
		driver = new EdgeDriver(option);
		driver.get("https://www.makemytrip.com/hotels/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		WebElement adclose = driver.findElement(By.xpath("//span[@class=\"commonModal__close\"]"));   //span[@class="commonModal__close"]
		adclose.click();
		
//	}
}
	@Parameters ({"para"})
	@Test(priority =1 )
 public void hotel(@Optional String d)  {
	//	Thread.sleep(4000);
//		WebElement adclose = driver.findElement(By.xpath("//span[@class=\"commonModal__close\"]"));   //span[@class="commonModal__close"]
//		adclose.click();
		WebElement findElement = driver.findElement(By.xpath("//input[@id=\"city\"]"));
		findElement.click();
	 WebElement f1 = driver.findElement(By.xpath("//span[@class='chNavIcon appendBottom2 chSprite chHotels active']"));
	 f1.click();
//	
		 WebElement f2 = driver.findElement(By.xpath("//input[@placeholder=\"Where do you want to stay?\"]"));
	 f2.sendKeys(d);
	 
	 WebElement f3 = driver.findElement(By.xpath("//b[text()=\"Goa\"]/preceding::p[contains(text(),\"City in India\")]"));
	 f3.click();
//		js.executeScript("arguments[0].click()", applybutton);

	 
 }
	@Test(priority =2, groups="Taken")
	public void checkinDate() {
		DateFormat dateformate = new SimpleDateFormat("dd");
		Date date =new Date(17);
		String today =dateformate.format(date);
		System.out.println(today);
 }
	@Test(priority =3)
	public void checkoutDate() {
		DateFormat dateformate = new SimpleDateFormat("dd");
		Date date =new Date();
		String today =dateformate.format(date);
		System.out.println(today);
}
	@Test(priority =3)
	public void room_Guest() {
		 WebElement f1 = driver .findElement(By.xpath("//span[text()='Rooms & Guests']"));
		 f1.click();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		 WebElement f2 = driver .findElement(By.xpath("//div[@class=\"rmsGst__row\"]"));
//		 f2.click();
		 WebElement f3 = driver .findElement(By.xpath("//button[@data-cy=\\\"RoomsGuestsNew_327\\\"]"));
		 f3.click();
	}		
	@Test (priority=4)
	public void price() {
		 WebElement f1 = driver .findElement(By.xpath("//span[text()=\"Price per Night\"]"));                                              
		 f1.click();
		 WebElement f2 = driver .findElement(By.xpath("//ul[@class=\"ppn__list\"]//following-sibling::li[text()=\"₹1500-₹2500\"]"));
		 f2.click();
		
	}	
	@Test (priority=5 ,dependsOnMethods ="price")
	public void search() {
	 WebElement f3 = driver .findElement(By.xpath("//button[@id=\"hsw_search_button\"]"));
	 f3.click();
}
	@Test (priority=6)
	public void validate() throws InterruptedException {
	String Expected1 = "Goa";
	WebElement element1 = driver.findElement(By.xpath("//input[@value=\"Goa\"]"));
	String Actual1 = element1.getAttribute("value");
	SoftAssert s1=new SoftAssert();
	s1.assertEquals(Expected1, Actual1);
	
	String Expected2 = "Thu, 15 Aug 2024";
	WebElement element2 = driver.findElement(By.xpath("//input[@value=\"Thu, 15 Aug 2024\"]"));
	String Actual2 = element2.getAttribute("value");
	SoftAssert s2=new SoftAssert();
	s2.assertEquals(Expected2, Actual2);
	
	String Expected3 = "Fri, 16 Aug 2024";
	WebElement element3 = driver.findElement(By.xpath("//input[@value=\"Fri, 16 Aug 2024\"]"));
	String Actual3 = element3.getAttribute("value");
	SoftAssert s3=new SoftAssert();
	s3.assertEquals(Expected3, Actual3);
	//label[text()='Rooms & Guests']
	
	String Expected4 = "Fri, 16 Aug 2024";
	WebElement element4 = driver.findElement(By.xpath("//label[text()='Rooms & Guests']"));
	String Actual4 = element4.getAttribute("value");
	SoftAssert s4=new SoftAssert();
	s4.assertEquals(Expected4, Actual4);
	System.out.println(Actual4);
	List<WebElement> element9 = driver.findElements(By.xpath("//p[@id=\"hlistpg_hotel_name\"]"));
	
	for(int i=0;i<element9.size();i++) {
		WebElement webElement = element9.get(i);
	String text = webElement.getText();
	System.out.println(text);
	}

}
	
	
}