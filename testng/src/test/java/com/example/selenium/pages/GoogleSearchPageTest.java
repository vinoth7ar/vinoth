package com.example.selenium.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleSearchPageTest extends TestBase {

	GoogleSearchPage homepage;
	
	@BeforeClass
	public void testInit() {
		webDriver.get(websiteUrl);
		homepage = PageFactory.initElements(webDriver, GoogleSearchPage.class);
	}

	@Test
	public void testLogoExisting() throws InterruptedException {
		Assert.assertTrue(homepage.getLogoTitle() != null);
	}

	@Test
	public void testSearch() throws InterruptedException {
		String searchText = "Hello world!";

		homepage.search(searchText);

		Assert.assertTrue(homepage.hasText(searchText));
	}
}
