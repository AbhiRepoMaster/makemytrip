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

import makemytrip.ExtentReports.TestReports;
import makemytrip.Pages.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//import dev.failsafe.internal.util.Assert;

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

    
    
    @Test (priority = 1)
    
    public void testFromCity() {
    	homePage = new HomePage(driver);
        homePage.enterFromCity("Shivaji");
    }
    
    @Test (priority = 2)
    public void testToCity() {
        homePage.enterToCity("Dabolim");
    }
      
    
    @Test(priority = 3)
    public void selectDateTest() {
        String dateToSelect = "3 Jun 2024";
        boolean isDateSelected = HomePage.selectDate(driver, dateToSelect, 22);

        if (!isDateSelected) {
            Assert.fail("Failed to select the date");
        } else {
            System.out.println("Date selected successfully. Exiting loop and passing the test.");
        }
    }

 
   // @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
