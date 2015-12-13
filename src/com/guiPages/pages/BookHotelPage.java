package com.guiPages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guiPages.common.CommonMethods;
import com.guiPages.excelReader.UserDetails;
import com.guiPages.navigation.Header;

public class BookHotelPage extends BasePage{

	public static final String BOOK_HOTEL_PAGETITLE="AdactIn.com - Book A Hotel";
	private Header header;
	
	public BookHotelPage(WebDriver driver) {
		super(driver);
		header=PageFactory.initElements(driver, Header.class);
		this.setHeader(header);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="last_name")
	WebElement lastName;
	
	@FindBy(id="address") 
	WebElement address;
	
	@FindBy(id="cc_num")
	WebElement creditCardNumber;
	
	@FindBy(id="cc_type")
	WebElement creditCardType;
	
	@FindBy(id="cc_exp_month")
	WebElement expMonth;
	
	@FindBy(id="cc_exp_year")
	WebElement expYear;
	
	@FindBy(id="cc_cvv")
	WebElement cvvNumber;
	
	@FindBy(id="book_now")
	WebElement bookNowButton;
	
	public void populateUserDetails(UserDetails user) {
		CommonMethods.populateTextBox(firstName, user.getFirstName());
		CommonMethods.populateTextBox(lastName, user.getLastName());
		CommonMethods.populateTextBox(address, user.getAddress());
		CommonMethods.populateTextBox(creditCardNumber, user.getCreditCard());
		CommonMethods.populateTextBox(cvvNumber, user.getcvvNumber());
		CommonMethods.selectDropDown(creditCardType,user.getccType());
		CommonMethods.selectDropDown(expMonth, user.getexpMonth());
		CommonMethods.selectDropDown(expYear, user.getExpYear());		
	}
	
	public BookConfirmationpage clickBookNow() {
		return clickAndNavigate(BookConfirmationpage.class, bookNowButton);
	}
		
	public Header getHeader() {
		return header;
	}
	
	public void setHeader(Header header) {
		this.header=header;
	}
	
}
