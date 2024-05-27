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

public class Search {

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
	Actions actions = new Actions(driver);
	WebElement closeButton = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
	// Perform the click action
	actions.moveToElement(closeButton).click().perform();
}

}

