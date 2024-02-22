package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadValue {

	static String filePath = System.getProperty("user.dir")+"\\config.properties";
	static String excelFilePath = System.getProperty("user.dir")+"\\test.xlsx";
	static int col_Idx = -1;
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		System.out.println("The file path is "+filePath);
		String username = readExcelFile("username",6);
		String password = readExcelFile("password",6);
		System.out.println(username+" "+password);
	}
	
	public static String readConfigFile(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
	
	public static String readExcelFile(String colName, int row_idx) throws EncryptedDocumentException, IOException {		
		FileInputStream fis = new FileInputStream(excelFilePath);		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheetAt(0);
		Row row = sh.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++) {
			Cell cell = row.getCell(i);
			if(cell!=null && colName.equalsIgnoreCase(cell.toString())) {
				col_Idx = i;
				break;
			}
		}
		
		String value = sh.getRow(row_idx).getCell(col_Idx).toString();
		return value;
		
	}
}
