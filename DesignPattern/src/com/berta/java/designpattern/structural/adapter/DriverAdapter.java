package com.berta.java.designpattern.structural.adapter;

public class DriverAdapter {

	public static void main(String[] args) {
		int[] numbers = new int[]{23,4,8,5};
		Sorter sorter = new SortListAdapter();
		for(int i : numbers) {
			System.out.println(i);
		}
		System.out.println("-------- Sorted --------");
		int[] sortedNumbers = sorter.sort(numbers);
		for(int i : sortedNumbers) {
			System.out.println(i);
		}
	}

}
