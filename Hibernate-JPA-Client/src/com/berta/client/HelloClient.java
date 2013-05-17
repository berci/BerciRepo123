package com.berta.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.berta.api.jaxb.GetAllEventRequest;
import com.berta.server.impl.HelloInterface;

public class HelloClient {

	/**
	 * Entry Point to this Application
	 */
	public static void main(String[] args) {

		try {
			Registry myRegistry1 = LocateRegistry.getRegistry();
			Registry myRegistry2 = LocateRegistry.getRegistry(1089);
			Registry myRegistry3 = LocateRegistry.getRegistry("localhost");
			Registry myRegistry = LocateRegistry.getRegistry("localhost", 1089);
			HelloInterface impl = (HelloInterface) myRegistry.lookup("myMessage");
			
			
			Object xmlCall = impl.xmlCall(new GetAllEventRequest());
			
			
			System.out.println(xmlCall);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
