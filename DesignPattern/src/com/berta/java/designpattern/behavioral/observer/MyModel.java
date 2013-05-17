package com.berta.java.designpattern.behavioral.observer;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MyModel {

	private List<Person> persons = new ArrayList<Person>();
	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();
	
	public class Person {
		private String firstName;
		private String lastName;
		
		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
			notifyListeners();
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
			notifyListeners();
		}
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public MyModel() {
		persons.add(new Person("Marton", "Berta"));
		persons.add(new Person("Laszlo", "Toth"));
	}
	
	private void notifyListeners() {
		for(PropertyChangeListener name : listener) {
			name.propertyChange(null);
		}
	}
	
	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}
}
