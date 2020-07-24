package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.Homepage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstantsClass;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Homepage homepage;
	Credentials userCred;
	
	@BeforeTest
	public void Setup() throws InterruptedException{
		basePage=new BasePage();
		prop=basePage.init_properties();
		String browserName=prop.getProperty("browser");
		driver=basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage=new LoginPage(driver);
		userCred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homepage=loginPage.doLogin(userCred);
		Thread.sleep(5000);
		
	}@Test(priority=1 ,description="home page title _Dashboard Library")
	public void verifyHomePageTitleTest(){
	String title=	homepage.gethomepagetitle();
	System.out.println("Home page Title is "+title);
	Assert.assertEquals(title, AppConstantsClass.Home_Page_Title);
	}
	@Test(priority=2)
	public void verifyHomepageheaderTest(){
		String header=homepage.gethomepageHeader();
		System.out.println("Header is "+header);
		Assert.assertEquals(header,AppConstantsClass.Home_Page_Header);
		
	}@Test
	public void verifyUserAccountName() {
		String accountName = homepage.getLoggedinUSerAccountName();
		System.out.println(accountName);
		Assert.assertEquals(accountName, AppConstantsClass.Home_page_Accountname);
	}

	@AfterTest
	public void teardown(){
		driver.quit();
	}

}
