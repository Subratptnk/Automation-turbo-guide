package subratpattanaik;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//global wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//fetch the url of test
		driver.get("https://rahulshettyacademy.com/client/");
		//for making the window as full screen
		driver.manage().window().fullscreen();
		 
		
		driver.findElement(By.id("userEmail")).sendKeys("subratp2022@testing.com");
		driver.findElement(By.id("userPassword")).sendKeys("Testing123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
		
		//every iteration one product is retrived which got stored in product, 
		// and from product element (where we need to find the actual text name of the product) and we check whose name is matching with "ZARA COAT 3"
		WebElement prod =  products.stream()
		.filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//clicking on the particular add to cart element
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait untill the toast message shwoing in your screen
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//verify add to cart bucket is matching with the item which we are selecting
		List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match =  cartProducts.stream()
		.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
	}

}
