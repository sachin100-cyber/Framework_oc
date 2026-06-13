package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	@Test(groups= {"Sanity","Master"})
	public void verify_LoginTest()
	{
		logger.info("Starting LoginTest");
		try
		{
		// HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginbtn();
        // LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("Email"));
		lp.setPassword(p.getProperty("Password"));
		lp.clickLogin();
		
		// MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetpage, true, "Test Failed");
		Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Finished LoginTest");
	}

}
