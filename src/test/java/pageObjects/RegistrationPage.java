package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	public void setEmail(String eMail) {
		txtEmail.sendKeys(eMail);
	}

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassWord;

	public void setPassword(String passWord) {
		txtPassWord.sendKeys(passWord);
	}

	@FindBy(name = "agree")
	WebElement chkdPolicy;

	public void enabledCheckedPolicy() {

		chkdPolicy.click();
	}

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnContinue;

	public void clickContinue() {

		btnContinue.click();
	}

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
