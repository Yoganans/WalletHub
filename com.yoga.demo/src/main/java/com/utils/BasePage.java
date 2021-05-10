package com.utils;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class BasePage {

	protected WebDriver driver;
	protected Properties properties;
	protected ExtentTest extentTest;
	
	
	public BasePage(ScriptHelper scriptHelper) {
		this.driver=scriptHelper.getDriver();
		this.properties=scriptHelper.getProperties();
		this.extentTest=scriptHelper.getextentTest();
	}
	
		
}
