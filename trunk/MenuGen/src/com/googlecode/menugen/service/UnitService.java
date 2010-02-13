/**
 * 
 */
package com.googlecode.menugen.service;

import java.util.List;

import com.googlecode.menugen.domain.Unit;

/**
 * Perform CRUD services on {@link Unit}
 */
public interface UnitService {

	/**
	 * Create a new {@link Unit}
	 * 
	 * @param unit
	 *            {@link Unit} to create
	 * @return new {@link Unit}
	 */
	Unit create(Unit unit);

	/**
	 * Delete an existing {@link Unit}
	 * 
	 * @param unit
	 *            existing {@link Unit} to delete
	 */
	void delete(Unit unit);

	/**
	 * Retrieve all {@link Unit} in alphabetical order.
	 * 
	 * @return List of {@link Unit}
	 */
	List<Unit> retrieve();

	/**
	 * Update an existing {@link Unit}
	 * 
	 * @param unit
	 *            existing {@link Unit} to update
	 * @return updated {@link Unit}
	 */
	Unit update(Unit unit);
}