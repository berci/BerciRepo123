package com.berta.server;

public class AdderImpl implements Adder {

	@Override
	public Test get(String name) {
		return new Test(name);
	}

}
