package makemytrip.DataFetch;

import java.io.File;

import makemytrip.Commons.ExcelOperations;
import org.testng.annotations.DataProvider; 

public class InputTestData {
 
		@DataProvider(name = "TestData")
		public Object[][] datafetch() {
			ReadPropertyData readproperty = new ReadPropertyData();
			String inputdatapath = readproperty.readPropertyData().getProperty("inputdatapath");
			File f = new File(inputdatapath);
			ExcelOperations eo = new ExcelOperations();
			Object[][] ob = eo.ExcelreadData(f);
			Object[][] filteredData = filterNullRows(ob);
	 
			return filteredData;
	 
		}
	 
		private Object[][] filterNullRows(Object[][] data) {
			int countNonNullRows = 0;
			for (Object[] row : data) {
				boolean hasNonNullValue = false;
				for (Object value : row) {
					if (value != null) {
						hasNonNullValue = true;
						break;
					}
				}
				if (hasNonNullValue) {
					countNonNullRows++;
				}
			}
	 
			Object[][] filteredData = new Object[countNonNullRows][];
			int index = 0;
			for (Object[] row : data) {
				boolean hasNonNullValue = false;
				for (Object value : row) {
					if (value != null) {
						hasNonNullValue = true;
						break;
					}
				}
				if (hasNonNullValue) {
					filteredData[index++] = row;
				}
			}
	 
			return filteredData;
	 
		}
}
