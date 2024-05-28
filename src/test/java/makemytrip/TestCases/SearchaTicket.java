package makemytrip.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import makemytrip.ExtentReports.TestReports;
import makemytrip.Pages.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchaTicket {
	 HomePage homePage;
    WebDriverWait wait;
    static WebDriver driver;
    static ExtentTest test;
    static ExtentReports report;
    TestReports reportinstance = new TestReports();
    Search search = new Search(SearchaTicket.driver);

    @BeforeClass
    public void setUp() {
        driver = search.launchBookingScreen();
        search.closePopup();
    }

    
    
    @Test
    public void testFromCity() {
    	homePage = new HomePage(driver);
        homePage.enterFromCity("Bangkok");
    }
    
    @Test
    public void testToCity() {
        homePage.enterToCity("BOM");
    }
      
    
    
    
   // @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
