package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleSearchPage extends Page {

	private final String LOGO_ID = "hplogo";
	private final String SEARCH_TEXTBOX_NAME = "q";
	private final String SEARCH_SUBMIT_NAME = "btnG";

	@FindBy(how = How.ID, using = LOGO_ID)
	@CacheLookup
	private WebElement logoElement;

	@FindBy(how = How.NAME, using = SEARCH_TEXTBOX_NAME)
	@CacheLookup
	private WebElement searchTextboxElement;

	@FindBy(how = How.NAME, using = SEARCH_SUBMIT_NAME)
	@CacheLookup
	private WebElement searchSubmitElement;

	public GoogleSearchPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public String getLogoTitle() {
		return logoElement.getAttribute("title");
	}

	public void search(String searchText) {
		searchTextboxElement.sendKeys(searchText);
		searchSubmitElement.click();
	}
}
