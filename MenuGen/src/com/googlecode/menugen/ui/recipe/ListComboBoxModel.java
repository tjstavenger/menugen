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
public class ListComboBoxModel<T> extends AbstractListModel implements
		ComboBoxModel {

	private List<T> items;
	private T selectedItem;

	public ListComboBoxModel(List<T> items) {
		this.items = items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	@Override
	public T getSelectedItem() {
		return selectedItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	@Override
	public void setSelectedItem(Object anItem) {
		this.selectedItem = (T) anItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public T getElementAt(int index) {
		return items.get(index);
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
}