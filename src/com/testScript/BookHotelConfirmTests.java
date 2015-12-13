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
import com.guiPages.pages.BookConfirmationpage;
import com.guiPages.pages.BookHotelPage;
import com.guiPages.pages.BookedItineraryPage;
import com.guiPages.pages.LoginPage;
import com.guiPages.pages.LogoutPage;
import com.guiPages.pages.SearchHotelPage;

public class BookHotelConfirmTests extends TestBase{
	
	LoginPage loginPage;
	SearchHotelPage searchPage;
	BookHotelPage bookHotel;
	BookConfirmationpage bookConfirm;
	BookedItineraryPage itinerary;
	
	@BeforeClass
	public void setup() throws Exception {
		loginPage=PageFactory.initElements(driver,LoginPage.class);
		loginPage.webDriverImplicitWait();
		loginPage.open();
		loginPage.maximizeWindow();
	}
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=1,alwaysRun=true)
	public void orderIdPresentTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchPage=loginPage.clickSubMitButton();
		searchPage.populateLocationDropDown(user);
		bookHotel=searchPage.clickSubmitDetails().bookDefaultHotelSelection();
		bookHotel.populateUserDetails(user);
		bookConfirm=bookHotel.clickBookNow();
		bookConfirm.webDriverImplicitWait();
		String orderId=bookConfirm.fetchOrderNumber();
		itinerary=bookConfirm.getHeader().clickTab(Header.BOOKED_ITINERARY, BookedItineraryPage.class);
		Assert.assertTrue(itinerary.orderIdPresentInTable(orderId));
	}
		
	@RowNumber(rowNumber=2)	
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=2,alwaysRun=true)
	public void searchOrderIdTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchPage=loginPage.clickSubMitButton();
		searchPage.populateLocationDropDown(user);
		
		itinerary=searchPage.getHeader().clickTab(Header.BOOKED_ITINERARY,BookedItineraryPage.class);
		String defaultOrderId=itinerary.fetchDefaultOrderId();
		String message=itinerary.searchOrder(defaultOrderId);
		itinerary.pageScreenShot("SearchOrder-"+defaultOrderId);
		Assert.assertEquals(message, BookedItineraryPage.SEARCH_ORDER_ID_FOUND);
	}
	
	@AfterMethod
	public void afterTestMethod() throws Exception {
		itinerary.getHeader().clickTab(Header.LOGOUT, LogoutPage.class).goBackToLoginPage();
	}
	
	@AfterTest
	public void teardown() throws Exception {
		driver.quit();
	}
		
}
