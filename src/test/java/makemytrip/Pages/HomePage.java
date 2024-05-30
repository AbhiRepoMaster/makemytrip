package makemytrip.Pages;
import java.time.Duration;
import java.util.ArrayList;
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

//    public void enterFromCity(String cityCode) {
//        WebElement fromCityInput = driver.findElement(By.xpath(FROM_CITY_INPUT));
//        Actions actions = new Actions(driver);
//        actions.click(fromCityInput).perform();
//        actions.sendKeys(cityCode).sendKeys(Keys.ENTER).perform();
//    } 
    
    
    
    
    
    
    

    public boolean enterFromCity(String cityCode) {
        WebElement fromCityInput = driver.findElement(By.xpath(FROM_CITY_INPUT));
        Actions actions = new Actions(driver);
        actions.click(fromCityInput).perform();
        actions.sendKeys(cityCode).perform();

        // Wait for the suggestions to load using WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(FROM_CITY_NAME)));

        // Extract the city codes and names
        List<WebElement> cityElements = driver.findElements(By.xpath(FROM_CITY_NAME));
        List<WebElement> nameElements = driver.findElements(By.xpath(FROM_CITY_COD));
        List<WebElement> airportNameElements = driver.findElements(By.xpath(FROM_CITY_AIRPORT_NAME));

        // Iterate over city names
        for (WebElement cityElement : cityElements) {
            if (cityElement.getText().contains(cityCode)) {
                cityElement.click();
                actions.sendKeys(Keys.ENTER).perform();
                return true;
            }
        }

        // Iterate over city codes
        for (WebElement nameElement : nameElements) {
            if (nameElement.getText().equalsIgnoreCase(cityCode)) {
                nameElement.click();
                actions.sendKeys(Keys.ENTER).perform();
                return true;
            }
        }

        // Iterate over airport names
        for (WebElement airportNameElement : airportNameElements) {
            if (airportNameElement.getText().contains(cityCode)) {
                airportNameElement.click();
                actions.sendKeys(Keys.ENTER).perform();
                return true;
            }
        }

        // If no match is found
        System.out.println("City code not found.");
        return false;
    }
    
    

    public boolean enterToCity(String cityCode) {
        WebElement toCityInput = driver.findElement(By.xpath(TO_CITY_INPUT));
        Actions actions = new Actions(driver);
        actions.click(toCityInput).perform();
        actions.sendKeys(cityCode).perform();

        // Wait for the suggestions to load using WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(TO_CITY_NAME)));

        // Extract the city codes and names
        List<WebElement> cityElements = driver.findElements(By.xpath(TO_CITY_NAME));
        List<WebElement> nameElements = driver.findElements(By.xpath(TO_CITY_COD));
        List<WebElement> airportNameElements = driver.findElements(By.xpath(TO_CITY_AIRPORT_NAME));

        // Iterate over city names
        for (WebElement cityElement : cityElements) {
            if (cityElement.getText().contains(cityCode)) {
                cityElement.click();
                actions.sendKeys(Keys.ENTER).perform();
                return true;
            }
        }

        // Iterate over city codes
        for (WebElement nameElement : nameElements) {
            if (nameElement.getText().equalsIgnoreCase(cityCode)) {
                nameElement.click();
                actions.sendKeys(Keys.ENTER).perform();
                return true;
            }
        }

        // Iterate over airport names
        for (WebElement airportNameElement : airportNameElements) {
            if (airportNameElement.getText().contains(cityCode)) {
                airportNameElement.click();
                actions.sendKeys(Keys.ENTER).perform();
                return true;
            }
        }

        // If no match is found
        System.out.println("City code not found.");
        return false;
    }

    
    //Selecting Departure date and Return date.
    //dateFromSelect
    
    
    

    
    
   
}