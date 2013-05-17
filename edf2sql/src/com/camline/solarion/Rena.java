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

public class Rena {

	public static void main(String[] args) {
		System.out.println("VARIABLE***********************************************************");
		String sourceFile = "Rena_Variable2.txt";
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
				String array[] = line.split(";");
				//System.out.println("[" + array[0] + "][" + array[1] + "] " + "S" + " " + array[2]);
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("ALARM***********************************************************");
		String sourceF = "Rena_Alarm2.txt";
		File inF = new File("./sim/" + sourceF);
		FileReader frF;
		try {
			frF = new FileReader(inF);
			BufferedReader br = new BufferedReader(frF);
			do{
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String array[] = line.split(";");
				System.out.println(array[0] + " [" + array[1] + "] " + array[2]);
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		String fileName = "Rena_Event.xls";
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
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
////////////////////////////////////////////////////////////
		System.out.println("VARIABLE***********************************************************");
		String sourceFile3 = "Rena_Variable.xls";
		FileInputStream fis1;
		try {
			fis1 = new FileInputStream("./sim/" + sourceFile3);
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
						id = (int) cell.getNumericCellValue();
					else if(cell.getColumnIndex() == 1)
						name = cell.getStringCellValue();
					else if(cell.getColumnIndex() == 2)
						format = cell.getStringCellValue();
					else if(cell.getColumnIndex() == 3)
						type = cell.getStringCellValue();
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
		String sourceFile2 = "Rena_Alarm.xls";
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
						id = (int) cell.getNumericCellValue();
					else if(cell.getColumnIndex() == 1)
						name = cell.getStringCellValue();
				}
				//System.out.println(id + " [" + name + "] 1");
			}
				
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//xml setup
		//System.out.println("<Alarm ID=\"" + array[0] + "\" description=\"" + array[1] +"\" alarmClass=\"" + 1 + "\"><Consumers/></Alarm>");
	}

}
