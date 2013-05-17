package com.berta.java.designpattern.behavioral.observer3;

import java.util.Observable;
import java.util.Observer;

public class Screen implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
		//act on the update
		System.out.println("changed: " + ((DataStore) arg0).getData());
	}

}
