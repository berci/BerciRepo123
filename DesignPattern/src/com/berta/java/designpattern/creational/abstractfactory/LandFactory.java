package com.berta.java.designpattern.creational.abstractfactory;

public class LandFactory implements AnimalFactory {

	@Override
	public Animal createAnimal() {
		return new Elephant();
	}

}
