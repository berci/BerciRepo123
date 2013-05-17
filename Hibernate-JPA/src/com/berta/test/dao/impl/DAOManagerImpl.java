package com.berta.test.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.berta.test.dao.DAOManager;
import com.berta.test.entity.dao.EventDAO;
import com.berta.test.entity.dao.impl.EventDAOImpl;

public class DAOManagerImpl extends DAOManager {
	
	private static final Logger log = Logger.getLogger(DAOManagerImpl.class);
	
	private EntityManagerFactory entityManagerFactory;
	
	
	public DAOManagerImpl() {
		entityManagerFactory = Persistence.createEntityManagerFactory("events"); //$NON-NLS-1$
		log.info("Created entity manager factory"); //$NON-NLS-1$
	}
	
	@Override
	public Object startTransaction() {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		return em;
	}
	
	@Override
	public void flush(Object daoContext) {
		((EntityManager) daoContext).flush();
	}
	
	@Override
	public void endTransaction(Object daoContext, boolean successful) {
		EntityManager entityManager = (EntityManager) daoContext;
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			if (!transaction.isActive()) {
				return;
			}
			
			if (!successful) {
				transaction.rollback();
				return;
			}
			
			boolean committed = false;
			try {
				transaction.commit();
				committed = true;
			} finally {
				if (!committed) {
					transaction.rollback();
				}
			}
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public EventDAO createEventDAO(Object daoContext) {
		EventDAOImpl dao = new EventDAOImpl();
		dao.setEntityManager((EntityManager) daoContext);
		return dao;
	}
	
}
