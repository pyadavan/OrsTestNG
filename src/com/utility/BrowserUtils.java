package com.utility;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

	/**
	 * wait for method to poll page title
	 * 
	 * @param title
	 * @param timer
	 * @throws Exception
	 */

	public static void waitFor(String title, int timer) throws Exception {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//com//config//config.properties");
				Properties pro = new Properties();
				pro.load(fs);
		if(pro.getProperty("Browser").contains("firefox")){
			WebDriver driver = DriverFactory.getInstance().getDriver();
			WebDriverWait exists = new WebDriverWait(driver, 20);
			exists.until(ExpectedConditions.refreshed(ExpectedConditions
					.titleContains(title)));
			Thread.sleep(2000);
		}
		else{
		WebDriver driver = DriverFactory.getInstance().getDriver();
		WebDriverWait exists = new WebDriverWait(driver, timer);
		exists.until(ExpectedConditions.refreshed(ExpectedConditions
				.titleContains(title)));
		}
	}

	
	
	

	/**
	 * waitFor method to wait up to a designated period before throwing exception(static locator)
	 * @param element
	 * @param timer
	 * @throws Exception
	 */
	public static void waitFor(WebElement element, int timer) throws Exception {
		
			 try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "//src//com//config//config.properties");
					Properties pro = new Properties();
					pro.load(fs);
					
			if(pro.getProperty("Browser").contains("firefox")){
				WebDriver driver = DriverFactory.getInstance().getDriver();
				// wait for static element to appear
				WebDriverWait exists = new WebDriverWait(driver, 20);
				
				exists.until(ExpectedConditions.refreshed(ExpectedConditions
						.visibilityOf(element)));
				Thread.sleep(2000);
			}
			else{
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// wait for static element to appear
		WebDriverWait exists = new WebDriverWait(driver, timer);
		exists.until(ExpectedConditions.refreshed(ExpectedConditions
				.visibilityOf(element)));
			}
			
		}
			 
		catch(Exception e){
			Thread.sleep(7000);
		}
		
		
	}
	public static void waitForIframe(WebElement iframe , int timer) throws Exception {
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// wait for static element to appear
		WebDriverWait exists = new WebDriverWait(driver, timer);
		exists.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
	}
	/**
	 * waitFor method to wait up to a designated period before throwing exception(dynamic locator)
	 * @param by
	 * @param timer
	 * @throws Exception
	 */
	public static void waitFor(By by, int timer) throws Exception {
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// wait for dynamic element to appear
		WebDriverWait exists = new WebDriverWait(driver, timer);
		exists.until(ExpectedConditions.refreshed(ExpectedConditions
				.visibilityOfElementLocated(by)));
	}

	
	/**
	 * waitForGone method to wait up to a designated period before throwing exception if element still exists
	 * @param by
	 * @param timer
	 * @throws Exception
	 */
	public static void waitForGone(By by, int timer) throws Exception {
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// wait for dynamic element to appear
		WebDriverWait exists = new WebDriverWait(driver, timer);
		exists.until(ExpectedConditions.refreshed(ExpectedConditions
				.invisibilityOfElementLocated(by)));
	}
	
	/**
	 * waitForUrl method to wait up to a designated period before throwing exception if URL is not found
	 * @param url
	 * @param seconds
	 * @throws Exception
	 */
	public static void waitForURL(String url, int seconds) throws Exception {
		WebDriver driver = DriverFactory.getInstance().getDriver();
		// wait for dynamic element to appear
		WebDriverWait exists = new WebDriverWait(driver, seconds);
		exists.until(ExpectedConditions.refreshed(ExpectedConditions
				.urlContains(url)));
	}
	public static String Emailidgenerator(String email) throws Exception {
		String date = new SimpleDateFormat("ddHHmm").format(new Date());
		String dateEmail=date+email;
		return dateEmail;
	}
	
}
