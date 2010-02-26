/**
 * 
 */
package com.googlecode.menugen.ui.search;

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.googlecode.menugen.domain.Recipe;

/**
 * Contain the {@link Recipe} search results
 */
public class SearchResultsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String COLUMN_NAME = "Name";

	private List<Recipe> recipes;

	/**
	 * Set an empty List of {@link Recipe}
	 */
	public SearchResultsTableModel() {
		this.recipes = Collections.emptyList();
	}

	/**
	 * Set the List of {@link Recipe} to use as a {@link TableModel}.
	 * 
	 * @param recipes
	 *            List of {@link Recipe}
	 */
	public SearchResultsTableModel(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	/**
	 * There is only one column, the recipe name
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 1;
	}

	/**
	 * There are the same number of rows as the size of the List.
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return recipes.size();
	}

	/**
	 * Return the {@link Recipe#getName()} for the given row.
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return recipes.get(rowIndex).getName();
	}

	/**
	 * No cells are editable.
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * There is only one column, therefore only one column name,
	 * {@link #COLUMN_NAME}.
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAME;
	}

	/**
	 * @return the recipes
	 */
	public List<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * @param recipes
	 *            the recipes to set
	 */
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
}