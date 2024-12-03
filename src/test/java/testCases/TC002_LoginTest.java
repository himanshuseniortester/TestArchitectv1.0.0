package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void verify_login() {
		logger.info("****** Starting TC002 Login Test*****");
		try {

			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickmyAccount();
			hp.clickLogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setLoginEmail(p.getProperty("email"));
			lp.setLoginPassword(p.getProperty("password"));
			lp.clickLoginSubmit();

			// MyAccountPage
			MyAccountPage macc = new MyAccountPage(driver);
			boolean target = macc.isMyAccountPageExists();

			Assert.assertTrue(target); // Assert.assertEquals(target, true , "LoginFailed");

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("****** Finished TC002 Login Test*****");
	}

}
