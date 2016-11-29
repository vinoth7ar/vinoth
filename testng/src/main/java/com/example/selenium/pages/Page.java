package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {

	protected WebDriver webDriver;

	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

	public Boolean hasText(String text) {
		return this.webDriver.getPageSource().contains(text);
	}

}
