package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.util.AppConstantsClass;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	//Locators
	By email=By.id("username");
	By password=By.id("password");
	By loginBtn=By.id("loginBtn");
	By signUp=By.linkText("Sign up");
	By  loginErrorText=By.cssSelector("div.private-alert__inner");
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
		
	}
	//Actions== methods
	public String getPageTitle(){
		elementUtil.waitForTitlePresent(AppConstantsClass.Login_Page_Title);
		return elementUtil.doGetPageTitle();
	}
	public boolean checkSignupLink(){
		//elementUtil.waitForElementVisible(signUp);
		return elementUtil.doIsDisplayed(signUp);
	}
public Homepage doLogin(Credentials userCred){
	elementUtil.waitForElementPresent(email);
	elementUtil.doSendKeys(email, userCred.getAppUsername());
	elementUtil.doSendKeys(password, userCred.getAppPassword());
	elementUtil.doClick(loginBtn);
//	driver.findElement(email).sendKeys(username);
//	driver.findElement(password).sendKeys(pass);
//	driver.findElement(loginBtn).click();
	return new Homepage(driver);
}public boolean checkloginErrormessge(){
	return elementUtil.doIsDisplayed(loginErrorText);
}

}
