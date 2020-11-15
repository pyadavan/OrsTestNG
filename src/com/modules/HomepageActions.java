package com.modules;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.utility.WebdriverUtils;

import pageObjects.HomepageObjects;
import pageObjects.LoginObjects;
import pageObjects.PLPObjects;

public class HomepageActions {
	
	public static void verifyQVPopUpWeekDealAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		String expProductName = HomepageObjects.weekDealFirstProductLink().getText();
		String expProductPrice = HomepageObjects.weekDealFirstPrice().getText().replaceAll("$", "");
		String expProductSku = HomepageObjects.weekDealFirstSKU().getText();
		WebdriverUtils.moveToElementByActions(HomepageObjects.quickViewWeekDealButton());
		WebdriverUtils.clickAction(HomepageObjects.quickViewWeekDealButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.saveFullDetailsButtonQV());
		String actProductName = PLPObjects.productNameQV().getText();
		String actProductPrice = PLPObjects.productPriceQV().getText().replaceAll("$", "");
		String actProductSku = PLPObjects.productSkuQV().getText();
		assertEquals(actProductName, expProductName);
		assertEquals(actProductPrice, expProductPrice);
		assertEquals(actProductSku, expProductSku);		
	}
	
	public static void verifyQVPopUpWeekDealShip2HomeATC() throws Exception {
		verifyQVPopUpWeekDealAction();
		WebdriverUtils.clickAction(PLPObjects.shipToHomeRadioQV());
		WebdriverUtils.clickAction(PLPObjects.aTCButtonQV());
		Thread.sleep(2000);
		assertTrue(WebdriverUtils.currentURL().contains("cart"));		
	}

}
