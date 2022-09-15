package subratpattanaik.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import subratpattanaik.abstractcomponents.OrderPage;
import subratpattanaik.pageobjects.CartPage;
import subratpattanaik.pageobjects.CheckoutPage;
import subratpattanaik.pageobjects.ConfirmationPage;
import subratpattanaik.pageobjects.LandingPage;
import subratpattanaik.pageobjects.ProductCatalogue;
import subratpattanaik.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName= "ZARA COAT 3";
		@Test
		public void submitOrder() throws IOException, InterruptedException {
		
	
		String countryname = "India";
	
		ProductCatalogue productCatalogue =	landingpage.loginApplication("subratp2022@testing.com", "Testing123");
		
		List<WebElement> productList =  productCatalogue.getProducts();
		
		productCatalogue.addProductToCart(productName);

		CartPage cartPage =  productCatalogue.goToCart();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =  cartPage.goToCheckout();
		checkoutPage.selectCountryofTransaction(countryname);
		ConfirmationPage confirmpage = checkoutPage.submitOrder();
		String msg = confirmpage.getOrderConfirmation();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
		
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() throws InterruptedException {
		
		ProductCatalogue productCatalogue =	landingpage.loginApplication("subratp2022@testing.com", "Testing123");
		OrderPage orderpage =  productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName)); 
	}
	

}
