package com.guiPages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage {
	
	@FindBy(linkText="Click here to login again")
	private WebElement loginAgainLink;
	
	public LogoutPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage goBackToLoginPage() {
		return clickAndNavigate(LoginPage.class, loginAgainLink);
	}

}
