package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil javaScriptUtil;
	Properties prop;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, AppConstantsClass.Default_Time);
		javaScriptUtil=new JavaScriptUtil(driver);
	}
	public boolean waitForTitlePresent(String title){
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
public boolean waitForElementPresent(By locator){
	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	return true;
}public boolean waitForElementVisible(By locator){
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	return true;
}
public String doGetPageTitle(){
	try {
		return driver.getTitle();
		
	} catch (Exception e) {
		System.out.println("some exception got occured while getting the title...");
		
	}return null;
	
}
public WebElement getElement(By locator){
	WebElement  element=null;
	try {
		element=driver.findElement(locator);
		if(highlightElemet){
			javaScriptUtil.flash(element);
		}
	} catch (Exception e) {
		System.out.println("some exception got occured while creating the web element");
	}return element;
}public void doClick(By locator){
	try {
		getElement(locator).click();
	} catch (Exception e) {
		System.out.println("some exception got occured while clicking the web element");
	}
}public void doSendKeys(By locator,String value){
	try {
		WebElement element=getElement(locator);
		element.clear();
		element.sendKeys(value);
		
		} catch (Exception e) {
		System.out.println("some exception got occured while entering values in a field");
	}
	
}public boolean doIsDisplayed(By locator){
	try {
		return getElement(locator).isDisplayed();
	} catch (Exception e) {
		System.out.println("some exception got occured isEnabled method");
	}return false;
	
}public boolean doIsSelected(By locator){
	try {
		return getElement(locator).isSelected();
	} catch (Exception e) {
		System.out.println("some exception got occured isSelected method");
	}return false;
}public String doGetText(By locator){
	try {
		return getElement(locator).getText();
		
	} catch (Exception e) {
		System.out.println("some exception got occured while getting text...");
	}
	return null;
}
}
