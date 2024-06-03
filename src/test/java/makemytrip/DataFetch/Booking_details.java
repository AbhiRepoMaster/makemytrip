package makemytrip.DataFetch;

public interface Booking_details {
		String CLOSEPOPUP = "//span[@class='commonModal__close']";
		
		//Input fromCity elements  
		String FROM_CITY_INPUT = "//input[@id='fromCity']//parent::label";
		String FROM_CITY_NAME = "//div[contains(@class, 'font14 lightGreyText latoBold')]";
	    String FROM_CITY_COD = "//p[contains(@class, 'font14 appendBottom5 blackText')]";
	    String FROM_CITY_AIRPORT_NAME = "//p[contains(@class, 'font12 greyText appendBottom3 lineHeight14')]";
		
	    //Input ToCity elements  
	    String TO_CITY_INPUT = "//label[@for='toCity']";
	    String TO_CITY_NAME = "//div[contains(@class, 'font14 lightGreyText latoBold')]";
	    String TO_CITY_COD = "//p[contains(@class, 'font14 appendBottom5 blackText')]";
	    String TO_CITY_AIRPORT_NAME = "//p[contains(@class, 'font12 greyText appendBottom3 lineHeight14')]";
	    
	    //Departure elements xpath
	    
	    String DATE_ELEMENT_XPATH = "//div[@class='DayPicker-Month']//p";
	    String MONTH_ELEMENT_XPATH = "../../../../..//div[@class='DayPicker-Caption']";
	    String NEXT_MONTH_BUTTON = "//span[@aria-label='Next Month']";
	    String DEPARTURE_ELEMENT = "//input[@id='departure-input']";
	    
	    
	    //Travellers elements
	    
	    String TRAVELLERS_DROPDOWN = "//label[contains(@for,'travellers')]";
	    String APPLY_BUTTON = "//button[@data-cy='travellerApplyBtn']";
	    String TOTAL_TRAVELERS = "//span[@class='appendRight10']//span[@class='font30 latoBlack']";
	    
	    //SearchButton elements
	    String SEARCH_BUTTON_XPATH = "//a[contains(@class,'primaryBtn font24 latoBold widgetSearchBtn')]";
	    
	    
	
}


