package com.guiPages.baseClasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {
	
	public static WebDriver driver;
	protected static AutomationProperties properties = new AutomationProperties();
		
	@Parameters("browser")
	@BeforeTest
	public void beforeClass(String BROWSER) throws Exception {
		
		if(BROWSER.contentEquals("firefox")) {
			FirefoxProfile ffprofile = new FirefoxProfile();
			ffprofile.setAssumeUntrustedCertificateIssuer(false);
			driver= new FirefoxDriver(ffprofile);
			}
		
		else if(BROWSER.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--test-type");
			driver=new ChromeDriver(chromeoptions);
		}
		
		else if(BROWSER.contentEquals("ie")) {
			System.setProperty("webdriver.ie.driver",getIEDriverPath());
			driver = new InternetExplorerDriver();
		}
		
		else {
			throw new IllegalArgumentException("Invalid browser type");
		}
	}
	
	public static String getTodayDate() {
		DateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String today=dateformat.format(date);
		return today;
	}
	
	public static String getBeforeDate(int days) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		return dateFormat.format(calendar.getTime());
	}
	
	public static String getAfterDate(int days) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,days);
		return dateFormat.format(calendar.getTime());
	}

	public static String getUrl() {
		return properties.getPropertyValue(AutomationProperties.BASE_URL);
	}
	
	public static String getExcelPath() {
		return properties.getPropertyValue(AutomationProperties.XLS_PATH);
	}
	
	public static String getScreenshotPath() {
		return properties.getPropertyValue(AutomationProperties.SCREENSHOT_PATH);
	}
	
	public static String getChromeDriverPath() {
		return properties.getPropertyValue(AutomationProperties.CHROME_DRIVER_PATH);
	}
	
	public static String getIEDriverPath() {
		return properties.getPropertyValue(AutomationProperties.IE_DRIVER_PATH);
	}
	
}
