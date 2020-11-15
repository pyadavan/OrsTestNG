package com.modules;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;

import com.utility.WebdriverUtils;

import pageObjects.HomepageObjects;
import pageObjects.LoginObjects;
import pageObjects.PDPObjects;
import pageObjects.PLPObjects;
import pageObjects.SRPObjects;
import pageObjects.ShoppingCartObjects;
import pageObjects.myAccountObjects;

public class PDPActions {
	static String proPricePDP;
	static String proNamePDP;
	static String proSkuPDP;
	
	public static String pdpValidationPriceData() throws Exception {
	return proPricePDP = PLPObjects.productPriceQV().getText().replaceAll("$", "").trim();	
	}
	
	public static String pdpValidationNameData() throws Exception {
		return proNamePDP = PLPObjects.productNameQV().getText();		
	}
	
	public static String pdpValidationSkuData() throws Exception {
		return proSkuPDP = PLPObjects.productSkuQV().getText().trim();		
	}	
	
	public static void verifyATCSimpleAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productSimple"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productSimple")));
		String producName = PDPObjects.productName().getText().trim();
		String price = PDPObjects.productPrice().getAttribute("data-price-amount");
		String qty = PDPObjects.productQty().getAttribute("value");
		WebdriverUtils.clickAction(PDPObjects.shipToHomeRadio());
		pdpCartValidation(producName, price, qty);		
	}	
	
	public static void verifyATCSimpleBOPISAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productSimple"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productSimple")));
		String producName = PDPObjects.productName().getText().trim();
		String price = PDPObjects.productPrice().getAttribute("data-price-amount");
		String qty = PDPObjects.productQty().getAttribute("value");
		WebdriverUtils.sendKeys(PDPObjects.zipCodeField(), "65109");
		WebdriverUtils.clickAction(PDPObjects.goZipButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.inStorePickupRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToCartButton());
		pdpCartValidation(producName, price, qty);		
	}
	
	public static void verifyATCConfigAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productConfig"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productConfig")));
		String producName = PDPObjects.productName().getText().trim();
		String price = PDPObjects.productPrice().getAttribute("data-price-amount");
		String qty = PDPObjects.productQty().getAttribute("value");
		WebdriverUtils.clickAction(PDPObjects.shipToHomeRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToCartButton());
		pdpCartValidation(producName, price, qty);		
	}
	
	public static void verifyATCConfigBOPISAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productConfig"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productConfig")));
		String producName = PDPObjects.productName().getText().trim();
		String price = PDPObjects.productPrice().getAttribute("data-price-amount");
		String qty = PDPObjects.productQty().getAttribute("value");
		WebdriverUtils.sendKeys(PDPObjects.zipCodeField(), "65109");
		WebdriverUtils.clickAction(PDPObjects.goZipButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.inStorePickupRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToCartButton());
		pdpCartValidation(producName, price, qty);		
	}
	
	public static void verifyAddToWishSimplePDPAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productConfig"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productConfig")));
		String producName = PDPObjects.productName().getText().trim();
		WebdriverUtils.clickAction(PDPObjects.shipToHomeRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToWishButton());
		assertTrue(WebdriverUtils.currentURL().contains("wishlist"));
		int totalSavedProducts = myAccountObjects.totalProductNames().size();
		String productNameInWishlist = null;
		if(totalSavedProducts>1) {
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts-1).getText().trim();
		}
		else
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts).getText().trim();
		assertEquals(productNameInWishlist, producName);		
		
	}
	
	public static void verifyAddToWishSimpleBOPISPDPAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productConfig"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productConfig")));
		String producName = PDPObjects.productName().getText().trim();
		WebdriverUtils.sendKeys(PDPObjects.zipCodeField(), "65109");
		WebdriverUtils.clickAction(PDPObjects.goZipButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.inStorePickupRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToWishButton());
		assertTrue(WebdriverUtils.currentURL().contains("wishlist"));
		int totalSavedProducts = myAccountObjects.totalProductNames().size();
		String productNameInWishlist = null;
		if(totalSavedProducts>1) {
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts-1).getText().trim();
		}
		else
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts).getText().trim();
		assertEquals(productNameInWishlist, producName);		
	}
	
	public static void verifyAddToWishConfigPDPAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productConfig"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productConfig")));
		String producName = PDPObjects.productName().getText().trim();
		WebdriverUtils.clickAction(PDPObjects.shipToHomeRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToWishButton());
		assertTrue(WebdriverUtils.currentURL().contains("wishlist"));
		int totalSavedProducts = myAccountObjects.totalProductNames().size();
		String productNameInWishlist = null;
		if(totalSavedProducts>1) {
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts-1).getText().trim();
		}
		else
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts).getText().trim();
		assertEquals(productNameInWishlist, producName);
		
	}
	
	public static void verifyAddToWishConfigBOPISPDPAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productConfig"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productConfig")));
		String producName = PDPObjects.productName().getText().trim();
		WebdriverUtils.sendKeys(PDPObjects.zipCodeField(), "65109");
		WebdriverUtils.clickAction(PDPObjects.goZipButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.inStorePickupRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToWishButton());
		assertTrue(WebdriverUtils.currentURL().contains("wishlist"));
		int totalSavedProducts = myAccountObjects.totalProductNames().size();
		String productNameInWishlist = null;
		if(totalSavedProducts>1) {
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts-1).getText().trim();
		}
		else
			productNameInWishlist = myAccountObjects.productName(totalSavedProducts).getText().trim();
		assertEquals(productNameInWishlist, producName);
		
	}
	
	public static void editCartFuncSimpleAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productSimple"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productSimple")));
		String producName = PDPObjects.productName().getText().trim();
		String price = PDPObjects.productPrice().getAttribute("data-price-amount");
		String qty = PDPObjects.productQty().getAttribute("value");
		WebdriverUtils.clickAction(PDPObjects.shipToHomeRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToCartButton());
		pdpCartValidation(producName, price, qty);
		WebdriverUtils.clickAction(ShoppingCartObjects.editCartFirstproduct());
		assertTrue(WebdriverUtils.currentURL().contains("checkout/cart"));
		assertTrue(WebdriverUtils.currentURL().contains("configure"));
		WebdriverUtils.sendKeys(PDPObjects.productQty(), "2");
		WebdriverUtils.clickAction(PDPObjects.updateButton());
		WebdriverUtils.waitUntilValuePresentInElement(ShoppingCartObjects.cartCount(), "2");
		String productNameCartUpdate = ShoppingCartObjects.productName().getText().trim();
		String productPriceCartUpdate = ShoppingCartObjects.productPrice().getText().trim().replaceAll("$", "");
		String productQtyCartUpdate = ShoppingCartObjects.productQty().getAttribute("value");
		assertEquals(productNameCartUpdate, producName);
		assertEquals(productPriceCartUpdate, price);
		assertEquals(productQtyCartUpdate, "2");	
		
	}
	
	public static void editCartFuncSimpleBOPISAction() throws Exception {
		WebdriverUtils.goToURL(WebdriverUtils.getProperty("testURL"));
		WebdriverUtils.waitForVisibilityOfElementLocated(LoginObjects.login_Link());
		WebdriverUtils.sendKeys(HomepageObjects.searchField(), WebdriverUtils.getProperty("productSimple"));
		toPDP();	
		assertTrue(WebdriverUtils.currentURL().contains(WebdriverUtils.getProperty("productSimple")));
		String producName = PDPObjects.productName().getText().trim();
		String price = PDPObjects.productPrice().getAttribute("data-price-amount");
		String qty = PDPObjects.productQty().getAttribute("value");
		WebdriverUtils.sendKeys(PDPObjects.zipCodeField(), "65109");
		WebdriverUtils.clickAction(PDPObjects.goZipButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.storeSearchResult41());
		WebdriverUtils.clickAction(PDPObjects.inStorePickupRadio());
		WebdriverUtils.clickAction(PDPObjects.productAddToCartButton());
		pdpCartValidation(producName, price, qty);
		WebdriverUtils.clickAction(WebdriverUtils.xpathAttributeIndex("a", "class", "action action-edit", 1));
		assertTrue(WebdriverUtils.currentURL().contains("checkout/cart"));
		assertTrue(WebdriverUtils.currentURL().contains("configure"));
		WebdriverUtils.clickAction(PDPObjects.inStorePickupRadio());
		WebdriverUtils.sendKeys(PDPObjects.productQty(), "2");
		WebdriverUtils.clickAction(PDPObjects.updateButton());
		WebdriverUtils.waitUntilValuePresentInElement(ShoppingCartObjects.cartCount(), "2");
		String productNameCartUpdate = ShoppingCartObjects.productName().getText().trim();
		String productPriceCartUpdate = ShoppingCartObjects.productPrice().getText().trim().replaceAll("$", "");
		String productQtyCartUpdate = ShoppingCartObjects.productQty().getAttribute("value");
		assertEquals(productNameCartUpdate, producName);
		assertEquals(productPriceCartUpdate, price);
		assertEquals(productQtyCartUpdate, "2");		
	}
	
	public static void editCartFuncConfigAction() {
		
	}
	
	public static void editCartFuncConfigBOPISAction() {
		
	}
	
	public static void toPDP() throws Exception {
		WebdriverUtils.clickAction(HomepageObjects.searchButton());
		WebdriverUtils.waitForVisibilityOfElementLocated(SRPObjects.srpFirstLink());
		WebdriverUtils.clickAction(SRPObjects.srpFirstLink());		
	}
	
	public static void variantSelections() {
		try {
			if(WebdriverUtils.xpathsAttribute("select", "class", "super-attribute-select")!=null) {
				int totalVariantDropdowns= WebdriverUtils.xpathsAttribute("select", "class", "super-attribute-select").size();
				if(totalVariantDropdowns>=1) {
					for(int i=1;i<=totalVariantDropdowns;i++) {						
							// Generate a random number with in range
							int randomNumber = ThreadLocalRandom.current().nextInt(0, WebdriverUtils.findElements(By.xpath("(//select[@class='super-attribute-select'])["+i+"]/option")).size());

							
							// Selecting random value
							WebdriverUtils.clickAction(WebdriverUtils.findElement(By.xpath("(//select[@class='super-attribute-select'])["+i+"]/option")));							
						
					}
					
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("No variants displayed");
			assertTrue(1>2);
		}
	}
	
	public static void pdpCartValidation(String producName, String price, String qty) throws Exception {		
		assertTrue(WebdriverUtils.currentURL().contains("checkout/cart"));
		String productNameCart = ShoppingCartObjects.productName().getText().trim();
		String productPriceCart = ShoppingCartObjects.productPrice().getText().trim().replaceAll("$", "");
		String productQtyCart = ShoppingCartObjects.productQty().getAttribute("value");
		assertEquals(productNameCart, producName);
		assertEquals(productPriceCart, price);
		assertEquals(productQtyCart, qty);		
	}
	
}
