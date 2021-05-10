package com.Testscripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.FbHomePage;
import com.pages.FbLogin;
import com.utils.BaseTest;
import com.utils.UIHelper;

import org.testng.AssertJUnit;

/**
 * Unit test for simple App.
 */
public class FbLoginTest extends BaseTest
{
	@Test
	public void task1(){
		intialise("Login Check for User");
		extentTest.log(Status.INFO, "Initialization Success");

		FbLogin fbLoginObj=new FbLogin(scriptHelper);
		FbHomePage fbhomePageObj = new FbHomePage(scriptHelper);

		driver.get(properties.getProperty("url"));
		fbLoginObj.fBLogin(properties.getProperty("username"), properties.getProperty("password"));
		
		fbhomePageObj.toPostStatusMessage(properties.getProperty("FbstatusMessage"));
		fbhomePageObj.tovalidateThePostMessage(properties.getProperty("FbstatusMessage"));
	}


}