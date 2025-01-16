package rahulshettyacademy.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.Abstractcomponents.Abstarctcomponent;

public class checkoutPage extends Abstarctcomponent
{
	
	WebDriver driver;
	
	public checkoutPage(WebDriver driver)
	{
		 super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	// driver.findElement(By.cssSelector(".action__submit ")).click();
	@FindBy(css=".action__submit")
	WebElement submit;
	
	//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2) ")).click();
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement SelectCountry;
	
	By result=By.cssSelector(".ta-results");
	
	
	
	public void SelectCountry(String countryname)
	{
	 Actions act=new Actions(driver);
	   act.sendKeys((country), countryname).build().perform();
	   waitForElementToAppeare(result);
       
	   SelectCountry.click();
	  
	   
	}
	
	public  confirmationPage submitOrder()
	{
		submit.click();
		
		//we are going on confirmation page
		
		return new confirmationPage(driver);
		 
	}

	

	
}
