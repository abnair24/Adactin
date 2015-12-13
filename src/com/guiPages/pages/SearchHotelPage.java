package com.guiPages.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guiPages.common.CommonMethods;
import com.guiPages.excelReader.UserDetails;
import com.guiPages.navigation.Header;

public class SearchHotelPage extends BasePage{
	
	public static final String  SEARCH_HOTEL_PAGE_TITLE="AdactIn.com - Search Hotel";
	public static final String ERRMSG_CHKOUT_BEFORE_CHKIN="Check-Out Date shall be after than Check-In Date";
	public static final String ERRMSG_CHKIN_AFTER_CHKOUT="Check-In Date shall be before than Check-Out Date";
	public static final String CHKIN_PASTDATE="Check-In Date should be either Today or Later Date";
	
	String[] locations = {"- Select Location -","Sydney","Melbourne","Brisbane","Adelaide","London","New York","Los Angeles","Paris"};
	String[] hotels = {"- Select Hotel -","Hotel Creek","Hotel Sunshine","Hotel Hervey","Hotel Cornice"};
	String[] roomTypes = {"- Select Room Type -","Standard","Double","Deluxe","Super Deluxe"};
	
	private Header header;
	
	public SearchHotelPage(WebDriver driver) {
		super(driver);
		header=PageFactory.initElements(driver, Header.class);
		this.setHeader(header);
	}
	
	@FindBy(id = "location")
	private WebElement locationElement;

	@FindBy(id = "hotels")
	private WebElement hotelsElement;

	@FindBy(id = "room_type")
	private WebElement roomTypeElement;

	@FindBy(id = "room_nos")
	private WebElement roomNosElement;

	@FindBy(id = "datepick_in")
	private WebElement checkinDateElement;

	@FindBy(id = "datepick_out")
	private WebElement checkOutDateElement;

	@FindBy(id = "checkin_span")
	private WebElement checkinErrorElement;

	@FindBy(id = "checkout_span")
	private WebElement checkoutErrorElement;

	@FindBy(id = "adult_room")
	private WebElement adultPerRoomElement;

	@FindBy(id = "child_room")
	private WebElement childPerRoomElement;
	
	@FindBy(id = "Submit")
	private WebElement submitButtonElement;
	
	
	
	public void populateAllDropDown(UserDetails user) {
		CommonMethods.selectDropDown(locationElement,user.getLocation());
		CommonMethods.selectDropDown(hotelsElement,user.getHotel());
		CommonMethods.selectDropDown(roomTypeElement,user.getRoomType());
		CommonMethods.selectDropDown(roomNosElement, user.getNoOfRooms());
		CommonMethods.selectDropDown(adultPerRoomElement,user.getAdultsPerRoom());
		CommonMethods.selectDropDown(childPerRoomElement, user.getChildPerRoom());
	}
	
	public void populateLocationDropDown(UserDetails user) {
		CommonMethods.selectDropDown(locationElement,user.getLocation());
	}

	public void populateDateFields(String checkinDate, String checkoutDate) {
		CommonMethods.populateTextBox(checkinDateElement,checkinDate );
		CommonMethods.populateTextBox(checkOutDateElement,checkoutDate);
	}
		
    public SelectHotelPage clickSubmitDetails() {
		return clickAndNavigate(SelectHotelPage.class, submitButtonElement);
	}
    
    public String checkoutErrorMessage() {
    	return checkoutErrorElement.getText();
    }
    
    public String checkinErrorMessage() {
    	return checkinErrorElement.getText();
    }
    
    public boolean validateAllDropDown() {
    	boolean isLocationValuesSame=CommonMethods.validateDropDown(locationElement, locations);
    	boolean isHotelValuesSame=CommonMethods.validateDropDown(hotelsElement, hotels);
    	boolean isRoomTypeValuesSame= CommonMethods.validateDropDown(roomTypeElement, roomTypes);
    	return isLocationValuesSame && isHotelValuesSame && isRoomTypeValuesSame ;
    }
    
    public Header getHeader() {
    	return header;
    }
    
    public void setHeader(Header header) {
    	this.header=header;
    }	
}
