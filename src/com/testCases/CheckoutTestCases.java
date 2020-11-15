package com.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.modules.CheckoutActions;
import com.utility.TestBase;

public class CheckoutTestCases extends TestBase{

	@Test(priority = 1, description = "Verify guest user is able to place order with Simple product- ship to home using Credit card")
	
	@Test(priority = 2, description = "Verify guest user is able to place order with Simple product- BOPIS using Credit card")
	
	@Test(priority = 3, description = "Verify guest user is able to place order with Simple product- complex cart using Credit card")
	
	@Test(priority = 4, description = "Verify guest user is able to place order with Config product- ship to home using Credit card")
	
	@Test(priority = 5, description = "Verify guest user is able to place order with Config product- BOPIS using Credit card")
	
	@Test(priority = 6, description = "Verify guest user is able to place order with Config product- complex cart using Credit card")
	
	@Test(priority = 7, description = "Verify guest user is able to place order with Simple and Config product- ship to home using Credit card")
	
	@Test(priority = 8, description = "Verify guest user is able to place order with Simple and Config product- BOPIS using Credit card")
	
	@Test(priority = 9, description = "Verify guest user is able to place order with Simple and Config product- complex cart using Credit card")
	
	@Test(priority = 10, description = "Verify guest user is able to place order with Simple product- ship to home using PayPal")
	
	@Test(priority = 11, description = "Verify guest user is able to place order with Simple product- BOPIS using PayPal")
	
	@Test(priority = 12, description = "Verify guest user is able to place order with Simple product- complex cart using PayPal")
	
	@Test(priority = 13, description = "Verify guest user is able to place order with Config product- ship to home using PayPal")
	
	@Test(priority = 14, description = "Verify guest user is able to place order with Config product- BOPIS using PayPal")
	
	@Test(priority = 15, description = "Verify guest user is able to place order with Config product- complex cart using PayPal")
	
	@Test(priority = 16, description = "Verify guest user is able to place order with Simple and Config product- ship to home using PayPal")
	
	@Test(priority = 17, description = "Verify guest user is able to place order with Simple and Config product- BOPIS using PayPal")
	
	@Test(priority = 18, description = "Verify guest user is able to place order with Simple and Config product- complex cart using PayPal")
	
	@Test(priority = 19, description = "Verify guest user is able to place order with Simple product- ship to home using OFH card")
	
	@Test(priority = 20, description = "Verify guest user is able to place order with Simple product- BOPIS using OFH card")
	
	@Test(priority = 21, description = "Verify guest user is able to place order with Simple product- complex cart using OFH card")
	
	@Test(priority = 22, description = "Verify guest user is able to place order with Config product- ship to home using OFH card")
	
	@Test(priority = 23, description = "Verify guest user is able to place order with Config product- BOPIS using OFH card")
	
	@Test(priority = 24, description = "Verify guest user is able to place order with Config product- complex cart using OFH card")
	
	@Test(priority = 25, description = "Verify guest user is able to place order with Simple and Config product- ship to home using OFH card")
	
	@Test(priority = 26, description = "Verify guest user is able to place order with Simple and Config product- BOPIS using OFH card")
	
	@Test(priority = 27, description = "Verify guest user is able to place order with Simple and Config product- complex cart using OFH card")
	
	@Test(priority = 28, description = "Verify guest user is able to place order with Simple product- ship to home using Gift card")
	
	@Test(priority = 29, description = "Verify guest user is able to place order with Simple product- BOPIS using Gift card")
	
	@Test(priority = 30, description = "Verify guest user is able to place order with Simple product- complex cart using Gift card")
	
	@Test(priority = 31, description = "Verify guest user is able to place order with Config product- ship to home using Gift card")
	
	@Test(priority = 32, description = "Verify guest user is able to place order with Config product- BOPIS using Gift card")
	
	@Test(priority = 33, description = "Verify guest user is able to place order with Config product- complex cart using Gift card")
	
	@Test(priority = 34, description = "Verify guest user is able to place order with Simple and Config product- ship to home using Gift card")
	
	@Test(priority = 35, description = "Verify guest user is able to place order with Simple and Config product- BOPIS using Gift card")
	
	@Test(priority = 36, description = "Verify guest user is able to place order with Simple and Config product- complex cart using Gift card")
	
	@Test(priority = 37, description = "Verify registered user is able to place order with Simple product- ship to home using Credit card")
	
	@Test(priority = 38, description = "Verify registered user is able to place order with Simple product- BOPIS using Credit card")
	
	@Test(priority = 39, description = "Verify registered user is able to place order with Simple product- complex cart using Credit card")
	
	@Test(priority = 40, description = "Verify registered user is able to place order with Config product- ship to home using Credit card")
	
	@Test(priority = 41, description = "Verify registered user is able to place order with Config product- BOPIS using Credit card")
	
	@Test(priority = 42, description = "Verify registered user is able to place order with Config product- complex cart using Credit card")
	
	@Test(priority = 43, description = "Verify registered user is able to place order with Simple and Config product- ship to home using Credit card")
	
	@Test(priority = 44, description = "Verify registered user is able to place order with Simple and Config product- BOPIS using Credit card")
	
	@Test(priority = 45, description = "Verify registered user is able to place order with Simple and Config product- complex cart using Credit card")
	
	@Test(priority = 46, description = "Verify registered user is able to place order with Simple product- ship to home using PayPal")
	
	@Test(priority = 47, description = "Verify registered user is able to place order with Simple product- BOPIS using PayPal")
	
	@Test(priority = 48, description = "Verify registered user is able to place order with Simple product- complex cart using PayPal")
	
	@Test(priority = 49, description = "Verify registered user is able to place order with Config product- ship to home using PayPal")
	
	@Test(priority = 50, description = "Verify registered user is able to place order with Config product- BOPIS using PayPal")
	
	@Test(priority = 51, description = "Verify registered user is able to place order with Config product- complex cart using PayPal")
	
	@Test(priority = 52, description = "Verify registered user is able to place order with Simple and Config product- ship to home using PayPal")
	
	@Test(priority = 53, description = "Verify registered user is able to place order with Simple and Config product- BOPIS using PayPal")
	
	@Test(priority = 54, description = "Verify registered user is able to place order with Simple and Config product- complex cart using PayPal")
	
	@Test(priority = 55, description = "Verify registered user is able to place order with Simple product- ship to home using OFH card")
	
	@Test(priority = 56, description = "Verify registered user is able to place order with Simple product- BOPIS using OFH card")
	
	@Test(priority = 57, description = "Verify registered user is able to place order with Simple product- complex cart using OFH card")
	
	@Test(priority = 58, description = "Verify registered user is able to place order with Config product- ship to home using OFH card")
	
	@Test(priority = 59, description = "Verify registered user is able to place order with Config product- BOPIS using OFH card")
	
	@Test(priority = 60, description = "Verify registered user is able to place order with Config product- complex cart using OFH card")
	
	@Test(priority = 61, description = "Verify registered user is able to place order with Simple and Config product- ship to home using OFH card")
	
	@Test(priority = 62, description = "Verify registered user is able to place order with Simple and Config product- BOPIS using OFH card")
	
	@Test(priority = 63, description = "Verify registered user is able to place order with Simple and Config product- complex cart using OFH card")
	
	@Test(priority = 64, description = "Verify registered user is able to place order with Simple product- ship to home using Gift card")
	
	@Test(priority = 65, description = "Verify registered user is able to place order with Simple product- BOPIS using Gift card")
	
	@Test(priority = 66, description = "Verify registered user is able to place order with Simple product- complex cart using Gift card")
	
	@Test(priority = 67, description = "Verify registered user is able to place order with Config product- ship to home using Gift card")
	
	@Test(priority = 68, description = "Verify registered user is able to place order with Config product- BOPIS using Gift card")
	
	@Test(priority = 69, description = "Verify registered user is able to place order with Config product- complex cart using Gift card")
	
	@Test(priority = 70, description = "Verify registered user is able to place order with Simple and Config product- ship to home using Gift card")
	
	@Test(priority = 71, description = "Verify registered user is able to place order with Simple and Config product- BOPIS using Gift card")
	
	@Test(priority = 72, description = "Verify registered user is able to place order with Simple and Config product- complex cart using Gift card")
	
	@Test(priority = 73, description = "Verify registered user is able to place order with Simple product from wishist- ship to home using Credit card")
	
	@Test(priority = 74, description = "Verify registered user is able to place order with Simple product from wishist- ship to home using PayPal")
	
	@Test(priority = 75, description = "Verify registered user is able to place order with Simple product from wishist- ship to home using Gift card")
	
	@Test(priority = 76, description = "Verify registered user is able to place order with Simple product from wishist- ship to home using OFH card")
	
	@Test(priority = 77, description = "Verify registered user is able to place order with Simple product from wishlist- BOPIS using Credit card")
	
	@Test(priority = 78, description = "Verify registered user is able to place order with Simple product from wishlist- BOPIS using PayPal")
	
	@Test(priority = 79, description = "Verify registered user is able to place order with Simple product from wishlist- BOPIS using Gift card")
	
	@Test(priority = 80, description = "Verify registered user is able to place order with Simple product from wishlist- complex cart using OFH card")
	
	@Test(priority = 81, description = "Verify registered user is able to place order with Simple product from wishlist- complex cart using Credit card")
	
	@Test(priority = 82, description = "Verify registered user is able to place order with Simple product from wishlist- complex cart using PayPal")
	
	@Test(priority = 83, description = "Verify registered user is able to place order with Simple product from wishlist- complex cart using Gift card")
	
	@Test(priority = 84, description = "Verify registered user is able to place order with Simple product from wishlist- complex cart using OFH card")
	
	@Test(priority = 85, description = "Verify registered user is able to place order with Config product from wishist- ship to home using Credit card")
	
	@Test(priority = 86, description = "Verify registered user is able to place order with Config product from wishist- ship to home using PayPal")
	
	@Test(priority = 87, description = "Verify registered user is able to place order with Config product from wishist- ship to home using Gift card")
	
	@Test(priority = 88, description = "Verify registered user is able to place order with Config product from wishist- ship to home using OFH card")
	
	@Test(priority = 89, description = "Verify registered user is able to place order with Config product from wishlist- BOPIS using Credit card")
	
	@Test(priority = 90, description = "Verify registered user is able to place order with Config product from wishlist- BOPIS using PayPal")
	
	@Test(priority = 91, description = "Verify registered user is able to place order with Config product from wishlist- BOPIS using Gift card")
	
	@Test(priority = 92, description = "Verify registered user is able to place order with Config product from wishlist- BOPIS using OFH card")
	
	@Test(priority = 93, description = "Verify registered user is able to place order with Config product from wishlist- complex cart Credit card")
	
	@Test(priority = 94, description = "Verify registered user is able to place order with Config product from wishlist- complex cart using PayPal")
	
	@Test(priority = 95, description = "Verify registered user is able to place order with Config product from wishlist- complex cart using Gift card")
	
	@Test(priority = 96, description = "Verify registered user is able to place order with Config product from wishlist- complex cart using OFH card")
	
	@Test(priority = 97, description = "Verify registered user is able to place order with Simple and Config product from wishist- ship to home using Credit card")
	
	@Test(priority = 98, description = "Verify registered user is able to place order with Simple and Config product from wishist- ship to home using PayPal")
	
	@Test(priority = 99, description = "Verify registered user is able to place order with Simple and Config product from wishist- ship to home using Gift card")
	
	@Test(priority = 100, description = "Verify registered user is able to place order with Simple and Config product from wishist- ship to home using OFH card")
	
	@Test(priority = 101, description = "Verify registered user is able to place order with Simple and Config product from wishlist- BOPIS using Credit card")
	
	@Test(priority = 102, description = "Verify registered user is able to place order with Simple and Config product from wishlist- BOPIS using PayPal")
	
	@Test(priority = 103, description = "Verify registered user is able to place order with Simple and Config product from wishlist- BOPIS using Gift card")
	
	@Test(priority = 104, description = "Verify registered user is able to place order with Simple and Config product from wishlist- BOPIS using OFH card")
	
	@Test(priority = 105, description = "Verify registered user is able to place order with Simple and Config product from wishlist- complex cart using Credit card")
	
	@Test(priority = 106, description = "Verify registered user is able to place order with Simple and Config product from wishlist- complex cart using PayPal")
	
	@Test(priority = 107, description = "Verify registered user is able to place order with Simple and Config product from wishlist- complex cart using Gift card")
	
	@Test(priority = 108, description = "Verify registered user is able to place order with Simple and Config product from wishlist- complex cart using OFH card")
	
}
