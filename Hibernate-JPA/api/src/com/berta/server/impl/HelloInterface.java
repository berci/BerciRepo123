package com.berta.server.impl;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {
	
	public Serializable xmlCall(Serializable in) throws RemoteException;
}
