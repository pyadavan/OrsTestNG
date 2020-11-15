package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.WebdriverUtils;

public class LoginObjects {
	public static WebElement login_Link() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[contains(text(),'Login Or Register')]"));
	}
	public static WebElement login_email() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//input[@id='dialog-email']"));
	}
	public static WebElement login_Pwd() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//input[@id='dialog-pass']"));
	}
	public static WebElement login_Button() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@id='login-primary-send']"));
	}

}
