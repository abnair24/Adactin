package com.guiPages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public final String REGISTRATION_PAGE_HEADER="AdactIn.com - New User Registration";
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath=".//*[@id='register_form']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a")
	WebElement backToLoginPage;
	
	
	public void navigateBackToLoginPage() {
		backToLoginPage.click();
	}
	
	
}
