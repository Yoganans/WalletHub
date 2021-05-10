package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class UIHelper {


	public static void sendkeysToElement(WebDriver driver,String xpath, String value) {
		driver.findElement(By.xpath(xpath)).clear();
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public static void waitForVisibilityOfElement(WebDriver driver, String xpath) throws Exception {
			WebDriverWait wait=new WebDriverWait(driver,15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public static void clickElement(WebDriver driver, String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public static String getAlertText(WebDriver driver) {
		try {
		return driver.switchTo().alert().getText();
		}catch(Exception e) {
			return  "";
		}
		
	}
	
	public static void waitFor() {
		try {
			Thread.sleep(3000);
		}catch(Exception e) {
			
		}
	}
	
	public static String capture(WebDriver driver) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String name=dateFormat.format(date);
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
		    File source = ((TakesScreenshot)augmentedDriver).
		                        getScreenshotAs(OutputType.FILE);
			
			//File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir") + "\\Results\\ErrorScreenShot\\" + name + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			return dest;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	public static void onError(ExtentTest extentTest,WebDriver driver,String message){
		try {
			extentTest.info("Screen shot of failure", MediaEntityBuilder
			        .createScreenCaptureFromPath(UIHelper.capture(driver)).build());
		} catch (IOException e) {
			extentTest.log(Status.INFO,"Screenshot capture failed for error :"+message);
		}
	}
	
	public static String getText(WebDriver driver, String locator){
		return driver.findElement(By.xpath(locator)).getText();
	}
	
	public static void Handleacceptalert(WebDriver driver){
		try{
			waitFor();
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}

		catch (Exception e){
			e.printStackTrace();

		}
	}
	
	public static void Handledismisslert(WebDriver driver){
		try{
			waitFor();
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}

		catch (Exception e){
			e.printStackTrace();

		}
	}
	
	public static void javascriptClick(WebDriver driver, String locator){
		try {
			JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
			WebElement element= driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
		
		public static void typeUsingJavaScript(WebDriver driver, String locator, String text){
			try {
				JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
				//WebElement element= driver.findElement(By.xpath(locator));
				jsExecutor.executeScript("arguments[0].value=arguments[1];", driver.findElement(By.xpath(locator)), text);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
	}
		
		public static int switchWindowByHandles(WebDriver driver) 
		{
			Set <String> totalWindows = driver.getWindowHandles();
			int totalWindowCount = totalWindows.size();
			return totalWindowCount;
}
}