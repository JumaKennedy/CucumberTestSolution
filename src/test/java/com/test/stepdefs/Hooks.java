package com.test.stepdefs;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.framework.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

public class Hooks {
	
	protected static final Logger log = LoggerFactory.getLogger(Hooks.class);	   
	
	private String scenDesc;
	private ScenarioContext sc;
	private Scenario scenario;
	public static BrowserMobProxyServer proxy;
    public Proxy seleniumProxy;
    
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
	
	/*
	 * public static void openBrowser(WebDriver driver, String url) throws
	 * InterruptedException, IOException { proxy.newHar(url); driver.get(url);
	 * Thread.sleep(2000); Har har = proxy.getHar(); String userDirectory =
	 * System.getProperty("user.dir"); File harFile = new
	 * File(userDirectory+File.separator+"my.har"); har.writeTo(harFile); }
	 */
	
	private static Proxy getProxy() {
		BrowserMobProxy proxy = new BrowserMobProxyServer();	   
	    proxy.start(80);	   
	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
	   
	    String hostIp = null;
		try {
			hostIp = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.info("\n\n******\n Error {} \n********\n\n",e.getCause());	    	       
		}
        
	    seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
        seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
	    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
	    
	    proxy.addRequestFilter((request, contents, messageInfo)->{
	        request.headers().add("COP", "JKUFERIFUII879QN0E9F0JEF034");
	        log.info("\n\n******\nHeader Entries: \n{} \n********\n\n",request.headers().entries().toString());
	        return null;
	    });
		return seleniumProxy;		
	}
	
	public static RemoteWebDriver chromeproxy() {	    
	     //System.setProperty("webdriver.chrome.driver", "C:\\DevTools\\webDrivers\\chromedriver.exe");
	     //WebDriverManager.chromedriver().driverVersion("117.0.5938.92").setup();         
	     WebDriverManager.chromedriver().setup();
	     return new ChromeDriver(options());
	
   }
	
	private static ChromeOptions options() {
		ChromeOptions options = new ChromeOptions();
	    String proxyOption = "--proxy-server=" + getProxy().getHttpProxy();
	    options.addArguments(proxyOption);
	    options.addArguments("incognito");
	    options.addArguments("--disable-infobars--");    	    
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--ignore-urlfetcher-cert-requests");
		return options;		
	}
	
	public static RemoteWebDriver chrome() {
    	
	   	    ChromeOptions options = new ChromeOptions();
	        options.addArguments("incognito");
	        options.addArguments("--remote-allow-origins=*");
	        //System.setProperty("webdriver.chrome.driver", "C:\\DevTools\\webDrivers\\chromedriver.exe");
	        //WebDriverManager.chromedriver().driverVersion("117.0.5938.92").setup();         
	        WebDriverManager.chromedriver().setup();
	        return new ChromeDriver(options);
	   	
	 }


	@After
	public void after(Scenario scenario) {
		try {
			if(scenario.isFailed()) {
				final byte[] screenshot = sc.getDriver().getScreenshotAs(OutputType.BYTES);
				sc.getScenario().attach(screenshot, "image/png", scenDesc);
			}
			sc.quitDriver();
			//if(proxy.isStarted())
			//proxy.stop();
		} catch (WebDriverException e) {
			
		}
		
	}
	
	

}