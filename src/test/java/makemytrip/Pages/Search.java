package makemytrip.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import makemytrip.Commons.IdentifyDriver;
import makemytrip.Commons.WebConfigurations;
import makemytrip.DataFetch.*;
import makemytrip.ExplicitWaitFunction.*;
import org.openqa.selenium.interactions.Actions;

public class Search implements Booking_details {

	static WebDriver driver;
	WebDriverWait wait;
	public  static ExplicitWaitFunction waitFunction;
	
	
	public Search(WebDriver browser) {
		driver = browser;
	}


	public WebDriver launchBookingScreen()
	{
		IdentifyDriver id = new IdentifyDriver();
		String browserName = id.findDriver();
		driver = id.launchDriver(browserName);
		String URL = id.findURL();
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		waitFunction = new ExplicitWaitFunction(this.driver);
		//waitFunction.waitTitleContains(loginHeader);
		//Assert.assertEquals(driver.getTitle(), loginHeader);
		return driver;
	}
	

	public void closePopup() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
    Actions actions = new Actions(driver);
    int retryCount = 0;
    int maxRetries = 5; 
    while (retryCount < maxRetries) {
        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSEPOPUP)));
            actions.moveToElement(closeButton).click().perform();
            retryCount++;
            Thread.sleep(1000);
        } catch (Exception e) {
            break;
        }
    }
}
 }
