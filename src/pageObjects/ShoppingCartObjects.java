package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.WebdriverUtils;

public class ShoppingCartObjects {
	public static WebElement productName() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//strong[@class='product-item-name']/a)[2]"));
	}
	public static WebElement productPrice() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//span[@class='cart-price']/span[@class='price'])[2]"));
	}
	public static WebElement productQty() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//input[@title='Quantity']"));
	}
	public static WebElement editCartFirstproduct() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//a[@class='action action-edit'])[1]"));
	}
	public static WebElement cartCount() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//span[@class='counter-number']"));
	}

}
