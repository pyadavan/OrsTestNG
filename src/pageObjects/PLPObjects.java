package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.WebdriverUtils;

public class PLPObjects {
	public static WebElement megaMenuL1() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//a[contains(text(),'Automotive')])[1]"));
	}
	public static WebElement megaMenuL2() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//a[contains(text(),'Auto & Farm Battery Chargers')])[1]"));
	}
	public static WebElement plpHeading() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//span[contains(text(),'Auto & Farm Battery Chargers')]"));
	}
	public static WebElement qvButton() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//span[contains(text(),'Quick View')])[1]"));
	}
	public static WebElement saveListButtonQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//span[contains(text(),'Save to List')]"));
	}
	public static WebElement saveFullDetailsButtonQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//span[contains(text(),'See Full Details')]"));
	}
	public static WebElement productSkuQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//div[@itemprop='sku']"));
	}
	public static WebElement productNameQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//span[@itemprop='name']"));
	}
	public static WebElement productPriceQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//span[@data-price-type='finalPrice']/span"));
	}
	public static WebElement shipToHomeRadioQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//p[contains(text(),'Ship to home')]"));
	}
	public static WebElement inStorePickupRadioQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//p[contains(text(),'Instore Pickup Available')]"));
	}
	public static WebElement zipCodeFieldQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//input[@id='store-zipcode']"));
	}
	public static WebElement goZipButtonQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@title='Search Store']"));
	}
	public static WebElement storeSearchResult41QV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//div[@data-id='41']"));
	}
	public static WebElement aTCButtonQV() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@id='product-addtocart-button']"));
	}

}
