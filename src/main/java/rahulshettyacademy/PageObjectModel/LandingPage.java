package rahulshettyacademy.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstarctcomponent;

public class LandingPage extends Abstarctcomponent
{
	WebDriver driver;
	/*driver.findElement(By.id("userEmail")).sendKeys("shweta24@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Love@academy24");
	driver.findElement(By.id("login")).click();*/
	
	//1.To initialize the objects of the class to need to create constructor of the class
	//whatever code we right inside the class that is going to execute first
	//this is the just a java code for login page the execution code public static void main in in the standalonetest.java
	//to execute this code inside that class we need to create object of the this inside the standalonetest.java
	
	public LandingPage(WebDriver driver)
	{
		//we are calling the driver inside the constuctor by using this keyword
	    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);//this referer to the current class driver
		//we are telling here go and initialize all the elemets,for initalizating it takes the driver
		
	}
	
	
	
	
	//To reduce the code we are using page object model
	//*driver.findElement(By.id("userEmail")).sendKeys("shweta24@gmail.com");
	//FindBy act as driver.findelement
	//but here we have no refernace of driver in the costructor we need to use initelement to give refernance
	//this find by will exceute only when you give referance in the constuctor
	

	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//here we need to perform action on this element-like sendkeys -for that we need to create method
	//here we should not add hardcode-bcoz emaild,pass could be diff for each user-
	//for that whatever Email id or password we are providing for that
	//POM should not hold any data it should only focus on elemets and actions
	/*public void LoginApllication(String email, String pass)
	{
		userEmail.sendKeys(email);
		
		userPassword.sendKeys(pass);
		submit.click();
		//here we know that after login 
		//Productcatalogue productcatalogue=new Productcatalogue(driver);
		//going to happen so we are creating object of the next class that producatalogue
		/////for avaiding each class by creating object accessing the methods
		
	}*/
	public Productcatalogue LoginApllication(String email, String pass)
	{
		userEmail.sendKeys(email);
		
		userPassword.sendKeys(pass);
		submit.click();
		Productcatalogue productcatalogue=new Productcatalogue(driver);
		return productcatalogue;
		
	}
		
	public String getErrorMessage()
	{
		waitForWebElementToAppeare(errorMessage);
		
		return errorMessage.getText();
	}
	
	
	
	public void GoToURL()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.manage().window().maximize();
	}
	
	
	
	
	

}
