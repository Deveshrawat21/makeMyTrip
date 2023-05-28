package com.crm.qa.testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.MakeMyTripHomePage;
import com.crm.qa.pages.SearchFlightsPage;
import com.google.common.base.Verify;

public class FlightTestCases extends TestBase {

	MakeMyTripHomePage homepage;
	SearchFlightsPage searchFlightPage;

	public FlightTestCases() {
		super();
	}

	/*
	 * @BeforeTest public void setExtentReport() { setExtend(); }
	 */
	
	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		initializaton();
		driver.get(properties.getProperty("makeMyTripUrl"));
		homepage = new MakeMyTripHomePage();
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("document.getElementsByClassName('imageSlideContainer')[0].style.pointerEvents = 'none';");
	}
	
	@Test
	public void TC001_CheckifLogoisPresent() {
		boolean flag = homepage.isLogoDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void TC002_SelectFlightOption() {
		searchFlightPage=homepage.searchFlight(properties.getProperty("FromCity"),properties.getProperty("ToCity"));
	}

	@Test
	public void TC003_CheckifLogoisPresent() {
		boolean flag = searchFlightPage.getPageHeader();
		Assert.assertTrue(flag);
	}

	@Test
	public void TC004_SelectFlightName() {
		searchFlightPage.sortByDeparture();
		searchFlightPage.priceSort();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		tearDownMain();
	}

}
