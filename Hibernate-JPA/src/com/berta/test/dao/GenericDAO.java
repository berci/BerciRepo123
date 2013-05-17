package com.berta.test.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
	
	T findById(ID id, boolean lock);

    List<T> findAll(String orderByProperty, boolean ascending);

    List<T> findByExample(T exampleInstance, String[] excludeProperty);

    T makePersistent(T entity);

    void makeTransient(T entity);
    
    int deleteAll();
    
    void flush();

    void clear();
}
