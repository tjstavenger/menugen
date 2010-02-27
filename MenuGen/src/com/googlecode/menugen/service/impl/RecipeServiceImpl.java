/**
 * 
 */
package com.googlecode.menugen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.menugen.dao.RecipeDao;
import com.googlecode.menugen.domain.Ingredient;
import com.googlecode.menugen.domain.MeasuredIngredient;
import com.googlecode.menugen.domain.Recipe;
import com.googlecode.menugen.domain.Unit;
import com.googlecode.menugen.service.RecipeService;

/**
 * Perform CRUD services on {@link Recipe}
 */
@Service
public class RecipeServiceImpl implements RecipeService {
	private static final Logger LOG = Logger.getLogger(RecipeServiceImpl.class);

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
		LOG.debug("Creating Recipe: " + recipe);
		
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
	@Transactional(readOnly = true)
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
			} while (menu.contains(recipe) && servings.size() <= recipes.size());

			menu.add(recipe);

			// TODO handle leftovers
		}
		
		return menu;
	}

	/**
	 * Create a shopping list for the given menu.
	 * 
	 * @param menu
	 *            List of {@link Recipe}
	 * @return shopping list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<String> createShoppingList(List<Recipe> menu) {

		// TODO add conversions and return List of MeasuredIngredient, order the
		// list

		Map<Ingredient, List<MeasuredIngredient>> ingredients = new HashMap<Ingredient, List<MeasuredIngredient>>();

		for (Recipe recipe : menu) {
			for (MeasuredIngredient measuredIngredient : recipe
					.getIngredients()) {

				if (!ingredients
						.containsKey(measuredIngredient.getIngredient())) {
					ingredients.put(measuredIngredient.getIngredient(),
							new ArrayList<MeasuredIngredient>());
				}

				ingredients.get(measuredIngredient.getIngredient()).add(
						measuredIngredient);
			}
		}

		List<String> shoppingList = new ArrayList<String>(ingredients.size());

		for (Entry<Ingredient, List<MeasuredIngredient>> entry : ingredients
				.entrySet()) {

			Map<Unit, Double> units = new HashMap<Unit, Double>();

			for (MeasuredIngredient measuredIngredient : entry.getValue()) {
				if (!units.containsKey(measuredIngredient.getUnit())) {
					units.put(measuredIngredient.getUnit(), Double.valueOf(0));
				}

				Double value = units.get(measuredIngredient.getUnit())
						+ measuredIngredient.getAmount();
				units.put(measuredIngredient.getUnit(), value);
			}

			StringBuilder stringBuilder = new StringBuilder();

			for (Entry<Unit, Double> amountEntry : units.entrySet()) {
				if (stringBuilder.length() > 0) {
					stringBuilder.append(" + ");
				}

				stringBuilder.append(amountEntry.getValue());
				stringBuilder.append(" ");
				stringBuilder.append(amountEntry.getKey().getAbbreviation());
			}

			stringBuilder.append(" ");
			stringBuilder.append(entry.getKey().getName());

			shoppingList.add(stringBuilder.toString());
		}

		return shoppingList;
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
	 * {@link #create(Recipe)} if the ID is null, otherwise
	 * {@link #update(Recipe)}.
	 * 
	 * @param recipe
	 *            {@link Recipe} to create or update
	 * @return {@link Recipe} persisted to database
	 */
	@Override
	@Transactional
	public Recipe save(Recipe recipe) {
		Recipe persisted;

		if (recipe.getId() == null) {
			persisted = create(recipe);
		} else {
			persisted = update(recipe);
		}

		return persisted;
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
		LOG.debug("Updating Recipe: " + recipe);
		
		return recipeDao.saveOrUpdate(recipe);
	}
}