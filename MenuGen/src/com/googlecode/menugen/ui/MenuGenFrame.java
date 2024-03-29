/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuGenFrame.java
 *
 * Created on Feb 26, 2010, 8:04:53 AM
 */

package com.googlecode.menugen.ui;

import javax.swing.UIManager;

import com.googlecode.menugen.domain.Recipe;
import com.googlecode.menugen.ui.menu.MenuPanel;
import com.googlecode.menugen.ui.recipe.RecipePanel;
import com.googlecode.menugen.ui.search.RecipeSelectedEvent;
import com.googlecode.menugen.ui.search.RecipeSelectedEventListener;
import com.googlecode.menugen.ui.search.SearchPanel;
import com.googlecode.menugen.utility.SpringContextUtility;

/**
 * Main application
 */
public class MenuGenFrame extends javax.swing.JFrame implements
		RecipeSelectedEventListener {

	private static final long serialVersionUID = 1L;

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Could not set look and feel.");
		}
	}

	/** Creates new form MenuGenFrame */
	public MenuGenFrame() {
		SpringContextUtility.init();

		initComponents();

		this.searchPanel = new SearchPanel();
		searchPanel.addRecipeSelectedEventListener(this);

		switchToMenu();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        createMenuButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        newRecipeButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        searchButton = new javax.swing.JButton();
        contentsScrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Generator");

        toolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new java.awt.Dimension(200, 23));

        createMenuButton.setText("Create Menu");
        createMenuButton.setFocusable(false);
        createMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createMenuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createMenuButtonActionPerformed(evt);
            }
        });
        toolBar.add(createMenuButton);
        toolBar.add(jSeparator2);

        newRecipeButton.setText("New Recipe");
        newRecipeButton.setFocusable(false);
        newRecipeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newRecipeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newRecipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRecipeButtonActionPerformed(evt);
            }
        });
        toolBar.add(newRecipeButton);
        toolBar.add(jSeparator1);

        searchButton.setText("Search");
        searchButton.setFocusable(false);
        searchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchButton);

        contentsScrollPane.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
            .addComponent(contentsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void createMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createMenuButtonActionPerformed
		switchToMenu();
	}// GEN-LAST:event_createMenuButtonActionPerformed

	private void newRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newRecipeButtonActionPerformed
		switchToRecipe(null);
	}// GEN-LAST:event_newRecipeButtonActionPerformed

	private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchButtonActionPerformed
		switchToSearch();
	}// GEN-LAST:event_searchButtonActionPerformed

	private void switchToSearch() {
		contentsScrollPane.getViewport().removeAll();
		contentsScrollPane.getViewport().add(searchPanel);
	}

	private void switchToRecipe(Recipe recipe) {
		contentsScrollPane.getViewport().removeAll();
		contentsScrollPane.getViewport().add(new RecipePanel(recipe));
	}

	private void switchToMenu() {
		contentsScrollPane.getViewport().removeAll();
		contentsScrollPane.getViewport().add(new MenuPanel());
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MenuGenFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane contentsScrollPane;
    private javax.swing.JButton createMenuButton;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JButton newRecipeButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

	private SearchPanel searchPanel;

	@Override
	public void selectRecipe(RecipeSelectedEvent event) {
		switchToRecipe(event.getSource());
	}
}
