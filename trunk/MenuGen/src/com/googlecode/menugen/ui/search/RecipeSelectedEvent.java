/**
 * 
 */
package com.googlecode.menugen.ui.search;

import java.util.EventObject;

import com.googlecode.menugen.domain.Recipe;

/**
 * Event triggered by a user selecting a {@link Recipe} from the search results.
 */
public class RecipeSelectedEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	/**
	 * Set the source (selected {@link Recipe}).
	 * 
	 * @param recipe
	 *            selected {@link Recipe}
	 */
	public RecipeSelectedEvent(Recipe recipe) {
		super(recipe);
	}

	/**
	 * Get the recipe selection that triggered this event.
	 * 
	 * @return {@link Recipe}
	 */
	@Override
	public Recipe getSource() {
		return (Recipe) source;
	}
}