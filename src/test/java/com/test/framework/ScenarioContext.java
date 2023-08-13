package com.test.framework;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.google.gson.Gson;
import com.test.stepdefs.Hooks;

import io.cucumber.java.Scenario;

public class ScenarioContext {

	protected static final Logger LOG = LoggerFactory.getLogger(ScenarioContext.class);

	private RemoteWebDriver driver;
	private InitDriver initDriver = new InitDriver();
	private Map<String, Object> contextMap;
	private Scenario scenario;

	//Default Configs
	private String environment;
	private String browser;	
	
	
	public ScenarioContext() {
		setContextMap(new HashMap<>());
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public Scenario getScenario() {
		return scenario;
	}

	/**
	 * <h1>getDriver</h1>
	 * This getDriver method check if the driver is alive not null in the context before creating the driver session
	 * 
	 * The driver session is maintained across classes through out scenario session
	 * 
	 * Returns RemoteWebDriver for the browser instance (chrome / firefox)  set in context 
	 */
	
	public RemoteWebDriver getDriver() {
		if (null == driver) {
			driver = initDriver.makeDriver(getBrowser());
			// Maximize current window
			driver.manage().window().maximize();
			try {
				Hooks.openBrowser(driver, "reenbeauty.com");				
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}
		return driver;
	}
	
	public RemoteWebDriver getMobileDriver() {
		System.out.println("Initialise Android Driver");
		if (null == driver) {
			driver = initDriver.makeMobDriver("android");//configure it later for iOS
		}
		return driver;
	}

	/**
	 * <h1>quitDriver</h1>
	 * This quitDriver method checks if the browser's driver session is alive before quitting
	 * 
	 */
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public Map<String, Object> getContextMap() {
		return contextMap;
	}

	public void setContextMap(Map<String, Object> contextMap) {
		this.contextMap = contextMap;
	}
	
	//Utilities
	/**
	 * <h1>readJsonData</h1>
	 * This readJsonData method reads the individual test data attribute from json 
	 * 
	 * This method takes 2 parameters, 1 to identify the node and second parameter identifies to the attribute to fetch
	 * 
	 * Returns String value of the requested attribute
	 */
	public String readJsonData(String node, String field) {
		Object obj = null;
		LOG.info("\n========== Environment: {} ======= \n", getEnvironment().toUpperCase());
		try {
			switch (getEnvironment().toUpperCase()) {
			case "PROD":
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/prodconfig.json"));
				break;
			case "CAT":
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/catconfig.json"));
				break;
			case "SIT":
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/sitconfig.json"));
				break;

			default:
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/sitconfig.json"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "Exception on reading the json field " + field + " node " + node + " .");
		}

		JSONObject jo = (JSONObject) obj;
		return (String) ((JSONObject) jo.get(node)).get(field);
	}

	/**
	 * <h1>readWholeJsonData</h1>
	 * This readWholeJsonData method reads the test data array from json 
	 * 
	 * This method takes 1 parameters, 1 to identify the node 
	 * 
	 * Returns whole node value, use this when you want to read more than one value
	 * from json to avoid multiple reads from json
	 */

	// get the whole json map if more records are using to avoid multiple read
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> readWholeJsonData(String node) {
		Object obj = null;
		try {
			switch (getEnvironment().toUpperCase()) {
			case "PROD":
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/prodconfig.json"));
				break;
			case "CAT":
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/catconfig.json"));
				break;
			case "SIT":
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/sitconfig.json"));
				break;

			default:
				obj = new JSONParser().parse(new FileReader("src/test/resources/data/sitconfig.json"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "Exception on reading the json + " + node + " .");
		}
		JSONObject jo = (JSONObject) obj;
		return new Gson().fromJson(((JSONObject) jo.get(node)).toString(), HashMap.class);

	}

	/**
	 * <h1>getRandomString</h1>
	 * This getRandomString method  generates randomString - reads the parameter randomStringCount configurable at pom file
	 */
	public String getRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = Integer.parseInt(System.getProperty("randomStringCount"));
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	/**
	 * <h1>decodedString</h1>
	 * This decodedString method decodes the base64 encoded strings and returns it
	 */
	public String decodedString(String encodedString) {
		byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}

	/**
	 * <h1>encodedString</h1>
	 * This encodedString method encodes the string in base64 encoding and returns it
	 */
	public String encodedString(String actualString) {
		String encodedString = Base64.getEncoder().encodeToString(actualString.getBytes());
		return encodedString;
	}
	
	/**
	 * <h1>getCurrentDate</h1>
	 * This getCurrentDate method returns the current date in MM/dd/yyyy format
	 */
    public String getCurrentDate() {
       Calendar cal = Calendar.getInstance();
       DateFormat dateFormat=null;
       dateFormat = new SimpleDateFormat("MM/dd/yyyy");
       return dateFormat.format(cal.getTime());

    }

	/**
	 * <h1>getRandomDateString</h1>
	 * This getRandomDateString method returns the future date in dd-mm-yyyy format
	 */
	public String getRandomDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		Random r = new Random();
		int low = 250;
		int high = 500;
		int result = r.nextInt(high-low) + low;
		cal.add(Calendar.DAY_OF_MONTH, result);
		return sdf.format(cal.getTime());
	}

	

}
