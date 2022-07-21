package com.qa.pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtil;

public class LoginPage {
	private WebDriver driver;
	private ConfigReader configReader = new ConfigReader();;
	private Properties prop;
	private ExcelUtil excelUtil;
	String pageTitle;
	String AccountsDatapath = "C:\\Shameer\\Selenium\\CucumberTestNG\\src\\test\\resources\\Datatables\\AccountsPage.xlsx";
	
	@FindBy(xpath = "//*[@class='login']")
	WebElement SignInLink;

	@FindBy(xpath = "//*[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']")
	WebElement Menu;

	@FindBy(xpath = "//input[@id='email']")
	WebElement EmailAddress;

	@FindBy(xpath = "//input[@id='passwd']")
	WebElement Password;

	@FindBy(xpath = "//button[@id='SubmitLogin']")
	WebElement SigninButton;

	@FindBy(xpath = "//span[text()='My account']")
	WebElement MyAccount;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElement(WebElement element) {
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.visibilityOfElementLocated((By) element));
	}

	public void lauchURL() throws IOException {

		prop = configReader.initProperties();
		String url = prop.getProperty("url");
		driver.get(url);
		pageTitle = driver.getTitle();
		if (pageTitle.equals("My Store")) {
			System.out.println("Home page is displayed");
		} else {
			System.out.println("Home page is not displayed");
		}
	}

	public void clickSigninLink() throws IOException {
		SignInLink.click();
	}

	public String verifyLoginPageDisplayed() throws IOException {
		return pageTitle = driver.getTitle();
	}

	public void enterEmailAddress(String EmailAdd) {
		EmailAddress.sendKeys(EmailAdd);
	}

	public void enterPassword(String password) {
		Password.sendKeys(password);
	}

	public void clickSigninButton() throws IOException {
		SigninButton.click();
	}
	
	public AccountsPage Login(String SheetName,int RowNo) throws IOException {
		excelUtil = new ExcelUtil();
		excelUtil.excelReader(AccountsDatapath, SheetName);
		String username  = excelUtil.getCellValue("Username", RowNo);
		String password  = excelUtil.getCellValue("Password", RowNo);
		EmailAddress.sendKeys(username);
		Password.sendKeys(password);
		SigninButton.click();
		return new AccountsPage(driver);
	}
	
	public String verifyMyAccountPageDisplayed() throws IOException {
		pageTitle = driver.getTitle();
		return pageTitle;
	}
}
