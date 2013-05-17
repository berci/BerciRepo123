package com.berta.java.designpattern.behavioral.observer2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntegerDataBag implements Subject {
	
	private List<Integer> list  = new ArrayList<Integer>();
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void add(Integer i) {
		list.add(i);
		notifyObservers();
	}
	
	public Iterator iterator() {
		return list.iterator();
	}
	
	public Integer remove(int index) {
		if(index < list.size()) {
			Integer i = list.remove(index);
			notifyObservers();
			return i;
		}
		return null;
	}
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	private void notifyObservers() {
		// loop trough and notify each observer
		Iterator i = observers.iterator();
		while(i.hasNext()) {
			Observer o = (Observer) i.next();
			o.update(this);
		}
	}

}
