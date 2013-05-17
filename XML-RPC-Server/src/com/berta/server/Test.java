package com.berta.server;

import java.io.Serializable;

public class Test implements Serializable {
	String name;
	public Test(String name) {
		this.name = name;
	}
	public String getName() {
		return "Hello " + name + "!";
	}
}
