package subratpattanaik.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import subratpattanaik.pageobjects.CartPage;
import subratpattanaik.pageobjects.CheckoutPage;
import subratpattanaik.pageobjects.ConfirmationPage;
import subratpattanaik.pageobjects.ProductCatalogue;
import subratpattanaik.testcomponents.BaseTest;

public class ErrorValidations extends BaseTest {
	
	@Test(groups = "Errorhandling")
	public void LoginErrorValidation() {
	    landingpage.loginApplication("subratp2020@testing.com", "Testing123");
	    landingpage.getErrorText();
	    Assert.assertEquals("Incorrect email  password.", landingpage.getErrorText());
	}
	
	@Test(groups = "Errorhandling")
	public void ProductErrorValidation() throws IOException, InterruptedException {
	
	String productName= "ZARA COAT 3";
	ProductCatalogue productCatalogue =	landingpage.loginApplication("subratp2022@testing.com", "Testing123");
	List<WebElement> productList =  productCatalogue.getProducts();
	productCatalogue.addProductToCart(productName);
	CartPage cartPage =  productCatalogue.goToCart();
	
	Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);

}
	
	
}
