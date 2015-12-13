package com.guiPages.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guiPages.excelReader.UserDetails;
import com.guiPages.navigation.Header;

public class SelectHotelPage extends BasePage{

	private Header header;
	
	public SelectHotelPage(WebDriver driver) {
		super(driver);
		header=PageFactory.initElements(driver, Header.class);
		this.setHeader(header);
	}
	
	@FindBy(css=".login table tr td:nth-of-type(3) input")
	List<WebElement>locationTableValues;
	
	@FindBy(css = ".login table tr td:nth-of-type(4) input")
	List<WebElement> numberOfRoomsTableValues;
	
	@FindBy(css=".login table tr td:nth-of-type(5) input")
	List<WebElement>checkinTableValues;
		
	@FindBy(css=".login table tr td:nth-of-type(6) input")
	List<WebElement>checkoutTableValues;
	
	@FindBy(css = ".login table tr td:nth-of-type(8) input")
	List<WebElement> roomTypeTableValues;

	@FindBy(css = ".login table tr td:nth-of-type(9) input")
	WebElement pricePerNightTableValues;
	
	@FindBy(css = ".login table tr td:nth-of-type(10) input")
	WebElement totalPriceTableValues;
	
	@FindBy(css = ".login table tr:nth-child(2) input[type=radio]")
	WebElement defaultChoice;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	
	public BookHotelPage bookDefaultHotelSelection() {
		defaultChoice.click();
		return clickAndNavigate(BookHotelPage.class, continueButton);
	}
		
	public boolean validateLocationColumn(UserDetails user) {
		return isSameValueInColumn(user.getLocation(),locationTableValues);
	}
	
    public boolean validateRoomTypeColumn(UserDetails user) {
    	return isSameValueInColumn(user.getRoomType(),roomTypeTableValues);
    }
    
    public boolean validateCheckinColumn(String checkinDate) {
    	return isSameValueInColumn(checkinDate, checkinTableValues);
    }
    
    public boolean validateCheckoutColumn(String checkoutDate) {
    	return isSameValueInColumn(checkoutDate, checkoutTableValues);
    }
	
	public boolean isSameValueInColumn(String expectedValue, List<WebElement>columnValues) {
		boolean isColumnValueSame=false;
		for(WebElement column: columnValues) {
			isColumnValueSame=expectedValue.equals(column.getAttribute("value"));
		}
		return isColumnValueSame;
	}
	
	  public Header getHeader() {
		  return header;
	  }
	    
	  public void setHeader(Header header) {
		  this.header=header;
	  }
	
	
	

}
