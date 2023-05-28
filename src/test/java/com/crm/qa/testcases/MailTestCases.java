package com.crm.qa.testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.AolHomePage;
import com.crm.qa.pages.ComposeMail;
import com.crm.qa.pages.MakeMyTripHomePage;
import com.crm.qa.pages.SearchFlightsPage;

import schemasMicrosoftComOfficePowerpoint.impl.IscommentDocumentImpl;

public class MailTestCases extends TestBase {
	AolHomePage homepage;
	ComposeMail composemail;
	SearchFlightsPage searchFlightPage;

	public MailTestCases() {
		super();
	}

	/*
	 * @BeforeTest public void setExtentReport() { setExtend(); }
	 */
	
	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		initializaton();
		driver.get(properties.getProperty("aolMailUrl"));
		homepage = new AolHomePage();
	}
	
	@Test(priority = 1)
	public void TC001_LoginwithvalidCredential() {
		homepage.loginwithvalidCredential(properties.getProperty("userName"), properties.getProperty("passWord"));
	}

	@Test(priority = 2)
	public void TC002_CheckForMailLogo() {
		composemail=homepage.checkForMailLogo();
	}


	@Test(priority=3)
	public void TC003_ComposeNewMail() throws Exception {
		composemail.clickOnComposeBtn();
		composemail.clickSubjectName();
		composemail.subjectName(properties.getProperty("subjectName"));
	    composemail.clickReceiverId();
		composemail.toreceiver(properties.getProperty("receiverId"));
		composemail.clickEmailBody();
		composemail.enterEmailBody(properties.getProperty("emailBodyLine1"),properties.getProperty("emailBodyLine2") , properties.getProperty("emailBodyLine3"));
		//composemail.makeEmailBullet();
		composemail.clickAttachBtn();
		composemail.uploadImage();
		composemail.clickSendBtn();
		Thread.sleep(5000);
	}
	
	@Test(priority=4)
	public void TC004_ReadMail() {
		composemail.clickInboxBtn();
		composemail.clickUnreadMsg();
		String subjectText=composemail.getSubjectText();
		Assert.assertEquals(subjectText,properties.getProperty("subjectName"));
		String emailText=composemail.getEmailText();
		String expectedEmailText=properties.getProperty("emailBodyLine1")+"\n"+properties.getProperty("emailBodyLine2")+"\n" +properties.getProperty("emailBodyLine3");
		Assert.assertEquals(emailText,expectedEmailText);
		composemail.clickOnDownloadBtn();
		composemail.verifyDownloadedFile();
	}
	
	
	
	/*@AfterSuite(alwaysRun = true)
	public void tearDown() {
		tearDownMain();
	}*/

}
