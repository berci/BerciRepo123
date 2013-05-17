package com.berta.server.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class HelloImpl extends UnicastRemoteObject implements HelloInterface {

	private static final long serialVersionUID = 1L;

	public HelloImpl() throws RemoteException {
		super();
	}

	@Override
	public abstract Serializable xmlCall(Serializable in) throws RemoteException;
}
