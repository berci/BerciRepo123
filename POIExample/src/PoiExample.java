import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class PoiExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("file.xls");
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int numRows = sheet.getPhysicalNumberOfRows();
			int i=0, ii=0;
			while(i < numRows) {
				HSSFRow row = sheet.getRow(ii);ii++;
				if(row == null) continue;
				int numCells = row.getPhysicalNumberOfCells();
				int j = 0, jj=0;
				while(j < numCells) {
					HSSFCell cell = row.getCell((short) jj); jj++;
				if(cell == null) continue; 
					int type = cell.getCellType();
					switch(type) {
					case HSSFCell.CELL_TYPE_STRING:
						String content = cell.getStringCellValue();
						System.out.println("Row: " + (ii) + " Column: " + (jj) + " content: " + content);
						break;
					case  HSSFCell.CELL_TYPE_NUMERIC:
						double value = cell.getNumericCellValue();
						System.out.println("Row: " + (ii) + " Column: " + (jj) + " content: " + value);
						break;
					}
					
				j++;
				}
			i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
