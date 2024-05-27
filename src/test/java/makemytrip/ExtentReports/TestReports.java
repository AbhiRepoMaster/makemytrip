package makemytrip.ExtentReports;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.*;
import com.relevantcodes.extentreports.ExtentTest;

public class TestReports {
	
	static ExtentTest test;
	static ExtentReports report;
	
		
	public static ExtentReports getInstance()
	{
		String filePath = System.getProperty("user.dir")+"\\Report\\TestCaseReports.html";
		report = new ExtentReports(filePath,false);
		return report;
	}
	
	public static void endTestInstance()
	{
		report.endTest(test);
		report.flush();
	}

}
