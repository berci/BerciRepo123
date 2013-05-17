package com.berta.server;

public class XmlRpcHandler {

	public Test sayHello(String name) {
		return new Test(name);
	}
}
