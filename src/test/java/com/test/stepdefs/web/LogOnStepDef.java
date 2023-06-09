package com.test.stepdefs.web;
import org.testng.Assert;

import com.test.framework.ScenarioContext;
import com.test.pageclass.web.LogOnPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogOnStepDef {
   
	private ScenarioContext sc;
    private LogOnPage logOnPage;

    public LogOnStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        logOnPage = new LogOnPage(sc);
    }

    @Given("I am on Login page")
    public void i_am_on_login_page() {
       
    	logOnPage.visit();
    }
    
    @When("I enters {string} credentials: username and a apassword")
    public void i_enters_credentials_username_and_a_apassword(String credential) {
    	logOnPage.loginToSite(credential);  
    }

    @Then("I should be able to login sucessfully")
    public void i_should_be_able_to_login_sucessfully() {
    	logOnPage.getProfileTitle();
        
    }

    @Then("I logout")
    public void i_logout() throws Exception {
    	logOnPage.Logout();
        
    }
    
    @Then("I should get error message {string}")
    public void i_should_get_error_message(String error) {
    	Assert.assertEquals(logOnPage.ErrorMesssage(), error);
    }
    
}