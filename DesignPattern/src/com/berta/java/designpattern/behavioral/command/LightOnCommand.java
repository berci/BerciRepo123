package com.berta.java.designpattern.behavioral.command;

public class LightOnCommand implements Command {
	Light myLight;
	public LightOnCommand(Light l) {
		myLight = l;
	}
	@Override
	public void execute() {
		myLight.turnOn();
	}
}
