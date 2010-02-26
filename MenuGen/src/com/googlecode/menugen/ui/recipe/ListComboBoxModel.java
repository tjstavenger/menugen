/**
 * 
 */
package com.googlecode.menugen.ui.recipe;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * @author tstavenger
 * 
 */
public abstract class ListComboBoxModel<T> extends AbstractListModel implements
		ComboBoxModel {

	private List<T> items;
	private String selectedItem;

	public ListComboBoxModel(List<T> items) {
		this.items = items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	@Override
	public String getSelectedItem() {
		return selectedItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	@Override
	public void setSelectedItem(Object anItem) {
		this.selectedItem = String.valueOf(anItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return items.size();
	}

	/**
	 * @return the items
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}
}