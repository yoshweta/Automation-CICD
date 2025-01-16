
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  
  Background:
  Given I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username <name> and <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name               | password           | productName|
      | shweta24@gmail.com | Love@academy24     | IPHONE 13 PRO|
      
