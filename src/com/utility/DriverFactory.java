package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.utility.WebdriverUtils.BrowserEnum;

public class DriverFactory
{

   private DriverFactory()
   {
      //Do-nothing..Do not allow to initialize this class from outside
   }
   private static DriverFactory instance = new DriverFactory();

   public static DriverFactory getInstance()
   {
      return instance;
   }

   ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
   {
		@Override
		protected WebDriver initialValue() {

			String sBrowserName = Browsers.get();
			Log.info("Browser: " + sBrowserName, true);			
			
			if (sBrowserName.equals("Chrome") || sBrowserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", Constant.vChromeBrowserPath);
				return new ChromeDriver();
			} // can be replaced with other browser drivers

			else if (sBrowserName.equals("Firefox") || sBrowserName.equals("firefox")) {
				
				FirefoxProfile ffPF=new FirefoxProfile();
				ffPF.setPreference("browser.private.browsing.autostart",true);
				ffPF.setPreference("browser.autofocus", true);
				ffPF.setAcceptUntrustedCertificates(true);
				ffPF.setAssumeUntrustedCertificateIssuer(true);
				ffPF.setPreference("security.insecure_field_warning.contextual.enabled", false);
				
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				FirefoxOptions ffOpts=new FirefoxOptions();
				capabilities.setCapability(FirefoxDriver.PROFILE, ffPF);
				capabilities.setCapability("marionette", true);
				capabilities.setCapability("acceptSslCerts", true);
				capabilities.setCapability("acceptInsecureCerts", true);
				
				System.setProperty("webdriver.gecko.driver", Constant.vFirefoxBrowserPath);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,System.getProperty("user.dir") + "/logs.txt");
				return new FirefoxDriver(ffOpts.merge(capabilities));
			}
			
			else if (sBrowserName.equals("IE") || sBrowserName.equals("Ie")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.VERSION, "11");
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				System.setProperty("webdriver.ie.driver", Constant.vIEBrowserPath);
				return new InternetExplorerDriver(capabilities);
			}
			/*else if (sBrowserName.toLowerCase().equals("ieedge") || sBrowserName.toUpperCase().equals("IEEDGE")) {
				System.setProperty("webdriver.edge.driver", Constant.vIEEdge);
				return new EdgeDriver();
			}*/
			else if(sBrowserName.toLowerCase().equals("safari")|| sBrowserName.toUpperCase().equals("SAFARI")){
				DesiredCapabilities capabilities = DesiredCapabilities.safari();
				SafariOptions safOpts= new SafariOptions();
				capabilities.setCapability("requireWindowFocus" , true);
				capabilities.setCapability("safari.cleanSession", true);
				capabilities.setCapability("autoAcceptAlerts", true);
				return new SafariDriver(safOpts.merge(capabilities));
			}
			else {
				System.out.println("Browser " + sBrowserName + " is not valid in is project");
				return null;
			}
		}
   };

   public WebDriver getDriver() // call this method to get the driver object and launch the browser
   {
      return driver.get();
   }

   
 
   public void removeDriver() // Quits the driver and closes the browser
   {
      driver.get().quit();
      driver.remove();
   }
   
   
//   
//   @BeforeMethod
//   public void setup(){
//     WebDriver driver = DriverFactory.getInstance().getDriver();
//     driver.get("http://www.google.com");
//   }
   
//   @AfterMethod
//   public void tearDown(){
//     DriverFactory.getInstance().removeDriver();
//   }
}