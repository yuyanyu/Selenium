package com.iie.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class utils {
	
	public static boolean  ElementExist(WebDriver driver, By selector)
	{ 

	        try 
	        { 
	               driver.findElement(selector); 
	               return true; 
	        } 
	        catch (NoSuchElementException e) 
	        { 
	                return false; 
	        } 
	}

}
