package com.camline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EnumTest {
	
	private enum Line{
		DATETIMEFORMAT(new SimpleDateFormat("yyyy.MMM.dd")),
		SUBSTRATE("asda","dsa");
		
		private String var;
		private String str;
		Line(String var, String str){
			this.var = var;
			this.str = str;
		}
		
		private SimpleDateFormat dateFormat;
		Line(SimpleDateFormat dateFormat){
			this.dateFormat = dateFormat;
		}
		
		public SimpleDateFormat getDateFormat(){
			return dateFormat; 
		}
		
		public String getVariable(){
			return var;
		}
		
		public String getStr(){
			return str;
		}
	}
	
	private Line dateTimeFormat = Line.DATETIMEFORMAT;
	private Line substrate = Line.SUBSTRATE;
	
	public Line getDateTimeFormat(){
		return dateTimeFormat;
	}
	
	public Line getSubstrate(){
		return substrate;
	}
	
	public static void main(String[] args){
		String time = "2010.Aug.10";
		EnumTest test = new EnumTest();
		DateFormat format = test.getDateTimeFormat().getDateFormat();
		try {
			if(format.parse(time) != null) System.out.println("siker");
		} catch (ParseException e) {
			System.out.println("no");
		}
		
		
		System.out.println(test.getSubstrate().getVariable());
	}
}
