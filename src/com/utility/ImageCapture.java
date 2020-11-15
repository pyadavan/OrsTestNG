package com.utility;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.utility.DriverFactory;
import com.utility.Global_VARS;
/**
 * Image Capture and compare class
 * 
 * @author Susovan
 *
 */
public class ImageCapture {

	// constructor
	public ImageCapture() throws Exception {

	}

	/**
	 * screenShot - method that takes iTestResult as parameter
	 * 
	 * @param result
	 *            - The Result of the Test
	 * @return String
	 * @throws Exception
	 */
	public static String screenShot(ITestResult result) throws Exception {
//		DateFormat stamp = new SimpleDateFormat("MM.dd.yy.HH.mm.ss");
//		Date date = new Date();

		ITestNGMethod method = result.getMethod();
		String testName = method.getMethodName();
		//return captureScreen(testName + "_" + stamp.format(date) + ".png");
		return captureScreen(testName + ".png");
	}
	
	/**
	 * captureScreen - method to capture the entire screen of the browser
	 * @param filename the file name to save it to
	 * @return
	 * @throws Exception
	 */
	public static String captureScreen(String filename) throws Exception{
	String bitmapPath=Global_VARS.BITMAP_PATH;
	WebDriver driver=DriverFactory.getInstance().getDriver();
	File screen=null;
	screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screen, new File(bitmapPath + filename));
	return filename;
	}
	
	/**
	 * imageSnapshot - method to take snap shot of element.
	 * @param element the WebElement or the mobile Element to capture
	 * @return
	 * @throws Exception
	 */
	
	public static  File imageSnaphot(WebElement element) throws Exception{
		WrapsDriver  wrapsDriver=(WrapsDriver) element;
		File screen=null;
		//capture the WebElement snapshot
		screen=((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
		//create Buffered image instance from captured snapshot
		BufferedImage img=ImageIO.read(screen);
		//get the width ,height of the WebElement for the rectangle
		int width=element.getSize().getWidth();
		int height=element.getSize().getHeight();
        Rectangle rect=new Rectangle(width,height);
        //get the location of the WebElement in the point (x,y)
        Point p=element.getLocation();
        //create image for the element using location and size
        BufferedImage dest=img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
        //BMP,bmp jpg,wbmp,png
        ImageIO.write(dest, "png", screen);
        return screen;			
	}
	
	
	/**
//	 * captureImage - method to capture individual WebElement
//	 * @param image -the image to capture
//	 * @throws Exception; 
//	 */
	
	public static void captureImage(String image) throws Exception{
		WebDriver driver=DriverFactory.getInstance().getDriver();
		WebElement getImage=driver.findElement(By.cssSelector("img[src*='"+image+"']"));
		image=image.replace(".","_"+Global_VARS.BROWSER+".");
		FileUtils.copyFile(imageSnaphot(getImage),new File(Global_VARS.BITMAP_PATH +image));
	}
	
}
