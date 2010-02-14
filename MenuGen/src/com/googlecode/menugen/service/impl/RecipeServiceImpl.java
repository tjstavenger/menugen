/**
 * 
 */
package com.googlecode.menugen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.menugen.dao.RecipeDao;
import com.googlecode.menugen.domain.Recipe;
import com.googlecode.menugen.service.RecipeService;

/**
 * Perform CRUD services on {@link Recipe}
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDao recipeDao;

	/**
	 * Create a new {@link Recipe}
	 * 
	 * @param recipe
	 *            {@link Recipe} to create
	 * @return new {@link Recipe}
	 * 
	 * @see com.googlecode.menugen.service.RecipeService#create(com.googlecode.menugen
	 *      .domain.Recipe)
	 */
	@Override
	@Transactional
	public Recipe create(Recipe recipe) {
		return recipeDao.saveOrUpdate(recipe);
	}

	/**
	 * Delete an existing {@link Recipe}
	 * 
	 * @param recipe
	 *            existing {@link Recipe} to delete
	 * 
	 * @see com.googlecode.menugen.service.RecipeService#delete(com.googlecode.menugen
	 *      .domain.Recipe)
	 */
	@Override
	@Transactional
	public void delete(Recipe recipe) {
		recipeDao.delete(recipe);
	}

	/**
	 * Retrieve a Recipe with the given ID
	 * 
	 * @param id
	 *            Long {@link Recipe} ID
	 * @return {@link Recipe} with the given ID
	 * 
	 * @see com.googlecode.menugen.service.RecipeService#retrieve(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Recipe retrieve(Long id) {
		return recipeDao.findById(id);
	}

	/**
	 * Search for recipes matching the given criteria.
	 * 
	 * @param criteria
	 *            String search criteria
	 * @return List of {@link Recipe} matching the given criteria
	 * 
	 * @see com.googlecode.menugen.service.RecipeService#search(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Recipe> search(String criteria) {
		return recipeDao.findAll();
	}

	/**
	 * Update an existing {@link Recipe}
	 * 
	 * @param recipe
	 *            existing {@link Recipe} to update
	 * @return updated {@link Recipe}
	 * 
	 * @see com.googlecode.menugen.service.RecipeService#update(com.googlecode.menugen
	 *      .domain.Recipe)
	 */
	@Override
	@Transactional
	public Recipe update(Recipe recipe) {
		return recipeDao.saveOrUpdate(recipe);
	}
}