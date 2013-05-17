package com.berta.java.designpattern.behavioral.command;

public class FanOffCommand implements Command {
	Fan myFan;
	public FanOffCommand(Fan f) {
		myFan = f;
	}
	@Override
	public void execute() {
		myFan.stopRotate();
	}
}
