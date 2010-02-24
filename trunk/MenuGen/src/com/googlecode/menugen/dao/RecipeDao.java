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
	 * Find all recipes that serve at least the given number.
	 * 
	 * @param serves
	 *            number of servings
	 * @return List of {@link Recipe} that can serve the given number
	 */
	List<Recipe> findByServes(Integer serves);

	/**
	 * Search for recipes matching the given criteria.
	 * 
	 * @param criteria
	 *            String search criteria
	 * @return List of {@link Recipe} matching the given criteria
	 */
	List<Recipe> search(String criteria);
}
