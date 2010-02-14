/**
 * 
 */
package com.googlecode.menugen.domain.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.googlecode.menugen.domain.Ingredient;
import com.googlecode.menugen.service.IngredientService;
import com.googlecode.menugen.test.MenuGenTestCase;

/**
 * Load {@link Ingredient} data
 */
@TransactionConfiguration(defaultRollback = false)
public class IngredientTest extends MenuGenTestCase {

	@Autowired
	private IngredientService ingredientService;

	/**
	 * Create the default units
	 */
	@Test
	public void createData() {
		addIngredient("hamburger");
		addIngredient("garlic");
		addIngredient("barbeque sauce");
		addIngredient("cheese");
	}

	/**
	 * Add a new ingredient with the given name.
	 * 
	 * @param name
	 *            String name
	 */
	private void addIngredient(String name) {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(null);
		ingredient.setName(name);
		ingredientService.create(ingredient);
	}
}
