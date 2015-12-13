package com.guiPages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage{

	public static final String FORGOT_PASSWORD_PAGETITLE="AdactIn.com - Forgot Password";
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
		
	@FindBy(id="emaildadd_recovery")
	WebElement enterEmail;
	
	@FindBy(id="Submit")
	WebElement submitButton;
	
	@FindBy(id="Reset")
	WebElement resetButton;
	
	@FindBy(xpath=".//*[@id='passre_form']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a")
	WebElement goBackLink;
	
	public void navigateBackToLoginPage() {
		goBackLink.click();
	}

}
