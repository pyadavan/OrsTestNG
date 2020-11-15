package com.utility;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.utility.Global_VARS;



import java.lang.reflect.Method;

public class TestBase {
//	public static WebDriver driver=null;
	
	
	/**
	 * suiteSetup method
	 * 
	 * @param environment
	 *            '
	 * @param context
	 * @param Exception
	 */
	
	

	@BeforeSuite(alwaysRun = true, enabled = true)
	protected void suiteSetup(
			ITestContext context) throws Exception {
		Global_VARS.SUITENAME = context.getSuite().getXmlSuite().getName();
	}
	
	

	@AfterSuite(alwaysRun = true, enabled = true)
	protected void suiteTeardown() throws Exception {
   
	}
	
	
	
	
	@BeforeTest(alwaysRun = true, enabled = true)
	protected void testSetUp(ITestContext ctxt)
			throws Exception {
		
		
	}
	
	
	
	
	@AfterTest(alwaysRun=true , enabled=true)
	protected void testTearDown() throws Exception{
		
		
	}
	
	
	
//	@BeforeClass(alwaysRun=true,enabled=true)
//	protected void testClassSetup(ITestContext context) throws Exception{
//		
//	}
//	

	@AfterClass(alwaysRun=true , enabled=true)
	protected void testClassTearDown(ITestContext context) throws Exception {
		
	}
	
	

	/**
	 * Stop the standard test tearDown from being processed as we only want to
	 * stop the browser at the end of the suite
	 * 
	 * @throws Exception
	 */
	@AfterMethod(alwaysRun = true)
	public static void tearDown(ITestResult result) throws Exception {
		try {
			if (DriverFactory.getInstance().getDriver() != null) {
				try {
					DriverFactory.getInstance().removeDriver();
				} catch (Exception x) {
					WebdriverUtils.captureScreenShot(DriverFactory.getInstance().getDriver(),
							new Exception().getStackTrace()[0].getMethodName());
					Log.error("Did not manage to quit driver object cleanly: " + x.getMessage());
				}
			}
		} catch (Exception x) {
			WebdriverUtils.captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error("Error Quitting Browser: " + x.getMessage());
			Log.error("Killing Selenium!");
			Runtime.getRuntime().halt(1);
		}
	}

	

	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, ITestResult result) throws Exception {
		Log.info("---------------------------------------------------------------------", true);
		Log.info("Executing Setup Method");
		Log.info("Executing Test: " + method.getName(), true);
		WebdriverUtils.openBrowser();
		
	}

}
