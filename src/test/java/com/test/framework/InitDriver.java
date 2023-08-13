package com.test.framework;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

/**
* <h1>InitDriver</h1>
* The InitDriver class handles the driver initialization for web. 
* The drivers are added to the framework, and driver path is mentioned for mac OS. We need to change the driver according the OS and Chrome Version its running 

*/

public class InitDriver {
	
	public BrowserMobProxyServer proxy;
    public Proxy seleniumProxy;
	
    protected static final Logger log = LoggerFactory.getLogger(InitDriver.class);
    
    public RemoteWebDriver makeDriver(String driverType)  {
        switch (driverType.toLowerCase()) {

            case "chrome":              
              return chromeproxy();
            case "firefox":   
            // System.setProperty("webdriver.gecko.driver","src/test/resources/driver/geckodriver.exe");
            	WebDriverManager.chromedriver().driverVersion("113.0.5672.63").setup();
            	WebDriverManager.firefoxdriver().setup();
             return new FirefoxDriver();
            default:
                return null;
        }
    }
    

    public void openBrowser(WebDriver driver, String url) throws InterruptedException, IOException {
        proxy.newHar("google.com");
        driver.get(url);
        Thread.sleep(2000);
        Har har = proxy.getHar();
        File harFile = new File("google.har");
        har.writeTo(harFile);
    } 

    private RemoteWebDriver chromeproxy() {
    	
    	    BrowserMobProxy proxy = new BrowserMobProxyServer();
    	    proxy.start(0);
    	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

    	    proxy.addRequestFilter((request, contents, messageInfo)->{
    	        request.headers().add("iv-user", "login");
    	        System.out.println(request.headers().entries().toString());
    	        return null;
    	    });

    	    ChromeOptions options = new ChromeOptions();
    	    String proxyOption = "--proxy-server=" + seleniumProxy.getHttpProxy();
    	    options.addArguments(proxyOption);
    	    options.addArguments("-incognito");
    	    options.addArguments("disable-infobars");
    	    options.addArguments("incognito");
            options.addArguments("--remote-allow-origins=*");
    	    
         //System.setProperty("webdriver.chrome.driver", "C:\\DevTools\\webDrivers\\chromedriver.exe");
         //WebDriverManager.chromedriver().driverVersion("113.0.5672.63").setup();         
         WebDriverManager.chromedriver().setup();
         return new ChromeDriver(options);
    	
    }
    
    private RemoteWebDriver chrome() {
    	
   	 ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("--remote-allow-origins=*");
        //System.setProperty("webdriver.chrome.driver", "C:\\DevTools\\webDrivers\\chromedriver.exe");
        //WebDriverManager.chromedriver().driverVersion("113.0.5672.63").setup();         
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
   	
   }

    
    
    public RemoteWebDriver makeMobDriver(String deviceVersion)  {
    	RemoteWebDriver driver = null;
        
    	switch (deviceVersion.toLowerCase()) {
            case "android":
            	File appDir = new File("src/test/resources/apps");
                File app = new File(appDir, "ApiDemosdebug.apk"); //Android Dummy APK
            	DesiredCapabilities capabilities = new DesiredCapabilities();
            	capabilities.setCapability("DEVICE_NAME", "AndroidDevice");
            	capabilities.setCapability("VERSION", "10"); 
            	capabilities.setCapability("platformName","Android");
                capabilities.setCapability("app", app.getAbsolutePath());
                capabilities.setCapability("automationName","uiautomator2");
				try {
				    //start appium session manually and open the avd from android studio
					driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	break;
            case "ios":
            	break;
        }
		return driver;
        
    } 

}