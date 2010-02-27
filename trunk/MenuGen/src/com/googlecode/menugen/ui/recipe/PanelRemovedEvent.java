/**
 * 
 */
package com.googlecode.menugen.ui.recipe;

import java.util.EventObject;

import javax.swing.JPanel;

/**
 * Event triggered by a user when a panel should be removed from its parent.
 */
public class PanelRemovedEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	/**
	 * Set the source (selected {@link JPanel}).
	 * 
	 * @param jPanel
	 *            selected {@link JPanel}
	 */
	public PanelRemovedEvent(JPanel jPanel) {
		super(jPanel);
	}

	/**
	 * Get the {@link JPanel} that triggered this event.
	 * 
	 * @return {@link JPanel}
	 */
	@Override
	public JPanel getSource() {
		return (JPanel) source;
	}
}