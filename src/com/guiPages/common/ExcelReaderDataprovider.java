package com.guiPages.common;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.guiPages.excelReader.ExcelReader;

public class ExcelReaderDataprovider {
	
	private static final String TD_SHEET_1 = "LOGIN_DATA";
	private static final String TD_SHEET_2 = "BOOK_HOTEL";
		
	@DataProvider(name="LoginData")
	public static Iterator<Object[]> loginUserDetails(Method testName) throws Exception {
		RowNumber row = testName.getAnnotation(RowNumber.class);
		return ExcelReader.excelRead(row.rowNumber(), TD_SHEET_1);
	}
	
	@DataProvider(name="UserDetailSelection")
	public static Iterator<Object[]> selectHotelDetails(Method testName) throws Exception {
		RowNumber row = testName.getAnnotation(RowNumber.class);
		return ExcelReader.excelRead(row.rowNumber(), TD_SHEET_2);
	}
}
