package com.berta.java.designpattern.creational.prototype;

public class Person implements Prototype {
	
	private String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	@Override
	public Prototype doClone() {
		return new Person(name);
	}

	public String toString() {
		return "This person is named " + name;
	}
}
