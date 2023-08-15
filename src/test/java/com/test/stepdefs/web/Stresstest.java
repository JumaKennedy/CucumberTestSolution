package com.test.stepdefs.web;

import org.testng.annotations.Test;

import com.test.framework.ScenarioContext;
import com.test.pageclass.web.UpdateInfoSteps;

public class Stresstest {
	
	private ScenarioContext sc;
    private UpdateInfoSteps updateInfoSteps;

    public Stresstest(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        updateInfoSteps = new UpdateInfoSteps(sc);
    }
    
    @Test(description = "Updating profile info", invocationCount=5)
    public void i_login_with_my_credentials() {
    	updateInfoSteps.visit();   
    	updateInfoSteps.upDatemyInfo();
    	updateInfoSteps.upDatesuccess();
    }

}
