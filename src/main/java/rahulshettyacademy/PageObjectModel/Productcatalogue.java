package rahulshettyacademy.PageObjectModel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.Abstractcomponents.Abstarctcomponent;

public class Productcatalogue extends Abstarctcomponent
{
	/*WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
	//find all the elements
	
	List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	
	//we need to pass the scope of the selenium inside the prod list
	//for performing the filter we are using stream so whatever operation we are performing is going to happen on the steram products
	//here we are using product.fond element instead of this if we use driver.findelement again it is going to scan from start
	//to avoid that in the css selector-tag name of the product to find the first product
	WebElement prod=products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
	WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		*/
	WebDriver driver;
	
	public Productcatalogue(WebDriver driver)
	{
		//for every POM class we need to create constructor of that class to initialize the driver
		//By default class doesn't have any knowledge of the driver that we have to tell through this constructor
		 super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);//this referer to the current class driver
		//we are telling here go and initialize all the elements,for initalizating it takes the driver
		
	}
	
	//find all the elements
	//Before using this find elements we have wait code which is reuseblethis is generic code-for such kind of reusable code we use generic class
	//that utility-simple inherate that code inside the POM class-Abstaract components package-
	//this will be the parent class-to all the POM classes-Bcoz it holding all the reuseable code
	//here we are using inheritance-whatevenr variables,methods are declare in this class will be automatically ablicable to POM class
	
		//List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	//WebElement prod=products.stream().filter(product->
	//product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	By productlist=By.cssSelector(".mb-3");
	
	//By.cssSelector(".card-body button:last-of-type")
	
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage    =By.cssSelector("#toast-container");
	
	
	@FindBy(css=".ng-animating")
	WebElement Spinner;
	
	
	
	public List<WebElement> GetproductList()
	{
		//before the product list we need to wait for all the product to appeare
		//so here we have method waitForElementToAppeare() in the parent class-AbstractComponent
		//its appecting argument by productlist-pass it in the method
		//once product are loaded return product list
		waitForElementToAppeare(productlist);
		return products;
		
		
	}
	
	//WebElement prod=products.stream().filter(product->
	//product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
	//for executing this step we are creating method 
	
	
	public WebElement filterProductByName(String productName)
	{
		
		WebElement prod=GetproductList().stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	
	return prod;
	
	}
	
	
	
	
	
	//after finding the product click on that product
	
	//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	
	public void addProductTocart(String productName )
	{
		//instead if its driver we could have apply page factory but product what we found here
		//for custom things we cant use POM-By.cssSelector(".card-body button:last-of-type")
		//here first we are filter product name by name after we are adding it to the cart
		
		WebElement prod=filterProductByName(productName);
		prod.findElement(addToCart).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//this reusable we alredy have in the waitForElementToApperare() of Abstractcomponent.java class
		waitForElementToAppeare(toastMessage);
		waitForElementToDisapper(Spinner);
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));)
				//here driver.findElement(By.cssSelector(".ng-animating")) we are creating POM 
		
		
	}
	
		
	
	

}
