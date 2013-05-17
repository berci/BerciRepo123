package com.berci.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

class ParserUtils {
	
	private static final String separator = "\\|";
	private String[] values = new String[]{};
	
	public ParserUtils(String path) throws ParseException {
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				if(setArray(line)) {
					break;
				}
			}
		} catch (IOException ioex) {
			throw ParseException.createParseException(ioex, path);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ioex) {
				throw ParseException.createParseException(ioex, path);
			}
		}
	}
	
	private boolean setArray(String line) {
		if(StringUtils.isBlank(line)) {
			return false;
		}
		values = line.trim().split(separator);
		for(int i = 0; i < values.length ; i++) {
			values[i] = values[i].trim();
		}
		return true;
	}

	private String[] getArray() {
		return values;
	}
	
	public String getLineWithoutWhitespaces() {
		String l = "";
		for(String s : values) {
			l += s + '|';
		}
		return l;
	}
	
	enum ParserEnum {
		LOT(3, "Lot"),
		PRIORITY(32, "Priority"),
		
		EMPTY(-1, "Empty");
		
		int pos;
		String name;
		
		private ParserEnum(int pos, String name) {
			this.pos = pos;
			this.name = name;
		}
		
		public String getStringValue(ParserUtils parser) throws ParseException {
			try {
				return parser.getArray()[pos-1];
			} catch (Exception e) {
				throw ParseException.createParseException(e, this, parser.getLineWithoutWhitespaces());
			}
		}
		
		public Double getDoubleValue(ParserUtils parser) throws ParseException {
			try {
				return Double.valueOf(getStringValue(parser).replace(',', '.'));
			} catch (ParseException e) {
				throw e;
			} catch (Exception e) {
				throw ParseException.createParseException(e, this, parser.getLineWithoutWhitespaces());
			}
		}
		
		@Override
		public String toString() {
			return "Name: " + name + "; Pos: " + pos;
		}
	}
}
	

