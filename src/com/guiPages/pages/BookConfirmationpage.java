package com.guiPages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guiPages.navigation.Header;

public class BookConfirmationpage extends BasePage {

	public static final String BOOKING_CONFIRMATION_PAGETITLE="AdactIn.com - Hotel Booking Confirmation";
	
	private Header header;
	
	public BookConfirmationpage(WebDriver driver) {
		super(driver);
		header=PageFactory.initElements(driver, Header.class);
		this.setHeader(header);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="hotel_name")
	WebElement hotelname;
	
	@FindBy(id="location")
	WebElement location;
	
	@FindBy(id="arrival_date")
	WebElement arrivalDate;
	
	@FindBy(id="departure_text")
	WebElement departureDate;
	
	@FindBy(xpath=".//*[@id='order_no']")
	WebElement orderNumber;
	
	@FindBy(id="logout")
	WebElement logoutButton;
	
	@FindBy(id="my_itinerary")
	WebElement myItineraryButton;
	
	@FindBy(id="search_hotel")
	WebElement searchHotelButton;
	
	public String fetchOrderNumber() {
		webDriverExplicitWait(orderNumber);
		return orderNumber.getAttribute("value");
	}
	
	public SearchHotelPage clickSearchHotelButton() {
		return clickAndNavigate(SearchHotelPage.class, searchHotelButton);
	}
	
	public LogoutPage clickLogoutButton(){
		return clickAndNavigate(LogoutPage.class, logoutButton);
	}
	
	public BookedItineraryPage clickItineraryButton() {
		return clickAndNavigate(BookedItineraryPage.class, myItineraryButton);
	}
	
	 public Header getHeader() {
		 return header;
	 }
	    
	 public void setHeader(Header header) {
		 this.header=header;
	 }	
}
