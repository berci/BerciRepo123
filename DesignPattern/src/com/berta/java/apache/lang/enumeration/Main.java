package com.berta.java.apache.lang.enumeration;

import java.io.ObjectInputStream.GetField;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.enums.EnumUtils;

public class Main {

	public static void main(String[] args) {
		//ValuedEnum enum1 = EnumUtils.getEnum(TestEnum.T1.getClass(), 0);
		Map enumMap = EnumUtils.getEnumMap(TestEnum.class);
		Map enumMap2 = TestEnum.getEnumMap();
		Set keySet = enumMap2.entrySet();
		Map unmodifiableMap = Collections.unmodifiableMap(enumMap2);
		
		TestEnum enum2 = (TestEnum) EnumUtils.getEnum(TestEnum.class, "222");

		System.out.println();
	}

}
