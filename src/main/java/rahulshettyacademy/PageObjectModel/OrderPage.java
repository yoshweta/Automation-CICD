package rahulshettyacademy.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstarctcomponent;

public class OrderPage extends Abstarctcomponent
{
	WebDriver driver;
	
	
	public OrderPage(WebDriver driver)
	{
		 super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//List<WebElement>cartProducts=driver.findElements(By.cssSelector(".infoWrap h3"));
	 @FindBy(css="tr td:nth-child(3)")
	 
	 List<WebElement>productNames;
	 
	// driver.findElement(By.cssSelector(".totalRow button")).click();
	 
	 @FindBy(css=".totalRow button")
	 WebElement Checkout;
	
	 
	
    //boolean match=	cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
   
	
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		boolean match=	productNames.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		 return match;
		
	}
	
	
	
	
	
	
	

}
