package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.WebdriverUtils;

public class HomepageObjects {
	public static WebElement searchField() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//input[@name='q']"));
	}
	public static WebElement searchButton() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("//button[@title='Search']"));
	}	
	public static WebElement weekDealSection() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("(//strong[contains(text(),'This Week')]//ancestor::div[@class='block-title']/following-sibling::div[@class='block-content'])"));
	}
	public static WebElement quickViewWeekDealButton() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("("+weekDealSection()+"//button/span[contains(text(),'Quick View')]"+")"+"[1]"));
	}
	public static WebElement weekDealFirstProductLink() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("("+weekDealSection()+"//a[@class='product-item-link']"+")"+"[1]"));
	}
	public static WebElement weekDealFirstSKU() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("("+weekDealSection()+"//div[@itemprop='sku']"+")"+"[1]"));
	}
	public static WebElement weekDealFirstPrice() throws Exception{
		  return WebdriverUtils.findElement(By.xpath("("+weekDealSection()+"//span[@data-price-type='finalPrice']/span"+")"+"[1]"));
	}
	

}
