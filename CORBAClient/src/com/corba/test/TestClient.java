package com.corba.test;

import com.corba.test.client.Client;
import com.corba.test.server.functions;

public class TestClient {
	public static void main(String[] args) {
		Client client = new Client();
		functions func;
			func = client.getServer();
			func.sql("111111111111");
			
			func.add((short) 102);
	}
}
