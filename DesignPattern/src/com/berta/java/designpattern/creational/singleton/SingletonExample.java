package com.berta.java.designpattern.creational.singleton;

public class SingletonExample {
	
	private static SingletonExample singletonExample = null;
	
	public SingletonExample() {
	}
	
	public static SingletonExample getInstance() {
		if(singletonExample == null) {
			singletonExample = new SingletonExample();
		}
		return singletonExample;
	}
}
