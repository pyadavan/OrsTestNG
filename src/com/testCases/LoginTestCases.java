package com.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.modules.LoginActions;
import com.utility.TestBase;

public class LoginTestCases extends TestBase{
	
	@Test(priority=1, groups = { "UAT","Prod"}, description="Verify the user is able to login with valid email address and password")
	public void verifyLogin() throws IOException, Exception{
		LoginActions.verifyLogin();
		}	
	
}
