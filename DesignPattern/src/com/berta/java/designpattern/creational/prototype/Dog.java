package com.berta.java.designpattern.creational.prototype;

public class Dog implements Prototype {
	
	private String sound;
	
	public Dog(String sound) {
		this.sound = sound;
	}
	
	@Override
	public Prototype doClone() {
		return new Dog(sound);
	}

	public String toString() {
		return "This dog says "  + sound;
	}
}
