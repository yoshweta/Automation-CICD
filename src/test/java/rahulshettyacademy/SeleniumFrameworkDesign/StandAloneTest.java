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

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StandAloneTest extends BaseTest {
    
    String productname = "IPHONE 13 PRO"; // Common product name for both tests
    
    @Test(dataProvider = "getData", groups = {"purchase"})
    public void StandAloneTest(String email, String password, String productname) throws IOException {
        
        // Login to the application
        Productcatalogue productcatalogue = landingPage.LoginApplication(email, password);
        
        // Get the list of products and add the desired product to the cart
        List<WebElement> products = productcatalogue.GetproductList();
        productcatalogue.addProductToCart(productname);
        
        // Go to the cart and verify the product is displayed
        CartPage cartPage = productcatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productname);
        Assert.assertTrue(match, "The product is not displayed in the cart!");
        
        // Proceed to checkout and confirm the order
        checkoutPage checkout = cartPage.goToCheckout();
        checkout.SelectCountry("India");
        
        confirmationPage confirmationMsg = checkout.submitOrder();
        String confirmationMessage = confirmationMsg.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANK YOU FOR THE ORDER."),
                "Order confirmation message does not match.");
    }
    
    // OrderHistoryTest is dependent on the previous test (StandAloneTest)
    @Test(dependsOnMethods = {"StandAloneTest"})
    public void OrderHistoryTest() {
        Productcatalogue productcatalogue = landingPage.LoginApplication("shweta24@gmail.com", "Love@academy24");
        
        // Go to the orders page and verify the order
        OrderPage orderPage = productcatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productname), "The order is not displayed in the order history!");
    }
    
    // DataProvider for test data
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
            {"shweta24@gmail.com", "Love@academy24", "IPHONE 13 PRO"},
            {"shweta25@gmail.com", "Love@academy25", "qwerty"}
        };
    }
}
