package com.test.stepdefs.web;
import com.test.framework.ScenarioContext;
import com.test.pageclass.web.UpdateInfoSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UpdateInfoStepDef {
   
	private ScenarioContext sc;
    private UpdateInfoSteps updateInfoSteps;

    public UpdateInfoStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        updateInfoSteps = new UpdateInfoSteps(sc);
    }
    
    @Given("I login with my credentials")
    public void i_login_with_my_credentials() {
    	 updateInfoSteps.visit();    	   	
    }
    
    @Then("I fill in the profile update form and submit")
    public void i_fill_in_the_profileupdate_form_and_submit() {
    	updateInfoSteps.upDatemyInfo();
        
    }

    @Then("My information should be changed sucessfully")
    public void my_information_should_be_changed_sucessfully() {
    	updateInfoSteps.upDatesuccess();
        
    }

   
}