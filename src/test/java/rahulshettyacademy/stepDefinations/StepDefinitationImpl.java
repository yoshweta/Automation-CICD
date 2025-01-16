package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObjectModel.CartPage;
import rahulshettyacademy.PageObjectModel.LandingPage;
import rahulshettyacademy.PageObjectModel.Productcatalogue;
import rahulshettyacademy.PageObjectModel.checkoutPage;
import rahulshettyacademy.PageObjectModel.confirmationPage;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitationImpl extends BaseTest
{
	public LandingPage landingPage;
	public Productcatalogue productcatalogue;
	public confirmationPage confirmationmsg;
	//this is the plain scenario line
	@Given ("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	//when scenario is regular expression the you have to ^ at the front and $ at the end ,the regular express(.+)
	//cucumber find dynamic value automatically
	  @Given ("^Logged in with username (.+) and (.+)$")
	  public void Logged_in_with_username_and_password(String username,String password)
	  {
		   productcatalogue=landingPage.LoginApllication(username,password );
	  }
	  @When("^I add product (.+) to Cart$")
	  public void I_add_product_to_Cart(String productname)
	  {
	      List<WebElement> products = productcatalogue.GetproductList();
	      productcatalogue.addProductTocart(productname);
	  }

	  @And ("^Checkout (.+) and submit the order$")
	  public void Checkout_productName_and_submit_the_order(String productname)
	  {
	      CartPage cartpage = productcatalogue.goTocartPage();
	      Boolean match = cartpage.VerifyProductDisplay(productname);
	      Assert.assertTrue(match);

	      checkoutPage checkout = cartpage.goTocheckout();
	      checkout.SelectCountry("india");
	      confirmationmsg = checkout.submitOrder();
	  }
	  //here is the parameterization-the message is in the step only we are not getting that data from anywhere else
	  @Then("{string} message is displayed on ConfirmationPage")
	    public void message_is_displayed_on_ConfirmationPage(String string) {
	        String confirmMeaage = confirmationmsg.getConfiramtionMessage();
	        Assert.assertTrue(confirmMeaage.equalsIgnoreCase(string));
	    
	    }

	  //There are two ways to show the message 
	  @Then("{string} message is displayed")
	  public void message_is_displayed(String strArg1) throws Throwable
	  {
		  Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		 
	  }
	  @After
	  public void teardown() {
	      if (driver != null) {
	          driver.quit();
	      }
	  }

}
