package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	public void clickmyAccount(){
		lnkMyAccount.click();
		
	}
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Login']")
	WebElement lnkLogin;
	
	public void clickLogin() {
		lnkLogin.click();
	}

}
