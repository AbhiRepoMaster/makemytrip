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

    
    
    @Test (priority = 1)
    
    public void testFromCity() throws InterruptedException {
    	Thread.sleep(1000);
    	homePage = new HomePage(driver);
        homePage.enterFromCity("Shivaji");
    }
    
    @Test (priority = 2)
    public void testToCity() throws InterruptedException {
    	Thread.sleep(1000);
        homePage.enterToCity("Dabolim");
    }
      
    @Test(priority = 3)
    public void selectDateTest() {
        String dateToSelect = "5 Jun 2024";
        boolean isDateSelected = HomePage.selectDate(driver, dateToSelect, 22);

        if (!isDateSelected) {
            Assert.fail("Failed to select the date");
        } else {
            System.out.println("Date selected successfully. Exiting loop and passing the test.");
        }
    }

    @Test(priority = 4)
    public void TravellersDataSelectTest() {
        // Open the travelers dropdown
        WebElement returnElement = driver.findElement(By.xpath("//label[contains(@for,'travellers')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(returnElement).click().perform();

        // Select the number of adults, children, and infants
        int adults = 5;
        int children = 4;
        int infants = 3;

        selectNumberOfTravellers("adults", adults);
        selectNumberOfTravellers("children", children);
        selectNumberOfTravellers("infants", infants);

        // Click on the apply button
        WebElement applyButton = driver.findElement(By.xpath("//button[@data-cy='travellerApplyBtn']"));
        applyButton.click();
        System.out.println("Adults: " + adults);
        System.out.println("Children: " + children);
        System.out.println("Infants: " + infants);

        int expectedTotal = adults + children + infants;
        System.out.println("Expected Total Travelers: " + expectedTotal);

        // Verify the total number of travelers displayed
        WebElement totalTravelersDisplay = driver.findElement(By.xpath("//span[@class='appendRight10']//span[@class='font30 latoBlack']"));
        String totalTravelersText = totalTravelersDisplay.getText().trim();
        int actualTotal = Integer.parseInt(totalTravelersText);

        System.out.println("Actual Total Travelers Displayed: " + actualTotal);

        // Assert if the actual total matches the expected total
        Assert.assertEquals(actualTotal, expectedTotal, "Total number of travelers does not match the expected value.");
    }

    private void selectNumberOfTravellers(String type, int count) {
        String dataCy = type + "-" + count;
        WebElement element = driver.findElement(By.xpath("//li[@data-cy='" + dataCy + "']"));
        element.click();
    }
    
    
    
    
    @Test (priority = 5)
    public void testSearchButtonFunctionality() throws InterruptedException {
    	Thread.sleep(500);
        WebElement searchButton = driver.findElement(By.xpath("//a[contains(@class,'primaryBtn font24 latoBold widgetSearchBtn')]"));
        searchButton.click();
    }
    
    
   // @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
