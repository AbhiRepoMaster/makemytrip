package makemytrip.DataFetch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadPropertyData {
	
	String propertyFilePath = "C:\\Users\\19048\\eclipse-workspace\\makemytrip\\makemytrip\\Resources\\EnvironmentDetails.properties";
	public Properties readPropertyData()
	{
		File propertyFile = new  File(propertyFilePath);
		Properties browserProperty = null;
		
		try {
			FileInputStream propertyInputStream = new FileInputStream(propertyFile);
			browserProperty = new Properties();
			browserProperty.load(propertyInputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return browserProperty;
	}

}
