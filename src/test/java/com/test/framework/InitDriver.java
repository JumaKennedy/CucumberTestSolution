package com.test.framework;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.stepdefs.Hooks;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

/**
* <h1>InitDriver</h1>
* The InitDriver class handles the driver initialization for web. 
* The drivers are added to the framework, and driver path is mentioned for mac OS. We need to change the driver according the OS and Chrome Version its running 

*/

public class InitDriver {	
	
    protected static final Logger log = LoggerFactory.getLogger(InitDriver.class);
    
    public RemoteWebDriver makeDriver(String driverType)  {
        switch (driverType.toLowerCase()) {

            case "chrome":              
            	return Hooks.chromeproxy();
            case "firefox":   
                // System.setProperty("webdriver.gecko.driver","src/test/resources/driver/geckodriver.exe");
            	//WebDriverManager.chromedriver().driverVersion("117.0.5938.92").setup();
            	WebDriverManager.firefoxdriver().setup();
             return new FirefoxDriver();
            default:
                return null;
        }
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