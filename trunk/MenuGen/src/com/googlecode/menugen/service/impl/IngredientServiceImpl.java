/**
 * 
 */
package com.googlecode.menugen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.menugen.dao.IngredientDao;
import com.googlecode.menugen.domain.Ingredient;
import com.googlecode.menugen.service.IngredientService;

/**
 * Perform CRUD services on {@link Ingredient}
 */
@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDao ingredientDao;

	/**
	 * Create a new {@link Ingredient}
	 * 
	 * @param ingredient
	 *            {@link Ingredient} to create
	 * @return new {@link Ingredient}
	 * 
	 * @see com.googlecode.menugen.service.IngredientService#create(com.googlecode
	 *      .menugen.domain.Ingredient)
	 */
	@Override
	@Transactional
	public Ingredient create(Ingredient ingredient) {
		return ingredientDao.saveOrUpdate(ingredient);
	}

	/**
	 * Delete an existing {@link Ingredient}
	 * 
	 * @param ingredient
	 *            existing {@link Ingredient} to delete
	 * 
	 * @see com.googlecode.menugen.service.IngredientService#delete(com.googlecode
	 *      .menugen.domain.Ingredient)
	 */
	@Override
	@Transactional
	public void delete(Ingredient ingredient) {
		ingredientDao.delete(ingredient);
	}

	/**
	 * Retrieve all {@link Ingredient} in alphabetical order.
	 * 
	 * @return List of {@link Ingredient}
	 * 
	 * @see com.googlecode.menugen.service.IngredientService#retrieve()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> retrieve() {
		return ingredientDao.findAll();
	}

	/**
	 * Update an existing {@link Ingredient}
	 * 
	 * @param ingredient
	 *            existing {@link Ingredient} to update
	 * @return updated {@link Ingredient}
	 * 
	 * @see com.googlecode.menugen.service.IngredientService#update(com.googlecode
	 *      .menugen.domain.Ingredient)
	 */
	@Override
	@Transactional
	public Ingredient update(Ingredient ingredient) {
		return ingredientDao.saveOrUpdate(ingredient);
	}
}