package com.testScript;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.guiPages.baseClasses.TestBase;
import com.guiPages.common.ExcelReaderDataprovider;
import com.guiPages.common.RowNumber;
import com.guiPages.excelReader.UserDetails;
import com.guiPages.navigation.Header;
import com.guiPages.pages.BookHotelPage;
import com.guiPages.pages.LoginPage;
import com.guiPages.pages.LogoutPage;
import com.guiPages.pages.SearchHotelPage;
import com.guiPages.pages.SelectHotelPage;

public class SelectHotelValidationTest extends TestBase {
	
	LoginPage loginPage;	
	SearchHotelPage searchPage;
	SelectHotelPage selectHotel;
	BookHotelPage bookHotel;
	
	@BeforeClass
	public void setup() throws Exception {
		loginPage= PageFactory.initElements(driver,LoginPage.class);
		loginPage.webDriverImplicitWait();
		loginPage.open();
		loginPage.maximizeWindow();
	}
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=1,alwaysRun=true)
	public void locationNameReflectedInHotelSelectTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchPage=loginPage.clickSubMitButton();
		searchPage.populateAllDropDown(user);
		searchPage.populateDateFields(getAfterDate(2), getAfterDate(3));
		selectHotel=searchPage.clickSubmitDetails();
		selectHotel.pageScreenShot("SelectHotel");
		Assert.assertTrue(selectHotel.validateLocationColumn(user));
	}
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=2,alwaysRun=true) 
	public void checkinCheckoutReflectedInHotelSelectTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchPage= loginPage.clickSubMitButton();
		searchPage.populateLocationDropDown(user);
		searchPage.populateDateFields(getAfterDate(2),getAfterDate(3));
		selectHotel=searchPage.clickSubmitDetails();
		selectHotel.pageScreenShot("Checkin-CheckOut");
		Assert.assertTrue(selectHotel.validateCheckinColumn(getAfterDate(2)));
		Assert.assertTrue(selectHotel.validateCheckoutColumn(getAfterDate(3)));		
	}
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=3,alwaysRun=true)
	public void defaultHotelSelectionTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchPage=loginPage.clickSubMitButton();
		searchPage.populateLocationDropDown(user);
		searchPage.populateDateFields(getAfterDate(3), getAfterDate(4));
		selectHotel=searchPage.clickSubmitDetails();
		bookHotel=selectHotel.bookDefaultHotelSelection();
		Assert.assertEquals(bookHotel.pageTitle(),BookHotelPage.BOOK_HOTEL_PAGETITLE);
	}
	
	@AfterMethod
	public void afterTestMethod() throws Exception {
		selectHotel.getHeader().clickTab(Header.LOGOUT, LogoutPage.class).goBackToLoginPage();
	}
	
	@AfterTest
	public void teardown() throws Exception {
		driver.quit();
	}

}
