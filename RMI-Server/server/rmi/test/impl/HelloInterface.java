package rmi.test.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {
	
	public ByteArrayInputStream xmlCall(ByteArrayOutputStream baos) throws RemoteException;
}
