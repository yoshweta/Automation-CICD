package rahulshettyacademy.Abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageObjectModel.CartPage;
import rahulshettyacademy.PageObjectModel.OrderPage;

public class Abstarctcomponent
{

	/*WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));*/
	
	WebDriver driver;
	
	public Abstarctcomponent(WebDriver driver)
	{
		//scope of this driver is inside this method only
		//to intract driver which is ouside this method to intract with that we need to create local variable
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	//convert this to pom
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	
	@FindBy(css="[routerlink*='myorders']")
	
	WebElement orderHeader;
	
	
	public void waitForElementToAppeare(By FindBy)
	{

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		//By.cssSelector(".mb-3") is not a webelement -by locator half piece of info
		//we cannot hardcore this should pass it as argument
		//Find By will come from POM class
		//here driver have no idea that from where it is coming so whatever the first POM class we have create super(driver) in 
		//its constructor and use that child class driver here using this super keyword
		//only constructor can catch the variables
	}
	
	
	public void waitForWebElementToAppeare(WebElement FindBy )
	{

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	
	}
	
	
	
	
	
	
	public void waitForElementToDisapper(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		//here driver.findElement(By.cssSelector(".ng-animating")) web element 
	}
	
	
	//cart-is common header to all the page so we are using it here
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	public CartPage goTocartPage()
	{
		 cartHeader.click();
		 
		 CartPage CartPage=new CartPage(driver);
		 return CartPage;
	}
	
	
	public OrderPage goToOrdersPage()
	{
		 orderHeader.click();
		 
		 OrderPage Orderpage=new OrderPage(driver);
		 return Orderpage;
	}
	
	
	

}
