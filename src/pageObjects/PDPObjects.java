package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.WebdriverUtils;

public class PDPObjects {
	public static WebElement productName() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//span[@itemprop='name']"));
	}
	public static WebElement productPrice() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//span[@data-price-type='finalPrice'])[2]"));
	}
	public static WebElement productSku() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//div[@itemprop='sku']"));
	}
	public static WebElement productQty() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//input[@id='qty']"));
	}
	public static WebElement shipToHomeRadio() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//p[contains(text(),'Ship to home')]"));
	}
	public static WebElement inStorePickupRadio() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//p[contains(text(),'Instore Pickup Available')]"));
	}
	public static WebElement productAddToCartButton() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@id='product-addtocart-button']"));
	}
	public static WebElement productAddToWishButton() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@data-action='add-to-wishlist']"));
	}
	public static WebElement updateButton() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@id='product-updatecart-button']"));
	}
	public static WebElement zipCodeField() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//input[@id='store-zipcode']"));
	}
	public static WebElement goZipButton() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@title='Search Store']"));
	}
	public static WebElement storeSearchResult41() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//div[@data-id='41']"));
	}

}
