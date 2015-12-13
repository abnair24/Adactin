package com.testScript;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.guiPages.baseClasses.TestBase;
import com.guiPages.common.ExcelReaderDataprovider;
import com.guiPages.common.RowNumber;
import com.guiPages.excelReader.UserDetails;
import com.guiPages.navigation.Header;
import com.guiPages.pages.ForgotPasswordPage;
import com.guiPages.pages.LoginPage;
import com.guiPages.pages.LogoutPage;
import com.guiPages.pages.RegistrationPage;
import com.guiPages.pages.SearchHotelPage;


public class LoginTests extends TestBase {
	LoginPage loginPage;
				
	@BeforeClass
	public void setup() throws Exception {
		loginPage= PageFactory.initElements(driver,LoginPage.class);
		loginPage.webDriverImplicitWait();
		loginPage.open();
		loginPage.maximizeWindow();
	}
	
	@Test(priority=1,alwaysRun=true)
	public void registerNewUserNavigationTest() throws Exception {
		RegistrationPage register=loginPage.isNewUserLinkClickable();
		Assert.assertEquals(register.pageTitle(),register.REGISTRATION_PAGE_HEADER);
		register.navigateBackToLoginPage();
	}
	
	@Test(priority=2,alwaysRun=true)
	public void forgotPasswordLinkNavigationTest() throws Exception {
		ForgotPasswordPage passwordPage= loginPage.isForgotPasswordLinkClickable();
		Assert.assertEquals(passwordPage.pageTitle(), ForgotPasswordPage.FORGOT_PASSWORD_PAGETITLE);
		passwordPage.navigateBackToLoginPage();
	}	
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="LoginData",dataProviderClass=ExcelReaderDataprovider.class,priority=3,alwaysRun=true)
	public void invalidLoginTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		loginPage.clickSubMitButton();
		Assert.assertEquals(loginPage.getLoginMessage(), LoginPage.LOGIN_ERROR);
	}
	
	@RowNumber(rowNumber=2)
	@Test(dataProvider="LoginData",dataProviderClass=ExcelReaderDataprovider.class,priority=4,alwaysRun=true)
	public void validLoginTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		SearchHotelPage searchHotel=loginPage.clickSubMitButton();
		//Assert.assertEquals(searchHotel.getHeader().getWelcomeUserName(),"Hello "+user.getUserName()+"!");
		searchHotel.getHeader().clickTab(Header.LOGOUT, LogoutPage.class).goBackToLoginPage();
	}
	
	@AfterTest
	public void teardown() throws Exception {
		driver.quit();
	}
	
}
