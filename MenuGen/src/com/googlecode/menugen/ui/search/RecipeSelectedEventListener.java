/**
 * 
 */
package com.googlecode.menugen.ui.search;

import java.util.EventListener;

import com.googlecode.menugen.domain.Recipe;

/**
 * Event triggered when a user selects a {@link Recipe} from the search results.
 */
public interface RecipeSelectedEventListener extends EventListener {

	/**
	 * Triggered when a user selects a {@link Recipe} from the search results.
	 * 
	 * @param event
	 *            {@link RecipeSelectedEvent} containing the selected recipe as
	 *            the source
	 */
	void selectRecipe(RecipeSelectedEvent event);
}