/**
 * 
 */
package com.googlecode.menugen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Unit of measure
 */
@Entity
public class Unit extends DomainObject {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String abbreviation;
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
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation
	 *            the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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