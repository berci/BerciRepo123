package com.camline.ant.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.tools.ant.*;

/**
 * Create an sql file out of the edf file
 * <p>
 * HOWTO call it in ant file:
 * 
 * <target name="edf2sql" description="Create an sql file out of the edf file">
 *		<taskdef name="edf2sql" classname="com.camline.ant.utilities.Edf2sql">
 *   		<classpath>
 *				<fileset dir="${lib}/build/ant-utilities">
 *					<include name="*.jar"/>
 *				</fileset>
 *			</classpath>
 *		</taskdef>
 *			
 *		<edf2sql sourcefile="${edf}/Test.edf" destfile="${edf}/Test.sql"/>
 *	</target>
 * 
 * @author marton
 */

public class Edf2sql extends Task{
	private String sourceFile;
	private String destFile;
	
	private String treiberId;
	private String treiberName;
	private List<String> variableId = new ArrayList<String>();
	private List<String> variableName = new ArrayList<String>();
	private List<String> eventId = new ArrayList<String>();
	private List<String> eventName = new ArrayList<String>();
	private List<String> alarmId = new ArrayList<String>();
	private List<String> alarmName = new ArrayList<String>();
	
	public Edf2sql() {
	}
	
	private Pattern commentPattern = Pattern.compile("\\s*#.*");
	private Pattern emptyOrWhiteSpacesPattern = Pattern.compile("\\s*");
	
	public void execute() throws BuildException {
		File inFile = new File("../" + sourceFile);
		try {
			parseTreiberId("{TREIBER_ID}", inFile);
			parseTreiberName("{TREIBER_NAME}", inFile);
			parseVariables("{VARIABLES}", inFile);
			parseEvents("{DATACOLLECTIONEVENTS}", inFile);
			parseAlarms("{ALARMS}", inFile);
			
		} catch (FileNotFoundException e) {
			throw new BuildException(inFile.getName() + " not found!");		
		} catch (IOException e) {
			throw new BuildException("Error parsing file " + inFile.getName());
		} 
		
		File outFile = new File("../" + destFile);
		FileWriter fstream = null;
		try{
			StringBuilder sb = createSQL();
			outFile.createNewFile();
			fstream = new FileWriter(outFile);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(sb.toString());
			out.close();
		} catch (IOException e) {
			throw new BuildException(outFile.getName() + " can not created.");
		} 
		System.out.println(outFile.getName() + " has been creadted.");
	}	
		
	public StringBuilder createSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM T_TREIBER WHERE treiber_id = " + treiberId + ";\n" +
					"DELETE FROM T_KONDITION_DEF WHERE treiber_id = " + treiberId + ";\n" +
					"DELETE FROM T_PARA_DEF WHERE treiber_id = " + treiberId + ";\n" +
					"DELETE FROM T_VAR_DEF WHERE treiber_id = " + treiberId + ";\n" +
					"DELETE FROM T_ALARM_DEF WHERE treiber_id = " + treiberId + ";\n" +
					"DELETE FROM T_EVENT_DEF WHERE treiber_id = " + treiberId + ";\n" +
					"INSERT INTO T_TREIBER (treiber_id, treiber_name) VALUES (" + treiberId + ", '" + treiberName + "');\n");
		
		for(int i = 0; i < variableId.size(); i++){
			sb.append("INSERT INTO T_VAR_DEF (var_name, treiber_id, var_typ, seq_nr, vid) " +
					"VALUES ('" + variableName.get(i) + "', " + treiberId + ", 2, " + (i+1) + ", " + variableId.get(i) + ");\n");
		}
		
		for(int i = 0; i < eventId.size(); i++){
			sb.append("INSERT INTO T_EVENT_DEF (event_name, treiber_id, seq_nr, ceid) " +
					"VALUES ('" + eventName.get(i) + "', " + treiberId + ", " + (i+1) + ", " + eventId.get(i) + ");\n");
		}
		
		for(int i = 0; i < alarmId.size(); i++){
			sb.append("INSERT INTO T_ALARM_DEF (alid, treiber_id, alarm_class, alarm_desc, seq_nr) " +
					"VALUES (" + alarmId.get(i) + ", " + treiberId + ", " + 1 + ", '" + alarmName.get(i) + "', " + (i+1) + ");\n");
		}
		return sb;
	}
		

	public void parseTreiberId(String key, File inFile) throws IOException{
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
		do{
			 String lineStr = br.readLine();
			 if (lineStr == null) {
				 break;
			 }
			 if(lineStr.startsWith(key)){
				 while( emptyOrWhiteSpacesPattern.matcher(treiberId = br.readLine()).matches() 
						 || commentPattern.matcher(treiberId).matches() );
				 return;
			 }
		 }while (true);
	}
	
	public void parseTreiberName(String key, File inFile) throws IOException{
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
		do{
			 String lineStr = br.readLine();
			 if (lineStr == null) {
				 break;
			 }
			 if(lineStr.startsWith(key)){
				 while( emptyOrWhiteSpacesPattern.matcher(lineStr = br.readLine()).matches() 
						 || commentPattern.matcher(lineStr).matches() );
				 treiberName = lineStr.substring(1, lineStr.length()-1);
				 return;
			 }
		 }while (true);
	}
	
	public void parseVariables(String key, File inFile) throws IOException{
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
		do{
			 String lineStr = br.readLine();
			 if (lineStr == null) {
				 break;
			 }
			 if(lineStr.startsWith(key)){
				 f:	while( containsKey(lineStr = br.readLine()) ) {
						while(emptyOrWhiteSpacesPattern.matcher(lineStr).matches() 
								|| commentPattern.matcher(lineStr).matches())
							continue f;
						int idxIdStart = lineStr.indexOf("[");
						int idxIdEnd = lineStr.indexOf("]");
						variableId.add(lineStr.substring(idxIdStart+1, idxIdEnd));
						int idxNameStart = lineStr.indexOf("[", 2);
						int idxNameEnd = lineStr.indexOf("]", idxIdEnd+1);
						variableName.add(lineStr.substring(idxNameStart+1 , idxNameEnd));
					}
			 }
		 }while (true);
	}
	
	public void parseEvents(String key, File inFile) throws IOException{
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
		do{
			 String lineStr = br.readLine();
			 if (lineStr == null) {
				 break;
			 }
			 if(lineStr.startsWith(key)){
				 f:	while( containsKey(lineStr = br.readLine()) ) {
						while(emptyOrWhiteSpacesPattern.matcher(lineStr).matches() || commentPattern.matcher(lineStr).matches())
							continue f;
						int idxIdStart = lineStr.indexOf("[");
						int idxIdEnd = lineStr.indexOf("]");
						eventId.add(lineStr.substring(idxIdStart+1, idxIdEnd));
						int idxNameStart = lineStr.indexOf("[", 2);
						int idxNameEnd = lineStr.indexOf("]", idxIdEnd+1);
						eventName.add(lineStr.substring(idxNameStart+1 , idxNameEnd));
					}
			 }
		 }while (true);
	}
	
	public void parseAlarms(String key, File inFile) throws IOException{
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
		do{
			 String lineStr = br.readLine();
			 if (lineStr == null) {
				 break;
			 }
			 if(lineStr.startsWith(key)){
				 f:	while( containsKey(lineStr = br.readLine()) ) {
						while(emptyOrWhiteSpacesPattern.matcher(lineStr).matches() || commentPattern.matcher(lineStr).matches())
							continue f;
						int idxIdEnd = lineStr.indexOf("[");
						alarmId.add(lineStr.substring(0, idxIdEnd-1));
						int idxNameStart = lineStr.indexOf("[", 2);
						int idxNameEnd = lineStr.indexOf("]", idxIdEnd+1);
						alarmName.add(lineStr.substring(idxNameStart+1 , idxNameEnd));
					}
			 }
		 }while (true);
	}
	public boolean containsKey(String line){
		if(line == null) return false;
		if(line.startsWith("{FORMATS}") 
				|| line.startsWith("{DATASETS}") 
				|| line.startsWith("{HOSTCOMMANDS}")
				|| line.startsWith("{COMMANDCODES}")
				|| line.startsWith("{VARIABLES}")
				|| line.startsWith("{DATACOLLECTIONEVENTS}")
				|| line.startsWith("{ALARMS}")){
			return false;
		}
		return true;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	public void setDestFile(String destFile){
		this.destFile = destFile;
	}
}
