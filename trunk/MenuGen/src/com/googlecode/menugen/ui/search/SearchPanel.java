/**
 * 
 */
package com.googlecode.menugen.ui.search;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.googlecode.menugen.domain.Recipe;

/**
 * @author tstavenger
 * 
 */
public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField searchCriteria = null;
	private JButton searchButton = null;
	private JScrollPane searchResultsScrollPane = null;
	private JTable searchResultsTable = null;

	/**
	 * This is the default constructor
	 */
	public SearchPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.BOTH;
		gridBagConstraints2.gridy = 1;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.weighty = 1.0;
		gridBagConstraints2.gridwidth = 3;
		gridBagConstraints2.gridx = 0;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 0;
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getSearchCriteria(), gridBagConstraints);
		this.add(getSearchButton(), gridBagConstraints1);
		this.add(getSearchResultsScrollPane(), gridBagConstraints2);
	}

	/**
	 * This method initializes searchCriteria
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getSearchCriteria() {
		if (searchCriteria == null) {
			searchCriteria = new JTextField();
		}
		return searchCriteria;
	}

	/**
	 * This method initializes searchButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setText("Search");
			searchButton.addActionListener(new SearchButtonActionListener(
					getSearchCriteria(), getSearchResultsTable()));
		}
		return searchButton;
	}

	/**
	 * This method initializes searchResultsScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getSearchResultsScrollPane() {
		if (searchResultsScrollPane == null) {
			searchResultsScrollPane = new JScrollPane();
			searchResultsScrollPane.setViewportView(getSearchResultsTable());
		}
		return searchResultsScrollPane;
	}

	/**
	 * This method initializes searchResultsTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getSearchResultsTable() {
		if (searchResultsTable == null) {
			searchResultsTable = new JTable(new SearchResultsTableModel());
			searchResultsTable.setFillsViewportHeight(true);
			searchResultsTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							fireRecipeSelectedEvent((Recipe) e.getSource());
						}
					});

		}
		return searchResultsTable;
	}

	/**
	 * Add a listener for {@link Recipe} selection events.
	 * 
	 * @param listener
	 *            {@link RecipeSelectedEventListener} to add
	 */
	public void addRecipeSelectedEventListener(
			RecipeSelectedEventListener listener) {
		listenerList.add(RecipeSelectedEventListener.class, listener);
	}

	/**
	 * Remove listener for {@link Recipe} selection events.
	 * 
	 * @param listener
	 *            {@link RecipeSelectedEventListener} to remove
	 */
	public void removeRecipeSelectedEventListener(
			RecipeSelectedEventListener listener) {
		listenerList.remove(RecipeSelectedEventListener.class, listener);
	}

	/**
	 * Fire a new {@link RecipeSelectedEvent} for the currently selected
	 * {@link Recipe}.
	 * 
	 * @param recipe
	 *            selected {@link Recipe}
	 */
	private void fireRecipeSelectedEvent(Recipe recipe) {
		RecipeSelectedEvent event = new RecipeSelectedEvent(recipe);
		RecipeSelectedEventListener[] listeners = listenerList
				.getListeners(RecipeSelectedEventListener.class);

		for (RecipeSelectedEventListener recipeSelectedEventListener : listeners) {
			recipeSelectedEventListener.selectRecipe(event);
		}
	}

}
