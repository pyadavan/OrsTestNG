package com.testCases;

import org.testng.annotations.Test;

import com.modules.LoginActions;
import com.modules.PLPActions;
import com.utility.TestBase;

public class PLPTestCases extends TestBase {

	@Test(priority = 1, description = "Verify able to navigate to PLP from megamenu")
	public void verifyNavPLP() throws Exception {
		PLPActions.verifyNavPLPAction();		
	}
	
	@Test(priority = 2, description = "Verify able to navigate to PDP from QV")
	public void verifyNavPDPfroQV() throws Exception {
		PLPActions.verifyNavPDPfroQVAction();
		PLPActions.viewDetails();
	}
	
	@Test(priority = 3, description = "Verify QV selections and add to wishlist for simple product")
	public void verifyAddToWishSimple() throws Exception {
		LoginActions.verifyLogin();
		PLPActions.deleteWishOneByOne();
		PLPActions.verifyNavPLPAction();
		PLPActions.verifyAddToWishSimpleAction();		
	}
	
	@Test(priority = 4, description = "Verify QV selections and add to wishlist for simple product BOPIS")
	public void verifyAddToWishSimpleBOPIS() throws Exception {
		LoginActions.verifyLogin();
		PLPActions.deleteWishOneByOne();
		PLPActions.verifyNavPLPAction();		
		PLPActions.verifyAddToWishSimpleBOPISAction();
	}
	
	@Test(priority = 5, description = "Verify QV selections and add to wishlist for configurable")
	public void verifyAddToWishConfig() {
		PLPActions.verifyAddToWishConfigAction();
	}
	
	@Test(priority = 6, description = "Verify QV selections and add to wishlist for configurable BOPIS")
	public void verifyAddToWishConfigBOPIS() {
		PLPActions.verifyAddToWishConfigBOPISAction();
	}
	
	@Test(priority = 7, description = "Verify QV selections and add to cart for simple product")
	public void verifyAddToCartSimple() {
		PLPActions.verifyAddToCartSimpleAction();
	}
	
	@Test(priority = 8, description = "Verify QV selections and add to cart for simple product BOPIS")
	public void verifyAddToCartSimpleBOPIS() {
		PLPActions.verifyAddToCartSimpleBOPISAction();
	}
	
	@Test(priority = 9, description = "Verify QV selections and add to cart for configurable")
	public void verifyAddToCartConfig() {
		PLPActions.verifyAddToCartConfigAction();
	}
	
	@Test(priority = 10, description = "Verify QV selections and add to cart for configurable BOPIS")
	public void verifyAddToCartConfigBOPIS() {
		PLPActions.verifyAddToCartConfigBOPISAction();
	}
	
	@Test(priority = 11, description = "Verify compare functionality from PLP")
	public void verifyCompareFeature() {
		PLPActions.verifyCompareFeatureAction();
	}
	
	@Test(priority = 12, description = "Verify compare functionality from PLP")
	
}
