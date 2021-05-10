package com.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest{
	
	protected WebDriver driver;
	protected Report reportClass;
	static ExtentReports report;
	protected Properties properties;
	protected ScriptHelper scriptHelper;
	protected ExtentTest extentTest;
	protected ExtentHtmlReporter htmlReporter;
	
	
	
	@BeforeSuite
	public void suiteSetUp(ITestContext ctx) {
		String suitename=ctx.getCurrentXmlTest().getSuite().getName();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		String runtime=dateFormat.format(date);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Results\\"+suitename+runtime+"_Summary.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
	}
	
	@BeforeMethod
	public void setup(ITestContext ctx) {
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		
	
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver\\chromedriver.exe");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		
		FileReader fr;
		
		try {
			fr = new FileReader(new File(System.getProperty("user.dir")+"\\config.properties"));
			properties=new Properties();
			properties.load(fr);
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void intialise(String testCaseName) {
		reportClass=Report.getInstance();
		try {
			reportClass.setTest(report,testCaseName);
			extentTest = reportClass.getTest();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		scriptHelper =new ScriptHelper(driver, properties,extentTest);
	}
	
	@AfterMethod
	public void teardown(ITestResult result) throws IOException {
		
			if(result.getStatus() == ITestResult.FAILURE ){
				extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				
				//extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
				//extentTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(UIHelper.capture(driver, "LoginFailure")).build());
			}else if(result.getStatus() == ITestResult.SKIP){
				extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
			}
		
		
		driver.quit();
		
	}
	
	public void assertion() {
		if(extentTest.getStatus()==Status.FAIL) {
			Assert.fail();
		}
	}
	
	
	@AfterSuite
	public void suiteTeardown() {
		report.flush();
		System.out.println("closed");
	}
	

}