/**
 * 
 */
package com.googlecode.menugen.ui.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;

import com.googlecode.menugen.domain.Recipe;
import com.googlecode.menugen.service.RecipeService;
import com.googlecode.menugen.utility.SpringContextUtility;

/**
 * Handle the click event on the Search button.
 */
public class SearchButtonActionListener implements ActionListener {

	private RecipeService recipeService = SpringContextUtility
			.getBean(RecipeService.class);

	private JTextField searchCriteria;
	private JTable searchResults;

	/**
	 * Set the JTextField containing the search criteria and the JTable containing the search results.
	 * 
	 * @param searchCriteria JTextField search criteria
	 * @param searchResults JTable search results
	 */
	public SearchButtonActionListener(JTextField searchCriteria,
			JTable searchResults) {
		this.searchCriteria = searchCriteria;
		this.searchResults = searchResults;
	}

	/**
	 * Search for the recipes matching the given search criteria.
	 * 
	 * @param event
	 *            {@link ActionEvent}
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		List<Recipe> recipes = recipeService.search(searchCriteria.getText());
		searchResults.setModel(new SearchResultsTableModel(recipes));
	}
}