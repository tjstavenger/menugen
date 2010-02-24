/**
 * 
 */
package com.googlecode.menugen.service;

import java.util.List;

import com.googlecode.menugen.domain.Recipe;

/**
 * Perform CRUD services on {@link Recipe}
 */
public interface RecipeService {

	/**
	 * Create a new {@link Recipe}
	 * 
	 * @param recipe
	 *            {@link Recipe} to create
	 * @return new {@link Recipe}
	 */
	Recipe create(Recipe recipe);

	/**
	 * Create a menu (List) of {@link Recipe} of the same size as the List
	 * provided, with each day able to serve the given number of people.
	 * 
	 * @param servings
	 *            List of serving sizes for each {@link Recipe} in the menu
	 * @return List of {@link Recipe}
	 */
	List<Recipe> createMenu(List<Integer> servings);

	/**
	 * Delete an existing {@link Recipe}
	 * 
	 * @param recipe
	 *            existing {@link Recipe} to delete
	 */
	void delete(Recipe recipe);

	/**
	 * Retrieve a Recipe with the given ID
	 * 
	 * @param id
	 *            Long {@link Recipe} ID
	 * @return {@link Recipe} with the given ID
	 */
	Recipe retrieve(Long id);

	/**
	 * Search for recipes matching the given criteria.
	 * 
	 * @param criteria
	 *            String search criteria
	 * @return List of {@link Recipe} matching the given criteria
	 */
	List<Recipe> search(String criteria);

	/**
	 * Update an existing {@link Recipe}
	 * 
	 * @param recipe
	 *            existing {@link Recipe} to update
	 * @return updated {@link Recipe}
	 */
	Recipe update(Recipe recipe);
}