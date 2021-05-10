
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
	
	private String xpathForStatusMessage=".//span[contains(text(),'on your mind')]";
	
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
			if(UIHelper.toCheckTheStringIsPresent(driver, xpathForStatusMessage))
			extentTest.log(Status.PASS, "Facebook logged in Sucessfully");
			else
				extentTest.log(Status.FAIL, "Facebook log in is NOT Sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}