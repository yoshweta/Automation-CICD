package rahulshettyacademy.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstarctcomponent;

public class CartPage extends Abstarctcomponent
{
	WebDriver driver;
	
	
	public CartPage(WebDriver driver)
	{
		 super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//List<WebElement>cartProducts=driver.findElements(By.cssSelector(".infoWrap h3"));
	 @FindBy(css=".cartSection h3")
	 
	 List<WebElement>cartProducts;
	 
	// driver.findElement(By.cssSelector(".totalRow button")).click();
	 
	 @FindBy(css=".totalRow button")
	 WebElement Checkout;
	
	 
	
    //boolean match=	cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
   
	
	
	public Boolean VerifyProductDisplay(String productName)
	{
		boolean match=	cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		 return match;
		
	}
	public checkoutPage goTocheckout()
	{
		Checkout.click();
		return new checkoutPage(driver);
		
	}
	
	
	
	
	
	

}
