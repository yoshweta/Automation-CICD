package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjectModel.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializaDriver() throws IOException
	{
		//code for setting up the global drive so that we can execute the code in any browser
		//Properties class-we can able to extract all the data which we have in the globaldata.properties
		
		Properties pros=new Properties();
		//this system.getprop()-will get the project  path for you insted of hardcoding
		FileInputStream fis = new FileInputStream("E:\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resource\\GlobalData.properties");

		//FileInputStream file=new FileInputStream(System.getProperty("user.dir")+ "src//main//java//rahulshettyacademy//resource//GlobalData.properties");
		pros.load(fis);
		
		
		//if we are using command line to test and browser we want to select dynamically using disturbing the code 
		//Their is java ternary cocecpt using -if System.getProperty("browser") condition is true then 1.will execute if 2.is true then 2 will get execute
		//  String browserName =System.getProperty("browser")!=null ? System.getProperty("browser") :pros.getProperty("browser");
		  
		 
		  
		String browserName =pros.getProperty("browser");
		
	       
	        WebDriverManager.chromedriver().setup();
		  //from the file we are getting the browser text for that we are using pros.getProperty()
	      
	        if (browserName.contains("chrome")) {
	            ChromeOptions options = new ChromeOptions(); // Initialize ChromeOptions
	            WebDriverManager.chromedriver().setup();
	            
	            if (browserName.contains("headless")) {
	                options.addArguments("--headless");
	                options.addArguments("--disable-gpu");
	                options.addArguments("--no-sandbox");
	                options.addArguments("--disable-dev-shm-usage");
	                options.addArguments("--window-size=1920x1080");
	            }
	            
	            driver = new ChromeDriver(options);
	            driver.manage().window().setSize(new Dimension(1440, 900));  // Set the screen size for non-headless mode
	        }

	        else if (browserName.equalsIgnoreCase("firefox")) {
	            WebDriverManager.firefoxdriver().setup();  // Using WebDriverManager for Firefox
	            driver = new FirefoxDriver();
	        }

	        else if (browserName.equalsIgnoreCase("Edge")) {
	            WebDriverManager.edgedriver().setup();  // Using WebDriverManager for Edge
	            driver = new EdgeDriver();
	        }

	        // Common setup for all browsers
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        // For non-headless browsers, maximize the window
	        if (!browserName.contains("headless")) {
	            driver.manage().window().maximize();
	        }
;
		  return driver;
		  
	}
	//for every steps we need to first login so we inializating login
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//1.reading json file to string
		//System.getproperty("user.dir") helps whatever the external file we want to use it creates the path of that file dynamically
	String jsoncontent=	FileUtils.readFileToString(new File(filepath),
			StandardCharsets.UTF_8);
	//2.convert this string to hashmap
	//download Jackson Databind-dependency-hepls to convert string content to hashmap
	//By creating the object of ObjectMapper class we are read value()-read the string and convert it to hashmap from the file
	ObjectMapper mapper=new ObjectMapper();
	
	List<HashMap<String,String>>data=mapper.readValue(jsoncontent,new TypeReference<List<HashMap<String,String>>>(){
			
	
	
	});
	return data;
	}
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source,dest);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
		
		
	}
//	public String getScreenshot(String testcaseName,WebDriver driver) {
//	    if (driver instanceof TakesScreenshot) {
//	        TakesScreenshot ts = (TakesScreenshot) driver;
//	        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
//	        // Your logic for saving the screenshot
//	    } else {
//	        System.out.println("Driver does not support screenshots");
//	    }
//		return testcaseName;
//	}

	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException

	{
		 driver=initializaDriver();	
		 landingPage=	new LandingPage(driver);//to excute LandingPage.java code we are creating the object of the class
			
		 landingPage.GoToURL();
			return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void driverclose()
	{
		driver.close();
	}
}
