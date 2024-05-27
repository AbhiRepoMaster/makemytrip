package makemytrip.Commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import makemytrip.ExplicitWaitFunction.ExplicitWaitFunction;

public class WebConfigurations {
	
	WebDriver browser;
	ExplicitWaitFunction waitFunction;
	
	public WebConfigurations(WebDriver driver) {
		
		browser =driver;
		waitFunction = new ExplicitWaitFunction(browser);
	}

	public void clickOnElement(String xpath)
	{
		waitFunction.waitElementToBeClickable(By.xpath(xpath));
		browser.findElement(By.xpath(xpath)).click();
	}

	public void clearAndSetValue(String xpath,String value)
	{
 		WebElement element = browser.findElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(value);
	}
	
	public String retriveAttribute(String xpath,String attributeName)
	{
		waitFunction.waitElementToBeVisible(By.xpath(xpath));
		return browser.findElement(By.xpath(xpath)).getAttribute(attributeName);
	}
	
	public void selectDropdownValue(String xpath,int index)
	{
		Select dropdown = new Select(browser.findElement(By.xpath(xpath)));
		dropdown.selectByIndex(index);
	}
	
	public void selectDropdownValue(String xpath,String value)
	{
		Select dropdown = new Select(browser.findElement(By.xpath(xpath)));
		dropdown.selectByValue(value);
	}
	
	public void selectDropdownValuevisibletext(String xpath,String Visiblevalue)
	{
		Select dropdown = new Select(browser.findElement(By.xpath(xpath)));
		dropdown.selectByVisibleText(Visiblevalue);
	}
}
