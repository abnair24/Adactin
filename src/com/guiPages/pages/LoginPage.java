package com.guiPages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.guiPages.common.CommonMethods;
import com.guiPages.excelReader.UserDetails;

public class LoginPage extends BasePage {

	public static final String HOME_PAGE_TITLE="AdactIn.com - Hotel Reservation System";
	public static final String LOGIN_ERROR="Invalid Login Details";
	
	@FindBy(id="username")
	WebElement userName;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login")
	public WebElement loginButton;
	
	@FindBy(xpath=".//*[@id='login_form']/table/tbody/tr[4]/td[2]/div/a")
	WebElement forgotPassword;
	
	@FindBy(xpath=".//*[@id='login_form']/table/tbody/tr[7]/td/a")
	WebElement newUserLink;
	
	@FindBy(xpath=".//*[@id='login_form']/table/tbody/tr[5]/td[2]/div/b")
	WebElement loginError;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public RegistrationPage isNewUserLinkClickable() {
		return clickAndNavigate(RegistrationPage.class, newUserLink);
	 }
	
	public ForgotPasswordPage isForgotPasswordLinkClickable() {
		return clickAndNavigate(ForgotPasswordPage.class, forgotPassword);
	}
	
	public void populateLoginData(UserDetails user) {
		CommonMethods.populateTextBox(userName, user.getUserName());
		CommonMethods.populateTextBox(password, user.getPassword());
	}
	
	public SearchHotelPage clickSubMitButton(){
		return clickAndNavigate(SearchHotelPage.class, loginButton);
	}
	
	public String getLoginMessage() {
		return loginError.getText();
	}
}
