/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RemovablePanel.java
 *
 * Created on Feb 27, 2010, 9:59:19 AM
 */

package com.googlecode.menugen.ui.recipe;

import javax.swing.JPanel;

import com.googlecode.menugen.ui.search.RecipeSelectedEventListener;

/**
 * Handle adding and removing {@link PanelRemovedEventListener} and firing the
 * {@link PanelRemovedEvent}.
 */
public abstract class RemovablePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Add a listener for {@link JPanel} remove events.
	 * 
	 * @param listener
	 *            {@link RecipeSelectedEventListener} to add
	 */
	public void addPanelRemovedEventListener(PanelRemovedEventListener listener) {
		listenerList.add(PanelRemovedEventListener.class, listener);
	}

	/**
	 * Remove listener for {@link JPanel} remove events.
	 * 
	 * @param listener
	 *            {@link RecipeSelectedEventListener} to remove
	 */
	public void removePanelRemovedEventListener(
			PanelRemovedEventListener listener) {
		listenerList.remove(PanelRemovedEventListener.class, listener);
	}

	/**
	 * Fire a new {@link PanelRemovedEvent} for the {@link JPanel} to remove
	 * 
	 * @param jPanel
	 *            {@link JPanel} to remove
	 */
	protected void fireRemovePanelEvent(JPanel jPanel) {
		PanelRemovedEvent event = new PanelRemovedEvent(jPanel);
		PanelRemovedEventListener[] listeners = listenerList
				.getListeners(PanelRemovedEventListener.class);

		for (PanelRemovedEventListener panelRemovedEventListener : listeners) {
			panelRemovedEventListener.removePanel(event);
		}
	}
}