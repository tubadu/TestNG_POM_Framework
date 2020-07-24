package com.qa.hubspot.test;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Verify;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.Homepage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstantsClass;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Epic("Epic - 101 :create login features")
@Feature("US -501 : Create test for login on hubspot")
public class LoginPageTest {
WebDriver driver;
BasePage basePage;
Properties prop;
LoginPage loginPage;
Credentials userCred;

Logger log = Logger.getLogger(LoginPageTest.class);


@BeforeMethod(alwaysRun=true)
@Parameters(value={"browser"})
public void SetUp(String browser){
	String browserName=null;
	basePage =new BasePage();
	prop=basePage.init_properties();
	if(browser.equals(null)){
		 browserName=prop.getProperty("browser");}else{
			 browserName=browser;
		 }
	driver=basePage.init_driver(browserName);
	driver.get(prop.getProperty("url"));
	log.info("url is launched "+prop.getProperty("url"));
	loginPage=new LoginPage(driver);
	userCred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	
}
//groups

@Test(priority=1,groups="sanity",enabled=true)
@Description("VErify login page title")
@Severity(SeverityLevel.NORMAL)
public void verifypageTitle() {
log.info("starting ------------>>>>> verify loginpagetest");
	String title=loginPage.getPageTitle();
	System.out.println("LoginPAge Title is "+title);
	Assert.assertEquals(title, AppConstantsClass.Login_Page_Title);
	log.info("ending ------------>>>>> verify loginpagetest");
	log.warn("some warning ");
	log.error("some error ");
	log.fatal("Fatal error");
	
}
@Test(priority=2,enabled=true)
@Description("VErify signuplink")
@Severity(SeverityLevel.NORMAL)
public void VerifySignupLinkTest(){
Assert.assertTrue(loginPage.checkSignupLink());
}
@Test(priority=3,enabled=true)
@Description("VErify logintest")
@Severity(SeverityLevel.BLOCKER)
public void loginTest(){
	
	Homepage homePage = loginPage.doLogin(userCred);
	String accountName = homePage.getLoggedinUSerAccountName();
	System.out.println("logged in account name: "+ accountName);
	Assert.assertEquals(accountName, prop.getProperty("accountname"));
}@DataProvider
public Object[][] getLoginInvalidData(){
	Object data[][]={{"bill@gmail.com","test123"},
			{"jimy@gmail.com","jgigee23423"},
	{" ","erefa232"},
	{"yummy","yummy"},
	{" "," "}};
	
	return data;
}
@Test(priority=4 ,dataProvider="getLoginInvalidData",enabled=false)
public void login_invalidTestCase(String username,String pwd){
	
	userCred.setAppUsername(username);
	userCred.setAppPassword(pwd);
	loginPage.doLogin(userCred);
	Assert.assertTrue(loginPage.checkloginErrormessge());
	
}
@AfterMethod
public void teardown(){
	driver.quit();
}
}
