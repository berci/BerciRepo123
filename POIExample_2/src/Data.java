import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;


public class Data {
	
	public void parse(HSSFSheet sheet,int i, int ii, int numRows){
		int k=0;
		int numRows2 = numRows-i-2;
		//System.out.println(numRows);
		while(k < (numRows2)) {
			HSSFRow row = sheet.getRow(ii);ii++;
			if(row == null) continue;
			int numCells = row.getPhysicalNumberOfCells();
			int l = 0, jj=0;
			while(l < numCells) {
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
				
			l++;
			}
		k++;
		}
	}
}
