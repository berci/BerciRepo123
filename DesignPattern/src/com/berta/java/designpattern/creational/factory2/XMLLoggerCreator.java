package com.berta.java.designpattern.creational.factory2;

public class XMLLoggerCreator extends AbstractLoggerCreator {

	@Override
	public Logger createLogger() {
		XMLLogger logger = new XMLLogger();
		return logger;
	}

}
