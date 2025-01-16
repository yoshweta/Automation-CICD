package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;

import rahulshettyacademy.TestComponents.Retry;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjectModel.CartPage;
import rahulshettyacademy.PageObjectModel.Productcatalogue;
import rahulshettyacademy.PageObjectModel.checkoutPage;
import rahulshettyacademy.PageObjectModel.confirmationPage;
import rahulshettyacademy.TestComponents.BaseTest;
//for handling the Error Message

public class Errorvalidation extends BaseTest
{
	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException
	{
		
		//Productcatalogue productcatalogue=landingPage.LoginApllication("shweta24@gmail.com", "Love@academy24");
		
	   landingPage.LoginApllication("shweta24@gmail.com", "Love@academy3424");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	  // Assert.assertEquals("Incorrect email or  password.", landingPage.getErrorMessage());
		
	}
//	@Test
//	public void ProductErrorValidation() throws IOException
//	{			
//		//Verifying the negative Scenario
//		String productname="qwerty";
//			
//		
//		Productcatalogue productcatalogue=landingPage.LoginApllication("shweta24@gmail.com", "Love@academy24");
//		
//		List<WebElement>products=productcatalogue.GetproductList();
//		
//		productcatalogue.addProductTocart(productname);
//		//goTocartPage() is in the Abstractcompnent.java class but thats a parent class and we are extend to child class
//		//with using child class object you can access the methods of parent class
//				
//	CartPage cartpage=productcatalogue.goTocartPage();
//	
//		Boolean match=cartpage.VerifyProductDisplay("qwerty");
//		 Assert.assertFalse(match);
//		//validation should stay in the execution class it should not go in the POM class
//
//	}
	
	
//@Test
	public void ProductErrorValidation() throws IOException {
	    // Verifying the negative scenario
	    String productname = "qwerty";
	    
	    // Log in and get the product catalogue
	    Productcatalogue productcatalogue = landingPage.LoginApllication("shweta24@gmail.com", "Love@academy24");
	    
	    // Fetch the product list
	    List<WebElement> products = productcatalogue.GetproductList();
	    System.out.println("Number of products: " + products.size());
	    
	    // Check if the product exists
	    boolean productFound = products.stream()
	        .anyMatch(product -> product.getText().equals(productname));
	    
	    if (productFound) {
	        productcatalogue.addProductTocart(productname);
	    } else {
	        System.out.println("Product not found: " + productname);
	    }
	    
	    // Navigate to the cart page
	    CartPage cartpage = productcatalogue.goTocartPage();
	    
	    // Verify that the product does not exist in the cart
	    Boolean match = cartpage.VerifyProductDisplay(productname);
	    Assert.assertFalse(match, "The product should not be found in the cart.");
	}


}



