package com.camline.solarion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FHR {

	public static void main(String[] args) {
		System.out.println("VARIABLE***********************************************************");
		String sourceFile = "FHR_Variable.txt";
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
				//System.out.println("[" + array[2] + "][" + array[1] + "] " + array[0] + " " + array[3]);
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("EVENT***********************************************************");
		String eventFile = "FHR_Event.txt";
		File inEFile = new File("./sim/" + eventFile);
		FileReader fr1;
		try {
			fr1 = new FileReader(inEFile);
			BufferedReader br = new BufferedReader(fr1);
			do{
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String array[] = line.split(" ");
				//System.out.println("[" + array[1] + "][" + array[0] + "]");
				//event in xml setup
				//System.out.println("<Event Name=\"" + array[0] + "\"><VariableName>Clock</VariableName><Consumers/></Event>");
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("ALARM***********************************************************");
		String alarmFile = "FHR_Alarm.txt";
		File inAFile = new File("./sim/" + alarmFile);
		FileReader fr2;
		try {
			fr2 = new FileReader(inAFile);
			BufferedReader br = new BufferedReader(fr2);
			do{
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String array[] = line.split(";");
				String desc = line.substring(31);
				//System.out.println(array[1] + " [" + array[5] + "] 0");
				//alarm event
				int setId = Integer.valueOf(array[2]);
				int clearId = Integer.valueOf(array[3]);
				System.out.println("[" + (setId) + "][al" + array[1] + "Set]");
				System.out.println("[" + (clearId) + "][al" + array[1] + "Clear]");
				//alarm, event in xml setup
				//System.out.println("<Alarm ID=\"" + array[1] + "\" description=\"" + desc +"\" alarmClass=\"" + 1 + "\"><Consumers/></Alarm>");
				//System.out.println("<Event Name=\"al" + array[1] + "Set\"><Consumers/></Event>");
				//System.out.println("<Event Name=\"al" + array[1] + "Clear\"><Consumers/></Event>");
				
			}while(true);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
