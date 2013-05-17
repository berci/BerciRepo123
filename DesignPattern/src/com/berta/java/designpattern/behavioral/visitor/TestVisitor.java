package com.berta.java.designpattern.behavioral.visitor;

public class TestVisitor implements Visitor {

	private double totalPrice;
	
	@Override
	public void visit(Element e) {
		this.totalPrice += e.getPrice();
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
}
