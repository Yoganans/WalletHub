package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Report {

	private static Report reports=null;
	
	ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	
	public static Report getInstance(){
		if(reports==null){
			reports=new Report();
		}
		return reports;
	}
	
	public final void setTest(ExtentReports report,String testName){
		test.set(report.createTest(testName));
	}
	
	public ExtentTest getTest(){
		return test.get();
	}
	
}
