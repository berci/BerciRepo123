package com.berta.test.entity.dao;

import com.berta.test.dao.GenericDAO;
import com.berta.test.entity.Event;

public interface EventDAO extends GenericDAO<Event, Long> {
	
	Event findByName(String name);
}
