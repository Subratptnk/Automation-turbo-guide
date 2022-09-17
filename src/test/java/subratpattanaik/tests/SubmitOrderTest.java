package subratpattanaik.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
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
	//String productName= "ZARA COAT 3";
		@Test(dataProvider = "getData", groups = "PurchaseOrders")
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
	
		String countryname = "India";
	
		ProductCatalogue productCatalogue =	landingpage.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> productList =  productCatalogue.getProducts();
		
		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage =  productCatalogue.goToCart();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =  cartPage.goToCheckout();
		checkoutPage.selectCountryofTransaction(countryname);
		ConfirmationPage confirmpage = checkoutPage.submitOrder();
		String msg = confirmpage.getOrderConfirmation();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
		
	@Test(dependsOnMethods = {"submitOrder"},dataProvider = "getData")
	public void orderHistoryTest(HashMap<String,String> input) throws InterruptedException {
		
		ProductCatalogue productCatalogue =	landingpage.loginApplication(input.get("email"),input.get("password"));
		OrderPage orderpage =  productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(input.get("product"))); 
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		HashMap<String, String> map = new HashMap<String,String>();

		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//subratpattanaik//data//PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)},{data.get(1)} };
	}

}






//map.put("email", "subratp2022@testing.com");
//map.put("password", "Testing123");
//map.put("product", "ZARA COAT 3");
//
//
//  HashMap<String, String> map1 = new HashMap<String,String>(); 
//  map1.put("email","subratp2021@testing.com"); 
//  map1.put("password", "Testing123");
//  map1.put("product", "ADIDAS ORIGINAL");
