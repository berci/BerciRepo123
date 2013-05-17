package com.camline.solarion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SpaleckS {

	public static void main(String[] args) {
		System.out.println("Event***********************************************************");
		String sourceFile = "Spaleck_Stevens_Event.txt";
		File inFile = new File("./sim/" + sourceFile);
		FileReader fr;
		try {
			fr = new FileReader(inFile);
			BufferedReader br = new BufferedReader(fr);
			do{
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String array[] = line.split("	");
				//System.out.println("[" + array[0] + "][" + array[1] + "]");
				//event in xml setup
				//System.out.println("<Event Name=\"" + array[1] + "\"><VariableName></VariableName><Consumers/></Event>");
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
////////////////////////////////////////////////////////////
		System.out.println("VARIABLE***********************************************************");
		String sourceFile2 = "Spaleck_Stevens_Variable.txt";
		File inFile2 = new File("./sim/" + sourceFile2);
		FileReader fr2;
		try {
			fr2 = new FileReader(inFile2);
			BufferedReader br = new BufferedReader(fr2);
			do{
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String array[] = line.split(" ");
				//System.out.println("[" + array[0] + "][" + array[1] + "] " + "S" + " " + array[2]);
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
////////////////////////////////////////////////////////////
		System.out.println("ALARM***********************************************************");
		String sourceFile3 = "Spaleck_Stevens_Alarm.xls";
		FileInputStream fis2;
		try {
			fis2 = new FileInputStream("./sim/" + sourceFile3);
			POIFSFileSystem fs2 = new POIFSFileSystem(fis2);
			HSSFWorkbook workbook = new HSSFWorkbook(fs2);
			HSSFSheet sheet = workbook.getSheetAt(0);
			String name = "";
			int id = 0;
			for (Row row : sheet) {
				for (Cell cell : row) {
					if(cell.getColumnIndex() == 0)
						id = (int) cell.getNumericCellValue();
					else if(cell.getColumnIndex() == 1)
						name = cell.getStringCellValue();
				}
				//System.out.println(id + " [" + name + "] 1");
				//xml setup
				//System.out.println("<Alarm ID=\"" + id + "\" description=\"" + name +"\" alarmClass=\"" + 1 + "\"><Consumers/></Alarm>");
			}
				
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String sourceFile4 = "Spaleck_Stevens_Alarm.txt";
		File inFile4 = new File("./sim/" + sourceFile4);
		FileReader fr4;
		try {
			fr4 = new FileReader(inFile4);
			BufferedReader br = new BufferedReader(fr4);
			do{
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String array[] = line.split(";");
				System.out.println(array[0] + " [" + array[1] + "] "+ array[2]);
				//System.out.println("<Alarm ID=\"" + array[0] + "\" description=\"" + array[1] +"\" alarmClass=\"" + array[2] + "\"><Consumers/></Alarm>");
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
