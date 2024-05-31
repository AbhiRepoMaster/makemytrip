package makemytrip.Pages;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
    
    public void selectDate(String dateToSelect, String monthElementLocator, String nextMonthButtonLocator) {
        // Extract day, month, and year from the input date
        String[] dateParts = dateToSelect.split(" ");
        int day = Integer.parseInt(dateParts[0]);
        String month = dateParts[1];
        int year = Integer.parseInt(dateParts[2]);
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        LocalDate inputDate = LocalDate.parse(dateToSelect, formatter);
        
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
            List<WebElement> dateElements = driver.findElements(By.xpath(DATE_ELEMENT_XPATH + "//p[text()='" + day + "']"));
            
            // If the desired date is found, click on it and exit the loop
            if (!dateElements.isEmpty()) {
                for (WebElement dateElement : dateElements) {
                    // Check if the date is in the desired month and year
                    WebElement monthElement = dateElement.findElement(By.xpath(monthElementLocator));
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
                    break;
                }
            }
            
            // If the date is not found on the current page, click the next month button
            try {
                WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nextMonthButtonLocator)));
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



    
    
   
}