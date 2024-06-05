package makemytrip.TestCases;

import java.time.Duration;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import makemytrip.DataFetch.InputTestData;
import makemytrip.ExtentReports.TestReports;
import makemytrip.Pages.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchaTicket {
	HomePage homePage;
	WebDriverWait wait;
	public static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	TestReports reportinstance = new TestReports();
	Search search = new Search(SearchaTicket.driver);

	@BeforeClass
	public void setUp() {
		driver = search.launchBookingScreen();
		search.closePopup();
	}

	@Test(priority = 1, dataProvider = "TestData", dataProviderClass = InputTestData.class)
	public void testFromCity(String fromCity, String toCity, String dateToSelect, String adults, String children,
			String infants) throws InterruptedException {
		Thread.sleep(1000);
		homePage = new HomePage(driver);
		homePage.enterFromCity(fromCity);
	}

	@Test(priority = 2, dataProvider = "TestData", dataProviderClass = InputTestData.class)
	public void testToCity(String fromCity, String toCity, String dateToSelect, String adults, String children,
			String infants) throws InterruptedException {
		Thread.sleep(1000);
		homePage.enterToCity(toCity);
	}

	@Test(priority = 3, dataProvider = "TestData", dataProviderClass = InputTestData.class)
	public void selectDateTest(String fromCity, String toCity, String dateToSelect, String adults, String children,
			String infants) {
		//Pass in "d-MMM-yy" format
		boolean isDateSelected = HomePage.selectDate(driver, dateToSelect, 22);

		if (!isDateSelected) {
			Assert.fail("Failed to select the date");
		} else {
			System.out.println("Date selected successfully. Exiting loop and passing the test.");
		}
	}

	@Test(priority = 4, dataProvider = "TestData", dataProviderClass = InputTestData.class)
	public void travellersDataSelectTest(String fromCity, String toCity, String dateToSelect, String adultsStr,
			String childrenStr, String infantsStr) throws InterruptedException {
		homePage.travellersDataSelectTest(driver, fromCity, toCity, dateToSelect, adultsStr, childrenStr, infantsStr);
	}

	@Test(priority = 5, dataProvider = "TestData", dataProviderClass = InputTestData.class)
	public void testSearchButtonFunctionality(String fromCity, String toCity, String dateToSelect, String adults,
			String children, String infants) throws InterruptedException {
		homePage.searchButtonFunctionality();
	}

	// @AfterClass
	public void tearDown() {
		driver.quit();
	}
}
