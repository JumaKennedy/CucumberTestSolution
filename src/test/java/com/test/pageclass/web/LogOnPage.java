package com.test.pageclass.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;
import com.test.framework.TextEncryptor;

/**
* <h1>LoginPage</h1>
* The LoginPage class is has all Login Page Attributes and the related action methods
* Single point of place to change if any locator is updated in Login Page
* 
*/
public class LogOnPage extends AbstractPage {
	
	protected static final Logger LOG = LoggerFactory.getLogger(LogOnPage.class);

	@FindBy(name = "email")
    public WebElement userName;
  
    @FindBy(name = "password")
    public WebElement password;
  
    @FindBy(xpath = "//*[@id='mp-pusher']/section/div/div/div/form/div/div[1]/div/h2")
    public WebElement titleText;
  
    @FindBy(name = "submit")
    public WebElement loginBtn;
    
    @FindBy(xpath = "//*[@id='nav']/li[6]")
    public WebElement logOutBtn;
  
    @FindBy(xpath = "//*[@id='error']")
    public  WebElement errorMessage;
    
    @FindBy(xpath = "//*[@id='mp-pusher']/section[2]/div/div/div[2]/div/div/h4")
    public  WebElement profilePageTitle; 
    
    @FindBy(xpath = "//*[@id='mp-pusher']/section/div/div/div/h5/small")
    public  WebElement logOutMessage;
    
    @FindBy(xpath = "//*[@id='mp-pusher']/section[2]/div/div/div[1]/div/div[1]/div/h6")
    public WebElement fullname;    

    @FindBy(xpath = "//a[@title='Register with Now']")
    public WebElement registerLink;
	
	private ScenarioContext sc;
	
	// constructor
	public LogOnPage(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);	
	}
	
	public boolean visit() {
		//LOG.info("url: -->",  sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		sc.getDriver().get(sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		 try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 scrollTo(registerLink);
		 webClickElement(loginBtn);	 
		 takeScreenShot(sc,"application lunch successful");
		 return true;
	}

	// Launch Shopping Web Site and Login
	public boolean loginToSite(String credentials) {
		webSendKeys(userName, TextEncryptor.decodedString(sc.readJsonData(credentials, "userName")));
		webSendKeys(password, TextEncryptor.decodedString(sc.readJsonData(credentials, "passWord")));
		scrollTo(registerLink);
		takeScreenShot(sc,"Login to application");
		webClickElement(loginBtn);	 
		return true;
	}	
	
	 // Get the title of Login Page
    public String getLoginTitle() {
        return titleText.getText();
    }
    
   // Get the title of profile Page
    public String getProfileTitle() {
    	scrollTo(fullname);
    	takeScreenShot(sc,"profile");
        return profilePageTitle.getText();
    }
     
    public void Logout() throws Exception {
    	logOutBtn.click(); 
    	takeScreenShot(sc,"Logout success");
    	Assert.assertTrue(logOutMessage.getText().contains("You have been successfully logged out"));
    }
     
	public boolean close() {
		 sc.getDriver().close();
		 return true;
	}

	public String ErrorMesssage() {		
		takeScreenShot(sc,"Invalid Credentials Error");
		return errorMessage.getText();		
		
	}
	

}