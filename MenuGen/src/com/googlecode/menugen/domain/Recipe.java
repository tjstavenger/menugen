/**
 * 
 */
package com.googlecode.menugen.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;

/**
 * A list of measured ingredients and instructions to make the recipe.
 */
@Entity
public class Recipe extends DomainObject {
	public static final String NAME = "name";
	public static final String INSTRUCTIONS = "instructions";
	public static final String MEASURED_INGREDIENT_NAME = "ingredients.ingredient.name";
	public static final String NOTES = "notes";
	public static final String SERVES = "serves";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 2000, unique = true)
	private String name;
	private Integer serves;
	private Double prepartionTime;
	private Double cookingTime;
	@OneToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "ingredientsIndex")
	@Cascade(CascadeType.ALL)
	private List<MeasuredIngredient> ingredients = new ArrayList<MeasuredIngredient>(
			0);
	@CollectionOfElements(fetch = FetchType.EAGER)
	@IndexColumn(name = "instructionsIndex")
	@Column(length = 2000)
	@Cascade(CascadeType.ALL)
	private List<String> instructions = new ArrayList<String>(0);
	@Column(length = 2000)
	private String notes;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the serves
	 */
	public Integer getServes() {
		return serves;
	}

	/**
	 * @param serves
	 *            the serves to set
	 */
	public void setServes(Integer serves) {
		this.serves = serves;
	}

	/**
	 * @return the prepartionTime
	 */
	public Double getPrepartionTime() {
		return prepartionTime;
	}

	/**
	 * @param prepartionTime
	 *            the prepartionTime to set
	 */
	public void setPrepartionTime(Double prepartionTime) {
		this.prepartionTime = prepartionTime;
	}

	/**
	 * @return the cookingTime
	 */
	public Double getCookingTime() {
		return cookingTime;
	}

	/**
	 * @param cookingTime
	 *            the cookingTime to set
	 */
	public void setCookingTime(Double cookingTime) {
		this.cookingTime = cookingTime;
	}

	/**
	 * @return the ingredients
	 */
	public List<MeasuredIngredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients(List<MeasuredIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the instructions
	 */
	public List<String> getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}