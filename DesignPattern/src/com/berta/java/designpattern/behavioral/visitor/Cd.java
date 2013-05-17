package com.berta.java.designpattern.behavioral.visitor;

public class Cd extends Element implements Visitable {

	public Cd(double price) {
		super(price);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
