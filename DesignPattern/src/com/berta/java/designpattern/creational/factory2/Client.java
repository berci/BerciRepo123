package com.berta.java.designpattern.creational.factory2;

public class Client {
	
	public void someMethodThatLogs(AbstractLoggerCreator logCreator) {
		Logger logger = logCreator.createLogger();
		logger.log("message22");
	}
	
	public static void main(String[] args) {
		AbstractLoggerCreator logCreator = new XMLLoggerCreator();
		Client client = new Client();
		client.someMethodThatLogs(logCreator);
	}

}
