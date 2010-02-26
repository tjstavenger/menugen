/**
 * 
 */
package com.googlecode.menugen.ui.recipe;

import java.util.List;

import com.googlecode.menugen.domain.Unit;

/**
 * @author tstavenger
 * 
 */
public class UnitComboBoxModel extends ListComboBoxModel<Unit> {

	public UnitComboBoxModel(List<Unit> items) {
		super(items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public String getElementAt(int index) {
		return getItems().get(index).getAbbreviation();
	}
}
