package com.berta.java.designpattern.behavioral.observer2;

public interface Subject {
	
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
}
