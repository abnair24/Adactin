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
import com.guiPages.pages.LoginPage;
import com.guiPages.pages.SearchHotelPage;

public class SearchHotelTests extends TestBase {

	LoginPage loginPage;	
	SearchHotelPage searchPage;
			
	@BeforeClass
	public void setup() throws Exception {
		loginPage= PageFactory.initElements(driver,LoginPage.class);
		loginPage.webDriverImplicitWait();
		loginPage.open();
		loginPage.maximizeWindow();
	}
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=0,alwaysRun=true)
	public void validateDropDownsTest(UserDetails user) throws Exception {
		loginPage.populateLoginData(user);
		searchPage=loginPage.clickSubMitButton();
		Assert.assertTrue(searchPage.validateAllDropDown());
	}
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=1,alwaysRun=true)
	public void checkOutDateIsPastCheckInDateTest(UserDetails user) throws Exception {
		//loginPage.populateLoginData(user);
		//SearchHotelPage searchPage=loginPage.clickSubMitButton();
		searchPage.populateLocationDropDown(user);
	    searchPage.populateDateFields(getTodayDate(), getBeforeDate(-2));
	    searchPage.clickSubmitDetails();
	    searchPage.pageScreenShot("Checkout-Past");
	    Assert.assertEquals(searchPage.checkoutErrorMessage(),SearchHotelPage.ERRMSG_CHKOUT_BEFORE_CHKIN);
	    Assert.assertEquals(searchPage.checkinErrorMessage(),SearchHotelPage.ERRMSG_CHKIN_AFTER_CHKOUT);
	}
	
	@RowNumber(rowNumber=1)
	@Test(dataProvider="UserDetailSelection",dataProviderClass=ExcelReaderDataprovider.class,priority=2,alwaysRun=true)
	public void checkInDateIsPastTest(UserDetails user) throws Exception {
		//loginPage.populateLoginData(user);
		//SearchHotelPage searchPage=loginPage.clickSubMitButton();
		searchPage.populateLocationDropDown(user);
		searchPage.populateDateFields(getBeforeDate(-2), getTodayDate());
	    searchPage.clickSubmitDetails();
	    searchPage.pageScreenShot("Checkin-past");
	    Assert.assertEquals(searchPage.checkinErrorMessage(),SearchHotelPage.CHKIN_PASTDATE);
	 }
	
	@AfterMethod
	public void afterEachTest() throws Exception {
		 searchPage.refreshPage();
	}
	
	@AfterTest
	public void teardown() throws Exception {
		driver.quit();
	}
}