/**
 * 
 */
package com.googlecode.menugen.ui.recipe;

import java.util.EventListener;

import javax.swing.JPanel;

/**
 * Triggered by a user when a panel should be removed from its parent.
 */
public interface PanelRemovedEventListener extends EventListener {

	/**
	 * Triggered by a user when a panel should be removed from its parent.
	 * 
	 * @param event
	 *            {@link PanelRemovedEvent} containing the {@link JPanel} to
	 *            remove
	 */
	void removePanel(PanelRemovedEvent event);
}
