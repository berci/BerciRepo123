package rmi.test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.test.impl.HelloImpl;

public class HelloServer {

	/**
	 * Entry Point to this application
	 */
	public static void main(String[] args) {
		try {
			
			HelloImpl myObject = new HelloImpl();
			
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("myMessage", myObject);
			System.out.println("Server is up and ready...");
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}