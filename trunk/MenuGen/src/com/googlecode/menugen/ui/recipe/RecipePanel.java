/**
 * 
 */
package com.googlecode.menugen.ui.recipe;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * @author tstavenger
 *
 */
public class RecipePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public RecipePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
	}

}
