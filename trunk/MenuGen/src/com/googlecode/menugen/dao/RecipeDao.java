/**
 * 
 */
package com.googlecode.menugen.dao;

import java.util.List;

import com.googlecode.menugen.domain.Recipe;

/**
 * Perform CRUD operations on {@link Recipe}
 */
public interface RecipeDao extends DataAccessObject<Recipe, Long> {

	/**
	 * Search for recipes matching the given criteria.
	 * 
	 * @param criteria
	 *            String search criteria
	 * @return List of {@link Recipe} matching the given criteria
	 */
	List<Recipe> search(String criteria);
}
