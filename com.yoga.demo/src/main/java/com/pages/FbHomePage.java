package com.pages;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.utils.BasePage;
import com.utils.ScriptHelper;
import com.utils.UIHelper;

public class FbHomePage extends BasePage{
	public FbHomePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	private String xpathForStatusMessage=".//span[contains(text(),'on your mind')]";
	private String xpathForCreatePostWindow=".//span[text()='Create post']";
	//private String xpathForStatusMessageInCreatePostWindow=".//div[contains(text(),'on your mind')]";
	private String xpathForStatusMessageInCreatePostWindow=".//div[contains(@aria-label,'on your mind')]//span//*";
	private String xpathToClickPostMessage=".//span[text()='Post']/../../../..";
	
	private String xpathToGetThePostedMessage=".//div[@dir='auto']";
	
	public void toPostStatusMessage(String MessageType){
		try {
			UIHelper.waitFor();
			//UIHelper.Handleacceptalert(driver);
			//UIHelper.Handledismisslert(driver);;
			UIHelper.waitForVisibilityOfElement(driver, xpathForStatusMessage);
			UIHelper.clickElement(driver, xpathForStatusMessage);
			
			int count=UIHelper.switchWindowByHandles(driver);
			System.out.println(count);
			UIHelper.waitForVisibilityOfElement(driver, xpathForCreatePostWindow);
			
			//UIHelper.javascriptClick(driver, xpathForStatusMessageInCreatePostWindow);
			UIHelper.typeUsingJavaScript(driver, xpathForStatusMessageInCreatePostWindow, MessageType);
			//UIHelper.clickElement(driver, xpathForStatusMessageInCreatePostWindow);
			//UIHelper.sendkeysToElement(driver, xpathForStatusMessageInCreatePostWindow, MessageType);
			UIHelper.waitFor();
			UIHelper.clickElement(driver, xpathToClickPostMessage);
			UIHelper.waitFor();
			UIHelper.waitFor();
		} catch (Exception e) {
			
			extentTest.log(Status.FAIL, "Unable to Post a status Message");
		}
	}
	
	public void tovalidateThePostMessage(String expectedMessage){
		try {
			UIHelper.waitForVisibilityOfElement(driver, xpathToGetThePostedMessage);
			String actualMessage=UIHelper.getText(driver, xpathToGetThePostedMessage);
			Assert.assertEquals(expectedMessage, actualMessage, "Posted Message is not same - current Message displaying is " +actualMessage);
		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Posted Message is not same");
		}
		

	
	}
}