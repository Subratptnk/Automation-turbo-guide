package subratpattanaik.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import subratpattanaik.abstractcomponents.AbstractComponent;

public class CartPage  extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		//initialization page
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> products;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton ;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match =  products.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
	
}
