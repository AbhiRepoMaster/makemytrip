package makemytrip.DataFetch;

public interface Booking_details {
	
		String FROM_CITY_INPUT = "//input[@id='fromCity']//parent::label";
		String FROM_CITY_NAME = "//div[contains(@class, 'font14 lightGreyText latoBold')]";
	    String FROM_CITY_COD = "//p[contains(@class, 'font14 appendBottom5 blackText')]";
	    String FROM_CITY_AIRPORT_NAME = "//p[contains(@class, 'font12 greyText appendBottom3 lineHeight14')]";
	    
	    String TO_CITY_INPUT = "//label[@for='toCity']";
	    String TO_CITY_NAME = "//div[contains(@class, 'font14 lightGreyText latoBold')]";
	    String TO_CITY_COD = "//p[contains(@class, 'font14 appendBottom5 blackText')]";
	    String TO_CITY_AIRPORT_NAME = "//p[contains(@class, 'font12 greyText appendBottom3 lineHeight14')]";
	
}


