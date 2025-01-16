package rahulshettyacademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjectModel.LandingPage;

public class StandAloneTestWithoutUsingPOM 
{
	public static void main(String args[])
	{
		String productname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		//using implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		LandingPage logine=new LandingPage(driver);//to excute LandingPage.java code we are creating the object of the class
		driver.findElement(By.id("userEmail")).sendKeys("shweta24@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Love@academy24");
		driver.findElement(By.id("login")).click();
		
		//use expilicitwait 
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//find all the elements
		
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		
		//we need to pass the scope of the selenium inside the prod list
		//for performing the filter we are using stream so whatever operation we are performing is going to happen on the steram products
		//here we are using product.fond element instead of this if we use driver.findelement again it is going to scan from start
		//to avoid that in the css selector-tag name of the product to find the first product
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		//it is going to find product with the name-zara coat 3 //first-if it find that name it will return name or if it dosent find anything it will return null
		
		//need to perform add to cart product
		
		
		//here we are not using add to cart text bcoz sometimes when we click add to cart the text quickely vchanges to added and sometime it gives error
		
		//some browsing things happens here so we need wait untill is proceed
		
		
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//here we are not using by.css directrectly if we use it taking ling time 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement>cartProducts=driver.findElements(By.cssSelector(".infoWrap h3"));
		//whatever items which are added to the we can see the list here
		//want to match product with the name
	    boolean match=	cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
	    Assert.assertTrue(match);
	
	
	   driver.findElement(By.cssSelector(".totalRow button")).click();
	   
	   Actions act=new Actions(driver);
	   act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
	   //wait until elements should be visible
	   
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	   
	   driver.findElement(By.cssSelector(".ta-item:nth-of-type(2) ")).click();
	   
	   driver.findElement(By.cssSelector(".action__submit ")).click();
	   
	   String Message=driver.findElement(By.cssSelector(".hero-primary")).getText();
	  Assert.assertTrue( Message.equalsIgnoreCase("Thankyou for the order."));
	   
	   
	   
		
		}

}
