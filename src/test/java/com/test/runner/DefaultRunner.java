package com.test.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * <h1>DefaultRunner</h1>  The DefaultRunner class will get called first. This config is made in pom file. 
 * We specify tags to be run here & we can still override the tag from cli 
 */

@CucumberOptions(tags = "@CartCheckOut", glue = { "com.test.stepdefs" }, 
				plugin = {"json:target/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"pretty", "html:target/cucumber-reports/report.html",
					    "json:target/cucumber-reports/CucumberTestReport.json"},
				features = { "src/test/resources/features/" })
public class DefaultRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}


}
