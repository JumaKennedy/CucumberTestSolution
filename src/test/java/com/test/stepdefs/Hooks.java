package com.test.stepdefs;

import org.openqa.selenium.OutputType;

import com.test.framework.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private String scenDesc;
	private ScenarioContext sc;
	private Scenario scenario;

	public Hooks(ScenarioContext scenarioContext) {
		this.sc = scenarioContext;
	}

	@Before
	public void before(Scenario scenario) {
		this.scenDesc = scenario.getName();
		sc.setEnvironment(System.getProperty("environment"));
		sc.setBrowser(System.getProperty("browser"));
		this.scenario = scenario;
		sc.setScenario(scenario);
	}

	@After
	public void after(Scenario scenario) {
		if(scenario.isFailed()) {
			final byte[] screenshot = sc.getDriver().getScreenshotAs(OutputType.BYTES);
			sc.getScenario().attach(screenshot, "image/png", scenDesc);
		}
		sc.quitDriver();
	}

}