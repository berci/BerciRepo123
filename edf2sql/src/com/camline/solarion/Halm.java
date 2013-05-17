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

public class Halm {

	public static void main(String[] args) {
		String fileName = "Halm_Event.xls";
		FileInputStream fis;
		try {
			fis = new FileInputStream("./sim/" + fileName);
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			String name = "";
			int id = 0;
			for (Row row : sheet) {
				for (Cell cell : row) {
					if(cell.getColumnIndex() == 0)
						name = cell.getStringCellValue();
					else
						id = (int) cell.getNumericCellValue();
				}
				//System.out.println("[" + id + "][" + name + "]");
				//xml setup
				//System.out.println("<Event Name=\"" + name + "\"><VariableName></VariableName><Consumers/></Event>");
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
////////////////////////////////////////////////////////////
		System.out.println("VARIABLE***********************************************************");
		String sourceFile = "Halm_Variable_2.xls";
		FileInputStream fis1;
		try {
			fis1 = new FileInputStream("./sim/" + sourceFile);
			POIFSFileSystem fs1 = new POIFSFileSystem(fis1);
			HSSFWorkbook workbook = new HSSFWorkbook(fs1);
			HSSFSheet sheet = workbook.getSheetAt(0);
			String name = "";
			int id = 0;
			String format = "";
			String type = "";
			for (Row row : sheet) {
				for (Cell cell : row) {
					if(cell.getColumnIndex() == 0)
						name = cell.getStringCellValue();
					else if(cell.getColumnIndex() == 1){
						format = cell.getStringCellValue();
					}	
					else if(cell.getColumnIndex() == 2){
						type = cell.getStringCellValue();
						type = type.substring(0,1);
					}
					else if(cell.getColumnIndex() == 3)
						id = (int) cell.getNumericCellValue();
				}
				//System.out.println("[" + id + "][" + name + "] " + type + " " + format);
			}
				
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
////////////////////////////////////////////////////////////
		System.out.println("ALARM***********************************************************");
		String sourceFile2 = "Halm_Alarm.xls";
		FileInputStream fis2;
		try {
			fis2 = new FileInputStream("./sim/" + sourceFile2);
			POIFSFileSystem fs2 = new POIFSFileSystem(fis2);
			HSSFWorkbook workbook = new HSSFWorkbook(fs2);
			HSSFSheet sheet = workbook.getSheetAt(0);
			String name = "";
			int id = 0;
			for (Row row : sheet) {
				for (Cell cell : row) {
					if(cell.getColumnIndex() == 0)
						name = cell.getStringCellValue();
					else if(cell.getColumnIndex() == 1)
						id = (int) cell.getNumericCellValue();
				}
				//System.out.println(id + " [" + name + "] 1");
				//xml setup
				System.out.println("<Alarm ID=\"" + id + "\" description=\"" + name +"\" alarmClass=\"" + 1 + "\"><Consumers/></Alarm>");
			}
				
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
