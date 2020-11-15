package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.WebdriverUtils;

public class myAccountObjects {
	public static WebElement productName(int index) throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//a[@class='product-item-link'])"+"["+index+"]"));
	}
	public static List<WebElement> totalProductNames() throws Exception{
		try {
			return WebdriverUtils.findElements(By.xpath("//a[@class='product-item-link']"));			
		} catch (Exception e) {
			return null;
		}
		  
	}
	public static List<WebElement> productWishCheckboxes() throws Exception {
		try {
		return WebdriverUtils.findElements(By.xpath("//a[@class='product-item-photo']/following-sibling::div/div/input"));
		} catch (Exception e) {
			return null;
		}		
	}
	public static WebElement productWishCheckbox(int index) throws Exception {
		return WebdriverUtils.findElement(By.xpath("(//a[@class='product-item-photo']/following-sibling::div/div/input)"+"["+index+"]"));		
	}
	public static WebElement removeItem() throws Exception {
		return WebdriverUtils.findElement(By.xpath("//a[@title='Remove Item']"));		
	}
	public static WebElement myAccountLinkHeader() throws Exception {
		return WebdriverUtils.findElement(By.xpath("//li[@class='customer-welcome']//button"));		
	}
	public static WebElement myWishLinkInLeftPanel() throws Exception {
		return WebdriverUtils.findElement(By.xpath("//ul[@class='nav items']//li/strong[contains(text(),'Wish')]"));		
	}
	public static WebElement wishListTitleInSection() throws Exception {
		return WebdriverUtils.findElement(By.xpath("//div[@class='wishlist-title']"));		
	}

}
