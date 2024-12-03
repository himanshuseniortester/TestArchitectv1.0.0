package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	// Test method using the DataProvider
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven") // Fetching data from the DataProviders
																				// class
	public void verify_loginDDT(String email, String password, String expresult) {
		logger.info("*****TC003 Start the  test case");
		// HomePage actions
		try {
			HomePage hp = new HomePage(driver);
			hp.clickmyAccount();
			hp.clickLogin();

			// LoginPage actions
			LoginPage lp = new LoginPage(driver);
			lp.setLoginEmail(email); // Pass email from DataProvider
			lp.setLoginPassword(password); // Pass password from DataProvider
			lp.clickLoginSubmit();

			// MyAccountPage validation
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists(); // Check if My Account Page is displayed

			/*
			 * Data is valid -- login Success -- test pass -- Logout login Failed - test
			 * failed
			 */
			if (expresult.equalsIgnoreCase("Valid")) 
			{
				if (targetPage == true) 
				{
					scrollDownPage();
					macc.clickLogout();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}
			}
			/*
			 * Data is Invalid -- login UnSuccessful -- test pass -- logout login successful
			 * - test failed
			 */
			if (expresult.equalsIgnoreCase("Invalid")) 
			{
				if (targetPage == true)
				{
					scrollDownPage();
					macc.clickLogout();
					Assert.assertTrue(false);
				}else
			 {
				
				Assert.assertTrue(true);
			}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*****TC003 Finished the  test case");
	}
}
