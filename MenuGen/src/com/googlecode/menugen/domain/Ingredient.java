/**
 * 
 */
package com.googlecode.menugen.domain;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Ingredient used within a recipe
 */
@Entity
public class Ingredient extends DomainObject {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String name;

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