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

public class Xenon {

	public static void main(String[] args) {
		String fileName = "Xenon_Event.xls";
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
		String sourceFile = "Xenon_Variable.txt";
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
				String array[] = line.split(" ");
				//System.out.println("[" + array[2] + "][" + array[0] + "] " + array[1].substring(0,1) + " " + array[3]);
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
////////////////////////////////////////////////////////////
		System.out.println("ALARM***********************************************************");
		String alarmFile = "Xenon_Alarm.txt";
		File inAFile = new File("./sim/" + alarmFile);
		FileReader fr1;
		try {
			fr1 = new FileReader(inAFile);
			BufferedReader br = new BufferedReader(fr1);
			do{
				String line = br.readLine();
				if (line == null) {
					break;
				}
				if(line.length() > 2){
					String array[] = line.split("	");
					System.out.println(array[0] + " [" + array[1] + "] 1");
					//xml setup
					//System.out.println("<Alarm ID=\"" + array[0] + "\" description=\"" + array[1] +"\" alarmClass=\"" + 1 + "\"><Consumers/></Alarm>");
				}	
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
