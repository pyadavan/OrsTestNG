package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.WebdriverUtils;

public class SRPObjects {
	public static WebElement srpFirstLink() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//a[@class='product-item-link']"));
	}
	

}
