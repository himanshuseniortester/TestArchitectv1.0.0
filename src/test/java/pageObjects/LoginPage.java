package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtLoginEmail;

	public void setLoginEmail(String email) {
		txtLoginEmail.sendKeys(email);

	}

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtLoginPassword;

	public void setLoginPassword(String password) {
		txtLoginPassword.sendKeys(password);

	}

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnLoginSubmit;

	public void clickLoginSubmit() {
		btnLoginSubmit.click();
	}

}
