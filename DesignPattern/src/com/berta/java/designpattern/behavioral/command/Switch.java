package com.berta.java.designpattern.behavioral.command;

public class Switch {
	// Invoker
	private Command upCommand, downCommand;
	
	public Switch(Command up, Command down) {
		upCommand = up;
		downCommand = down;
	}
	void flipUp() {
		upCommand.execute();
	}
	void flipDown() {
		downCommand.execute();
	}
}
