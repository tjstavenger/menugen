/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.menugen.dao.DataAccessObject;
import com.googlecode.menugen.domain.DomainObject;
import com.googlecode.menugen.utility.ClassUtility;

/**
 * DataAccessObject superclass
 * 
 * @see https://www.hibernate.org/328.html
 */
public abstract class DataAccessObjectImpl<T extends DomainObject, ID extends Serializable>
		implements DataAccessObject<T, ID> {
	private static final String UNCHECKED = "unchecked";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Create a {@link Query} with the given HQL.
	 * 
	 * @param hql
	 *            String
	 * @return {@link Query}
	 */
	protected final Query createQuery(String hql) {
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);

		return query;
	}

	/**
	 * Create a {@link Query} with the given HQL and return the List result.
	 * 
	 * @param hql
	 *            String HQL
	 * @return List of results
	 * 
	 * @see #createQuery(String)
	 * @see Query#list()
	 */
	@SuppressWarnings(UNCHECKED)
	protected final List<T> listQuery(String hql) {
		Query query = createQuery(hql);
		List<T> results = query.list();

		return results;
	}

	/**
	 * Create a {@link Query} with the given HQL and return the unique result.
	 * 
	 * @param hql
	 *            String HQL
	 * @return unique result
	 */
	@SuppressWarnings(UNCHECKED)
	protected final T uniqueResultQuery(String hql) {
		Query query = createQuery(hql);
		T result = (T) query.uniqueResult();

		return result;
	}

	/**
	 * Get the current Hibernate session.
	 * 
	 * @return {@link Session}
	 * 
	 * @see SessionFactory#getCurrentSession()
	 */
	protected final Session getCurrentSession() {
		Session session = sessionFactory.getCurrentSession();

		return session;
	}

	/**
	 * Get a new {@link Criteria} for the given persistent class.
	 * 
	 * @param persistentClass
	 *            {@link Class} persistent class
	 * @return {@link Criteria}
	 */
	@SuppressWarnings(UNCHECKED)
	protected final Criteria createCriteria(Class persistentClass) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(persistentClass);

		return criteria;
	}

	/**
	 * Get a new {@link Criteria} for the persistent class specified by the
	 * generic type of the sub class.
	 * 
	 * @return {@link Criteria}
	 */
	@SuppressWarnings(UNCHECKED)
	protected final Criteria createCriteria() {
		Class persistentClass = getPersistentClass();
		Criteria criteria = createCriteria(persistentClass);

		return criteria;
	}

	/**
	 * The persistent class type is declared as the first generic type in
	 * subclasses.
	 * 
	 * @return persistent Class
	 * 
	 * @see ClassUtility#getGenericType(Class, int)
	 */
	@SuppressWarnings(UNCHECKED)
	protected final Class getPersistentClass() {
		return ClassUtility.getGenericType(getClass(), 0);
	}

	/**
	 * The ID class type is declared as the second generic type in subclasses.
	 * 
	 * @return ID Class
	 * 
	 * @see ClassUtility#getGenericType(Class, int)
	 */
	@SuppressWarnings(UNCHECKED)
	protected final Class getIdClass() {
		return ClassUtility.getGenericType(getClass(), 1);
	}

	/**
	 * Find by the given criteria on the subclass' persistent class generic
	 * type.
	 * 
	 * @param criterion
	 *            varargs list of Criterion to find
	 * @return List of persistent classes
	 */
	@SuppressWarnings(UNCHECKED)
	protected List<T> findByCriteria(Criterion... criterion) {
		return findByCriteria(getPersistentClass(), criterion);
	}

	/**
	 * Find by the given criteria on the given persistent class.
	 * 
	 * @param persistentClass
	 *            persistent class for which to create a {@link Criteria}
	 * @param criterion
	 *            varargs list of Criterion to find
	 * @return List of persistent classes
	 */
	@SuppressWarnings(UNCHECKED)
	protected List findByCriteria(Class persistentClass, Criterion... criterion) {
		Criteria criteria = getCurrentSession().createCriteria(
				getPersistentClass());

		for (Criterion c : criterion) {
			criteria.add(c);
		}

		return criteria.list();
	}

	/**
	 * Find a unique instance by the given criteria on the subclass' persistent
	 * class generic type.
	 * 
	 * @param criterion
	 *            varargs list of Criterion to find
	 * @return unique persistent instance or null if the query returns no
	 *         results
	 */
	@SuppressWarnings(UNCHECKED)
	protected T findUniqueByCriteria(Criterion... criterion) {
		return (T) findUniqueByCriteria(getPersistentClass(), criterion);
	}

	/**
	 * Find a unique instance by the given criteria on the given persistent
	 * class.
	 * 
	 * @param persistentClass
	 *            persistent class for which to create a {@link Criteria}
	 * @param criterion
	 *            varargs list of Criterion to find
	 * @return unique persistent instance or null if the query returns no
	 *         results
	 */
	@SuppressWarnings(UNCHECKED)
	protected Object findUniqueByCriteria(Class persistentClass,
			Criterion... criterion) {
		Criteria criteria = getCurrentSession().createCriteria(
				getPersistentClass());

		for (Criterion c : criterion) {
			criteria.add(c);
		}

		return criteria.uniqueResult();
	}

	/**
	 * Load the persistent class by ID with the {@link LockMode#UPGRADE} if lock
	 * is true.
	 * 
	 * @param id
	 *            ID
	 * @param lock
	 *            boolean true if {@link LockMode#UPGRADE}
	 * @return persistent class
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public T findById(ID id, boolean lock) {
		T entity;

		if (lock) {
			entity = (T) getCurrentSession().load(getPersistentClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getCurrentSession().load(getPersistentClass(), id);
		}

		return entity;
	}

	/**
	 * Load the persistent class by ID without locking.
	 * 
	 * @param id
	 *            ID
	 * @return persistent class
	 */
	@Override
	public T findById(ID id) {
		return findById(id, false);
	}

	/**
	 * Get all instances of the persistent class.
	 * 
	 * @return List of persistent class
	 */
	@Override
	public List<T> findAll() {
		return findByCriteria();
	}

	/**
	 * Save or update the given persistent class.
	 * 
	 * @param entity
	 *            persistent class
	 * @return persistent class
	 * 
	 * @see Session#saveOrUpdate(Object)
	 */
	@Override
	public T saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);

		return entity;
	}

	/**
	 * Save or update the given persistent classes.
	 * 
	 * @param entities
	 *            Collection of persist classes
	 * @return persistent classes
	 * 
	 * @see #saveOrUpdate(Object)
	 */
	@Override
	public Collection<T> saveOrUpdate(Collection<T> entities) {
		Collection<T> updates = new ArrayList<T>(entities.size());

		for (T entity : entities) {
			T updated = saveOrUpdate(entity);
			updates.add(updated);
		}

		return updates;
	}

	public void evict(T persistentClass) {
		getCurrentSession().evict(persistentClass);
	}

	/**
	 * Delete the given persistent class.
	 * 
	 * @param entity
	 *            persistent class
	 * 
	 * @see Session#delete(Object)
	 */
	@Override
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	/**
	 * Merge the given persistent class.
	 * 
	 * @param entity
	 *            persistent class
	 * 
	 * @see Session#delete(Object)
	 */
	@Override
	public void merge(T entity) {
		getCurrentSession().merge(entity);
	}

	/**
	 * Check if an entity with the given ID exists.
	 * 
	 * @param id
	 *            ID
	 * @return boolean true if an entity exists with the given ID
	 */
	@Override
	public boolean exists(ID id) {
		Object object = getCurrentSession().get(getPersistentClass(), id);

		return object != null;
	}

	/**
	 * Flush the current session.
	 * 
	 * @see Session#flush()
	 */
	public void flush() {
		getCurrentSession().flush();
	}

	/**
	 * clear the current session.
	 * 
	 * @see Session#clear()
	 */
	protected final void clear() {
		getCurrentSession().clear();
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}