package com.berta.java.designpattern.creational.factory;

public class Driver {

	public static void main(String[] args) {
		Dog dog = DogFactory.getDog("small");
		dog.speak();
		
		dog = DogFactory.getDog("big");
		dog.speak();
		
		dog = DogFactory.getDog("working");
		dog.speak();
	}

}
