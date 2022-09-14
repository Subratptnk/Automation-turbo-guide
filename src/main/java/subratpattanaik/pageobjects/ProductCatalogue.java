package subratpattanaik.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import subratpattanaik.abstractcomponents.AbstractComponent;

public class ProductCatalogue  extends AbstractComponent {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		//initialization page
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastWait = By.cssSelector("#toast-container");
	
	public List<WebElement> getProducts() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProducts().stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);	
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastWait);
		waitForElementToDisappear(spinner);
	}
	
	
	
	
}
