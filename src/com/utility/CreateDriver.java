package com.utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.utility.File_IO;

/*
 * Single Skeleton Class
 */
public class CreateDriver {
//local variables
private static CreateDriver instance=null;
//private String browserHandle=null;
	private static final int IMPLICIT_TIMEOUT=0;


private ThreadLocal<WebDriver> webDriver= new ThreadLocal<WebDriver>();
private ThreadLocal<String> sessionId=new ThreadLocal<String>();
private ThreadLocal<String> sessionBrowser=new ThreadLocal<String>();
private ThreadLocal<String> sessionPlatform=new ThreadLocal<String>();
private ThreadLocal<String> sessionVersion=new ThreadLocal<String>();
private String getEnv=null;

private Properties driverprops=null;
private static final String propertyFile=new File(System.getProperty("user.dir")+"//src//Selenium.properties").getAbsolutePath();


	/**
	 * getSessionId method gets the browser
	 * 
	 * @return String
	 */
	public String getSessionId() {

		return sessionId.get();
	}

	
	/**
	 * getSessionBrowser method gets the browser the active session
	 * @return String
	 */
	
	public String getSessionBrowser(){
	return sessionBrowser.get();
	}
	
	
	/**
	 * getSessionVersion method gets the browser of the active session
	 */
	public String getSessionVersion(){
		return sessionVersion.get();
	}
	
	/**
	 * getSessionPlatform method gets the browser of the active session
	 */
	
	public String getSessionPlatform(){
		return sessionPlatform.get();
	}
	
	
	//Constructor
	private CreateDriver(){
		
	}
	
	/**
	 * setDriver method
	 * 
	 * @param browser
	 * @param environment
	 * @param platform
	 * @param optPreferences
	 * @param Exception
	 */
	@SafeVarargs
	public final void setDriver(String browser, String platform,
			String environment, Map<String, Object>... optPreferences)
			throws Exception {

		String getPlatform = null;
		
		//load properties from file;
		driverprops=File_IO.loadProps(propertyFile);


		DesiredCapabilities caps = null;
		// String localHub="http://127.0.0.1:4273/wd/hub";
		// String getPlaStringtform=null;

		switch (browser) {

		case "firefox":

			caps = DesiredCapabilities.firefox();
			FirefoxOptions ffOpts = new FirefoxOptions();
			FirefoxProfile ffProfile = new FirefoxProfile();
			ffProfile.setPreference("browser.autofocus", true);
			caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
			caps.setCapability("marionette", true);
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir")+driverprops.getProperty("gecko.driver.windows.path"));
			// if(optPreferences.length > 0){
			// processFFProfile(ffProfile, optPreferences);
			// }

			webDriver.set(new FirefoxDriver(ffOpts.merge(caps)));
			break;

		case "chrome":

			caps = DesiredCapabilities.chrome();
			ChromeOptions chOptions = new ChromeOptions();
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("credentials_enable_service", false);
			chOptions.setExperimentalOption("prefs", chromePrefs);
			chOptions.addArguments("--disable-plugins", "--disable-extensions",
					"--disable--popup-blocking");
			caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
			caps.setCapability("applicationCacheEnabled", false);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+driverprops.getProperty("chrome.driver.windows.path"));
			// if(optPreferences.length > 0){
			// processCHOptions(chOptions, optPreferences);
			// }
			webDriver.set(new ChromeDriver(chOptions.merge(caps)));
			break;

		case "internet explorer":

			caps = DesiredCapabilities.internetExplorer();
			 chOptions = new ChromeOptions();
				InternetExplorerOptions ieOpts= new InternetExplorerOptions();
				ieOpts.requireWindowFocus();
				caps.setCapability("requireWindowFocus", true);
//				caps.setCapability(CapabilityType.VERSION, "11");
//				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				System.setProperty("webdriver.ie.driver", Constant.vIEBrowserPath);
				webDriver.set(new InternetExplorerDriver(ieOpts.merge(caps)));
			 	break;
			
			
		default:
			System.out.println("The browser : " + browser + "not valid");
		}

		getEnv = environment;
		getPlatform = platform;

		sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId()
				.toString());
		sessionBrowser.set(caps.getBrowserName());
		sessionVersion.set(caps.getVersion());
		sessionPlatform.set(getPlatform);

		System.out.println("\n*** TEST ENVIRONMENT = "
				+ getSessionBrowser().toUpperCase() + "/"
				+ getSessionPlatform().toUpperCase() + "/"
				+ getEnv.toUpperCase() + "/SeleniumVersion="
				+ driverprops.getProperty("selenium.revesion") + "/Session ID"
				+ getSessionId() + "\n");
		
		getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
	}


public void setDriver(WebDriver driver){
	
	
webDriver.set(driver);
sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName().toString());
sessionPlatform.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getPlatform().toString());
//setBrowserHandle(getDriver().getWindowHandle());
}


/**
 * getDriver method will retrieve the active WebDriver
 * 
 * @return WebDriver
 */



public WebDriver getDriver(){
return webDriver.get();	
}

public WebDriver getCurrentDriver(){
return getInstance().getDriver();
}


	
/**
 * getInstance Method to retrieve active driver instance
 * @return CreateDriver	
 */
public static CreateDriver getInstance(){
if(instance == null)	{
instance=new CreateDriver();
}
return instance;	
}


/**
 * driverWait method pauses the driver in seconds
 * @param seconds to pause
 */

public void driverWait(long seconds){
	try{
		Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
	}
	catch(InterruptedException e){
		// do something
	}
}

public void closeDriver(){
	try{
	getCurrentDriver().quit();	
	
}
	catch(Exception e){
		
	}
}	

}
