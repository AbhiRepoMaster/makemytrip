package makemytrip.TestCases;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import makemytrip.DataFetch.InputTestData;
import makemytrip.ExtentReports.MyScreenRecorder;
import makemytrip.ExtentReports.TestReports;
import makemytrip.Pages.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class SearchaTicket {
	Search search = new Search();
	
	
	WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	TestReports reportinstance = new TestReports();
	
	
	
	//@BeforeMethod
	public void getReportInstance() {
		report = reportinstance.getInstance();
	}


	@Test
	
	public void launchURL()
	{
		search.launchBookingScreen();
	}
	
	
	
}
