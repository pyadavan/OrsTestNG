package com.utility;

import com.exceptions.InvalidElementTypeException;
import com.exceptions.TooFewWindowsException;
import com.exceptions.TooManyWindowsException;
import com.exceptions.UnableToFindWindowException;
import com.google.common.io.Files;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.Exception;
import java.lang.Boolean;

@SuppressWarnings("deprecation")
public class WebdriverUtils {

	// <editor-fold desc="Variables">

	//public static WebDriver DriverFactory.getInstance().getDriver() = null;

	private static String testName;

	public static String getTestName() {
		return testName;
	}

	public static void setTestName(String methodName) {
		testName = methodName;
	}

	// </editor-fold>

	// <editor-fold desc="Browser Methods">

	public static WebDriver openBrowser() throws Exception {

		  DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  DriverFactory.getInstance().getDriver().manage().window().maximize();
			Thread.sleep(1000);
		return  DriverFactory.getInstance().getDriver();
	}

	public static String currentURL() {
		String URL = DriverFactory.getInstance().getDriver().getCurrentUrl();
		return URL;
	}

	public static WebElement waitForElementToBeClickable(WebElement element, String name) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Log.info("Wait for element '" + name + "' is performed");
			return element;
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	
	public static By waitForElementToBepresent(By by) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Log.info("Wait for element is performed");
			return by;
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}	
	
	public static WebElement waitForElementToBeClickable(WebElement element, int waitSeconds)
			throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), waitSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Log.info("Wait for element is performed");
			return element;
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	
	public static WebElement waitForElementpresent(WebElement element, int waitSeconds)
			throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), waitSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(Elementtoby(element)));
			Log.info("Wait for element is performed");
			return element;
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	public static By Elementtoby(WebElement element) throws Exception {
	    try {
	     WebElement elementx=element;
	     String str = elementx.toString().substring(element.toString().indexOf("xpath")).replace("xpath:", "").trim();
	     String str1 = str.substring(0, str.length()-1);
	     //String str2 = "By.xpath("+str1+")";
	     By loc = By.xpath(str1);
	     System.out.println(loc);
	    return loc;
	    } catch (Exception ex) {
	     Log.error(ex.getMessage());
	     captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
	     return null;
	    }
	   }

	// </editor-fold>

	// <editor-fold desc="Wait Methods">

	public static WebElement waitForElementToBeClickable(By by) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
			Log.info("Wait for element '" + by.toString() + "' is performed");
			return element;
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	
	public static void waitForAllElementsLocatedBy(By by) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			Log.info("Wait for all elements located in " + by.toString() + " is performed");
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForAllElementsLocatedBy | Exception occurred while waiting for presence of all elements located by "
							+ by.toString() + ". " + e.getMessage());
			throw e;
		}
	}

	public static void waitForVisibilityOfElementLocated(WebElement wElement) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.visibilityOf(wElement));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ wElement.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitForInvisibilityOfElementLocated(WebElement element) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ element.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitForInvisibilityOfElementLocated(By by, int waitTimeSec) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), waitTimeSec);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ by.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitForVisibilityOfElementLocated(By by) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ by.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void scrollToElement(String pixel) throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			jse.executeScript("window.scrollBy(0," + pixel + ")");
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static String HandleAlertText() {
		Alert alert = DriverFactory.getInstance().getDriver().switchTo().alert();
		String s = alert.getText();
		return s;
	}
	
	public static void switchtoNewTab() throws Exception {
		try {
			ArrayList<String> tabs2 = new ArrayList<String> (DriverFactory.getInstance().getDriver().getWindowHandles());
		    DriverFactory.getInstance().getDriver().switchTo().window(tabs2.get(1));
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void waitUntilElementIsDisplayed(By elementLocator) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ elementLocator.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitUntilElementIsDisplayed(By elementLocator, int waitTimeSec) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), waitTimeSec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ elementLocator.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitUntilElementIsDisplayed(By elementLocator, WebElement parentElement) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.visibilityOf(parentElement.findElement(elementLocator)));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ elementLocator.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitUntilElementIsDisplayed(By elementLocator, WebElement parentElement, int waitTimeSec)
			throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), waitTimeSec);
			wait.until(ExpectedConditions.visibilityOf(parentElement.findElement(elementLocator)));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ elementLocator.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitUntilElementIsDisplayed(WebElement wElement) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.visibilityOf(wElement));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ wElement.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static boolean isElementDisplayed(WebElement element) throws Exception {
		try {
			if (element.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void waitUntilElementIsDisplayed(WebElement wElement, int waitTimeSec) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), waitTimeSec);
			wait.until(ExpectedConditions.visibilityOf(wElement));
		} catch (Exception e) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitForInvisibilityOfElementLocated | Exception occurred while waiting for invisibility of an element: "
							+ wElement.toString() + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public static void waitUntilElementIsEnabled(final By elementlocator) throws Exception {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds) {
			}.until(new ExpectedCondition<Boolean>() {
				// @Override
				public Boolean apply(WebDriver driver) {
					return (DriverFactory.getInstance().getDriver().findElement(elementlocator).isEnabled());
				}
			});
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void waitUntilInstancesEqual(final By elementLocator, final int instances) throws Exception {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds) {
			}.until(new ExpectedCondition<Boolean>() {
				// @Override
				public Boolean apply(WebDriver driver) {
					return (DriverFactory.getInstance().getDriver().findElements(elementLocator).size() == instances);
				}
			});
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void waitUntilInstancesEqual(final By elementLocator, final WebElement wParent, final int instances)
			throws Exception {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds) {
			}.until(new ExpectedCondition<Boolean>() {
				// @Override
				public Boolean apply(WebDriver driver) {
					return (wParent.findElements(elementLocator).size() == instances);
				}
			});
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void waitUntilInstancesAreMoreThan(final By elementLocator, final int instances) throws Exception {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds) {
			}.until(new ExpectedCondition<Boolean>() {
				// @Override
				public Boolean apply(WebDriver driver) {
					return (DriverFactory.getInstance().getDriver().findElements(elementLocator).size() > instances);
				}
			});
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void waitUntilInstancesAreMoreThan(final By elementLocator, final WebElement wParent,
			final int instances) throws Exception {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds) {
			}.until(new ExpectedCondition<Boolean>() {
				// @Override
				public Boolean apply(WebDriver driver) {
					return (wParent.findElements(elementLocator).size() > instances);
				}
			});
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void waitUntilInstancesAreMoreThan(final By elementLocator, final WebElement wParent,
			final int instances, final int waitTimeSec) throws Exception {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), waitTimeSec) {
			}.until(new ExpectedCondition<Boolean>() {
				// @Override
				public Boolean apply(WebDriver driver) {
					return (wParent.findElements(elementLocator).size() > instances);
				}
			});
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void waitUntilTextPresentInElement(By by, String text) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
			Log.info("Wait Until text Present in Element " + by.toString() + " is performed");
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitUntilTextPresentInElement | Exception occurred while waiting for presence of all elements located by "
							+ by.toString() + ". " + ex.getMessage());
			throw ex;
		}
	}

	public static void waitUntilValuePresentInElement(By by, String text) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.textToBe(by, text));
			Log.info("Wait Until text Present in Element " + by.toString() + " is performed");
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitUntilTextPresentInElement | Exception occurred while waiting for presence of all elements located by "
							+ by.toString() + ". " + ex.getMessage());
			throw ex;
		}
	}

	public static void waitUntilValuePresentInElement(WebElement wElement, String text) throws Exception {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//com//config//config.properties");
		Properties pro = new Properties();
		pro.load(fs);
		if(pro.getProperty("Browser").equalsIgnoreCase("ie11") || pro.getProperty("Browser").equalsIgnoreCase("safari")) {
		Thread.sleep(5000);
		}
		else {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.textToBePresentInElement(wElement, text));
			Log.info("Wait Until text Present in Element " + wElement.toString() + " is performed");
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(
					"Class WebdriverUtils | Method waitUntilTextPresentInElement | Exception occurred while waiting for presence of all elements located by "
							+ wElement.toString() + ". " + ex.getMessage());
			throw ex;
		}
		}
	}

	public static void normalizeWaitingTimeForElement() throws Exception {
		try {
			DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(Constant.WaitingSeconds, TimeUnit.SECONDS);
			Log.info("Set the implicit waiting time to " + Constant.WaitingSeconds + " seconds");
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void waitForElementAfterClickMethod(By waitForLocator, By clickLocator, int ms, int retry)
			throws Exception {
		try {
			int count = 0;
			WebElement invoiceGrid = findElement(waitForLocator);
			while (invoiceGrid == null) {
				pauseTime(ms);
				invoiceGrid = findElement(waitForLocator);
				count++;
				if (count == retry) {
					break;
				}
				WebdriverUtils.clickElement(findElement(clickLocator));
			}
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void waitForElementAfterClickMethod(By waitForLocator, WebElement wElement, int ms, int retry)
			throws Exception {
		try {
			int count = 0;
			WebElement invoiceGrid = findElement(waitForLocator);
			while (invoiceGrid == null) {
				pauseTime(ms);
				invoiceGrid = findElement(waitForLocator);
				count++;
				if (count == retry) {
					break;
				}
				WebdriverUtils.clickElement(wElement);
			}
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void waitForElementAfterClickThroughJsMethod(By waitForLocator, WebElement wElement, int ms,
			int retry) throws Exception {
		try {
			int count = 0;
			WebElement invoiceGrid = findElement(waitForLocator);
			while (invoiceGrid == null) {
				pauseTime(ms);
				invoiceGrid = findElement(waitForLocator);
				count++;
				if (count == retry) {
					break;
				}
				WebdriverUtils.clickButtonThroughJS(wElement, "Click Element");
			}
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void waitForElementAfterClickThroughJsMethod(By waitForLocator, By clickElementLocator, int ms,
			int retry) throws Exception {
		try {
			int count = 0;
			WebElement invoiceGrid = findElement(waitForLocator);
			while (invoiceGrid == null) {
				pauseTime(ms);
				invoiceGrid = findElement(waitForLocator);
				count++;
				if (count == retry) {
					break;
				}
				WebElement clickElement = findElement(clickElementLocator);
				if (clickElement != null) {
					WebdriverUtils.clickButtonThroughJS(clickElement, "Click Element");
				}
			}
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void implicitWait(int ms) throws Exception {
		try {
			DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(ms, TimeUnit.MILLISECONDS);
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void HoverOnElement(WebElement wElement) throws Exception {
		try {
			WebElement CLP_menu = DriverFactory.getInstance().getDriver().findElement(By.xpath("wElement"));
			Actions builder = new Actions(DriverFactory.getInstance().getDriver());
			builder.moveToElement(CLP_menu).build().perform();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	// </editor-fold>

	// <editor-fold desc="Click Methods">

	public static void clickElement(WebElement element, String controlName) throws Exception {
		try {
			element.click();
			Log.info("Click action is performed on '" + controlName + "' button");
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void Page_Zoomin(int zoomlevel) {
		for (int i = 0; i < zoomlevel; i++) {
			DriverFactory.getInstance().getDriver().findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}

	public static void Page_ZoomOut(int zoomlevel) {
		// To zoom out page 4 time using CTRL and - keys.
		for (int i = 0; i < zoomlevel; i++) {
			DriverFactory.getInstance().getDriver().findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}

	public static void clickElement(WebElement element) throws Exception {
		try {
			element.click();
			Log.info("Click action is performed...");
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void clickElement(By by) throws Exception {
		try {
			DriverFactory.getInstance().getDriver().findElement(by).click();
			Log.info("Click action is performed...");
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void clickAction(WebElement element) throws Exception {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//com//config//config.properties");
		Properties pro = new Properties();
		pro.load(fs);
		//if(pro.getProperty("Browser").equalsIgnoreCase("ie11") || pro.getProperty("Browser").equalsIgnoreCase("firefox"))
		swipeDownUntillElement(element);
		Thread.sleep(2000);
		try {
			if(pro.getProperty("Browser").equalsIgnoreCase("ie11") || pro.getProperty("Browser").equalsIgnoreCase("firefox")) {
			WebdriverUtils.isClickable(element);
			Thread.sleep(2000);
			}
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.click(element).perform();
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
			
			//recheck(element);
		}
	}

	/*public static void recheck(WebElement element) throws Exception {
		try {
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.click(element).perform();
		}
		catch(Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
		
	}*/
	public static void clickActionWithSwipe(WebElement element) throws Exception {
		    swipeDownUntillElement(element);
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.click(element).perform();
			
	}

	
	public static void clickActionWithoutSwipe(WebElement element) throws Exception {
		try {
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.click(element).perform();
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void doubleClick(WebElement element) throws Exception {
		try {
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.moveToElement(element).doubleClick().perform();
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void clickElementActions(WebElement element) throws Exception {
		try {
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.click(element).perform();
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void clickButtonThroughJS(WebElement element, String controlName) throws Exception {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].click();", element);
			Log.info("Click action through javascript is performed on '" + controlName + "' button");
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void doubleClickButtonThroughJS(WebElement element, String controlName) throws Exception {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			String event = "var event=new MouseEvent(\"dblclick\",{view:window,bubbles:!0,cancelable:!0});document.querySelector(\"div[id='InProcessGrid']>div>table>tbody>tr.rowselected>td:nth-child(1)\").dispatchEvent(event);";
			// executor.executeScript("arguments[0].dispatchEvent(" + event +
			// ");", element);
			executor.executeScript(event);
			Log.info("Click action through javascript is performed on '" + controlName + "' button");
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	// </editor-fold>

	// <editor-fold desc="SendKeys Methods">

	public static void sendKeys(By locator, String text) throws Exception {
		try {
			Log.info("Sendkeys " + text);
			WebElement wElement = findElement(locator);
			if (wElement != null)
				wElement.sendKeys(text);
			else
				throw new Exception("Can't find the Element, Locator: " + locator.toString());
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeys(WebElement wElement, String text) throws Exception {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//com//config//config.properties");
		Properties pro = new Properties();
		pro.load(fs);
//		if(pro.getProperty("Browser").equalsIgnoreCase("ie11") || pro.getProperty("Browser").equalsIgnoreCase("safari"))
//			swipeDownUntillElement(wElement);
		try {
			wElement.clear();
			wElement.sendKeys(text);
			Log.info("Sendkeys " + text);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeysWithClear(WebElement wElement, String text) throws Exception {
		try {
			wElement.clear();
			wElement.sendKeys(text);
			Log.info("Sendkeys with Clear " + text);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeys(WebElement wElement, Keys key) throws Exception {
		try {
			wElement.sendKeys(key);
			Log.info("Sendkeys " + key);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeys(By locator, Keys key) throws Exception {
		try {
			DriverFactory.getInstance().getDriver().findElement(locator).sendKeys(key);
			Log.info("Sendkeys " + key);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeys(int rowNumber, WebElement element, String columnName) throws Exception {
		try {
			sendKeys(rowNumber, element, columnName, columnName);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeys(int rowNumber, WebElement element, String excelColumnName, String elementName)
			throws Exception {
		try {
			String dataValue = ExcelUtils.getCellData(rowNumber, excelColumnName);
			if (!dataValue.isEmpty())
				WebdriverUtils.sendKeys(element, elementName, dataValue);
			Log.info("Sendkeys " + dataValue);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeys(WebElement element, String elementName, String dataValue) throws Exception {
		try {
			element.clear();
			element.sendKeys(dataValue);
			Log.info("'" + dataValue + "' is entered in '" + elementName + "' input");
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeysThroughJS(int rowNumber, WebElement element, String columnName) throws Exception {
		try {
			sendKeysThroughJS(rowNumber, element, columnName, columnName);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeysThroughJS(int rowNumber, WebElement element, String columnName, String fieldName)
			throws Exception {
		try {
			String dataValue = ExcelUtils.getCellData(rowNumber, columnName);
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].value = arguments[1]", element, dataValue);
			Log.info("'" + dataValue + "' is entered in '" + fieldName + "' input", true);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeysThroughJS(String dataValue, WebElement element, String fieldName) throws Exception {
		try {
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].value = arguments[1]", element, dataValue);
			Log.info("'" + dataValue + "' is entered in '" + fieldName + "' input", true);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void editHTMLText(String dataValue, WebElement element) throws Exception {
		try {
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].innerHTML = arguments[1]", element, dataValue);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void editValueThroughJS(String dataValue, WebElement element) throws Exception {
		try {
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].value = arguments[1]", element, dataValue);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void sendKeysThroughActions(WebElement element, String dataValue) throws Exception {
		try {
			Actions action = new Actions(DriverFactory.getInstance().getDriver());
			action.moveToElement(element).sendKeys(dataValue).perform();
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	// </editor-fold>

	// <editor-fold desc="Check/Uncheck Methods">

	public static void checkElement(By locator) throws Exception {
		try {
			WebElement checkBox = DriverFactory.getInstance().getDriver().findElement(locator);
			if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
				throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
			}
			if (!checkBox.isSelected()) {
				checkBox.click();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void checkElement(WebElement checkBox) throws Exception {
		try {
			if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
				throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
			}
			if (!checkBox.isSelected()) {
				checkBox.click();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void unCheckElement(By locator) throws Exception {
		try {
			WebElement checkBox = DriverFactory.getInstance().getDriver().findElement(locator);
			if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
				throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
			}
			if (checkBox.isSelected()) {
				checkBox.click();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void unCheckElement(WebElement checkBox) throws Exception {
		try {
			if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
				throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
			}
			if (checkBox.isSelected()) {
				checkBox.click();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static boolean isChecked(By locator) throws Exception {
		try {
			WebElement checkBox = DriverFactory.getInstance().getDriver().findElement(locator);
			if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
				throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
			}
			if (checkBox.getAttribute("checked").equals("checked")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			return false;
		}
	}

	public static void Check(int rowNumber, WebElement element, String columnName) throws Exception {
		try {
			Check(rowNumber, element, columnName, columnName);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void Check(int rowNumber, WebElement element, String columnName, String fieldName) throws Exception {
		String dataValue = ExcelUtils.getCellData(rowNumber, columnName);
		if ("Yes".equalsIgnoreCase(dataValue)) {
			if (!element.isSelected())
				element.click();
			Log.info("It is checked '" + fieldName + "' checkElement box");
		} else {
			if (element.isSelected())
				element.click();
			Log.info("It is not checked '" + fieldName + "' checkElement box");
		}
	}

	// </editor-fold>

	// <editor-fold desc="SelectOption Methods">

	public static void selectOption(int rowNumber, WebElement element, String columnName) throws Exception {
		try {
			selectOption(rowNumber, element, columnName, columnName);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void selectOption(int rowNumber, WebElement element, String columnName, String fieldName)
			throws Exception {
		Boolean founded = false;
		String fullText = "";
		String dataValue = ExcelUtils.getCellData(rowNumber, columnName);
		Select select = new Select(element);
		List<WebElement> list = select.getOptions();
		for (WebElement option : list) {
			fullText = option.getText();
			if (fullText.contains(dataValue)) {
				select.selectByVisibleText(fullText);
				founded = true;
				break;
			}
		}

		if (founded)
			Log.info(String.format("'" + fullText + "' is selected in '" + fieldName + "' drop down list", dataValue));
		else {
			Log.error("Option '" + fullText + "' is not found in '" + fieldName + "' drop down list");
			// I put this line intentionally to generate an exception.
			(new Select(element)).selectByVisibleText(dataValue);
		}
	}

//	public static void selectByValue(By locator, String value) throws Exception {
//		try {
//			Select select = new Select(DriverFactory.getInstance().getDriver().findElement(locator));
//			select.selectByValue(value);
//		} catch (Exception ex) {
//			Log.error(ex.getMessage());
//			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
//			throw ex;
//		}
//	}
	public static String selectedValue(String value){
		try {
			WebElement welement = null;
			Select select = new Select(welement);
			WebElement option = select.getFirstSelectedOption();
			option.getAttribute(value);			
		} catch (Exception ex) {
			
		}
		return value;					
	}

	public static void selectByValue(WebElement welement, String value) throws Exception {
		try {
			Select select = new Select(welement);
			select.selectByValue(value);
		} catch (Exception ex) {
			
		}
	}

	public static void selectByIndex(By locator, int index) throws Exception {
		try {
			Select select = new Select(DriverFactory.getInstance().getDriver().findElement(locator));
			select.selectByIndex(index);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void selectByIndex(WebElement webElement, int index) throws Exception {
		try {
			Select select = new Select(webElement);
			select.selectByIndex(index);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
		
		
	}

	public static void selectByVisibleText(By locator, String text) throws Exception {
		try {
			Select select = new Select(DriverFactory.getInstance().getDriver().findElement(locator));
			select.selectByVisibleText(text);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void selectByVisibleText(WebElement welement, String text) throws Exception {
		try {
			Select select = new Select(welement);
			select.selectByVisibleText(text);
		} catch (Exception ex) {
//			Log.error(ex.getMessage());
//			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
//			throw ex;
		}
	}

	public static boolean doesElementExist(By locator) throws Exception {
		try {
			if (DriverFactory.getInstance().getDriver().findElements(locator).size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			return false;
		}

	}
	
	
	

	// </editor-fold>

	// <editor-fold desc="Element Methods">

	public static List<WebElement> dropDownList(WebElement eElement, String tagName) throws Exception {
		try {
			List<WebElement> dropDownList = eElement.findElements(By.tagName(tagName));
			return dropDownList;
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static WebElement findElement(By locator) throws Exception {
		try {
			if (DriverFactory.getInstance().getDriver().findElements(locator).size() > 0) {
				return DriverFactory.getInstance().getDriver().findElement(locator);
			} else {
				return null;
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			return null;
		}
	}

	
	public static int countOfElements(String Locator) throws Exception{
		
		int i=DriverFactory.getInstance().getDriver().findElements(By.xpath(Locator)).size();
		return i;
		
	}

	
	
	
	
	public static List<WebElement> findElements(By locator) throws Exception {
		try {
			if (DriverFactory.getInstance().getDriver().findElements(locator).size() > 0) {
				return DriverFactory.getInstance().getDriver().findElements(locator);
			} else {
				return null;
			}
		} catch (Exception ex) {
			Log.error("Class WebdriverUtils | Method pauseTime | Exception occurred: Exception: " + ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static boolean isElementDisplayed(By locator) throws Exception {
		try {
			if (doesElementExist(locator)) {
				return DriverFactory.getInstance().getDriver().findElement(locator).isDisplayed();
			} else {
				return false;
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static int getElementCount(By locator) throws Exception {
		try {
			List<WebElement> elementsFound = DriverFactory.getInstance().getDriver().findElements(locator);
			return elementsFound.size();
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static Boolean existsElement(By by, Boolean extraTime) throws Exception {
		Boolean found = false;
		try {
			WebElement element = WebdriverUtils.findElement(by);
			if (element != null)
				found = true;

			if (extraTime)
				normalizeWaitingTimeForElement();

		} catch (Exception e) {
			Log.error(e.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			if (extraTime)
				normalizeWaitingTimeForElement();
		}
		return found;
	}

	public static void clickAndHold(By locator) throws Exception {
		try {
			WebElement webElement = DriverFactory.getInstance().getDriver().findElement(locator);

			if (webElement != null) {
				new Actions(DriverFactory.getInstance().getDriver()).clickAndHold(webElement).perform();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void clickAndHold(WebElement webElement) throws Exception {
		try {
			if (webElement != null) {
				new Actions(DriverFactory.getInstance().getDriver()).clickAndHold(webElement).perform();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void releaseElement(By locator) throws Exception {
		try {
			WebElement webElement = DriverFactory.getInstance().getDriver().findElement(locator);

			if (webElement != null) {
				new Actions(DriverFactory.getInstance().getDriver()).release(webElement).perform();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void releaseElement(WebElement webElement) throws Exception {
		try {
			if (webElement != null) {
				new Actions(DriverFactory.getInstance().getDriver()).release(webElement).perform();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void swipeDownUntillElement(WebElement webElement) throws Exception {
		try {
			Thread.sleep(1500);
			int count=0;
//			System.out.println(isVisibleInViewport(webElement));
			while(!isVisibleInViewport(webElement)) {
			scrollToElement("300");	
			Thread.sleep(400);
			count++;
			if(count==30)
		    break;
			}	
			Thread.sleep(1000);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	
	public static void swipeUpUntillElement(WebElement webElement) throws Exception {
		try {
			int count=0;
			System.out.println(isVisibleInViewport(webElement));
			while(!isVisibleInViewport(webElement)) {
			scrollToElement("-300");	
			Thread.sleep(300);
			count++;
			if(count==30)
		    break;
			}	
			Thread.sleep(1000);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	
	
	public static void clickAt(By locator, int x, int y) throws Exception {
		try {
			WebElement webElement = DriverFactory.getInstance().getDriver().findElement(locator);

			if (webElement != null) {
				Actions builder = new Actions(DriverFactory.getInstance().getDriver());
				builder.moveToElement(webElement).moveByOffset(x, y).click().perform();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void clickAt(WebElement webElement, int x, int y) throws Exception {
		try {
			if (webElement != null) {
				Actions builder = new Actions(DriverFactory.getInstance().getDriver());
				builder.moveToElement(webElement).moveByOffset(x, y).click().perform();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void submitElement(By locator) throws Exception {
		try {
			WebElement webElement = DriverFactory.getInstance().getDriver().findElement(locator);

			if (webElement != null) {
				webElement.submit();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static void submitElement(WebElement webElement) throws Exception {
		try {
			if (webElement != null) {
				webElement.submit();
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static WebElement getElement(By by, String name, String type, String place) throws Exception {
		return getElement(by, name, type, place, null);
	}

	public static WebElement getElement(By by, String name, String type, String place, WebElement father)
			throws Exception {
		WebElement element;
		String messageSuccess = "Found - Element: '" + name + "', Type: '" + type + "', In: '" + place + "'";
		String messageError = "Not Found - Element: '" + name + "', Type: '" + type + "', In: '" + place + "'";

		try {
			if (father != null)
				element = father.findElement(by);
			else
				element = DriverFactory.getInstance().getDriver().findElement(by);
			Log.info(messageSuccess);
		} catch (Exception ex) {
			Log.error(messageError);
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
		return element;
	}

	public static String getCssValue(WebElement webElement, String value) throws Exception {
		try {
			if (webElement != null) {
				return webElement.getCssValue(value);
			}
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
		return null;
	}

	public static void goToURL(String url) throws Exception {
		try {
			Log.info("Executing Goto method...");
			// String fullUrl = Constant.URL + url;

			Log.info(url);
			if (DriverFactory.getInstance().getDriver() == null) {
				Log.error("DriverFactory.getInstance().getDriver() is null");
				throw new Exception("DriverFactory.getInstance().getDriver() is null");
			}
			DriverFactory.getInstance().getDriver().get(url);
			WebdriverUtils.pauseTime(2000);
			System.out.println(url);
			Log.info("Going to " + url, true);
			
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error("Class WebdriverUtils | Method goToURL | Exception occurred while going to a page : "
					+ ex.getMessage());
			throw ex;
		}
	}
	
	// </editor-fold>

	// <editor-fold desc="Windows Methods">

	public static void displayWindow(WebElement button, String buttonName, By window) throws Exception {
		try {

			WebdriverUtils.clickElement(button, buttonName);
			WebdriverUtils.pauseTime(3000);
			// Check if the Dialog is displayed
			if (!WebdriverUtils.existsElement(window, false)) {
				Thread.sleep(3000);
				WebdriverUtils.clickElement(button); // If the Add dialog does
														// not appear, click add
														// button again
				Log.info("Click action is performed on '" + buttonName + "' button by second time");
				WebdriverUtils.waitForAllElementsLocatedBy(window);
			}
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error("Class WebdriverUtils | Method displayWindow | Exception: " + ex.getMessage());
			throw ex;
		}
	}

	public static void scrollToElement(By locator) throws Exception {
		try {
			Locatable scrollToItem = (Locatable) DriverFactory.getInstance().getDriver().findElement(locator);
			int y = scrollToItem.getCoordinates().inViewPort().getY();
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("window.scrollBy(0," + y + ");");
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void scrollToElement(WebElement wElement) throws Exception {
		try {
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript(
	                "arguments[0].scrollIntoView();", wElement);
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void moveToElementByXpath(String xpath) throws Exception {
		try {
			WebElement mapObject = DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath));
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();", mapObject);
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void moveToElementByCssSelector(String selector) throws Exception {
		try {
			WebElement mapObject = DriverFactory.getInstance().getDriver().findElement(By.cssSelector(selector));
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();", mapObject);
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void moveToElement(WebElement wElement) throws Exception {
		try {
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();", wElement);
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void moveToElementByActions(By locator) throws Exception {
		try {
			WebElement element = DriverFactory.getInstance().getDriver().findElement(locator);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(element);
			actions.perform();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void moveToElementByActions(WebElement wElement) throws Exception {
		try {
			Thread.sleep(2000);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());

			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "//src//com//config//config.properties");
			Properties pro = new Properties();
			pro.load(fs);
			if (pro.getProperty("Browser").contains("Firefox") || pro.getProperty("Browser").contains("firefox"))
				actions.moveToElement(wElement).moveToElement(wElement).build().perform();
			else
				actions.moveToElement(wElement).build().perform();

		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static Boolean switchBetweenWindows() throws Exception {
		try {
			Set<?> listOfWindows = DriverFactory.getInstance().getDriver().getWindowHandles();
			if (listOfWindows.size() != 2) {
				if (listOfWindows.size() > 2) {
					throw new TooManyWindowsException();
				} else {
					throw new TooFewWindowsException();
				}
			}
			String currentWindow = DriverFactory.getInstance().getDriver().getWindowHandle();
			for (Iterator<?> i = listOfWindows.iterator(); i.hasNext();) {
				String selectedWindowHandle = i.next().toString();
				if (!selectedWindowHandle.equals(currentWindow)) {
					DriverFactory.getInstance().getDriver().switchTo().window(selectedWindowHandle);
					return true;
				}
			}
			// Just in case something goes wrong
			throw new UnableToFindWindowException("Unable to switch windows!");

		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
	}

	
	public static Boolean switchBetweenWindowsForTitle(String Title) throws Exception {
		try {
			Set<?> listOfWindows = DriverFactory.getInstance().getDriver().getWindowHandles();
			System.out.println("No of windows : "+listOfWindows.size());
			String currentWindow = DriverFactory.getInstance().getDriver().getWindowHandle();
			Iterator<?> i = listOfWindows.iterator();
				while(i.hasNext()){
				String selectedWindowHandle = i.next().toString();
				if (!selectedWindowHandle.equals(currentWindow)) {
					System.out.println("Searching For Windows");
					DriverFactory.getInstance().getDriver().switchTo().window(selectedWindowHandle);
					System.out.println("Switched to Window");
					System.out.println(DriverFactory.getInstance().getDriver().getTitle());
					if(DriverFactory.getInstance().getDriver().getTitle().contains(Title)){
				    System.out.println("I got my window");
					break;
					}
						
					return true;
				}
			}
			// Just in case something goes wrong
			throw new UnableToFindWindowException("Unable to switch windows!");

		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
	}
	public static Boolean switchToWindowTitled(String windowTitle) throws Exception {
		try {
			DriverFactory.getInstance().getDriver().switchTo().window(windowTitle);
			return true;
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
	}
	
	public static Boolean switchToiframe(WebElement frame) throws Exception {
		try {
			DriverFactory.getInstance().getDriver().switchTo().frame(frame);
			System.out.println("Switched");
			return true;
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
	}
	
	public static String switchToAlertText() throws Exception {
		try {
			String mess = DriverFactory.getInstance().getDriver().switchTo().alert().getText();
			System.out.println("Switched");
			return mess;
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
	}
	
	public static Boolean switchToiframeMultiple(WebElement frame,String expframe) throws Exception {
		try {
			//DriverFactory.getInstance().getDriver().switchTo().frame(frame);
			List<WebElement> iframeElements = DriverFactory.getInstance().getDriver().findElements(By.tagName("iframe"));
			System.out.println("The total number of iframes are " + iframeElements.size());
			for(int i=0;i<iframeElements.size();i++){
				DriverFactory.getInstance().getDriver().switchTo().frame(iframeElements.size());
				if(DriverFactory.getInstance().getDriver().findElement(By.xpath(expframe)).isDisplayed()){
				}
				else{
					DriverFactory.getInstance().getDriver().switchTo().defaultContent();
					DriverFactory.getInstance().getDriver().switchTo().parentFrame();
				}
			}
		return true;
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
	}
	

	public static void maximizeWindow() throws Exception {
		try {
			DriverFactory.getInstance().getDriver().manage().window().maximize();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static boolean isAlertPresent() throws Exception {
		boolean alertPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
			wait.until(ExpectedConditions.alertIsPresent());
			DriverFactory.getInstance().getDriver().switchTo().alert();
			alertPresent = true;
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
		return alertPresent;
	}

	public static void dismissAlert() throws Exception {
		try {
			Alert alert = DriverFactory.getInstance().getDriver().switchTo().alert();
			alert.dismiss();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}
	
	public static void acceptAlert() throws Exception {
		try {
			Alert alert = DriverFactory.getInstance().getDriver().switchTo().alert();
			alert.accept();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static String getPageTitle() throws Exception {
		String title = "";
		try {
			title = DriverFactory.getInstance().getDriver().getTitle();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
		return title;
	}

	public static String getWindowURL() throws Exception {
		String url;
		try {
			url = DriverFactory.getInstance().getDriver().getCurrentUrl();
			return url;
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			return null;
		}
	}

	public static void closeBrowser() throws Exception {
		try {
			DriverFactory.getInstance().getDriver().close();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void getBackInBrowser() throws Exception {
		try {
			DriverFactory.getInstance().getDriver().navigate().back();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}

	}

	public static void getForwardInBrowser() throws Exception {
		try {
			DriverFactory.getInstance().getDriver().navigate().forward();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void deleteAllCookies() throws Exception {
		try {
			DriverFactory.getInstance().getDriver().manage().deleteAllCookies();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}

	public static void refreshPage() throws Exception {
		try {
			DriverFactory.getInstance().getDriver().navigate().refresh();
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error(ex.getMessage());
			throw ex;
		}
	}
	public static String sendUniqueData(WebElement wElement, String text) throws Exception {
	    try {
	     DateFormat dateFormat = new SimpleDateFormat("ddHHmmss"); 
	        Date date = new Date();                                           
	        String random_number = dateFormat.format(date);
	     wElement.sendKeys(text+random_number+"@gmail.com");
	     String txt = text+random_number;
	     Log.info("Sendkeys " + text);
	     return txt;
	    } catch (Exception ex) {
	     Log.error(ex.getMessage());
	     captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
	     throw ex;
	    }
	  
	   }

	public static void executeJS(String jsCode) throws Exception {
		try {
			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript(jsCode);
		} catch (Exception ex) {
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			Log.error("Class WebdriverUtils | Method executeJS | Exception occurred while executing javascript. Code: "
					+ jsCode + "   Exception: " + ex.getMessage());
			throw ex;
		}
	}
	

	// </editor-fold>

	// <editor-fold desc="Java Script Methods">

	public static String generateTimestamp() throws Exception {
		try {

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSS");
			String value = simpleDateFormat.format(new Date());
			if (value.length() < 17) {
				value = simpleDateFormat.format(new Date());
			}
			return value;
		} catch (Exception ex) {
			Log.error("Class WebdriverUtils | Method generateTimestamp | Exception occurred: Exception: "
					+ ex.getMessage());
			throw ex;
		}
	}

	// </editor-fold>

	// <editor-fold desc="WebdriverUtils Methods">

	public static void pauseTime(int timeToSleep) throws Exception {
		try {
			Thread.sleep(timeToSleep);
		} catch (Exception ex) {
			Log.error("Class WebdriverUtils | Method pauseTime | Exception occurred: Exception: " + ex.getMessage());
			throw ex;
		}
	}

	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception ex) {
			Log.error("Class WebdriverUtils | Method getTestCaseName | Exception desc : " + ex.getMessage());
			throw ex;
		}
	}

	public static void captureScreenShot(WebDriver driver, String methodName) throws Exception {
		try {
			// logger.info("Take Screenshot");
			DateFormat dfn = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat df = new SimpleDateFormat("MMM . dd . yyyy _HH:mm:ss");

			String formattedDate = df.format(new Date());
			String folderName = dfn.format(new Date());

			// ScreenShots will be saved in different levels in folders, i.e,
			// /#YEAR/#MONTH/#DAY.jpg
			formattedDate = formattedDate.replace(" ", "");
			formattedDate = formattedDate.replace(":", "");
			String filePath = System.getProperty("user.dir") + "/ScreenShots/" + folderName.replace("/", "") + "/"
					+ WebdriverUtils.getTestName() + "/" + methodName + "_" + formattedDate + ".jpg";

			File screenShot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
			Files.createParentDirs(new File(filePath));
			Files.copy(screenShot, new File(filePath));
			// File.createTempFile("", "", new File(filePath));
		} catch (Exception ex) {
			Log.error("Class WebdriverUtils | Method Capture Screenshot | Exception desc : " + ex.getMessage());
			throw ex;
		}
	}

	public static String getDate(int days) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		Date tmpDate = calendar.getTime();
		String finalDate = dateFormat.format(tmpDate);
		return finalDate;
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		Date tmpDate = calendar.getTime();
		String finalDate = dateFormat.format(tmpDate);
		return finalDate;
	}


	public enum BrowserEnum {
		FIREFOX, CHROME, IE11, IE10, IE9, SAFARI, SAUCELABS
	}
	
	public static String findElementString(String string) throws Exception {
		  try {
		  return string;
		  } catch (Exception ex) {
		   Log.error(ex.getMessage());
		   captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
		   return null;
		  }
		 }

	 public static boolean isClickable(WebElement element)      
		{
		try
		{
		   WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), 5);
		   wait.until(ExpectedConditions.elementToBeClickable(element));
		   return true;
		}
		catch (Exception e)
		{
		  return false;
		}
		}
		
	 
	 public static WebElement StringToWebElement(String string) throws Exception {
		  return DriverFactory.getInstance().getDriver().findElement(By.xpath(string));
	}
	 
	 public static String getTextOfSelectedItem(WebElement element){
		 Select select = new Select(element);
		 WebElement option = select.getFirstSelectedOption();
		 String defaultItem = option.getText();
		 System.out.println(defaultItem );
		 return defaultItem;
	 }
	 
	public static Boolean isVisibleInViewport(WebElement element){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return (Boolean)((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript(
		      "var elem = arguments[0],                 " +
		      "  box = elem.getBoundingClientRect(),    " +
		      "  cx = box.left + box.width / 2,         " +
		      "  cy = box.top + box.height / 2,         " +
		      "  e = document.elementFromPoint(cx, cy); " +
		      "for (; e; e = e.parentElement) {         " +
		      "  if (e === elem)                        " +
		      "    return true;                         " +
		      "}                                        " +
		      "return false;                            "
		      , element);
		}
	
	
	
	public static void swipeUntillElement(WebElement webElement) throws Exception {
		try {
			int count=0;
			System.out.println(isVisibleInViewport(webElement));
			while(!isVisibleInViewport(webElement)) {
			scrollToElement("100");	
			count++;
			if(count==13)
		    break;
			}		
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(DriverFactory.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}

	public static String generateRandomEmail() {
		String SALTCHARS = "ABCDEFGHIJKLMNO";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr.toLowerCase()+"@gmail.com";

	}
	
	public static void checkPageIsReady() {

		  JavascriptExecutor js = (JavascriptExecutor)CreateDriver.getInstance().getDriver();

		  //Initially bellow given if condition will check ready state of page.
		  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		   System.out.println("Page Is loaded.");
		   return; 
		  } 

		  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
		  //You can replace your value with 25 If you wants to Increase or decrease wait time.
		  for (int i=0; i<25; i++){ 
		   try {
		    Thread.sleep(1000);
		    }catch (InterruptedException e) {} 
		   //To check page ready state.
		   if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		    break; 
		   }   
		  }
	 }
		  public static void clickThroughJS(WebElement element) throws Exception {
				try {
					JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
					executor.executeScript("arguments[0].click();", element);
					Log.info("Click action through javascript is performed on '"  + "' button");
				} catch (Exception ex) {
					Log.error(ex.getMessage());
					captureScreenShot(CreateDriver.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
					throw ex;
				}
			}
	
	
	
	public static ArrayList<String> sortAscending(ArrayList<String> arrayList) {         
		  Collections.sort(arrayList,new SortIgnoreCase()); 
		  return arrayList;
	  }  
	
	public static ArrayList<String> sortDescending(ArrayList<String> arrayList) {         
	    Collections.sort(arrayList, Collections.reverseOrder());         
	    return arrayList;     
	  }
	
	public static List<Date> dateFormat(List<Date> inputListName) {
		List<Date> listDates = new ArrayList<Date>();
	    @SuppressWarnings("unused")
		DateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
		return listDates;
	}
	
	
	public static List<Date> sortAscending_date(List<Date> arrayList) {         
		  Collections.sort(arrayList); 
		  return arrayList;
	  }  
	
	  public static List<Date> sortDescending_date(List<Date> arrayList) {         
	    Collections.sort(arrayList, Collections.reverseOrder());         
	    return arrayList;     
	  }
	
	public static class SortIgnoreCase implements Comparator<Object> {
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }

	public static String getProperty(String string) throws Exception{
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//com//config//config.properties");
		Properties pro = new Properties();
		pro.load(fs);
		return pro.getProperty(string);
	}
	

	public static void clickAndAction(WebElement element) throws Exception {
		for(int i=0; i<=5; i++) {
			try {
				WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Constant.WaitingSeconds);
				Actions action = new Actions(DriverFactory.getInstance().getDriver());
				action.moveToElement(element).build().perform();
				WebElement clickedElement = wait.until(ExpectedConditions.elementToBeClickable(element)); 
				action.moveToElement(clickedElement).build().perform();
				Thread.sleep(2000);
				element.click();
				break;
			} catch (Exception e) {
				Thread.sleep(2000);
				JavascriptExecutor js = (JavascriptExecutor)DriverFactory.getInstance().getDriver();
				js.executeScript("window.scrollBy(0,400)");
				continue;
			}
		}
	}
	
	public static String getTextThroughJS(WebElement element) throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor)DriverFactory.getInstance().getDriver();
			String data = (String) jse.executeScript("return arguments[0].value", element);
			return data;
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(CreateDriver.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	
	public static void moveToElementThroughActions(WebElement element) throws Exception {
		try {
			Actions act = new Actions(DriverFactory.getInstance().getDriver());
			act.moveToElement(element).build().perform();
		} catch (Exception ex) {
			Log.error(ex.getMessage());
			captureScreenShot(CreateDriver.getInstance().getDriver(), new Exception().getStackTrace()[0].getMethodName());
			throw ex;
		}
	}
	
	public static WebElement xpathSimple(String tagName, String textContains) throws Exception {
		return WebdriverUtils.findElement(By.xpath("//"+tagName+"[contains(text(),'"+textContains+"')]"));		
	}
	
	public static WebElement xpathSimpleIndex(String tagName, String textContains, int index) throws Exception {
		return WebdriverUtils.findElement(By.xpath("(//"+tagName+"[contains(text(),'"+textContains+"')])["+index+"]"));		
	}
	
	public static WebElement xpathAttribute(String tagName, String attribute, String atrValue) throws Exception {
		return WebdriverUtils.findElement(By.xpath("//"+tagName+"[@"+attribute+"='"+atrValue+"']"));		
	}
	
	public static WebElement xpathAttributeIndex(String tagName, String attribute, String atrValue, int index) throws Exception {
		return WebdriverUtils.findElement(By.xpath("(//"+tagName+"[@"+attribute+"='"+atrValue+"'])["+index+"]"));		
	}
	
	public static WebElement xpathComplex(String tagName, String textContains, int index) throws Exception {
		return WebdriverUtils.findElement(By.xpath("(//"+tagName+textContains+")["+index+"]"));		
	}
		
	public static List<WebElement> xpathsSimple(String tagName, String textContains) throws Exception {
		return WebdriverUtils.findElements(By.xpath("//"+tagName+"[contains(text(),'"+textContains+"')]"));		
	}
	
	public static List<WebElement> xpathsSimpleIndex(String tagName, String textContains, int index) throws Exception {
		return WebdriverUtils.findElements(By.xpath("(//"+tagName+"[contains(text(),'"+textContains+"')])["+index+"]"));		
	}
	
	public static List<WebElement> xpathsAttribute(String tagName, String attribute, String atrValue) throws Exception {
		return WebdriverUtils.findElements(By.xpath("//"+tagName+"[@"+attribute+"='"+atrValue+"']"));		
	}
	
	public static List<WebElement> xpathsAttributeIndex(String tagName, String attribute, String atrValue, int index) throws Exception {
		return WebdriverUtils.findElements(By.xpath("(//"+tagName+"[@"+attribute+"='"+atrValue+"'])["+index+"]"));		
	}
	
	public static List<WebElement> xpathsComplex(String tagName, String textContains, int index) throws Exception {
		return WebdriverUtils.findElements(By.xpath("(//"+tagName+textContains+")["+index+"]"));		
	}
}
