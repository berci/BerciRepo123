package com.berci.enums;

import java.io.PrintStream;

public enum Console {
	INFO("INFO", System.out) {
		@Override
		public void print(String s) {
			ps.print(getFormattedMessage(s));
		}
		@Override
		public void println(String s) {
			ps.println(getFormattedMessage(s));
		}
	},
	ERROR("ERROR", System.err) {
		@Override
		public void print(String s) {
			ps.print(getFormattedMessage(s));
		}
		@Override
		public void println(String s) {
			ps.println(getFormattedMessage(s));
		}
	};
	
	String name;
	PrintStream ps;
	
	private Console(String name, PrintStream ps) {
		this.name = name;
		this.ps = ps;
	}
	
	public String getFormattedMessage(String s) {
		String message = null;
		message = name + " " + s;
		return message;
	}
		
	public void print(String s) {
        throw new AbstractMethodError();
    }
	
	public void println(String s) {
        throw new AbstractMethodError();
    }

}
