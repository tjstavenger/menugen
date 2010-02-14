/**
 * 
 */
package com.googlecode.menugen.domain.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.googlecode.menugen.domain.Recipe;
import com.googlecode.menugen.service.RecipeService;
import com.googlecode.menugen.test.MenuGenTestCase;

/**
 * Load {@link Recipe} data
 */
@TransactionConfiguration(defaultRollback = false)
public class RecipeTest extends MenuGenTestCase {

	@Autowired
	private RecipeService recipeService;

	/**
	 * Create the default recipes
	 */
	@Test
	public void createData() {
		addRecipe("Pizza");
		addRecipe("Steak");
		addRecipe("Hamburger Stroganoff");
		addRecipe("Spaghetti");
	}

	/**
	 * Add a new Recipe with the given name.
	 * 
	 * @param name
	 *            String name
	 */
	private void addRecipe(String name) {
		Recipe recipe = new Recipe();
		recipe.setId(null);
		recipe.setName(name);
		recipeService.create(recipe);
	}
}
