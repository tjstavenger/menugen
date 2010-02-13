/**
 * 
 */
package com.googlecode.menugen.domain;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Entity;

/**
 * A single measured ingredient within a recipe.
 */
@Entity
public class MeasuredIngredient extends DomainObject {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private double amount;
	@ManyToOne
	private Unit unit;
	@ManyToOne
	private Ingredient ingredient;

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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * @return the ingredient
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}

	/**
	 * @param ingredient
	 *            the ingredient to set
	 */
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

}
