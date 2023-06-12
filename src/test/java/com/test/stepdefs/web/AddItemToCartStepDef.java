package com.test.stepdefs.web;
import com.test.framework.ScenarioContext;
import com.test.pageclass.web.AddItemsToCartPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddItemToCartStepDef {
   
	private ScenarioContext sc;
    private AddItemsToCartPage addItemsToCartPage;

    public AddItemToCartStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        addItemsToCartPage = new AddItemsToCartPage(sc);
    }

    @Given("I am at home page")
    public void i_am_at_home_page() {
        
    	addItemsToCartPage.visit();
    }

    @When("I click on shop button from top menu")
    public void i_click_on_shop_button_from_top_menu() {
    	addItemsToCartPage.clickShopOption();
      
    }
    
    @Then("I am taken to shop menu list and I select item")
    public void i_select_item() throws InterruptedException {
    	addItemsToCartPage.quickviewItem();
      
    }

    @Then("I add an Item to cart by selecting color, size and Qty")
    public void i_add_to_cart() {
    	addItemsToCartPage.addToCartBtn();
      
    }

    @Then("I am taken to cart with Option to check out or continue shopping.")
    public void i_am_taken_to_cart_withoption_to_check_out_or_continue_shopping() {
    	addItemsToCartPage.CartPage();
      
    }

    @Then("I change my mind, so I cancel the order by clearing the cart")
    public void i_change_my_mind_so_i_cancel_the_order_by_clearing_the_cart() {
    	addItemsToCartPage.emptyCart();
      
    }

    @Then("The cart should be empty")
    public void the_cart_should_be_empty() {
    	addItemsToCartPage.noItemsIncart();
      
    }
    
}