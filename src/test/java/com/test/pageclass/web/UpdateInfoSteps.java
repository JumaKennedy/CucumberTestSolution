package com.test.pageclass.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;
/**
* <h1>update info</h1>
* The update class is has all Login Page Attributes and the related action methods
* Single point of place to change if any locator is updated in the Page
* 
*/
public class UpdateInfoSteps extends AbstractPage {
	
	private Logger log = LoggerFactory.getLogger(UpdateInfoSteps.class);
	
	String fullName="";
	
	@FindBy(xpath = "//*[@id='headingFour']/h5/button")
    public WebElement headingFour;	
	
	@FindBy(name = "//*[@id='myinfo']/div/div/div[17]/button[2]") 
    public WebElement resetBtn;
	
	@FindBy(name = "firstName")
    public WebElement fName;
	
	@FindBy(name = "lastName")
    public WebElement lName;
	
	@FindBy(xpath = "//*[@id='mp-pusher']/section[2]/div/div/div[1]/div/div[1]/div/h6")
    public WebElement fullname;
	
	@FindBy(name = "phone")
    public WebElement phone;
  
    @FindBy(name = "adderess")
    public WebElement address;
    
    @FindBy(name = "city")
    public WebElement city; 
    
    @FindBy(name = "state")
    public WebElement state; 
    
    @FindBy(name = "zip")
    public WebElement zip; 
    
    @FindBy(name = "bill_adderess")
    public WebElement bill_address;
    
    @FindBy(name = "bill_city")
    public WebElement bill_city;
    
    @FindBy(xpath = "//*[@id='bill_state']")
    public WebElement bill_state;    
    
    @FindBy(name = "bill_zip")
    public WebElement bill_zip;
    
    @FindBy(name = "shippingnotes")
    public WebElement shippingnotes;
  
    @FindBy(xpath = "//*[@id='myinfo']/div/div/div[17]/button[1]")
    public WebElement submitBtn;    
  
	private ScenarioContext sc;	
	
	// constructor
	public UpdateInfoSteps(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);
	}	
	
	public boolean upDatemyInfo() {	
		headingFour.click();
		//resetBtn.click();
		fName.clear();
		
		webSendKeys(fName, sc.readJsonData("addressDetails", "fname")+sc.getRandomString());
		lName.clear();
		webSendKeys(lName, sc.readJsonData("addressDetails", "lname")+sc.getRandomString());
		phone.clear();
		webSendKeys(phone, sc.readJsonData("addressDetails", "phone"));
		address.clear();
		webSendKeys(address, sc.readJsonData("addressDetails", "address"));
		city.clear();
		webSendKeys(city, sc.readJsonData("addressDetails", "city"));
		selectDropDownByText(state, sc.readJsonData("addressDetails", "state"));
		
		zip.clear();
		webSendKeys(zip, sc.readJsonData("addressDetails", "zip"));
		bill_address.clear();
		webSendKeys(bill_address, sc.readJsonData("addressDetails", "bill_address"));
		bill_city.clear();
		webSendKeys(bill_city, sc.readJsonData("addressDetails", "bill_city"));
		selectDropDownByText(bill_state, sc.readJsonData("addressDetails", "bill_state"));
		bill_zip.clear();
		
		webSendKeys(bill_zip, sc.readJsonData("addressDetails", "bill_zip"));
		shippingnotes.clear();
		webSendKeys(shippingnotes, sc.readJsonData("addressDetails", "shippingnotes"));
		
		fullName = fName.getText() + " " + lName.getText();
		scrollTo(fullname);
		takeScreenShot(sc,fullname.getText()+" filling the form");
	    webClickElement(submitBtn);
	    try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}	
	
	public boolean visit() {
		 //log.info("\nurl: --> {} \n", sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		 sc.getDriver().get(sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		 try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 takeScreenShot(sc,"application launch successful");
		 return true;
	}
	
	public boolean upDatesuccess() {	
		//log.info("\n\nFull name: {} \n\n", fullName, fullname.getText());
		 scrollTo(headingFour);
		 Assert.assertTrue(driver.getPageSource().contains(fullName));
		 takeScreenShot(sc,"Update successful");
		 return true;
	}	
	
	

}