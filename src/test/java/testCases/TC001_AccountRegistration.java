package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {

	@Test(groups = {"Reggresion","Master"})
	public void verifyAccountRegistration() throws InterruptedException {
		try {
		logger.info("****Starting T001 Account Registration*****");
		
		HomePage hp = new HomePage(driver);
		
		hp.clickmyAccount();
		logger.info("****click my  account*****");
		

		hp.clickRegister();
		logger.info("****click on Register*****");
		RegistrationPage regPage = new RegistrationPage(driver);
		logger.info("****set First Name*****");


		regPage.setFirstName(randomeString().toUpperCase());
		logger.info("****set Last Name*****");


		regPage.setLastName(randomeString().toUpperCase());
		logger.info("****Set Email*****");


		regPage.setEmail(randomeString() + "@gmail.com");
		logger.info("****Set Password*****");


		regPage.setPassword(randomeString());
		
		scrollDownPage();
		logger.info("****Enabled Policy*****");

		regPage.enabledCheckedPolicy();
		logger.info("****Click on Continue*****");

		regPage.clickContinue();

		String confmsg = regPage.getConfirmationMsg();
		logger.info("****Account is created*****");

		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		}
		catch(Exception e)
		{
			logger.error("Test Failed..");
			logger.debug("Debug Logs..");
			Assert.fail();
		}
		logger.info("****Finished T001 Account Registration*****");
	}

}
