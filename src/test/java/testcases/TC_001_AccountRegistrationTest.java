package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	

	@Test(groups= {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("starting verify_account_registration");
		try
		{
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked on myaccount");
		
		hp.clickRegister();
		logger.info("clicked on register");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("provide customer details");
		regpage.setFirstName(randomstring().toUpperCase());
		regpage.setLastName(randomstring().toUpperCase());
		regpage.setEmail(randomstring()+"@gmail.com"); // randomly generated the email
		regpage.setTelephone(randomnumber());
		
		String password=randomalphanumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password); 
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("validating expected message");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		
		// Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		logger.info("Finished verify_account_registration");
	}
	   

}
