/**
 * 
 */
package com.googlecode.menugen.service;

import java.util.List;

import com.googlecode.menugen.domain.Ingredient;

/**
 * Perform CRUD services on {@link Ingredient}
 */
public interface IngredientService {

	/**
	 * Create a new {@link Ingredient}
	 * 
	 * @param ingredient
	 *            {@link Ingredient} to create
	 * @return new {@link Ingredient}
	 */
	Ingredient create(Ingredient ingredient);

	/**
	 * Delete an existing {@link Ingredient}
	 * 
	 * @param ingredient
	 *            existing {@link Ingredient} to delete
	 */
	void delete(Ingredient ingredient);

	/**
	 * Retrieve all {@link Ingredient} in alphabetical order.
	 * 
	 * @return List of {@link Ingredient}
	 */
	List<Ingredient> retrieve();

	/**
	 * Update an existing {@link Ingredient}
	 * 
	 * @param ingredient
	 *            existing {@link Ingredient} to update
	 * @return updated {@link Ingredient}
	 */
	Ingredient update(Ingredient ingredient);
}
