package com.testScript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTests {
	
	@BeforeClass
	public void beforeclass() throws Exception {
		System.out.println("beforeclass");
	}
	
	@AfterClass
	public void afterclass() throws Exception {
		System.out.println("afterclass");
	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.out.println("beforeMethod");
	}
	
	@AfterMethod
	public void afterMethod() throws Exception {
		System.out.println("AfterMethod");
	}
	
	@BeforeGroups
	public void beforegroups() throws Exception {
		System.out.println("beforegroups");
	}
	
	@AfterGroups
	public void aftergroups() throws Exception {
		System.out.println("aftergroups");
	}
	
	@Test
	public void TEST1() throws Exception {
		System.out.println("TEST1");
	}
	@Test
	public void TEST2() throws Exception {
		System.out.println("TEST2");
	}
	@Test
	public void TEST3() throws Exception {
		System.out.println("TEST3");
	}
	
	@BeforeTest
	public void beforetest() throws Exception {
		System.out.println("beforetest");
	}
	
	@AfterTest
	public void aftertest() throws Exception {
		System.out.println("aftertest");
	}

}
