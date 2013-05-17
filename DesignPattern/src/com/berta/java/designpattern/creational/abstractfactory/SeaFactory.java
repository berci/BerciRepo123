package com.berta.java.designpattern.creational.abstractfactory;

public class SeaFactory implements AnimalFactory {

	@Override
	public Animal createAnimal() {
		return new Shark();
	}

}
