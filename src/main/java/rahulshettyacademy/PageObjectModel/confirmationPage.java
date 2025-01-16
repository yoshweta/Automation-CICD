package rahulshettyacademy.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstarctcomponent;

public class confirmationPage extends Abstarctcomponent
{
	WebDriver driver;
	
	public confirmationPage(WebDriver driver)
	{
		 super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	// String Message=driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	@FindBy(css=".hero-primary")
	
	WebElement confirmationmesg;
	
	
	public  String getConfiramtionMessage()
	{
		return confirmationmesg.getText();
	}

}
