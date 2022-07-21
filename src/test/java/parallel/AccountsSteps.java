package parallel;


import java.io.IOException;

import org.junit.Assert;

import com.qa.factory.DriverFactory;
import com.qa.factory.DriverFactoryParallel;
import com.qa.pages.AccountsPage;
import com.qa.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsSteps {
	
	LoginPage login = new LoginPage(DriverFactoryParallel.getDriver());
	private AccountsPage accountsPage;
	
	@Given("User has logged into application with data available in {string} and {int}")
	public void user_has_logged_into_application(String sheetName,int RowNo) throws IOException {
		accountsPage = login.Login(sheetName,RowNo);
	}
	
	@Then("Accounts page title should be displayed as {string}" )
	public void accounts_page_title_should_be_displayed_as(String expPageTitle) throws IOException {
		String actPageTitle = accountsPage.getMyAccountPageTitle();
		Assert.assertEquals(actPageTitle, expPageTitle);
	}
}
