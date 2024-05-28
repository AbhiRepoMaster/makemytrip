package makemytrip.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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