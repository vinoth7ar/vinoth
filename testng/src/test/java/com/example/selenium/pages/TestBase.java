package com.example.selenium.pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.example.selenium.util.PropertyLoader;

public class TestBase {
	protected WebDriver webDriver;
	protected String websiteUrl;
	protected String hubUrl;
	protected String browserName;

	protected static final String BROWSER_CHROME    = "chrome";
	protected static final String BROWSER_FIREFOX   = "firefox";
	protected static final String BROWSER_PHANTOMJS = "phantomjs";

	@BeforeClass
	public void init() throws MalformedURLException {
		websiteUrl = PropertyLoader.loadProperty("site.url");
		hubUrl = PropertyLoader.loadProperty("hub.address");
		browserName = PropertyLoader.loadProperty("browser.name");

		DesiredCapabilities desiredCapabilities;
		switch (browserName) {
			case BROWSER_CHROME: 
				desiredCapabilities = DesiredCapabilities.chrome(); 
				System.setProperty("webdriver.chrome.driver", "/Users/vinothrajarajan/Downloads/chromedriver");
				webDriver = new ChromeDriver();
				break;
			case BROWSER_FIREFOX: 
				desiredCapabilities = DesiredCapabilities.firefox(); 
		    	System.setProperty("webdriver.gecko.driver", "/Users/vinothrajarajan/Downloads/geckodriver");
				webDriver = new FirefoxDriver();
				break;
			case BROWSER_PHANTOMJS: 
				desiredCapabilities = DesiredCapabilities.phantomjs(); 
				break;
			default: 
				throw new IllegalArgumentException("Could not find supported browser: " + browserName);
		}
		desiredCapabilities.setJavascriptEnabled(true);
		desiredCapabilities.setCapability("takesScreenshot", true);
		desiredCapabilities.setBrowserName(browserName);
		//webDriver = new RemoteWebDriver(new URL("http://192.168.59.103:4444/wd/hub"), desiredCapabilities);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
}
