package com.berta.java.designpattern.behavioral.observer3;

public class DriverObserver {

	public static void main(String[] args) {
		Screen screen = new Screen();
		DataStore dataStore = new DataStore();
		
		dataStore.addObserver(screen);
		dataStore.notifyObservers();
		dataStore.setData("test");
		dataStore.notifyObservers();

	}

}
