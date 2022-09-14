package subratpattanaik.testcomponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import subratpattanaik.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//subratpattanaik//resources//GlobalData.properties");
		prop.load(fis);
		String browserName =  prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome")){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.makeWindowFullScreen();
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
}
