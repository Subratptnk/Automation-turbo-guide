package subratpattanaik.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import subratpattanaik.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//subratpattanaik//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = 	System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
	//	String browserName =  prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome")){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return driver;
	}
	
public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {
		
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File (filepath), StandardCharsets.UTF_8);
		
	//String to Hashmap ( jakson databin)
	
	ObjectMapper mapper = new ObjectMapper();
	
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	});
	
	return data;
		
	}

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	
	//cast driver to screenshot mode
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source =  ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+"//Reports//"+ testCaseName+ ".png");
	FileUtils.copyFile(source, file );
	return System.getProperty("user.dir")+"//Reports//"+ testCaseName+ ".png" ;
}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.makeWindowFullScreen();
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
}
