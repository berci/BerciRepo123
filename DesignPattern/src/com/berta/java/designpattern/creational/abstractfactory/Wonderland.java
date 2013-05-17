package com.berta.java.designpattern.creational.abstractfactory;

public class Wonderland {
	public Wonderland(AnimalFactory factory) {
		Animal animal = factory.createAnimal();
		animal.breathe();
	}
}
