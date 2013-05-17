package com.berta.test.server;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.berta.test.dao.DAOManager;

public class HelloServer implements Runnable {
	
	private Thread t;
	private Registry registry;
	private Hello myObject;
	private static final int DEFAULT_SERVER_PORT = 1089;
	
	public HelloServer(String name, String[] args) {
		try {
			int port = getPort(args);
			createRegistry(port);
			rebind();
			t = new Thread(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void rebind() throws AccessException, RemoteException {
		myObject = new Hello();
		registry.rebind("myMessage", myObject);
	}

	private int getPort(String[] args) {
		try {
			return Integer.parseInt(args[0]);
		} catch (Exception e) {
			return DEFAULT_SERVER_PORT;
		}
	}
	
	private void createRegistry(int port) throws RemoteException {
		registry = LocateRegistry.createRegistry(port);
	}

	public void start() {
		t.start();
		System.out.println("Server up!");
	}
	
	@Override
	public void run() {
		DAOManager.getDAOManager();
	}
	
	public static void main(String[] args) {
		HelloServer helloServer = new HelloServer("Server", args);
		helloServer.start();
	}
}