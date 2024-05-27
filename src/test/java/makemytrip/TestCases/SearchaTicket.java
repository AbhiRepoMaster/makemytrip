package makemytrip.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import makemytrip.DataFetch.InputTestData;
import makemytrip.ExtentReports.MyScreenRecorder;
import makemytrip.ExtentReports.TestReports;
import makemytrip.Pages.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchaTicket {
    WebDriverWait wait;
    static WebDriver driver;
    static ExtentTest test;
    static ExtentReports report;
    TestReports reportinstance = new TestReports();
    Search search = new Search(this.driver);

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver
        driver = search.launchBookingScreen();
        search.closePopup();
       // wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void fromCity() {
        WebElement fromCityInput = driver.findElement(By.xpath("//input[@id='fromCity']//parent::label"));
        Actions actions = new Actions(driver);
        actions.click(fromCityInput).perform();
        actions.sendKeys("MAA").sendKeys(Keys.ENTER).perform();
    }

    @Test
    public void toCity() {
        WebElement toCityInput = driver.findElement(By.xpath("//label[@for='toCity']"));
        Actions actions = new Actions(driver);
        actions.click(toCityInput).perform();
        actions.sendKeys("DEL").sendKeys(Keys.ENTER).perform();
    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver
        driver.quit();
    }
}
