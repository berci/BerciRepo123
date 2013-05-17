package com.berta.java.designpattern.behavioral.command;

public class LightOffCommand implements Command {
	Light myLight;
	public LightOffCommand(Light l) {
		myLight = l;
	}
	@Override
	public void execute() {
		myLight.turnOff();
	}
}
