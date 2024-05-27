package makemytrip.Commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

	public Object[][] ExcelreadData(File f) {
		XSSFWorkbook book;
		FileInputStream fs;
		Object[][] ob = null;
		try {
			fs = new FileInputStream(f);
			book = new XSSFWorkbook(fs);

			XSSFSheet sheet = book.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			int column = sheet.getRow(0).getLastCellNum();
			ob = new Object[rows][column];
			for (int rowcount = 0; rowcount < rows; rowcount++) {
				try {
					Row r = sheet.getRow(rowcount);
					int usedcolumns = r.getLastCellNum();
					for (int columncount = 0; columncount < usedcolumns; columncount++) {
						Cell cellvalue = r.getCell(columncount);
						ob[rowcount][columncount] = readdata(cellvalue);
					}

				} catch (NullPointerException e) {
					continue;
				}
			}
			book.close();
			fs.close();
		}

		catch (Exception e) {

		}

		return ob;

	}

	public Object readdata(Cell cellvalue) {
		Object ob = null;

		if (cellvalue.getCellType().equals(CellType.STRING)) {
			ob = cellvalue.getStringCellValue();
		} else if (cellvalue.getCellType().equals(CellType.NUMERIC)) {

			DataFormatter format = new DataFormatter();
			ob = format.formatCellValue(cellvalue);
		}

		return ob;
	}

	public void excelWriteData(String path,String resultData,int rowno) {

		XSSFWorkbook book;
		FileInputStream fs;
		Object[][] ob = null;
		try {
			fs = new FileInputStream(path);
			book = new XSSFWorkbook(fs);

			XSSFSheet sheet = book.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			int column = sheet.getRow(0).getLastCellNum();
			ob = new Object[rows][column];

				Row r = sheet.getRow(rowno);
				Cell cellvalue = r.createCell(3);
				//System.out.println("Row No:"+rowno+"Value against it: "+resultData);

				cellvalue.setCellValue(resultData);
			
				
			FileOutputStream fos = new FileOutputStream(path);
			book.write(fos);
			book.close();
			fs.close();
			fos.close();
		}

		catch (Exception e) {

		}
	}

}
