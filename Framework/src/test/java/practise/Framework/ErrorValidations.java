package practise.Framework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import java.io.IOException;


import org.testng.Assert;

import practise.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(priority=1)
	public void aerrorValidation() throws IOException {

		String user = "msd71@gmail.com";
		String password = "Qwerty@123";

		System.out.println(driver.getTitle());

		loginDetails.loginPage(user, password);
		String message = loginDetails.getErrorMessage();
		System.out.println(loginDetails.getErrorMessage());
		AssertJUnit.assertEquals(message, loginDetails.getErrorMessage());
		
	}
	
	@Test(priority=2)
	public void directLogin()
	{
		String user = "mah@gmal.com";
		loginDetails.onlyUser(user);
		String message = loginDetails.emptyPassword();
		System.out.println(message);
		AssertJUnit.assertEquals(message, loginDetails.emptyPassword());
	}

}
