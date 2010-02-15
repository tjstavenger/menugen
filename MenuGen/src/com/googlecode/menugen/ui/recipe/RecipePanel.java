/**
 * 
 */
package com.googlecode.menugen.ui.recipe;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.googlecode.menugen.domain.Ingredient;
import com.googlecode.menugen.domain.Unit;
import com.googlecode.menugen.service.IngredientService;
import com.googlecode.menugen.service.UnitService;
import com.googlecode.menugen.utility.SpringContextUtility;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author tstavenger
 * 
 */
public class RecipePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private IngredientService ingredientService = SpringContextUtility.getBean(IngredientService.class);
	private UnitService unitService = SpringContextUtility.getBean(UnitService.class);

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
		DefaultFormBuilder name = new DefaultFormBuilder(new FormLayout());
		name.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		name.appendColumn("right:pref");
		name.appendColumn("3dlu");
		name.appendColumn("fill:max(pref; 400px)");

		name.append("Name:", new JTextField());
		add(name.getPanel());
		
		DefaultFormBuilder servesTime = new DefaultFormBuilder(new FormLayout());
		servesTime.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		servesTime.appendColumn("right:pref");
		servesTime.appendColumn("3dlu");
		servesTime.appendColumn("fill:max(pref; 75px)");
		servesTime.appendColumn("5dlu");
		servesTime.appendColumn("right:pref");
		servesTime.appendColumn("3dlu");
		servesTime.appendColumn("fill:max(pref; 75px)");
		servesTime.appendColumn("5dlu");
		servesTime.appendColumn("right:pref");
		servesTime.appendColumn("3dlu");
		servesTime.appendColumn("fill:max(pref; 75px)");
		
		servesTime.append("Serves:", new JTextField());
		servesTime.append("Prep Time:", new JTextField());
		servesTime.append("Cook Time:", new JTextField());
		add(servesTime.getPanel());

		List<Ingredient> ingredients = ingredientService.retrieve();
		ListComboBoxModel<Ingredient> ingredientsModel = new ListComboBoxModel<Ingredient>(ingredients);
		
		List<Unit> units = unitService.retrieve();
		ListComboBoxModel<Unit> unitsModel = new ListComboBoxModel<Unit>(units);
		
		
		DefaultFormBuilder ingredientsForm = new DefaultFormBuilder(
				new FormLayout());
		ingredientsForm.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		ingredientsForm.appendColumn("fill:max(pref; 50px)");
		ingredientsForm.appendColumn("5dlu");
		ingredientsForm.appendColumn("fill:max(pref; 100px)");
		ingredientsForm.appendColumn("5dlu");
		ingredientsForm.appendColumn("fill:max(pref; 150px)");

		ingredientsForm.appendSeparator("Ingredients");

		ingredientsForm.append(new JTextField());
		JComboBox unit = new JComboBox();
		unit.setEditable(true);
		unit.setModel(unitsModel);
		ingredientsForm.append(unit);
		JComboBox ingredient = new JComboBox();
		ingredient.setEditable(true);
		ingredient.setModel(ingredientsModel);
		ingredientsForm.append(ingredient);
		ingredientsForm.nextLine();

		add(ingredientsForm.getPanel());

		DefaultFormBuilder notes = new DefaultFormBuilder(new FormLayout());
		notes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		notes.appendColumn("right:pref");
		notes.appendColumn("3dlu");
		notes.appendColumn("fill:max(pref; 400px)");

		notes.appendSeparator("Notes");

		notes.append("-", new JTextPane());
		notes.nextLine();

		add(notes.getPanel());

	}
}
