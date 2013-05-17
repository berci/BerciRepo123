package com.berta.java.designpattern.structural.adapter;

import java.util.Collections;
import java.util.List;

public class NumberSorter {
	
	public List<Integer> sort(List<Integer> numbers) {
		Collections.sort(numbers);
		return numbers;
	}
}
