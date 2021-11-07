package com.esi;

import static org.junit.Assert.*;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestAmazon {

	static WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();
	FirefoxOptions firefoxOptions = new FirefoxOptions();
	EdgeOptions edgeOptions = new EdgeOptions();
	
	@Before
	public void setUpBeforeClass() throws Exception {

		URL url = new URL("http://localhost:4444");
		driver = new RemoteWebDriver(url, chromeOptions);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	

	@Test
	public void test() {
		
	    driver.get("https://www.ebay.com");

		// Search task
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    WebElement inputSearch = driver.findElement(By.id("gh-ac"));
		Select categories = new Select(driver.findElement(By.id("gh-cat")));
		inputSearch.sendKeys("python in easy steps");
		categories.selectByVisibleText("Books");
	    inputSearch.submit();

		//book selection
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement result = driver.findElement(By.cssSelector("#srp-river-results > ul > li:nth-child(2) > div > div.s-item__info.clearfix > a"));
		String mainWindow = driver.getWindowHandle();
		result.click();

		//switch window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String secondWindows = (String) driver.getWindowHandles().toArray()[1];
		driver.switchTo().window(secondWindows);
		
		//add to cart
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement addToCart = driver.findElement(By.cssSelector("#atcRedesignId_btn"));
		addToCart.click();

		// Handle extra pop ups 
		// ##Captcha problem


		//assertions
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = driver.getCurrentUrl();
		WebElement price = driver.findElement(By.cssSelector("#mainContent > div > div.right-section > div > div.table > div:nth-child(4) > div.val-col.total-row > span > span > span"));
		WebElement icon = driver.findElement(By.cssSelector("#gh-cart-n"));

		assertEquals("https://cart.payments.ebay.com/", url);
		assertEquals("US $12.14", price.getText());
		assertEquals(1, icon.getText());

	}
	

	
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		driver.quit();
	   
		
	}

	
}