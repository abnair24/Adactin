package com.guiPages.excelReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelReader {
	
	private final static int USER_NAME= 0;
	private final static int PASSWORD = 1;
	private final static int LOCATION = 2;
	private final static int HOTEL = 3;
	private final static int ROOMTYPE = 4;
	private final static int NO_OF_ROOMS = 5;
	private final static int NO_OF_ADULTS = 6;
	private final static int NO_OF_CHILDREN = 7;
	private final static int FIRST_NAME=8;
	private final static int LAST_NAME=9;
	private final static int ADDRESS=10;
	private final static int CC_NO=11;
	private final static int CC_TYPE=12;
	private final static int EXP_MONTH=13;
	private final static int EXP_YEAR=14;
	private final static int CVV=15;
	
	public static Iterator<Object[]> excelRead(int rowNumber,String sheetName) throws Exception {
		List<Object[]>userData= new ArrayList<Object[]>();
				
		Workbook wb= Workbook.getWorkbook(new File("D:\\Study\\AutomationAdactin\\Adactin\\Input-Data\\TestData.xls"));
		Sheet sheet =wb.getSheet(sheetName);
		int col=sheet.getColumns();
				
		if(rowNumber==0){
			for(int row =1; row<=sheet.getRows()-1;row++) {
				UserDetails user = fetchSpecificRecord(sheet, row,col);
				userData.add(new Object[]{user});
			}
		}
		else {
			UserDetails user = fetchSpecificRecord(sheet, rowNumber,col);
			userData.add(new Object[]{user});	
		}
		return userData.iterator();
	}

	private static UserDetails fetchSpecificRecord(Sheet sheet, int row,int column ) {
		UserDetails users = new UserDetails();
		
		if(column==2) {
			users.setUserName(sheet.getCell(USER_NAME,row).getContents());
			users.setPassword(sheet.getCell(PASSWORD,row).getContents());
		}
		else {
			users.setUserName(sheet.getCell(USER_NAME,row).getContents());
			users.setPassword(sheet.getCell(PASSWORD,row).getContents());
			users.setLocation(sheet.getCell(LOCATION,row).getContents());
			users.setHotel(sheet.getCell(HOTEL,row).getContents());
			users.setRoomType(sheet.getCell(ROOMTYPE,row).getContents());
			users.setNoOfRooms(sheet.getCell(NO_OF_ROOMS,row).getContents());
			users.setAdultsPerRoom(sheet.getCell(NO_OF_ADULTS,row).getContents());
			users.setChildPerRoom(sheet.getCell(NO_OF_CHILDREN,row).getContents());
			users.setFirstName(sheet.getCell(FIRST_NAME,row).getContents());
			users.setLastName(sheet.getCell(LAST_NAME,row).getContents());
			users.setAddress(sheet.getCell(ADDRESS,row).getContents());
			users.setCreditCard(sheet.getCell(CC_NO,row).getContents());
			users.setccType(sheet.getCell(CC_TYPE,row).getContents());
			users.setexpMonth(sheet.getCell(EXP_MONTH,row).getContents());
			users.setExpYear(sheet.getCell(EXP_YEAR,row).getContents());
			users.setcvvNumber(sheet.getCell(CVV,row).getContents());
		}		
		return users;
	}
}
