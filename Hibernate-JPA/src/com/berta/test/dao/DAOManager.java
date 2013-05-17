package com.berta.test.dao;

import com.berta.test.dao.impl.DAOManagerImpl;
import com.berta.test.entity.dao.EventDAO;

public abstract class DAOManager {
	
	private static DAOManager daoManager = new DAOManagerImpl();
	
	public static DAOManager getDAOManager() {
		return daoManager;
	}
	
	public abstract Object startTransaction();
	public abstract void flush(Object daoContext);
	public abstract void endTransaction(Object daoContext, boolean commitOrRollback);
	
	public abstract EventDAO createEventDAO(Object daoContext);

}
