package com.test.stepdefs.web;
import com.test.framework.ScenarioContext;
import com.test.pageclass.web.ShoppingCartPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingCartStepDef {
   
	private ScenarioContext sc;
    private ShoppingCartPage shoppingCartPage;

    public ShoppingCartStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        shoppingCartPage = new ShoppingCartPage(sc);
    }

    @Given("I am at home page")
    public void i_am_at_home_page() {
        
    	shoppingCartPage.visit();
    }

    @When("I click on shop button from top menu")
    public void i_click_on_shop_button_from_top_menu() {
    	shoppingCartPage.clickShopOption();
      
    }
    
    @Then("I am taken to shop menu list and I select item")
    public void i_select_item() throws InterruptedException {
    	shoppingCartPage.quickviewItem();
      
    }

    @Then("I add an Item to cart by selecting color, size and Qty")
    public void i_add_to_cart() {
    	shoppingCartPage.addToCartBtn();
      
    }

    @Then("I am taken to cart with Option to check out or continue shopping.")
    public void i_am_taken_to_cart_withoption_to_check_out_or_continue_shopping() {
    	shoppingCartPage.CartPage();
      
    }

    @Then("I change my mind, so I cancel the order by clearing the cart")
    public void i_change_my_mind_so_i_cancel_the_order_by_clearing_the_cart() {
    	shoppingCartPage.emptyCart();
      
    }

    @Then("The cart should be empty")
    public void the_cart_should_be_empty() {
    	shoppingCartPage.noItemsIncart();
      
    }
    
    @Then("I click check out button")
    public void i_click_check_out_button() {
    	shoppingCartPage.checkOut(); 
        
    }

    @Then("Ienter my shipping information")
    public void ienter_my_shipping_information() {
    	shoppingCartPage.addShippingAddress();
        
    }

    @Then("I am taken to payment option and I select paypal")
    public void i_am_taken_to_payment_option_and_i_select_paypal() {
    	shoppingCartPage.checkouwithPaypal();
        
    }

    @Then("Im taken to paypal check out site")
    public void im_taken_to_paypal_check_out_site() {
    	shoppingCartPage.paypalOption();
    	
        
    }
    
}