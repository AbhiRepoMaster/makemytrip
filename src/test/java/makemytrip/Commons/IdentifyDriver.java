package makemytrip.Commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import makemytrip.DataFetch.ReadPropertyData;




public class IdentifyDriver {
	
	public String findDriver()
	{
		ReadPropertyData readproperty = new ReadPropertyData();
		String browserName = readproperty.readPropertyData().getProperty("browser");
		return browserName;
	}
	
	public String findURL()
	{
		ReadPropertyData readproperty = new ReadPropertyData();
		String URL = readproperty.readPropertyData().getProperty("URL");
		return URL;
	}
	
	
	public WebDriver launchDriver(String browserName)
	{
		WebDriver driver=null;
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			op.addArguments("--incognito");
			op.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});
			op.addArguments("--disable-notifications");	
			
			driver = new ChromeDriver();
		}
		
		else
			driver = new EdgeDriver();
		
		return driver;
	}

}
