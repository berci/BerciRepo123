package com.berta.java.designpattern.creational.builder;

public interface MealBuilder {
	
	public void buildDrink();
	
	public void buildMainCourse();
	
	public void buildSide();
	
	public Meal getMeal();
}
