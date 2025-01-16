package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjectModel.CartPage;
import rahulshettyacademy.PageObjectModel.OrderPage;
import rahulshettyacademy.PageObjectModel.Productcatalogue;
import rahulshettyacademy.PageObjectModel.checkoutPage;
import rahulshettyacademy.PageObjectModel.confirmationPage;
import rahulshettyacademy.TestComponents.BaseTest;

public class UsingHashmapTest extends BaseTest
{

	
		String productname="IPHONE 13 PRO";
		@Test(dataProvider="getData",groups= {"Hashmap"})
		public void UsingHashmap(HashMap<String,String>input) throws IOException,InterruptedException
		{
		
			
			
			
			
			
			//LandingPaga.java
			//By creating the Object of the Class We are accessing the methods of that class
			
			Productcatalogue productcatalogue=landingPage.LoginApllication(input.get("email"), input.get("password"));
			
			
			
			//Productcatalogue.java
			//whatever product list we have it going to store in the products
			
			//Productcatalogue productcatalogue=new Productcatalogue(driver);
			
			List<WebElement>products=productcatalogue.GetproductList();
			
			productcatalogue.addProductTocart(input.get("productname"));
			//goTocartPage() is in the Abstractcompnent.java class but thats a parent class and we are extend to child class
			//with using child class object you can access the methods of parent class
			
			
			CartPage cartpage=productcatalogue.goTocartPage();
			
			
			
			//cartPage.java
			
		//	CartPage cartpage=new CartPage(driver);
			Boolean match=cartpage.VerifyProductDisplay(input.get("productname"));
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
		public  Object[][] getData()
		{
			HashMap<String,String>map=new HashMap<String,String>();
			map.put("email","shweta24@gmail.com");
			map.put("password", "Love@academy24");
			map.put("productname", "IPHONE 13 PRO");
			
			HashMap<String,String>map1=new HashMap<String,String>();
			map1.put("email", "shweta25@gmail.com");
			map1.put("password", "Love@academy25");
			map1.put("productname", "ADIDAS ORIGINAL");
			
			return new Object[][] {{map},{map1}};
			
		}
		
}
