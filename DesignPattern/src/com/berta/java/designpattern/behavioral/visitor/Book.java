package com.berta.java.designpattern.behavioral.visitor;

public class Book extends Element implements Visitable {

	public Book(double price) {
		super(price);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
