/**
 * 
 */
package com.googlecode.menugen.ui.recipe;

import java.util.List;

import com.googlecode.menugen.domain.Ingredient;

/**
 * @author tstavenger
 * 
 */
public class IngredientComboBoxModel extends ListComboBoxModel<Ingredient> {

	public IngredientComboBoxModel(List<Ingredient> items) {
		super(items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public String getElementAt(int index) {
		return getItems().get(index).getName();
	}
}
