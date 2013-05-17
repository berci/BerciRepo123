package rmi.test.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements HelloInterface {

	private static final long serialVersionUID = 1L;

	public HelloImpl() throws RemoteException {
	}

	@Override
	public ByteArrayInputStream xmlCall(ByteArrayOutputStream baos)
			throws RemoteException {
		
		
		return null;
	}

}
