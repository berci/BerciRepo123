package com.camline;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<Double> dataList;
	
	public Data(){
		dataList = new ArrayList<Double>();
	}
	
	public List<Double> getDataList() {
		return dataList;
	}

	public void addData(double d){
		dataList.add(d);
	}
}
