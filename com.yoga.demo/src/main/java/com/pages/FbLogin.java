
package com.pages;

import com.utils.ScriptHelper;
import com.utils.UIHelper;
import com.aventstack.extentreports.Status;
import com.utils.BasePage;

public class FbLogin extends BasePage{
	public FbLogin(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	private String xpathForUserName=".//input[@id='email']";
	private String xpathForPassword=".//input[@id='pass']";
	private String faceBookImageIcon=".//img[@alt='Facebook']";
	private String xpathForLoginButton=".//button[@name='login']";
	
		 //Below method is for Facebook Login
	public void fBLogin(String username, String password){
		try {
			UIHelper.waitForVisibilityOfElement(driver, faceBookImageIcon);
			UIHelper.clickElement(driver, xpathForUserName);
			UIHelper.sendkeysToElement(driver, xpathForUserName, username);
			UIHelper.clickElement(driver, xpathForPassword);
			UIHelper.sendkeysToElement(driver, xpathForPassword, password);
			UIHelper.clickElement(driver, xpathForLoginButton);
			UIHelper.waitFor();
			UIHelper.waitFor();
			extentTest.log(Status.PASS, "Facebook logged in Sucessfully");
		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Facebook log in is NOT Sucessfull");
		}
		
	}
	
}