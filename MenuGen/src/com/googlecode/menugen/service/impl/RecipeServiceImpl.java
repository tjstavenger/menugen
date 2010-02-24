/**
 * 
 */
package com.googlecode.menugen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
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
	 * Create a menu (List) of {@link Recipe} of the same size as the List
	 * provided, with each day able to serve the given number of people.
	 * 
	 * @param servings
	 *            List of serving sizes for each {@link Recipe} in the menu
	 * @return List of {@link Recipe}
	 * 
	 * @see com.googlecode.menugen.service.RecipeService#createMenu(java.util.List)
	 */
	@Override
	public List<Recipe> createMenu(List<Integer> servings) {
		Map<Integer, List<Recipe>> recipeServes = new HashMap<Integer, List<Recipe>>();

		for (Integer serves : servings) {
			if (!recipeServes.containsKey(serves)) {
				List<Recipe> recipes = recipeDao.findByServes(serves);
				recipeServes.put(serves, recipes);
			}
		}

		List<Recipe> menu = new ArrayList<Recipe>(servings.size());

		for (Integer serves : servings) {
			List<Recipe> recipes = recipeServes.get(serves);

			Recipe recipe;

			do {
				int random = RandomUtils.nextInt(recipes.size());
				recipe = recipes.get(random);
			} while (!menu.contains(recipe) && servings.size() < recipes.size());

			menu.add(recipe);

			// TODO handle leftovers
		}

		return menu;
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
		return recipeDao.search(criteria);
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