package com.berta.java.designpattern.creational.factory2;

/**
 * @author marton
 * 	Concrete implementation of the Logger
 */
public class XMLLogger implements Logger {

	@Override
	public void log(String message) {
		// Log to xml
		System.err.println(message);
	}

}
