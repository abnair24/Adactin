package com.guiPages.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guiPages.common.CommonMethods;
import com.guiPages.navigation.Header;

public class BookedItineraryPage extends BasePage {

	public static final String BOOKED_ITINERARY_PAGETITLE="AdactIn.com - Select Hotel";
	private Header header;
	
	public BookedItineraryPage(WebDriver driver) {
		super(driver);
		header=PageFactory.initElements(driver, Header.class);
		this.setHeader(header);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="order_id_text")
	WebElement searchOrderId;
	
	@FindBy(id="search_hotel")
	WebElement searchHotelButton;
	
	@FindBy(id="search_result_error")
	WebElement cancelError;
	
	@FindBy(id="search_hotel_id")
	WebElement goButton;
	
	@FindBy(xpath=".//*[@id='booked_form']/table/tbody/tr[3]/td/input[1]")
	WebElement cancelSelectedButton;
	
	@FindBy(id="check_all")
	WebElement checkAllCheckBox;
	
	@FindBy(css = ".login table tr td:nth-of-type(2) input")
	List<WebElement> orderIdList;
	
	@FindBy(xpath=".//*[@id='search_result_error']/a")
	WebElement showAllLink;
	
	@FindBy(css = ".login table tr:nth-child(2) input[type=checkbox]")
	WebElement defaultOrderId;
	
	@FindBy(css=".login table tr:nth-child(3) input[type=button]")
	WebElement cancelOrderButton;
	
	public static final String CHECKBOX_NOT_SELECTED_ERROR= "please check checkbox to proceed!!";
	public static final String CANCEL_BOOKING_MESSAGE= "Selected booking Are cancelled.";
	public static final String SEARCH_ORDER_ID_NOT_FOUND= "0 result(s) found.";
	public static final String SEARCH_ORDER_ID_FOUND= "1 result(s) found. Show all";
	public static final String CANCEL_BUTTON_MESSAGE="The booking has been cancelled.";

	
	public String cancelOrder(boolean bool) {
		cancelSelectedButton.click();
		handleAlertWindow(bool);
		webDriverExplicitWait(cancelError);
		return cancelError.getText();
	}
	
	public BookedItineraryPage clickShowAllLink() {
		return clickAndNavigate(BookedItineraryPage.class,showAllLink);
	}
	
	public String searchOrder(String orderId) {
		CommonMethods.populateTextBox(searchOrderId, orderId);
		goButton.click();
		webDriverExplicitWait(cancelError);
		return cancelError.getText();
	}
	
	public boolean orderIdPresentInTable(String orderId) {
		boolean bool=false;
		for(WebElement id: orderIdList) {
			if(orderId.equals(id.getAttribute("value"))) {
				bool=true;
			}
		}
		return bool;
	}
	
	public String fetchDefaultOrderId() {
		return orderIdList.get(0).getAttribute("value");
	}
	
	public String cancelDefaultOrder() {
		defaultOrderId.click();
		cancelSelectedButton.click();
		
		handleAlertWindow(true);
		webDriverExplicitWait(cancelError);
		return cancelError.getText();
	}
	
	public String clickCancelOrderButton(boolean bool) {
		cancelOrderButton.click();
		handleAlertWindow(bool);
		webDriverExplicitWait(cancelError);
		return cancelError.getText();
	}
	
	public void setHeader(Header header) {
		this.header=header;
	}
	
	public Header getHeader() {
		return header;
	}
	
	
}
