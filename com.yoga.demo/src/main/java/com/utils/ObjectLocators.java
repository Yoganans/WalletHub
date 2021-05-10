package com.utils;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ObjectLocators {
	protected WebDriver driver;
	protected WebDriver wait;
	protected static Properties props=new Properties();
	protected static File xmlFiles;

	public static By getBySelector(String propKey){
		//This is to get he value from Properties file and split the type and value
		String[] split=propKey.split(";");
		String type=split[0];
		try {
			if(type.equalsIgnoreCase("id")){
				return By.id(split[1]);
			} else if(type.equalsIgnoreCase("css")){
				return By.cssSelector(split[1]);
			}else if(type.equalsIgnoreCase("tagname")){
				return By.tagName(split[1]);
			}else if(type.equalsIgnoreCase("class")){
				return By.className(split[1]);
			}else if(type.equalsIgnoreCase("name")){
				return By.name(split[1]);
			}else if(type.equalsIgnoreCase("xpath")){
				return By.xpath(split[1]);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return By.xpath(split[1]);
		
	}

}
