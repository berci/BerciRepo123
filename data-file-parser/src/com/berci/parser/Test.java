package com.berci.parser;

import com.berci.parser.ParserUtils.ParserEnum;

public class Test {
	
	public static void main(String[] args) {
		String file = "input\\input.txt";
		try {
			ParserUtils parser = new ParserUtils(file);
			
			String lot = ParserEnum.LOT.getStringValue(parser);
			Double priority = ParserEnum.PRIORITY.getDoubleValue(parser);
			
			System.out.println(ParserEnum.LOT.toString() + "; Value: " + lot);
			System.out.println(ParserEnum.PRIORITY.toString() + "; Value: " + priority);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
