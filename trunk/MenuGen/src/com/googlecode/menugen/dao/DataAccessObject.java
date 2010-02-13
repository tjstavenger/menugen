/**
 * 
 */
package com.googlecode.menugen.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.googlecode.menugen.domain.DomainObject;

/**
 * Super interface for all DAOs.
 */
public interface DataAccessObject<T extends DomainObject, ID extends Serializable> {

	/**
	 * Get all instances of the persistent class
	 * 
	 * @return List of persistent class
	 */
	List<T> findAll();

	/**
	 * Load the persistent class by ID without locking.
	 * 
	 * @param id
	 *            ID
	 * @return persistent class
	 */
	T findById(ID id);


	/**
	 * Save or update the given persistent class.
	 * 
	 * @param entity
	 *            persistent class
	 * @return persistent class
	 * 
	 * @see Session#saveOrUpdate(Object)
	 */
	T saveOrUpdate(T entity);

	/**
	 * Save or update the given persistent classes.
	 * 
	 * @param entities
	 *            Collection of persist classes
	 * @return persistent classes
	 * 
	 * @see #saveOrUpdate(Object)
	 */
	Collection<T> saveOrUpdate(Collection<T> entities);

	/**
	 * Evitct the given persistant class from the Hibernate session.
	 * 
	 * @param persistentClass instance
	 */
	void evict(T persistentClass);

	/**
	 * Delete the given persistent class.
	 * 
	 * @param entity
	 *            persistent class
	 * 
	 * @see Session#delete(Object)
	 */
	void delete(T entity);

	/**
	 * Merge the given persistent class.
	 * 
	 * @param entity
	 *            persistent class
	 * 
	 * @see Session#merge(Object)
	 */
	void merge(T entity);

	/**
	 * Check if an entity with the given ID exists.
	 * 
	 * @param id
	 *            ID
	 * @return boolean true if an entity exists with the given ID
	 */
	boolean exists(ID id);

	/**
	 * Flush the current session.
	 * 
	 * @see Session#flush()
	 */
	void flush();
}