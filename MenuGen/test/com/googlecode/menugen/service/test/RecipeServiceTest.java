/**
 * 
 */
package com.googlecode.menugen.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
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
	
	@Test
	public void testCreateMenu() {
		List<Integer> servings = new ArrayList<Integer>(7);
		servings.add(2);
		servings.add(2);
		servings.add(2);
		servings.add(2);
		servings.add(2);
		servings.add(2);
		servings.add(2);
		
		List<Recipe> menu = recipeService.createMenu(servings);
		
		System.out.println(menu);
		
		assertEquals("Should have 7 meals", 7, menu.size());
	}
}
