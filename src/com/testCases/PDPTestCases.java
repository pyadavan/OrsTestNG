package com.testCases;

import org.testng.annotations.Test;

import com.modules.PDPActions;
import com.utility.TestBase;

public class PDPTestCases extends TestBase{
	
	@Test(priority = 1, description = "Verify able to navigate to PDP from megamenu")
	public void verifyPDPNav() {
		
	}
		
	@Test(priority = 2, description = "Verify add to wishlist for simple product")
    public void verifyAddToWishSimplePDP() throws Exception {
		PDPActions.verifyAddToWishSimplePDPAction();
		
	}
	
	@Test(priority = 3, description = "Verify add to wishlist for simple product BOPIS")
	public void verifyAddToWishSimpleBOPISPDP() throws Exception {
		PDPActions.verifyAddToWishSimpleBOPISPDPAction();
		
	}
	
	@Test(priority = 4, description = "Verify add to wishlist for configurable")
	public void verifyAddToWishConfigPDP() throws Exception {
		PDPActions.verifyAddToWishConfigPDPAction();
		
	}
	
	@Test(priority = 5, description = "Verify add to wishlist for configurable BOPIS")
	public void verifyAddToWishConfigBOPISPDP() throws Exception {
		PDPActions.verifyAddToWishConfigBOPISPDPAction();
		
	}
	
	@Test(priority = 6, description = "Verify add to cart for simple product")
	public void verifyATCSimple() throws Exception {
		PDPActions.verifyATCSimpleAction();		
	}
	
	@Test(priority = 7, description = "Verify add to cart for simple product BOPIS")
	public void verifyATCSimpleBOPIS() throws Exception {
		PDPActions.verifyATCSimpleBOPISAction();		
	}
	
	@Test(priority = 8, description = "Verify add to cart for configurable")
	public void verifyATCConfig() throws Exception {
		PDPActions.verifyATCConfigAction();		
	}
	
	@Test(priority = 9, description = "Verify add to cart for configurable BOPIS")
	
	public void verifyATCConfigBOPIS() throws Exception {
		PDPActions.verifyATCConfigBOPISAction();		
	}
	
	@Test(priority = 10, description = "Verify update button's funcionality in PDP")
	public void verifyUpdateFuncPDP() {
		
	}
	
}
