package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjectModel.CartPage;
import rahulshettyacademy.PageObjectModel.LandingPage;
import rahulshettyacademy.PageObjectModel.OrderPage;
import rahulshettyacademy.PageObjectModel.Productcatalogue;
import rahulshettyacademy.PageObjectModel.checkoutPage;
import rahulshettyacademy.PageObjectModel.confirmationPage;
import rahulshettyacademy.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest
{
	String productname="IPHONE 13 PRO";
	@Test(dataProvider="getData",groups= {"purchase"})
	public void SatndAloneTest(String email,String password,String productname) throws IOException
	{
	
		
		
		
		
		
		//LandingPaga.java
		//By creating the Object of the Class We are accessing the methods of that class
		
		Productcatalogue productcatalogue=landingPage.LoginApllication(email, password);
		
		
		
		//Productcatalogue.java
		//whatever product list we have it going to store in the products
		
		//Productcatalogue productcatalogue=new Productcatalogue(driver);
		
		List<WebElement>products=productcatalogue.GetproductList();
		
		productcatalogue.addProductTocart(productname);
		//goTocartPage() is in the Abstractcompnent.java class but thats a parent class and we are extend to child class
		//with using child class object you can access the methods of parent class
		
		
		CartPage cartpage=productcatalogue.goTocartPage();
		
		
		
		//cartPage.java
		
	//	CartPage cartpage=new CartPage(driver);
		Boolean match=cartpage.VerifyProductDisplay(productname);
		 Assert.assertTrue(match);
		//validation should stay in the execution class it should not go in the POM class
		 
		 checkoutPage checkout= cartpage.goTocheckout();
		 checkout.SelectCountry("india");
		 
		 confirmationPage confirmationmsg=checkout.submitOrder();
		 
		 String confirmMeaage=confirmationmsg.getConfiramtionMessage();
		 Assert.assertTrue(confirmMeaage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	   
		
	}
	
	//Learning abouts dependecy attrubute of TESTNG-dependsOnMethods
	@Test(dependsOnMethods= {"SatndAloneTest"})
	public void OrderHistroyTest()
	{
		Productcatalogue productcatalogue=landingPage.LoginApllication("shweta24@gmail.com", "Love@academy24");
		OrderPage Orderpage=productcatalogue.goToOrdersPage();
		
		Assert.assertTrue(Orderpage.VerifyOrderDisplay(productname));	
	}
	
	@DataProvider
	public Object[] [] getData()
	{
		return new Object[][]{{"shweta24@gmail.com","Love@academy24","IPHONE 13 PRO"},{"shweta25@gmail.com","Love@academy25","ADIDAS ORIGINAL"}};
		
		}
		
		
}


