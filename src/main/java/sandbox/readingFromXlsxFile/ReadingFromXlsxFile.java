package sandbox.readingFromXlsxFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingFromXlsxFile {
	
	public static String desiredData(int desiredRow, int desiredCell, String desiredXlsxFile) {

		XSSFWorkbook wb;
		FileInputStream fis;

		try {
			fis = new FileInputStream(desiredXlsxFile);
			wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(desiredRow);
			Cell cell = row.getCell(desiredCell);
			String data = cell.toString();
			wb.close();
			return data;		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
