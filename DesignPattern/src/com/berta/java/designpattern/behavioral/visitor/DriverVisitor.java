package com.berta.java.designpattern.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class DriverVisitor {
	
	public static void main(String[] args) {
		List<Visitable> items = new ArrayList<Visitable>();
		Book b1 = new Book(10.34);
		Cd c1 = new Cd(12.34);
		items.add(b1);
		items.add(c1);
		
		TestVisitor tv = new TestVisitor();
		for(Visitable item : items) {
			item.accept(tv);
		}
		System.out.println(tv.getTotalPrice());
	}
}
