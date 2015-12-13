package com.guiPages.pages;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.guiPages.baseClasses.TestBase;

public class BasePage {
	
	private static final int EXPLICIT_WAIT_TIMEOUT=10;
	private static final int IMPLICIT_WAIT_TIMEOUT=10;
	
		
	protected WebDriver driver;
	String pageTitle;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void open() {
		driver.get(TestBase.getUrl());
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public void webDriverImplicitWait() {
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
	}
	
	public void webDriverExplicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void pageScreenShot(String name) throws Exception {
		TakesScreenshot shot = (TakesScreenshot)driver;
		File scrshot= shot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrshot, new File(TestBase.getScreenshotPath()+name+"-"+getTimeStamp()+".jpg"));
	}
	
	public String getTimeStamp() throws Exception {
		Date time=new Date();
	    String timeStamp = new Timestamp(time.getTime()).toString();
	    timeStamp=timeStamp.replace(' ','_');
	    timeStamp=timeStamp.replace(":", "_");
	    return timeStamp;
	}
	
	public void handleAlertWindow(boolean bool) {
		Alert dialogue = driver.switchTo().alert();
		if(bool) {
			dialogue.accept();
		}
		else {
			dialogue.dismiss();
		}
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public <T>T clickAndNavigate(Class<T>c , WebElement element) {
		element.click();
		return PageFactory.initElements(driver,c);
	}
	
	
}
