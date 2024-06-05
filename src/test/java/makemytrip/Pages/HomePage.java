package makemytrip.Pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

	public static boolean selectDate(WebDriver driver, String dateToSelect, int maxAttempts) {
		// Parse the input date with short year format
		LocalDate inputDate;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yy", Locale.ENGLISH);
			inputDate = LocalDate.parse(dateToSelect, formatter);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format: " + e.getMessage());
			return false;
		}

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Check if the input date is in the past
		if (inputDate.isBefore(currentDate)) {
			System.out.println("The selected date is in the past. Cannot book a ticket for a past date.");
			return false;
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		boolean dateSelected = false;

		for (int attempt = 0; attempt < maxAttempts; attempt++) {
			// Check if the current page contains the desired date in any displayed month
			List<WebElement> dateElements = driver.findElements(
					By.xpath("//div[@class='DayPicker-Month']//p[text()='" + inputDate.getDayOfMonth() + "']"));

			// If the desired date is found, click on it and exit the loop
			if (!dateElements.isEmpty()) {
				for (WebElement dateElement : dateElements) {
					// Check if the date is in the desired month and year
					WebElement monthElement = dateElement
							.findElement(By.xpath("../../../../..//div[@class='DayPicker-Caption']"));
					String monthYearText = monthElement.getText();
					String[] monthYearParts = monthYearText.split(" ");
					String displayedMonth = monthYearParts[0].substring(0, 3);
					int displayedYear = Integer.parseInt(monthYearParts[1]);

					System.out.println("Displayed month: " + displayedMonth + ", Displayed year: " + displayedYear);

					if (displayedMonth.equalsIgnoreCase(inputDate.getMonth().name().substring(0, 3))
							&& displayedYear == inputDate.getYear()) {
						// Log found date's month and year
						System.out.println("Found date - Month: " + displayedMonth + ", Year: " + displayedYear);

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
				WebElement nextMonthButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(NEXT_MONTH_BUTTON)));
				nextMonthButton.click();
				System.out.println("Moving to next month...");
			} catch (Exception e) {
				System.out.println("Failed to click the next month button: " + e.getMessage());
				return false;
			}
		}

		return dateSelected;
	}

	// searchButtonFunctionality
	public void searchButtonFunctionality(String searchButtonXpath) {
		WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
		searchButton.click();
	}

	// Travellers count handler

	public void travellersDataSelectTest(WebDriver driver, String fromCity, String toCity, String dateToSelect,
			String adultsStr, String childrenStr, String infantsStr) throws InterruptedException {
		// Convert string counts to integers
		int adults = Integer.parseInt(adultsStr);
		int children = Integer.parseInt(childrenStr);
		int infants = Integer.parseInt(infantsStr);

		// Open the travelers dropdown
		WebElement returnElement = driver.findElement(By.xpath(TRAVELLERS_DROPDOWN));
		Actions actions = new Actions(driver);
		actions.moveToElement(returnElement).click().perform();

		// Select the number of adults, children, and infants
		selectNumberOfTravellers(driver, "adults", adults);
		selectNumberOfTravellers(driver, "children", children);
		selectNumberOfTravellers(driver, "infants", infants);

		// Click on the apply button
		WebElement applyButton = driver.findElement(By.xpath(TRAVELLER_APPLY_BUTTON));
		applyButton.click();

		// Print the selected traveler counts
		System.out.println("Adults: " + adults);
		System.out.println("Children: " + children);
		System.out.println("Infants: " + infants);

		int expectedTotal = adults + children + infants;
		System.out.println("Expected Total Travelers: " + expectedTotal);

		// Verify the total number of travelers displayed
		WebElement totalTravelersDisplay = driver
				.findElement(By.xpath(TOTAL_TRAVELERS_DISPLAY));
		String totalTravelersText = totalTravelersDisplay.getText().trim();
		int actualTotal = Integer.parseInt(totalTravelersText);

		System.out.println("Actual Total Travelers Displayed: " + actualTotal);

		// Assert if the actual total matches the expected total
		Assert.assertEquals(actualTotal, expectedTotal, "Total number of travelers does not match the expected value.");
	}

	public static void selectNumberOfTravellers(WebDriver driver, String type, int count) {
		String dataCy = type + "-" + count;
		WebElement element = driver.findElement(By.xpath("//li[@data-cy='" + dataCy + "']"));
		element.click();
	}

	// Search button handler

	public void searchButtonFunctionality() throws InterruptedException {
		Thread.sleep(500);
		WebElement searchButton = driver
				.findElement(By.xpath(SearchBTN));
		searchButton.click();
	}
}
