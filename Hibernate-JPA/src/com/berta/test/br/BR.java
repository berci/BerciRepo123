package com.berta.test.br;

import org.apache.log4j.Logger;

import com.berta.test.dao.DAOManager;
import com.berta.test.entity.dao.EventDAO;

public final class BR<R> implements IBR {
	
	private String brName;
	private Logger log;
	
	private long started;
	private boolean succeeded;
	
	private Object daoContext;
	
	
	/**
	 * @param brName
	 * @param log
	 * @param applicationData
	 */
	public BR(String brName, Logger log) {
		this.brName = brName;
		this.log = log;
	}
	
	/**
	 * @see com.camline.jumo.server.br.IBR#getName()
	 */
	@Override
	public String getName() {
		return brName;
	}
	
	/**
	 * Enters the business rule. Logs a line and remembers start time.
	 */
	private void begin() {
		log.info("Entering business rule " + brName); //$NON-NLS-1$
		started = System.currentTimeMillis();
	}
	
	private void init() {
		daoContext = DAOManager.getDAOManager().startTransaction();
	}
	
	private void succeeded() {
		DAOManager.getDAOManager().flush(daoContext);
		succeeded = true;
	}
	
	private void cleanup() {
		try {
			DAOManager.getDAOManager().endTransaction(daoContext, succeeded);
		} catch (RuntimeException e){
			if (succeeded) {
				throw e;
			}
			log.warn("endTransaction failed but not raised", e); //$NON-NLS-1$
		}
	}
	
	/**
	 * Leaves business rule. Logs a line with duration info.
	 */
	private void end() {
		long ended = System.currentTimeMillis();
		long duration = ended - started;
		log.info("Leaving business rule " + brName + ". Duration: " + duration + " ms"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	public R execute(BRCallable<R> callable) throws Exception {
		begin();
		
		try {
			// Initialize sessions, transactions, ...
			init();
			
			// Call the real business logic
			R result = callable.call(this);
			
			// Mark the business rule as successful, changes can be committed
			succeeded();
			
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			// Cleanup transactions, sessions, etc.
			cleanup();
			end();
		}
	}
	
	public void execute(BRVoidCallable callable) throws Exception {
		begin();
		
		try {
			// Initialize sessions, transactions, ...
			init();
			
			// Call the real business logic
			callable.call(this);
			
			// Mark the business rule as successful, changes can be committed
			succeeded();
		} catch (Exception e) {
			throw e;
		} finally {
			// Cleanup transactions, sessions, etc.
			cleanup();
			end();
		}
	}
	
	@Override
	public EventDAO createEventDAO() {
		return DAOManager.getDAOManager().createEventDAO(daoContext);
	}
	

}
