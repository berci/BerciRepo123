package com.camline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
	public static void main(String[] args) throws ParseException {
		Data d = new Data();
		
		List<Double> l1 = new ArrayList<Double>();
		l1.add(10.4);
		System.out.println(l1.get(0));
		
		l1 = null;
		
		l1 = new ArrayList<Double>();
		l1.add(45.5);
		System.out.println(l1.get(0));
		
		Test_2 t2 = new Test_2();
		double data1 = 10.24;
		
		d.addData(data1);
		t2.addData(d);
		
		System.out.println(d.getDataList().get(0));
		System.out.println(d.getDataList().get(1));
		
		//DateParse
		String str_date="1/29/2011 10:20:23 AM";  
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
        Date date = (Date)formatter.parse(str_date);
         
        DateFormat testFormat = new SimpleDateFormat("yyyy.MMM.dd hh:mm:ss aaa");
        System.out.println("Test date is:  " + testFormat.format(date));
        
        String test1 = "test";
        String test2 = "test2";
        System.out.println(test1+" "+test2);
        
        String s ="-1.2";
        double doub = Double.parseDouble(s);
        System.out.println(doub);
        
        //String upperCase
        String a = "AMerika";
        
        String b = a.toLowerCase();
        System.out.println(b);
	}
}
