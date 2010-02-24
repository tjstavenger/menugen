/**
 * 
 */
package com.googlecode.menugen.service.test;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.menugen.domain.Recipe;
import com.googlecode.menugen.service.RecipeService;
import com.googlecode.menugen.test.MenuGenTestCase;

/**
 * Test methods on the {@link RecipeService}
 */
public class RecipeServiceTest extends MenuGenTestCase {

	@Autowired
	private RecipeService recipeService;

	@Test
	public void testSearch() {
		List<Recipe> recipes = recipeService.search("%");

		System.out.println(recipes);

		assertFalse("Should return results", recipes.isEmpty());
	}
}
