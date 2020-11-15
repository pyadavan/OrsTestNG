package com.testCases;

import org.testng.annotations.Test;

import com.modules.HomepageActions;
import com.utility.TestBase;

public class HomepageTestCases extends TestBase {
	
	@Test(priority = 1, description = "Verify QV popup is displayed for Week deal products in homepage")
	public void verifyQVPopUpWeekDeal() throws Exception {
		HomepageActions.verifyQVPopUpWeekDealAction();
	}
	
	@Test(priority = 1, description = "Verify able to add to cart from QV for Week deal products ship to home in homepage")
	public void verifyQVPopUpWeekDealShip2HomeATC() throws Exception {
		HomepageActions.verifyQVPopUpWeekDealShip2HomeATC();
		
	}

}
