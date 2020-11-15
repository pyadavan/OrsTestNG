package com.modules;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.utility.WebdriverUtils;

import pageObjects.LoginObjects;

public class LoginActions{
	public static void verifyLogin() throws IOException, Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.moveToElementByActions(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(LoginObjects.login_email(), WebdriverUtils.getProperty("email"));
		WebdriverUtils.sendKeys(LoginObjects.login_Pwd(), WebdriverUtils.getProperty("pwd"));
		WebdriverUtils.clickAction(LoginObjects.login_Button());	
		assertEquals(WebdriverUtils.currentURL(), WebdriverUtils.getProperty("orderHistoryPage"));
	}	
	
}
