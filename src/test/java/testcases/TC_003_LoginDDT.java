package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilites.DataProviders;

// Data is valid---login success---test passed----logout
// Data is invalid--- login failed---test failed

//Data is invalid--login success--test fail---logout
//Data is invalid--login failed--test pass

public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") // getting data provider from different class
	public void verify_DDT(String email, String pwd, String exp)
	{
		logger.info("Started verifying DDT");
		
		try
		{
		// HomePage
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLoginbtn();
		        // LoginPage
				LoginPage lp = new LoginPage(driver);
				lp.setEmailAddress(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				// MyAccount
				MyAccountPage macc = new MyAccountPage(driver);
				boolean targetpage=macc.isMyAccountPageExists();
				
				if(exp.equalsIgnoreCase("Valid"))
				{
					if(targetpage==true)
					{
						macc.clicLogout();
                        Assert.assertTrue(true);
					}
					else
					{
						Assert.assertTrue(false);
					}
				}
				if(exp.equalsIgnoreCase("Invalid"))
				{
					if(targetpage==true)
					{
						macc.clicLogout();
                        Assert.assertTrue(false);
					}
					else
					{
						Assert.assertTrue(true);
					}
				}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
				logger.info("Finished verifying DDT");
	}
}
