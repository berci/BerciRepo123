package com.berta.java.apache.lang.enumeration;

import java.util.Map;

import org.apache.commons.lang.enums.Enum;

public class TestEnum extends Enum {

	private static final long serialVersionUID = 1L;
	
	public static final TestEnum T1 = new TestEnum(1, "111");
	public static final TestEnum T2 = new TestEnum(2, "222");
	public static final TestEnum T3 = new TestEnum(3, "333");
	
	private int idx;
	private String t;
	
	private TestEnum(int idx, String t) {
		super(t);
		this.idx = idx;
		this.t = t;
	}
	
	public static TestEnum getEnum(String color) {
		return (TestEnum) getEnum(TestEnum.class, color);
	}
	
	public static Map getEnumMap() {
		return getEnumMap(TestEnum.class);
	}

	public int getIdx() {
		return idx;
	}
	
	public String getT() {
		return t;
	}
}
