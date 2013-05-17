package com.berta.test.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import com.berta.test.dao.GenericDAO;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	private Class<T> persistentClass;
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	EntityManager getEntityManager() {
		if (entityManager == null)
			throw new IllegalStateException(
					"EntityManager has not been set on DAO before usage");
		return entityManager;
	}

	protected Session getSession() {
		return (Session) getEntityManager().getDelegate();
	}
	
	private Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	@Override
	public T findById(ID id, boolean lock) {
		if (!lock) {
			return getEntityManager().find(getPersistentClass(), id);
		}
		return getEntityManager().find(
				getPersistentClass(),
				id,
				LockModeType.PESSIMISTIC_READ);
	}

	@Override
	public List<T> findAll(String orderByProperty, boolean ascending) {
		if (orderByProperty == null) {
			return findByCriteria(null);
		}
		return findByCriteria(
				ascending ? Order.asc(orderByProperty) : Order.desc(orderByProperty));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	@Override
	public T makePersistent(T entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public void makeTransient(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

	@Override
	public void clear() {
		getEntityManager().clear();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 * @param criterion criterions
	 * @return result list 
	 */
	@SuppressWarnings("unchecked")
	private List<T> findByCriteria(Order order, Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		if (order != null) {
			crit.addOrder(order);
		}
		return crit.list();
	}

	@Override
	public int deleteAll() {
        String hqlDelete = "delete " + persistentClass.getName();
        Query deleteQuery = getEntityManager().createQuery(hqlDelete);
		return deleteQuery.executeUpdate();
	}
	
	public final Query createNamedQuery(String id) {
		return getEntityManager().createNamedQuery(id);	
	}
	
	public static final Object getSingleResultOrNull(Query query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
