package com.qa.hubspot.test;

import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPAge;
import com.qa.hubspot.page.Homepage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {
BasePage basePage;
Properties prop;
WebDriver driver;
LoginPage loginPage;
Homepage homepage;
ContactsPAge contactsPAge;
Credentials userCred;
@BeforeMethod
public void Setup(){
	basePage = new BasePage();
	prop = basePage.init_properties();
	String browserName = prop.getProperty("browser");
	driver = basePage.init_driver(browserName);
	driver.get(prop.getProperty("url"));
	loginPage = new LoginPage(driver);
	userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	homepage = loginPage.doLogin(userCred);
	contactsPAge = homepage.gotocontactspage();
	
}
@Test(priority=1)
public void VerifyTitle(){
	String title=contactsPAge.getContactsPAgetitle();
	System.out.println(title);
	Assert.assertEquals(title, "Contacts");
	
}
@DataProvider
public Object[][] getContacts() throws InvalidFormatException{
	Object [][] data=ExcelUtil.getTestData("contacts");
	return data;
}
@Test (priority=2,dataProvider="getContacts")
public void createContactsTest(String email,String firstname,String lastname,String jobtitle){
	contactsPAge.createNewContact(email, firstname, lastname, jobtitle);
	
}@AfterMethod
public void TearDown(){
	driver.quit();
}
}
