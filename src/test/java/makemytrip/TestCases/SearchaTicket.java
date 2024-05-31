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
      
    
 // @Test
    public void selectDate1() {
        String dateFromSelect = "30 May 2024"; 
        
    }  

    @Test(priority = 3)
    public void selectDate() {
        // Date to select
        String dateFromSelect = "30 Sep 2024";
        
        // Extract day, month, and year from the input date
        String[] dateParts = dateFromSelect.split(" ");
        int day = Integer.parseInt(dateParts[0]);
        String month = dateParts[1];
        int year = Integer.parseInt(dateParts[2]);
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Parse the input date
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        LocalDate inputDate = LocalDate.parse(dateFromSelect, formatter);
        
        // Check if the input date is in the past
        if (inputDate.isBefore(currentDate)) {
            System.out.println("The selected date is in the past. Cannot book a ticket for a past date.");
            Assert.fail("The selected date is in the past. Cannot book a ticket for a past date.");
            return;
        }
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        
        boolean dateSelected = false;
        int maxAttempts = 22; // Maximum number of attempts to find the date
        
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            // Check if the current page contains the desired date in any displayed month
            List<WebElement> dateElements = driver.findElements(By.xpath("//div[@class='DayPicker-Month']//p[text()='" + day + "']"));
            
            // If the desired date is found, click on it and exit the loop
            if (!dateElements.isEmpty()) {
                for (WebElement dateElement : dateElements) {
                    // Check if the date is in the desired month and year
                    WebElement monthElement = dateElement.findElement(By.xpath("../../../../..//div[@class='DayPicker-Caption']"));
                    String monthYearText = monthElement.getText();
                    if (monthYearText.contains(month) && monthYearText.contains(String.valueOf(year))) {
                        // Log found date's month and year
                        System.out.println("Found date - Month: " + month + ", Year: " + year);
                        
                        // Click on the date element
                        Actions actions = new Actions(driver);
                        actions.moveToElement(dateElement).click().perform();
                        dateSelected = true; 
                        break;
                    }
                }
                if (dateSelected) {
                    break; // Exit the loop if date is successfully selected
                }
            }
            
            // If the date is not found on the current page, click the next month button
            try {
                WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='Next Month']")));
                nextMonthButton.click();
                System.out.println("Moving to next month...");
            } catch (Exception e) {
                System.out.println("Failed to click the next month button: " + e.getMessage());
                Assert.fail("Failed to click the next month button: " + e.getMessage());
            }
        }
        
        if (!dateSelected) {
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
