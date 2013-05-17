package com.berta.java.designpattern.behavioral.observer;

import com.berta.java.designpattern.behavioral.observer.MyModel.Person;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyModel model = new MyModel();
		new MyObserver(model);
		
		for(Person person : model.getPersons()) {
			person.setLastName(person.getLastName() + "new");
		}
		
		for(Person person : model.getPersons()) {
			person.setFirstName(person.getFirstName() + "new");
		}
	}

}
