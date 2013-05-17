package com.berta.java.designpattern.behavioral.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyObserver implements PropertyChangeListener {

	public MyObserver(MyModel model) {
		model.addChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Things are changing...");
	}
}
