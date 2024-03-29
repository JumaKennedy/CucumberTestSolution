package com.test.framework;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);
	protected static final String LOG_CONTEXT = "context";
	private int driverWaitTime = 30;
	protected RemoteWebDriver driver;

	public AbstractPage(ScenarioContext scenarioContext) {
		if (System.getProperty("driverWaitTime") != null) {
			driverWaitTime = Integer.valueOf(System.getProperty("driverWaitTime"));
		}
		driver = scenarioContext.getDriver();
	}
	
	//Driver Initialization for Mobile,  param to differentiate 
	public AbstractPage(ScenarioContext scenarioContext, String mobileVersion) {
		if (System.getProperty("driverWaitTime") != null) {
			driverWaitTime = Integer.valueOf(System.getProperty("driverWaitTime"));
		}
		driver = scenarioContext.getMobileDriver();
	}
	
	/**
	 * <h1>webClickElement</h1>
	 * This webClickElement - Clicks on the web element if present
	 * Accepts WebElement  
	 * Script Fails if element is not found
	 */
	public boolean webClickElement(WebElement ele) {		
		wiatTemeot().until(ExpectedConditions.visibilityOf(ele));
		ele.click();
		return true;
	}

	/**
	 * <h1>webClickElement</h1>
	 * This webClickElement - Clicks on the web element if present
	 * Accepts String web element locator 
	 * Script Fails if element is not found
	 */
	public boolean webClickElement(String ele) {		
		wiatTemeot().until(ExpectedConditions.elementToBeClickable((By.xpath(ele)))).click();
		return true;
	}

	/**
	 * <h1>webSendKeys</h1>
	 * This webSendKeys - enters the user provided input in the webelement 
	 * Accepts WebElement and the input string
	 * Script Fails if element is not found
	 */
	public boolean webSendKeys(WebElement ele, String input) {		
		wiatTemeot().until(ExpectedConditions.visibilityOf(ele));
		ele.sendKeys(input);
		return true;
	}


	/**
	 * <h1>webClearField</h1>
	 * This webClearField - clears the WebElement field
	 * Accepts WebElement  
	 * Script Fails if element is not found
	 */
	public boolean webClearField(WebElement ele) {
		ele.clear();
		return true;
	}

	/**
	 * <h1>webGetAttribute</h1>
	 * This webGetAttribute - returns text value for the webElement element if present
	 * Accepts String element path  
	 * Script Fails if element is not found
	 */
	public String webGetText(String ele) {		
		wiatTemeot().until(ExpectedConditions.elementToBeClickable((By.xpath(ele))));
		return driver.findElement(By.xpath(ele)).getText();
	}

	/**
	 * <h1>webGetAttribute</h1>
	 * This webGetAttribute - returns text value for the webElement element if present
	 * Accepts WebElement  
	 * Script Fails if element is not found
	 */
	public String webGetText(WebElement ele) {		
		wiatTemeot().until(ExpectedConditions.visibilityOf(ele));
		return ele.getText();
	}
	
	/**
	 * <h1>webGetAttribute</h1>
	 * This webGetAttribute - returns attribute value for the webElement element if present
	 * Accepts WebElement and the attribute Name 
	 * Script Fails if element is not found
	 */
	public String webGetAttribute(WebElement ele, String attributeName) {		
		wiatTemeot().until(ExpectedConditions.visibilityOf(ele));
		return ele.getAttribute(attributeName);
	}

	/**
	 * <h1>webIsElementVisible</h1>
	 * This webIsElementVisible - returns boolean if element is present, false otherwise
	 * 
	 * Accepts WebElement 
	 */
	public boolean webIsElementVisible(WebElement ele) {
		try{
			if(ele.isDisplayed()){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * <h1>webIsElementVisible</h1>
	 * This webIsElementVisible - returns boolean if element is present, false otherwise
	 * 
	 * Accepts String locator
	 */
	public boolean webIsElementVisible(String ele) {
		try{
			wiatTemeot().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele)));
			if(driver.findElement(By.xpath(ele)).isDisplayed()){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	

	/**
	 * <h1>jsClickWithoutWait</h1>
	 * This jsClickWithoutWait - Java script click on the element passed
	 */
	public void jsClickWithoutWait(WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	
	/**
	 * <h1>jsClickWithoutWait</h1>
	 * This jsClickWithoutWait - Java script click on the element passed
	 */
	public void jsClickWithWait(WebElement ele) {		
		wiatTemeot().until(ExpectedConditions.visibilityOf(ele));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	/**
	 * <h1>webGetListOfElements</h1>
	 * This webGetListOfElements -returns the list of available webelements for the provided locator element
	 */
	public List<WebElement> webGetListOfElements(String ele) {		
		wiatTemeot().until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(ele))));
		return driver.findElements(By.xpath(ele));
	}

	/**
	 * <h1>webIsElementSelected</h1>
	 * This webIsElementSelected - Returns true if checkbox / radio button is selected
	 */
	public boolean webIsElementSelected(WebElement ele) {
		return ele.isSelected();
	}

	/**
	 * <h1>waitForPageLoad</h1>
	 * This waitForPageLoad - waits for the browser to load completely
	 */
	public void waitForPageLoad() {		
		wiatTemeot().until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	/**
	 * <h1>pressTab</h1>
	 * This pressTab - presses Keyboard Tab Key
	 * Returns true boolean if the operation is successful
	 */
	public boolean pressTab() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		return true;
	}
	
	/**
	 * <h1>pressTab</h1>
	 * This Escape - presses Keyboard escape Key
	 * Returns true boolean if the operation is successful
	 */
	public boolean pressEscape() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ESCAPE).build().perform();
		return true;
	}
	
	/**
	 * <h1>pressEnter</h1>
	 * This pressEnter - presses Keyboard Enter Key
	 * Returns true boolean if the operation is successful
	 */
	public boolean pressEnter() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
		return true;
	}
	
	/**
	 * <h1>takeWebScreenShot</h1>
	 * This takeWebScreenShot method Take screenshot for web app
	 */
	public boolean takeScreenShot(ScenarioContext sc, String content) {
		final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);		
		sc.getScenario().attach(screenshot, "image/png", content);
		return true;
	}

	/**
	 * <h1>switchToIFrame</h1>
	 * This method accepts iframe id
	 */
	public boolean switchToIFrame(String ele) {
		driver.switchTo().frame(ele); //switch to frame
		return true;
	}

	/**
	 * <h1>selectDropDownByName</h1>
	 * This method selects a value by name from dropdown field
	 */
	public boolean selectDropDownByName(String dropDownName, String dropDownValue) {
		Select drpCountry = new Select(driver.findElement(By.name(dropDownName)));
		drpCountry.selectByVisibleText(dropDownValue);
		return true;
	}
	/**
	 * <h1>selectDropDown by element</h1>
	 * This method selects a drop down field by element
	 */
	public boolean selectDropDownByText(WebElement el, String dropDownValue) {
		Select select = new Select(el);
		select.selectByVisibleText(dropDownValue);
		return true;
	}
	
	public void scrollTo(WebElement element) {		
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	protected Wait<WebDriver> wiatTemeot(){
		return new WebDriverWait(driver, driverWaitTime).ignoring(NoSuchElementException.class);
	}	
	
	public WebElement find(By locator) {
		wiatTemeot().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }
	
    public void type(String inputText, By locator) {
    	 find(locator).sendKeys(inputText);
    }

    public void type(Keys key,By locator) {
    	 find(locator).sendKeys(key);
    }

    public void clear(By locator) {
    	 find(locator).clear();
    }

    public void click(By locator) {
        find(locator).click();
    }
	
	public boolean close() {
		 driver.close();
		 return true;
	}

}
