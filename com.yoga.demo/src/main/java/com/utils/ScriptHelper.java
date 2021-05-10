package com.utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ScriptHelper {

	private WebDriver driver;
	private ExtentReports report;
	private Properties properties;
	private ExtentTest extentTest;



	public  ScriptHelper(WebDriver driver,Properties properties, ExtentTest extentest) {
		this.driver=driver;
		//this.report=report;
		this.properties=properties;
		this.extentTest=extentest;

	}


	public WebDriver getDriver() {
		return driver;

	}

	public ExtentReports getReport() {
		return report;
	}


	public Properties getProperties() {
		return properties;
	}

	public ExtentTest getextentTest() {
		return extentTest;
	}

}
