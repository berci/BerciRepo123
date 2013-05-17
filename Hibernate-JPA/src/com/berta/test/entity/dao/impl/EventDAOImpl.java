package com.berta.test.entity.dao.impl;

import javax.persistence.Query;

import com.berta.test.dao.impl.GenericDAOImpl;
import com.berta.test.entity.Event;
import com.berta.test.entity.dao.EventDAO;

public class EventDAOImpl extends GenericDAOImpl<Event, Long> implements EventDAO {

	private String EVENT_FIND_BY_NAME = "event.find.by.name";

	@Override
	public Event findByName(String name) {
		Query query = createNamedQuery(EVENT_FIND_BY_NAME);
		query.setParameter("name", name);
		return (Event) getSingleResultOrNull(query);
	}
	
}
