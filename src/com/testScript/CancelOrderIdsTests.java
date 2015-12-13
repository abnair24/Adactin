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
import com.guiPages.pages.BookedItineraryPage;
import com.guiPages.pages.LoginPage;
import com.guiPages.pages.LogoutPage;
import com.guiPages.pages.SearchHotelPage;

public class CancelOrderIdsTests extends TestBase {
	LoginPage loginPage;
	SearchHotelPage searchHotel;
	BookedItineraryPage itineraryPage;
	
	@BeforeClass
	public void setup() throws Exception {
		loginPage=PageFactory.initElements(driver,LoginPage.class);
		loginPage.open();
		loginPage.webDriverImplicitWait();
		loginPage.maximizeWindow();
	}
	
	@RowNumber(rowNumber=2)
	@Test(dataProvider="LoginData",dataProviderClass=ExcelReaderDataprovider.class,priority=1,alwaysRun=true)
	public void cancelOrderButtonTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchHotel=loginPage.clickSubMitButton();
		itineraryPage=searchHotel.getHeader().clickTab(Header.BOOKED_ITINERARY, BookedItineraryPage.class);
		String cancelMessage=itineraryPage.clickCancelOrderButton(true);
		Assert.assertEquals(cancelMessage,BookedItineraryPage.CANCEL_BUTTON_MESSAGE);
	}
	
	@RowNumber(rowNumber=2)
	@Test(dataProvider="LoginData",dataProviderClass=ExcelReaderDataprovider.class,priority=2,alwaysRun=true)
	public void cancelDefaultOrderTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchHotel=loginPage.clickSubMitButton();
		itineraryPage=searchHotel.getHeader().clickTab(Header.BOOKED_ITINERARY, BookedItineraryPage.class);
		String cancelMessage=itineraryPage.cancelDefaultOrder();
		Assert.assertEquals(cancelMessage, BookedItineraryPage.CANCEL_BOOKING_MESSAGE);
	}
	
	@AfterMethod
	public void afterTestMethod() throws Exception {
		itineraryPage.getHeader().clickTab(Header.LOGOUT, LogoutPage.class).goBackToLoginPage();
	}
	
	@AfterTest
	public void teardown() throws Exception {
		driver.quit();
	}

}
