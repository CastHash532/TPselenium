package com.esi;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.List;

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
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestAmazon {

	static WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();
	FirefoxOptions firefoxOptions = new FirefoxOptions();
	EdgeOptions edgeOptions = new EdgeOptions();
	
	@Before
	public void setUpBeforeClass() throws Exception {

		URL url = new URL("http://localhost:4444");
		driver = new RemoteWebDriver(url, edgeOptions);
	}

	

	@Test
	public void test() {
		
	    driver.get("https://www.amazon.com");
	    WebElement inputSearch = driver.findElement(By.id("twotabsearchtextbox"));
	    inputSearch.sendKeys("google pixel 3");
	    inputSearch.submit();
	   // List<WebElement> elements =  (new WebDriverWait(driver, 10)).
	    // until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".a-link-normal.a-text-normal")));
		List<WebElement> elements =  driver.findElements(By.cssSelector(".a-link-normal.a-text-normal"));
		for(WebElement element:elements) {
			assertTrue(element.isDisplayed());
		}

	}
	

	
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		driver.quit();
	   
		
	}


}