package com.test.pageclass.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;
import com.test.framework.TextEncryptor;

/**
* <h1>LoginPage</h1>
* The LoginPage class is has all Login Page Attributes and the related action methods
* Single point of place to change if any locator is updated in Login Page
* 
*/
public class AddItemsToCartPage extends AbstractPage {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AddItemsToCartPage.class);

	@FindBy(linkText = "SHOP")
    public WebElement ShopMenuOption;
	
	@FindBy(linkText = "Clothing")
    public WebElement shopClothing;	
	
	@FindBy(xpath = "//i[@class='fa fa-arrows-alt']")
    public WebElement quickviewBtn;
	
	@FindBy(xpath = "//body")
    public WebElement clickBody;
	
	@FindBy(xpath = "//a[contains(.,'Print Maxi Dress ')]")
    public WebElement addToCartBtn;	
	
	@FindBy(xpath = "//select[contains(@id,'color')]")
    public WebElement selectColor;
	
	@FindBy(xpath = "//select[contains(@id,'size')]")
    public WebElement selectSize;
	
	@FindBy(xpath = "//input[contains(@id,'quanity')]")
    public WebElement selectQty;
	
	@FindBy(xpath = "//span[contains(.,' Add to Cart')]")
    public WebElement AddBtn;
	
	@FindBy(xpath = "//*[@id='mp-pusher']/section[2]/div/div/div/div[1]/div[2]/a[2]")
    public WebElement checkOutBtn;
	
	@FindBy(xpath = "//*[@id='mp-pusher']/section[2]/div/div/div/div[1]/div[2]/a[1]/span")
    public WebElement continueShoppingBtn;	
	
	@FindBy(xpath = "//*[@id='mp-pusher']/section[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[1]/a")
    public WebElement clearCart;
	
	@FindBy(xpath = "//h5[contains(.,'Cart is empty!  Shop ')]")
    public WebElement emptyCart;
	
	@FindBy(xpath = "//a[@data-target='#qreview']")
    public WebElement review;
	
	@FindBy(xpath = "//button[@data-toggle='collapse']")
    public WebElement shipInfo;
	
	JavascriptExecutor js;
	
	Actions actions = new Actions(driver);	   
	
	private ScenarioContext sc;
	
	// constructor
	public AddItemsToCartPage(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);	
	}
	
	public boolean visit() {
		 sc.getDriver().get(sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		 try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 takeScreenShot(sc,"application lunch successful");
		 return true;
	}
	
	public boolean clickShopOption() {	 
		
		actions.moveToElement(ShopMenuOption).perform();
		takeScreenShot(sc,"selecting clothing from Shop ShopMenu");
		scrollTo(shopClothing);
		webClickElement(shopClothing);	 
		return true;
	}
	
	public boolean quickviewItem() throws InterruptedException {
		quickviewBtn.click();	
		Thread.sleep(500);
		takeScreenShot(sc,"Quick View");
	    js =(JavascriptExecutor)driver;
		js.executeScript("history.go(0);");		
		
		return true;
	}
	
	public boolean addToCartBtn() {
		
		//webClickElement(addToCartBtn);	
		jsClickWithWait(addToCartBtn);
		webClickElement(selectSize);
		selectDropDownByText(selectSize, "L");
		webClickElement(selectColor);
		selectDropDownByText(selectColor, "Gold");	
		selectQty.clear();
		selectQty.sendKeys("2");
		scrollTo(review);
		takeScreenShot(sc,"adding item to card");
		webClickElement(AddBtn);
		
		return true;
	}
	
	public boolean CartPage() {
		
		Assert.assertTrue(checkOutBtn.isDisplayed());
		Assert.assertTrue(continueShoppingBtn.isDisplayed());
		scrollTo(shipInfo);
		takeScreenShot(sc,"at the shopping cart page");
		return true;
	}
	
	public boolean emptyCart() {
		scrollTo(shipInfo);
		webClickElement(clearCart);
		
		return true;
	}
	
	public boolean noItemsIncart() {
		
		waitForPageLoad();		
		scrollTo(emptyCart);
		String msg =emptyCart.getText();
		Reporter.log("\n\nMessage: "+msg+" Page title: "+driver.getTitle(), true);
		Assert.assertEquals(msg, "Cart is empty! Shop");
		
		takeScreenShot(sc,"cart is now empty");
		
		return true;
	}
	
	
	

	

}