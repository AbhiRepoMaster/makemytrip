package makemytrip.ExplicitWaitFunction;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitFunction {
	
	WebDriver browser;
	WebDriverWait wait;
	
	public ExplicitWaitFunction(WebDriver driver) {
		browser = driver;
		wait = new WebDriverWait(this.browser, Duration.ofSeconds(60));

	}


	public void waitTitleContains(String titleText)
	{
		wait.until(ExpectedConditions.titleContains(titleText));
	}
	
	public void waitElementToBeVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitElementToBeVisible(By elementPath)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementPath));
	}
	
	public void waitElementToBeClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitElementToBeClickable(By elementPath)
	{
		wait.until(ExpectedConditions.elementToBeClickable(elementPath));
	}


	public void waitURLContains(String URLText) 
	{
		wait.until(ExpectedConditions.urlContains(URLText));
	}
	
	public void waitElementToBeInvisible(By elementPath)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(elementPath));
	}


}
