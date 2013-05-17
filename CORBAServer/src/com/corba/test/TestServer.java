package com.corba.test;

import com.corba.test.server.functionsPOA;

public class TestServer extends functionsPOA { 
	
	@Override
	public void sql(String sql) {
		System.out.println(sql);

	}

	@Override
	public void add(short data) {
		System.out.println("add: " + data);
	}

}
