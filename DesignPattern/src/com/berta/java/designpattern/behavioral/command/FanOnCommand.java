package com.berta.java.designpattern.behavioral.command;

public class FanOnCommand implements Command {
	Fan myFan;
	public FanOnCommand(Fan f) {
		myFan = f;
	}
	@Override
	public void execute() {
		myFan.startRotate();
	}
}
