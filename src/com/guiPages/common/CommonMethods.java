package com.guiPages.common;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonMethods {

	public static void populateTextBox(WebElement element,String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public static boolean validateTextBox(WebElement element,String value) {
		boolean bool = false;
		if(value.equals(element.getText())) {
			bool=true;
		}
		return bool;
	}
	
	public static void selectDropDown(WebElement dropDown,String value) {
		new Select(dropDown).selectByVisibleText(value);
	}
	
	public static boolean validateDropDown(WebElement element,String[] expList) {
		List<WebElement> elements = new Select(element).getOptions();
		boolean finalValue=false;
		for(WebElement e:elements) {
			boolean match = false;
			for(int i=0; i<expList.length; i++) {
				if(e.getText().equals(expList[i])) {
					match= true;
				}
			}
			finalValue=match;
		}
		return finalValue;	
	}
	
	public static WebElement getElementByText(List<WebElement>elements,String value) {
		WebElement retValue = null;
		for(WebElement element: elements ) {
			if(value.equalsIgnoreCase(element.getText())) {
				retValue=element;
				break;
			}
		}
		return retValue;
	}
}
