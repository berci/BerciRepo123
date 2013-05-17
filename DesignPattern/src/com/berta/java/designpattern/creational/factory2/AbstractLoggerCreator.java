package com.berta.java.designpattern.creational.factory2;

public abstract class AbstractLoggerCreator {
	
	// the factory method
	public abstract Logger createLogger();
	
	public Logger getLogger() {
		Logger logger = createLogger();
		return logger;
	}
	
}