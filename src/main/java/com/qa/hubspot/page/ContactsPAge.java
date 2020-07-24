package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class ContactsPAge extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;
	By createContactButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]");
	By createContactFormButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]");
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	public ContactsPAge(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
		javaScriptUtil=new JavaScriptUtil(driver);
	}
public String getContactsPAgetitle(){
	elementUtil.waitForTitlePresent("Contacts");
	return elementUtil.doGetPageTitle();
}public void createNewContact(String mail, String Fn,String Ln,String job){
	
	elementUtil.doClick(createContactButton);
	elementUtil.doSendKeys(email, mail);
	elementUtil.doSendKeys(firstName, Fn);
	elementUtil.doSendKeys(lastName, Ln);
	elementUtil.doSendKeys(jobTitle, job);
	
	javaScriptUtil.clickElementByJS(elementUtil.getElement(createContactFormButton));
}
}