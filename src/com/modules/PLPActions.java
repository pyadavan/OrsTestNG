package com.modules;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.utility.WebdriverUtils;

import pageObjects.LoginObjects;
import pageObjects.PLPObjects;
import pageObjects.myAccountObjects;

public class PLPActions {
	static String proPricePLP;
	static String proNamePLP;
	static String proSkuPLP;
	
	public static String plpValidationPriceData() throws Exception {
	return proPricePLP = PLPObjects.productPriceQV().getText().replaceAll("$", "").trim();	
	}
	
	public static String plpValidationNameData() throws Exception {
		return proNamePLP = PLPObjects.productNameQV().getText();		
	}
	
	public static String plpValidationSkuData() throws Exception {
		return proSkuPLP = PLPObjects.productSkuQV().getText().trim();		
	}

	public static void verifyNavPLPAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.moveToElementByActions(PLPObjects.megaMenuL1());
		WebdriverUtils.clickAction(PLPObjects.megaMenuL2());
		assertTrue(PLPObjects.plpHeading().isDisplayed());		

	}

	public static void verifyNavPDPfroQVAction() throws Exception {
		verifyNavPLPAction();
		plpValidationPriceData();
		plpValidationNameData();
		plpValidationSkuData();
		WebdriverUtils.moveToElementByActions(PLPObjects.qvButton());
		WebdriverUtils.clickAction(PLPObjects.qvButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.saveListButtonQV());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.saveFullDetailsButtonQV());	
		assertEquals(PDPActions.pdpValidationPriceData(),plpValidationPriceData());
		assertEquals(PDPActions.pdpValidationNameData(),plpValidationNameData());
		assertEquals(PDPActions.pdpValidationSkuData(),plpValidationSkuData());
	}
		
	public static void viewDetails() throws Exception {
		String sku = PLPObjects.productSkuQV().getText().trim();
		WebdriverUtils.clickAction(PLPObjects.saveFullDetailsButtonQV());
		assertTrue(WebdriverUtils.currentURL().contains(sku));
		assertTrue(PLPObjects.saveFullDetailsButtonQV().isDisplayed());		
	}

	public static void verifyAddToWishSimpleAction() throws Exception {	
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.qvButton());		
		WebdriverUtils.moveToElementByActions(PLPObjects.qvButton());
		WebdriverUtils.clickAction(PLPObjects.qvButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.saveListButtonQV());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.saveFullDetailsButtonQV());	
//		plpValidationPriceData();
		String proName = plpValidationNameData();
//		plpValidationSkuData();
		WebdriverUtils.clickAction(PLPObjects.shipToHomeRadioQV());
		WebdriverUtils.clickAction(PLPObjects.saveListButtonQV());
		assertTrue(WebdriverUtils.currentURL().contains("wishlist"));
		WebdriverUtils.waitForVisibilityOfElementLocated(myAccountObjects.wishListTitleInSection());
		String productNameInWishlist=null;
		if(myAccountObjects.totalProductNames()!=null) {
		int totalSavedProducts = myAccountObjects.totalProductNames().size();
		productNameInWishlist = myAccountObjects.productName(totalSavedProducts).getText().trim();		
		}
		assertEquals(productNameInWishlist, proName);		
	}
	
	public static void deleteWishOneByOne() throws Exception {
		WebdriverUtils.clickAction(myAccountObjects.myAccountLinkHeader());
		assertTrue(WebdriverUtils.currentURL().contains("order"));
		WebdriverUtils.waitForVisibilityOfElementLocated(myAccountObjects.myWishLinkInLeftPanel());
		WebdriverUtils.clickAction(myAccountObjects.myWishLinkInLeftPanel());
		WebdriverUtils.waitForVisibilityOfElementLocated(myAccountObjects.wishListTitleInSection());
		if(myAccountObjects.productWishCheckboxes()!=null) {
			for(int i=myAccountObjects.productWishCheckboxes().size();i>=1;i--) {
				WebdriverUtils.clickAction(myAccountObjects.productWishCheckbox(i));
				WebdriverUtils.moveToElementByActions(myAccountObjects.productWishCheckbox(i));
				WebdriverUtils.clickAction(myAccountObjects.removeItem());
			}
		}
		assertTrue(myAccountObjects.productWishCheckboxes()==null);
	}
	
	public static void verifyAddToWishSimpleBOPISAction() throws Exception {
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.qvButton());		
		WebdriverUtils.moveToElementByActions(PLPObjects.qvButton());
		WebdriverUtils.clickAction(PLPObjects.qvButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.saveListButtonQV());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.saveFullDetailsButtonQV());	
		WebdriverUtils.sendKeys(PLPObjects.zipCodeFieldQV(), "65109");
		WebdriverUtils.clickAction(PLPObjects.goZipButtonQV());
		WebdriverUtils.waitForVisibilityOfElementLocated(PLPObjects.storeSearchResult41QV());
//		plpValidationPriceData();
		String proName = plpValidationNameData();
//		plpValidationSkuData();
		WebdriverUtils.clickAction(PLPObjects.storeSearchResult41QV());
		WebdriverUtils.clickAction(PLPObjects.inStorePickupRadioQV());
		WebdriverUtils.clickAction(PLPObjects.saveListButtonQV());
		assertTrue(WebdriverUtils.currentURL().contains("wishlist"));
		WebdriverUtils.waitForVisibilityOfElementLocated(myAccountObjects.wishListTitleInSection());
		String productNameInWishlist=null;
		if(myAccountObjects.totalProductNames()!=null) {
		int totalSavedProducts = myAccountObjects.totalProductNames().size();
		productNameInWishlist = myAccountObjects.productName(totalSavedProducts).getText().trim();		
		}
		assertEquals(productNameInWishlist, proName);

	}

	public static void verifyAddToWishConfigAction() {

	}

	public static void verifyAddToWishConfigBOPISAction() {

	}

	public static void verifyAddToCartSimpleAction() {

	}

	public static void verifyAddToCartSimpleBOPISAction() {

	}

	public static void verifyAddToCartConfigAction() {

	}

	public static void verifyAddToCartConfigBOPISAction() {

	}

	public static void verifyCompareFeatureAction() {

	}

}
