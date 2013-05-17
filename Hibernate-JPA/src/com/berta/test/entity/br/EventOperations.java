package com.berta.test.entity.br;

import java.util.List;

import org.apache.log4j.Logger;

import com.berta.test.br.BR;
import com.berta.test.br.BRCallable;
import com.berta.test.br.IBR;
import com.berta.test.entity.Event;
import com.berta.test.entity.dao.EventDAO;

public class EventOperations {
	
	private static final Logger log = Logger.getLogger(EventOperations.class);
	
	public List<Event> getAll() throws Exception {
		BRCallable<List<Event>> code = new BRCallable<List<Event>>() {

			@Override
			public List<Event> call(IBR br) throws Exception {
				EventDAO dao = br.createEventDAO();
				return dao.findAll(null, false);
			}
		};
		return new BR<List<Event>>("Event.getAll", log).execute(code); //$NON-NLS-1$
	}
	
	public Event getItemByName(final String name) throws Exception {
		BRCallable<Event> code = new BRCallable<Event>() {

			@Override
			public Event call(IBR br) throws Exception {
				EventDAO dao = br.createEventDAO();
				return dao.findByName(name);
			}
		};
		return new BR<Event>("Event.getItemByName", log).execute(code); //$NON-NLS-1$
	}
}
