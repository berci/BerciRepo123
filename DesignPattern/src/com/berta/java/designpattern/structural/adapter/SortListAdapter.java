package com.berta.java.designpattern.structural.adapter;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class SortListAdapter implements Sorter {

	@Override
	public int[] sort(int[] numbers) {
		// convert to array to a list
		Integer[] numberArray = ArrayUtils.toObject(numbers);
		List<Integer> numberList = Arrays.asList(numberArray);
		
		// call the adapter
		NumberSorter sorter = new NumberSorter();
		List<Integer> sortedList = sorter.sort(numberList);
		
		//convert the list back to an array and return 
		Integer[] sortedArray = sortedList.toArray(new Integer[sortedList.size()]);
		return ArrayUtils.toPrimitive(sortedArray);
	}

}
