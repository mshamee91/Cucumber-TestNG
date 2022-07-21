package parallel;

import java.io.IOException;

import org.junit.Assert;

import com.qa.factory.DriverFactory;
import com.qa.factory.DriverFactoryParallel;
import com.qa.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	LoginPage login = new LoginPage(DriverFactoryParallel.getDriver());

	@Given("User launches url")
	public void user_launches_url() throws IOException {
		login.lauchURL();
	}

	@Given("Clicks on Sign in link")
	public void clicks_on_sign_in_link() throws IOException {
		login.clickSigninLink();
	}

	@Then("Login page is displayed")
	public void login_page_is_displayed() throws IOException {
		String pageTitle = login.verifyLoginPageDisplayed();
		if (pageTitle.equals("Login - My Store")) {
			System.out.println("Login page is displayed");
		} else {
			System.out.println("Login page is not displayed");
		}
	}

	@When("user enters correct {string} and correct {string}")
	public void user_enters_correct_and_correct(String EmailAddress, String Password) throws IOException {
		login.enterEmailAddress(EmailAddress);
		login.enterPassword(Password);
	}

	@When("clicks on Signin button")
	public void clicks_on_Signin_button() throws IOException {
		login.clickSigninButton();
	}

	@Then("MyAccount page is displayed as {string}")
	public void my_account_page_is_displayed_as(String expPageTitle) throws IOException {
		String pageTitle = login.verifyMyAccountPageDisplayed();
		Assert.assertEquals(pageTitle, expPageTitle);
	}

}
