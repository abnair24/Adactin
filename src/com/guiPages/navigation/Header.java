package com.guiPages.navigation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.guiPages.common.CommonMethods;
import com.guiPages.pages.BasePage;


public class Header extends BasePage {
	
	public static final String SEARCH_HOTEL= "Search Hotel";
	public static final String BOOKED_ITINERARY="Booked Itinerary";
	public static final String CHANGE_PASSWORD="Change Password";
	public static final String LOGOUT="Logout";
	public static final String WELCOME_MESSAGE="Welcome to AdactIn Group of Hotels";
	
	@FindBy(xpath="html/body/table[2]/tbody/tr/td[1]/p[1]")
	WebElement welcomeMessage;
	
	@FindBys({@FindBy(className="welcome_menu"),@FindBy(tagName="a")})
	List<WebElement> tabs;
	
	@FindBy(xpath=".//*[@id='username_show']")
	WebElement userName;
	
	public Header(WebDriver driver) {
		super(driver);
	}
	
	public String getWelcomeMessage() {
		return welcomeMessage.getText();
	}
	
    public <T>T clickTab(String tabName,Class<T> c) throws Exception {
		CommonMethods.getElementByText(tabs,tabName).click();
		return PageFactory.initElements(driver,c);
	}
	
	
	public String getWelcomeUserName() {
		return userName.getText();
	}
	
}

	

