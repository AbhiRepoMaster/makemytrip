package makemytrip.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import makemytrip.Commons.IdentifyDriver;
import makemytrip.Commons.WebConfigurations;
import makemytrip.DataFetch.*;
import makemytrip.ExplicitWaitFunction.*;

public class Search {

	WebDriver driver;
	public  static ExplicitWaitFunction waitFunction;
	
	public void launchBookingScreen()
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
	}
	
	
}
