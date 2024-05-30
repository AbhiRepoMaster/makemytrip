package makemytrip.TestCases;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

import dev.failsafe.internal.util.Assert;

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
      
    
 // @Test
    public void selectDate1() {
        String dateFromSelect = "30 May 2024"; 
        
    }
    
  
//    @Test (priority = 3)
//    
//    public void selectDate() {
//        // Date to select
//        String dateFromSelect = "30 May 2024";
//        
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100)); // Change the timeout as needed
//        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='DayPicker-Day DayPicker-Day--today']//p[contains(text(),'" + dateFromSelect.split(" ")[0] + "')]")));
//        
//        dateElement.click();
//    }
    

    @Test(priority = 3)
    public void selectDate() {
        // Date to select
        String dateFromSelect = "30 June 2024";

        // Extract day, month, and year from the input date
        String[] dateParts = dateFromSelect.split(" ");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        // Print the extracted month and year
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        // Construct the CSS selector for the month and year elements
        String cssSelector = "div.DayPicker-Caption > div";

        // Find the month and year elements
        List<WebElement> monthYearElements = driver.findElements(By.cssSelector(cssSelector));
        List<String> months = new ArrayList<String>();

        // Retrieve all month values and store them in the list
        for (WebElement element : monthYearElements) {
            String monthYearText = element.getText();
            System.out.println("Found month and year: " + monthYearText);
            months.add(monthYearText);
        }

        // Check if the input month is in the list of months
        boolean monthFound = false;
        for (String monthYearText : months) {
            if (monthYearText.contains(month) && monthYearText.contains(year)) {
                monthFound = true;
                break;
            }
        }

        if (monthFound) {
            // Construct the XPath for the date element
            String dateXPath = String.format("//div[text()='%s %s']//ancestor::div[@class='DayPicker-Month']//div[@class='dateInnerCell']//p[text()='%s']", month, year, day);

            // Find the date element
            WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXPath)));

            // Click on the date element
            Actions actions = new Actions(driver);
            actions.moveToElement(dateElement).click().perform();

//            // Verify that the selected date matches the expected date
//            WebElement selectedDateElement = driver.findElement(By.xpath("//div[contains(@class, 'DayPicker-Day--selected')]//p"));
//            String selectedDate = selectedDateElement.getText();
//            System.out.println("Selected date: " + selectedDate);
//            Assert.assertTrue(selectedDate.equals(day), "Selected date does not match expected date: " + selectedDate + " != " + day);
//        } else {
//            System.out.println("Month not found in the list.");
//            Assert.fail("Month and year do not match or not found.");
        }
    }


    
   // @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
