package subratpattanaik;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//global wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//fetch the url of test
		driver.get("https://rahulshettyacademy.com/client/");
		//for making the window as full screen
		driver.manage().window().fullscreen();
		
		
		driver.findElement(By.id("userEmail")).sendKeys("subratp2022@testing.com");
		driver.findElement(By.id("userPassword")).sendKeys("Testing123");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
		
		//every iteration one product is retrived which got stored in product, 
		// and from product element (where we need to find the actual text name of the product) and we check whose name is matching with "ZARA COAT 3"
		WebElement prod =  products.stream()
		.filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		//clicking on the particular add to cart element
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		
	}

}
