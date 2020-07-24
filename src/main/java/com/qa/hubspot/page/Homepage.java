package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class Homepage extends BasePage {
WebDriver driver;
ElementUtil elementUtil;
By mainContactsLink = By.id("nav-primary-contacts-branch");
By childContactsLink = By.id("nav-secondary-contacts");
By header=By.xpath("//i18n-string[contains(text(),'Dashboard Library')]");
By accountname=By.xpath("//a[@id='navAccount-current']//div[@class='navAccount-accountName'][contains(text(),'Ideal Auto')]");
By click=By.xpath("//a[@id='account-menu']");
public Homepage(WebDriver driver) {
	this.driver=driver;
	elementUtil=new ElementUtil(driver);
	
}public String gethomepagetitle(){
	return elementUtil.doGetPageTitle();
}public String gethomepageHeader(){
	return elementUtil.doGetText(header);}
public String getLoggedinUSerAccountName(){
	elementUtil.doClick(click);
	return driver.findElement(accountname).getText();
}public void clickOnContacts(){
	elementUtil.waitForElementPresent(mainContactsLink);
	elementUtil.doClick(mainContactsLink);
	elementUtil.waitForElementPresent(childContactsLink);
	elementUtil.doClick(childContactsLink);
}public ContactsPAge gotocontactspage(){
	clickOnContacts();
	return new ContactsPAge(driver);
}
}
