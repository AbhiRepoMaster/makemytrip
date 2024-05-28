package makemytrip.Pages;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import makemytrip.DataFetch.Booking_details;

public class HomePage implements Booking_details {
    WebDriver driver;

    public HomePage(WebDriver browser) {
        this.driver = browser;
    }

    public void enterFromCity(String cityCode) {
        WebElement fromCityInput = driver.findElement(By.xpath(FROM_CITY_INPUT));
        Actions actions = new Actions(driver);
        actions.click(fromCityInput).perform();
        actions.sendKeys(cityCode).sendKeys(Keys.ENTER).perform();
    }    

    public void enterToCity(String cityCode) {
        WebElement fromCityInput = driver.findElement(By.xpath(TO_CITY_INPUT));
        Actions actions = new Actions(driver);
        actions.click(fromCityInput).perform();
        actions.sendKeys(cityCode).sendKeys(Keys.ENTER).perform();
    }


   
}