package com.guiPages.baseClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AutomationProperties {
	public static final String PROPERTY_FILENAME = "config/Automation_properties.properties";
	public static final String XLS_PATH = "XLS_DATA";
	public static final String SCREENSHOT_PATH = "SCREENSHOT";
	public static final String BROWSERNAME ="BROWSER";
	public static final String BASE_URL = "BASEURL";
	public static final String CHROME_DRIVER_PATH= "CHROME_DRIVER";
	public static final String IE_DRIVER_PATH = "IE_DRIVER";
	
	private Properties automationProperties = new Properties();
	
	public AutomationProperties() {
		try {
			automationProperties.load(new FileInputStream(PROPERTY_FILENAME));
		} catch(IOException e) {
			throw new RuntimeException(e); 
		}
	}
	
	public String getPropertyValue(final String key) {
		String property = automationProperties.getProperty(key);
		return property;
	}

}
