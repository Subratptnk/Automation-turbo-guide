package subratpattanaik;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import subratpattanaik.pageobjects.CartPage;
import subratpattanaik.pageobjects.CheckoutPage;
import subratpattanaik.pageobjects.ConfirmationPage;
import subratpattanaik.pageobjects.LandingPage;
import subratpattanaik.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName= "ZARA COAT 3";
		String countryname = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//global wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//for making the window as full screen
		driver.manage().window().fullscreen();
		 
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue =	landingPage.loginApplication("subratp2022@testing.com", "Testing123");
		
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
		
		driver.close();
	}

}
