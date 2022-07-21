package com.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class AccountsPage {
	
	private WebDriver driver;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getMyAccountPageTitle() throws IOException {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
}
